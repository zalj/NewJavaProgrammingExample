package chapter16;

import chapter15.PaneMethod;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LabelWithGraphic extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ImageView cn = new ImageView("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=4258501225,1574042011&fm=58");
		Label lb1 = new Label("CHINA", cn);
		lb1.setStyle("-fx-border-color: green; -fx-border-width: 2");
		lb1.setContentDisplay(ContentDisplay.BOTTOM);
		lb1.setTextFill(Color.RED);
		
		Label lb2 = new Label("Circle", new Circle(50, 50, 25));
		lb2.setContentDisplay(ContentDisplay.TOP);
		lb2.setTextFill(Color.ORANGE);
		
		Label lb3 = new Label("Rectangle", new Rectangle(10, 10, 50, 25));
		lb3.setContentDisplay(ContentDisplay.RIGHT);
		
		Label lb4 = new Label("Ellipse", new Ellipse(50, 50, 50, 25));
		lb4.setContentDisplay(ContentDisplay.LEFT);
		
		Ellipse ellipse = new Ellipse(50, 50, 50, 25);
		ellipse.setStroke(Color.GREEN);
		ellipse.setFill(Color.WHITE);
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(ellipse, new Label("JavaFX"));
		Label lb5 = new Label("A pane inside a label", stackPane);
		lb5.setContentDisplay(ContentDisplay.BOTTOM);
		
		HBox hBox = new HBox(20);
		hBox.getChildren().addAll(lb1, lb2, lb3, lb4, lb5);
		
		BorderPane pane = new BorderPane();
		pane.setTop(hBox);
		
		Scene scene = new Scene(pane, 650, 180);
		PaneMethod.addSceneInStage(primaryStage, scene, "LabelWithGraphic");
		PaneMethod.addStatusLabel(pane);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
