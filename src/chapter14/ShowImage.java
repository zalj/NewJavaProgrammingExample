package chapter14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShowImage extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new HBox();
		pane.setPadding(new Insets(5, 5, 5, 5));
		Image image = new Image("file:D:/Eclipse/workspace/javaProgrammingExample/images/china.jpg");
		ImageView imageView = new ImageView(image);
		pane.getChildren().add(imageView);
		
		ImageView imageView2 = new ImageView(image);
		imageView2.setFitHeight(100);
		imageView2.setFitWidth(100);
		pane.getChildren().add(imageView2);
		
		ImageView imageView3 = new ImageView(image);
		imageView3.setRotate(90);
		pane.getChildren().add(imageView3);
		
		Scene scene = new Scene(pane, 600, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ShowImage");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
