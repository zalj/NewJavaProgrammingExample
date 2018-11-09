package chapter15;

import chapter14.ClockPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DiplayResizableClock extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ClockPane clock = new ClockPane();
		clock.setPadding(new Insets(10));
		String timeString = clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond();
		Label lblCurrentTime = new Label(timeString);
		
		BorderPane pane = new BorderPane();
		pane.setCenter(clock);
		pane.setBottom(lblCurrentTime);
		BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("DisplayClock");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		pane.widthProperty().addListener(ov -> {
			clock.setW(pane.getWidth());
		});
		
		pane.heightProperty().addListener(ov -> {
			clock.setH(pane.getHeight());
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
