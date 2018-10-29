package chapter31;

import java.net.*;

import java.io.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TicTacToeClient extends Application implements TicTacToeConstants{
	// Indicate whether the player has the turn
	private boolean myTurn = false;
	
	// Indicate the token for the player
	private char myToken = ' ';
	
	// Indicate the token for the other player
	private char otherToken = ' ';

	// Create and initialize cells
	private Cell[][] cell = new Cell[3][3];
	
	// Create and initialize a title label
	private Label lblTitle = new Label();
	
	// Create and initialize a status label
	private Label lblStatus = new Label();
	
	// Indicate selected row and column by the current move
	private int rowSelected;
	private int columnSelected;
	
	// Input and output streams from/to server
	private DataInputStream fromServer;
	private DataOutputStream toServer;
	
	// Continue to play?
	private boolean continueToPlay = true;
	
	// Wait for the player to mark a cell
	private boolean waiting = true;
	
	// Host name or IP
	private String host = "localhost";
	
	@Override
	public void start(Stage primaryStage) {
		// Pane to hold cell
		GridPane pane = new GridPane();
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				pane.add(cell[i][j] = new Cell(i, j), j, i);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(lblTitle);
		borderPane.setCenter(pane);
		borderPane.setBottom(lblStatus);
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(borderPane, 320, 350);
		primaryStage.setTitle("TicTacToeClient");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// Connect to the server
		connectToServer();
	}
	
	private void connectToServer() {
		try {
			// Create a socket to connect to the server
			Socket socket = new Socket(host, 8001);
			
			// Create an input stream to receive data from the server
			fromServer = new DataInputStream(socket.getInputStream());
			
			// Create an output stream to send data to the server
			toServer = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Control the game on a separate thread
		new Thread(() -> {
			try {
				// Get notification from the server
				int player = fromServer.readInt();
				
				// Am I player 1 or 2?
				if(player == PLAYER1) {
					myToken = 'X';
					otherToken = 'O';
					Platform.runLater(() -> {
						lblTitle.setText("Player 1 with token 'X'");
						lblStatus.setText("Waiting for player 2 to join");
					});
					
					// Receive startup notification from the server
					fromServer.readInt();	// Whatever read is ignored
					
					// The other player has joined
					Platform.runLater(() -> 
						lblStatus.setText("Player 2 has joined. I start first"));
					
					// It is my turn
					myTurn = true;
				} else if (player == PLAYER2) {
					myToken = 'O';
					otherToken = 'X';
					Platform.runLater(() -> {
						lblTitle.setText("Player 2 with token 'O'");
						lblStatus.setText("Waiting for player 2 to move");
					});
				}
				
				// Continue to play
				if(player == PLAYER1) {
					while(continueToPlay) {
						waitForPlayerAction();
						sendMove();
						receiveInfoFromServer();
					}
				} else if (player == PLAYER2) {
					while(continueToPlay) {
						receiveInfoFromServer();
						waitForPlayerAction();
						sendMove();
					}
				}
				/*while(continueToPlay) {
					if(player == PLAYER1) {
						waitForPlayerAction();
						sendMove();
						receiveInfoFromServer();
					} else if (player == PLAYER2) {
						receiveInfoFromServer();
						waitForPlayerAction();
						sendMove();
					}
				}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	// Wait for the player to mark a cell
	private void waitForPlayerAction() throws InterruptedException{
		while(waiting) {
			Thread.sleep(100);
		}
		waiting = true;
	}
	
	// Send this player's move to the server
	private void sendMove() throws IOException{
		toServer.writeInt(rowSelected);
		toServer.writeInt(columnSelected);
	}
	
	// Receive info from the server
	private void receiveInfoFromServer() throws IOException{
		// Receive game status
		int status = fromServer.readInt();
		if(status == PLAYER1_WON) {
			// Player 1 win, stop playing
			continueToPlay = false;
			if(myToken == 'X')
				Platform.runLater(() -> lblStatus.setText("I won! (X)"));
			else if(myToken == 'O') {
				Platform.runLater(() -> lblStatus.setText("Player 1 (X) has won!"));
				receiveMove();
			}
		} else if(status == PLAYER2_WON) {
			// Player 2 win, stop playing
			continueToPlay = false;
			if(myToken == 'O')
				Platform.runLater(() -> lblStatus.setText("I won! (O)"));
			else if(myToken == 'X') {
				Platform.runLater(() -> lblStatus.setText("Player 2 (O) has won!"));
				receiveMove();
			}
		} else if(status == DRAW) {
			// No winner, game is over
			continueToPlay = false;
			Platform.runLater(() -> lblStatus.setText("Game is over, no winner!"));
			
			if(myToken == 'O')
				receiveMove();
		} else {
			receiveMove();
			Platform.runLater(() -> lblStatus.setText("My turn"));
			myTurn = true;	// It is my turn
		}
	}
	
	private void receiveMove() throws IOException{
		// Get the other player's move
		int row = fromServer.readInt();
		int column = fromServer.readInt();
		
		Platform.runLater(() -> cell[row][column].setToken(otherToken));
	}
	
	// An inner class for a cell
	public class Cell extends Pane {
		// Indicate the row and column of this cell in the board
		private int row;
		private int column;
		
		// Token used for this cell
		private char token = ' ';
		
		public Cell(int row, int column) {
			this.row = row;
			this.column = column;
			this.setPrefSize(2000, 2000);
			setStyle("-fx-border-color: black");
			this.setOnMouseClicked(e -> handleMouseClick());
		}

		public char getToken() {
			return token;
		}

		public void setToken(char token) {
			this.token = token;
			repaint();
		}
		
		protected void repaint() {
			if(token == 'X') {
				Line line1 = new Line(10, 10, 
					this.getWidth() - 10, this.getHeight() - 10);
				line1.endXProperty().bind(this.widthProperty().subtract(10));
				line1.endYProperty().bind(this.heightProperty().subtract(10));
				Line line2 = new Line(10, this.getHeight() - 10,
					this.getWidth(), 10);
				line2.startYProperty().bind(this.heightProperty().subtract(10));
				line2.endXProperty().bind(this.widthProperty().subtract(10));
				
				// Add the lines to the pane
				this.getChildren().addAll(line1, line2);
			}else if(token == 'O') {
				Ellipse ellipse = new Ellipse(this.getWidth() / 2, 
					this.getHeight() / 2, this.getWidth() / 2 - 10, 
					this.getHeight() / 2 - 10);
				ellipse.centerXProperty().bind(
					this.widthProperty().divide(2));
				ellipse.centerYProperty().bind(
					this.heightProperty().divide(2));
				ellipse.radiusXProperty().bind(
					this.widthProperty().divide(2).subtract(10));
				ellipse.radiusYProperty().bind(
					this.heightProperty().divide(2).subtract(10));
				ellipse.setStroke(Color.BLACK);
				ellipse.setFill(Color.WHITE);
				
				getChildren().add(ellipse);
			}
		}
		
		// Handle a mouse click event
		private void handleMouseClick() {
			// If cell is not occupied and the player has the turn
			if(token == ' ' && myTurn) {
				setToken(myToken);
				myTurn = false;
				rowSelected = row;
				columnSelected = column;
				lblStatus.setText("Waiting for the other player to move");
				waiting = false;	// Just completed a successful move
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
