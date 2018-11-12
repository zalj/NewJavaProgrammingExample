package chapter16;

import chapter15.PaneMethod;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SliderDemo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Text text = new Text(20, 20, "JavaFX Programming");
		
		Slider slHorizontal = new Slider();
		slHorizontal.setShowTickLabels(true);
		slHorizontal.setShowTickMarks(true);
		
		Slider slVertical = new Slider();
		slVertical.setOrientation(Orientation.VERTICAL);
		slVertical.setShowTickLabels(true);
		slVertical.setShowTickMarks(true);
		slVertical.setValue(100);
		
		Pane paneForText = new Pane();
		paneForText.getChildren().add(text);
		
		BorderPane pane = new BorderPane();
		pane.setCenter(paneForText);
		pane.setBottom(slHorizontal);
		pane.setRight(slVertical);
		
		slHorizontal.valueProperty().addListener(e -> {
			text.setX(slHorizontal.getValue() * paneForText.getWidth() 
					/ slHorizontal.getMax());
		});
		
		slVertical.valueProperty().addListener(e -> {
			text.setY((slVertical.getMax() - slVertical.getValue()) 
					* paneForText.getHeight() / slVertical.getMax());
		});
		
		Scene scene = new Scene(pane, 450, 170);
		PaneMethod.addSceneInStage(primaryStage, scene, "SliderDemo");
	}

	public static void main(String[] args) {
		launch(args);
	}

}
