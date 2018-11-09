package chapter15;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public final class PaneMethod {
	
	/**
	 * @author zz562334786
	 * @param pane A BorderPane
	 * @function add a status label at the bottom of the pane
	 * @addTime 11.8.2018 22:46
	 * @modifyTime
	 */
	public static void addStatusLabel(BorderPane pane) {
		Label lblStatus = new Label();
		pane.setBottom(lblStatus);
		
		pane.setOnMouseMoved(e -> lblStatus.setText("X: " + e.getX() + " Y: " + e.getY()));
		
		pane.setOnMouseExited(e -> lblStatus.setText(""));
	}
	
	/**
	 * @author zz562334786
	 * @param pane A BorderPane
	 * @function add a status label at the bottom of the pane
	 * @addTime 11.8.2018 22:46
	 * @modifyTime
	 */
	public static void addSceneInStage(Stage stage, Scene scene, String title) {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
}
