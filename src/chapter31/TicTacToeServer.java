package chapter31;

import java.io.*;
import java.net.*;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TicTacToeServer extends Application implements TicTacToeConstants{
	private int sessionNo = 1;	// Number a session
	
	@Override
	public void start(Stage primaryStage) {
		TextArea taLog = new TextArea();
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(new ScrollPane(taLog), 450, 200);
		primaryStage.setTitle("TicTacToeServer");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread(() -> {
			
			try {
				ServerSocket serverSocket = new ServerSocket(8001);
				while(true) {
					Platform.runLater(() -> taLog.appendText(new Date() + 
							": Wait for players to join session " + sessionNo + '\n'));
					
					// Connect to player 1
					Socket player1 = serverSocket.accept();
					
					Platform.runLater(() -> {
						taLog.appendText(new Date() + ": Player 1 joined session " + sessionNo + '\n');
						taLog.appendText("Player 1's IP address:" + 
								player1.getInetAddress().getHostAddress() + '\n');
					});
					
					// Notify that the player is Player 1
					new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);
					
					// Connect to player 2
					Socket player2 = serverSocket.accept();
					
					Platform.runLater(() -> {
						taLog.appendText(new Date() + 
								": Player 2 joined session " + sessionNo + '\n');
						taLog.appendText("Player 2's IP address:" + 
								player2.getInetAddress().getHostAddress() + '\n');
					});
					
					// Notify that the player 2
					new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);
					
					// Display this session and increment session number
					Platform.runLater(() -> 
						taLog.appendText(new Date() 
							+ ": Start a thread for session " + sessionNo + '\n'));
					
					// Launch a new thread for this session of two players
					new Thread(new HandleASession(player1, player2)).start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	// Define the thread class for handling a new session for two players
	class HandleASession implements Runnable, TicTacToeConstants {
		private Socket player1;
		private Socket player2;
		
		// Create and initialize cells
		private char[][] cells = new char[3][3];
		
		private DataInputStream fromPlayer1;
		private DataOutputStream toPlayer1;
		private DataInputStream fromPlayer2;
		private DataOutputStream toPlayer2;
		
		/** Construct a thread */
		public HandleASession(Socket player1, Socket player2) {
			this.player1 = player1;
			this.player2 = player2;
			
			// Initialize cells
			for(int i = 0; i < 3; i++) 
				for(int j = 0; j < 3; j++)
					cells[i][j] = ' '; 
			sessionNo++;
		}
		
		@Override
		public void run() {
			try {
				// Create data input and output streams
				fromPlayer1 = new DataInputStream(player1.getInputStream());
				toPlayer1 = new DataOutputStream(player1.getOutputStream());
				fromPlayer2 = new DataInputStream(player2.getInputStream());
				toPlayer2 = new DataOutputStream(player2.getOutputStream());
				
				// Write anything to notify player 1 to start
				// This is just to let player 1 know to start
				toPlayer1.writeInt(1);
				
				// Continuously serve the players and determine and report
				// the game status to the player2
				while(true) {
					// Receive a move from player 1
					int row = fromPlayer1.readInt();
					int column = fromPlayer1.readInt();
					cells[row][column] = 'X';
					
					// Check if Player 1 wins
					if(isWon('X')) {
						toPlayer1.writeInt(PLAYER1_WON);
						toPlayer2.writeInt(PLAYER1_WON);
						sendMove(toPlayer2, row, column);
						break;
					} else if (isFull()) { // Check if all cells are filled
						toPlayer1.writeInt(DRAW);
						toPlayer2.writeInt(DRAW);
						sendMove(toPlayer2, row, column);
						break;
					} else {
						// Notify player 2 to take the turn
						toPlayer2.writeInt(CONTINUE);
						
						// Send player 1's selected row and column to player 2
						sendMove(toPlayer2, row, column);
					}
					
					// Receive a move from player 2
					row = fromPlayer2.readInt();
					column = fromPlayer2.readInt();
					cells[row][column] = 'O';
					
					// Check if Player 2 wins
					if(isWon('O')) {
						toPlayer1.writeInt(PLAYER2_WON);
						toPlayer2.writeInt(PLAYER2_WON);
						sendMove(toPlayer1, row, column);
						break;
					} else {
						// Notify player 1 to take the turn
						toPlayer1.writeInt(CONTINUE);
						
						// Send player 2's selected row and column to player 1
						sendMove(toPlayer1, row, column);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/** Send the move to other player */
		private void sendMove(DataOutputStream out, int row, int column) throws IOException {
			out.writeInt(row);
			out.writeInt(column);
		}
		
		/** Determine if the cells are all occupied */
		private boolean isFull() {
			for(int i = 0; i < 3; i++)
				for(int j = 0; j < 3; j++)
					if(cells[i][j] == ' ')
						return false;
			return true;
		}
		
		/** Determine if the player with the specified token wins */
		private boolean isWon(char token) {
			// Check all rows
			for(int i = 0; i < 3; i++)
				if(cells[i][0] == token && cells[i][1] == token && cells[i][2] == token)
					return true;
			
			// Check all columns
			for(int j = 0; j < 3; j++)
				if(cells[0][j] == token && cells[1][j] == token && cells[2][j] == token)
					return true;
			
			// Check major diagonal
			if(cells[0][0] == token && cells[1][1] == token && cells[2][2] == token)
				return true;
			
			// Check sub diagonal
			if(cells[0][2] == token && cells[1][1] == token && cells[2][0] == token)
				return true;
			
			return false;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
