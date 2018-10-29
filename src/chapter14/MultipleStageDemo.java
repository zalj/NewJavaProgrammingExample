package chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MultipleStageDemo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new Button("OK"), 200, 250);
		primaryStage.setScene(scene);
		primaryStage.setTitle("MyJavaFX");
		primaryStage.show();
		
		Stage stage = new Stage();
		stage.setTitle("Second stage");
		
		stage.setScene(new Scene(new Button("New Stage"), 100, 100));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
