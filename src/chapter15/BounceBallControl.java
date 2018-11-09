package chapter15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class BounceBallControl extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BallPane pane = new BallPane();
		
		pane.setOnMousePressed(e -> pane.pause());
		pane.setOnMouseReleased(e -> pane.play());
		
		pane.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.UP) {
				pane.increaseSpeed();
			}else if(e.getCode() == KeyCode.DOWN) {
				pane.decreaseSpeed();
			}
		});
		
		Scene scene = new Scene(pane, 250, 150);
		PaneMethod.addSceneInStage(primaryStage, scene, this.getClass().toGenericString());
		pane.requestFocus();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
