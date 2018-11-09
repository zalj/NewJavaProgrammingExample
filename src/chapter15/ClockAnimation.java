package chapter15;

import chapter14.ClockPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;

public class ClockAnimation extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ClockPane clock = new ClockPane();
		clock.setPadding(new Insets(15));
		
		EventHandler<ActionEvent> eventHandler = e -> {
			clock.setCurrentTime();
		};
		
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		
		Scene scene = new Scene(clock);
		PaneMethod.addSceneInStage(primaryStage, scene, getClass().toString());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
