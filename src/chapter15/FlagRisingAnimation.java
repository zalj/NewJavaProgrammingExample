package chapter15;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlagRisingAnimation extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane pane = new BorderPane();
		
		ImageView imageView = new ImageView("file:C:\\Users\\zz562334786\\Pictures\\Saved Pictures\\cn.jpg");
		pane.setCenter(imageView);
		
		PathTransition pt = new PathTransition(new Duration(10000), 
				new Line(100, 200, 100, 0), imageView);
		pt.setCycleCount(Timeline.INDEFINITE);
		pt.play();
		
		PaneMethod.addStatusLabel(pane);
		
		Scene scene = new Scene(pane, 800, 600);
		PaneMethod.addSceneInStage(primaryStage, scene, this.getClass().toString());
	}

	public static void main(String[] args) {
		launch(args);
	}
	

}
