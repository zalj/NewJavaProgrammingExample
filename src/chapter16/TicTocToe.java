package chapter16;

import chapter15.PaneMethod;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TicTocToe extends Application {
	private char whoseTurn = 'X';
	
	private Cell[][] cell = new Cell[3][3];
	
	private Label lblStatus = new Label("X's turn to play");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane gridPane = new GridPane();
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				gridPane.add(cell[i][j] = new Cell(), j, i);
		
		BorderPane pane = new BorderPane();
		pane.setCenter(gridPane);
		pane.setBottom(lblStatus);
		
		Scene scene = new Scene(pane, 450, 470);
		PaneMethod.addSceneInStage(primaryStage, scene, "TicTacToe");
	}

	public boolean isFull() {
		for(int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) 
				if(cell[i][j].getToken() == ' ')
					return false;
		return true;
	}
	
	public boolean isWon(char c) {
		for (int i = 0; i < 3; i++)
			if (cell[i][0].getToken() == c
			 && cell[i][1].getToken() == c
			 && cell[i][2].getToken() == c) 
				return true;
		
		for (int j = 0; j < 3; j++)
			if (cell[0][j].getToken() == c
			 && cell[1][j].getToken() == c
			 && cell[2][j].getToken() == c) 
				return true;
		
		if (cell[0][0].getToken() == c
		 && cell[1][1].getToken() == c
		 && cell[2][2].getToken() == c)
			return true;
		
		if (cell[0][2].getToken() == c
		 && cell[1][1].getToken() == c
		 && cell[2][0].getToken() == c)
					return true;
		
		return false;
	}
	
	public class Cell extends Pane{
		private char token = ' ';
		
		public Cell() {
			setStyle("-fx-border-color: green");
			setPrefSize(2000, 2000);
			setOnMouseClicked(e -> handleMouseClick());
		}

		public char getToken() {
			return token;
		}
		
		public void setToken(char c) {
			token = c;
			
			if(token == 'X') {
				Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
				line1.endXProperty().bind(this.widthProperty().subtract(10));
				line1.endYProperty().bind(this.heightProperty().subtract(10));
				Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
				line2.startYProperty().bind(this.heightProperty().subtract(10));
				line2.endXProperty().bind(this.widthProperty().subtract(10));
				
				this.getChildren().addAll(line1, line2);
			}
			else if(token == 'O') {
				Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2,
						getWidth() / 2 - 10, this.getHeight() / 2 - 10);
				ellipse.centerXProperty().bind(this.widthProperty().divide(2));
				ellipse.centerYProperty().bind(this.heightProperty().divide(2));
				ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
				ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));
				
				this.getChildren().add(ellipse);
			}
		}
		
		private void handleMouseClick() {
			if(token == ' ' && whoseTurn != ' ') {
				setToken(whoseTurn);
				
				if(isWon(whoseTurn)) {
					lblStatus.setText(whoseTurn + " won! The game is over");
					whoseTurn = ' ';
				}
				else if (isFull()) {
					lblStatus.setText("Draw! The game is over");
					whoseTurn = ' ';
				}
				else {
					whoseTurn = whoseTurn == 'X' ? 'O' : 'X';
					lblStatus.setText(whoseTurn + "'s turn");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
