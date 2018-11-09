package chapter16;

import chapter15.PaneMethod;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ButtonDemo extends Application {
	protected Text text = new Text(50, 50, "JavaFX Programming");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox paneForButton = new HBox(20);
		Button btLeft = new Button("Left",
				new ImageView("https://www.easyicon.net/api/resizeApi.php?id=35662&size=128"));
		Button btRight = new Button("Right",
				new ImageView("https://www.easyicon.net/api/resizeApi.php?id=25914&size=128"));
		paneForButton.getChildren().addAll(btLeft, btRight);
		paneForButton.setAlignment(Pos.CENTER);
		paneForButton.setStyle("-fx-border-color: green");
		
		BorderPane pane = new BorderPane();
		pane.setBottom(paneForButton);
		
		Pane paneForText = new Pane();
		paneForText.getChildren().add(text);
		pane.setCenter(paneForText);
		
		btLeft.setOnAction(e -> text.setX(text.getX() - 10));
		btRight.setOnAction(e -> text.setX(text.getX() + 10));
		
		Scene scene = new Scene(pane, 450, 200);
		PaneMethod.addSceneInStage(primaryStage, scene, "ButtonDemo");
	}

	public static void main(String[] args) {
		launch(args);
	}

}
