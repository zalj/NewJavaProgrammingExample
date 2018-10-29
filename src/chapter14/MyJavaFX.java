package chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MyJavaFX extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create a scene and place a button in the scene
		Button btOK = new Button("OK");
		Scene scene = new Scene(btOK, 250, 200);
		primaryStage.setTitle("My JavaFX");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
