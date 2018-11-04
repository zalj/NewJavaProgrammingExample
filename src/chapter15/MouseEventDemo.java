package chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MouseEventDemo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		Text text = new Text(20, 20, "Programming is fun");
		pane.getChildren().add(text);
		text.setOnMouseDragged(e -> {
			if(e.getButton() == MouseButton.PRIMARY) {
				text.setX(e.getX());
				text.setY(e.getY());
			}
		});
		
		Scene scene = new Scene(pane, 300, 100);
		primaryStage.setTitle("MouseEventDemo");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
