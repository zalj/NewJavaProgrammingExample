package chapter16;

import chapter15.PaneMethod;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TextAreaDemo extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		DescriptionPane descriptionPane = new DescriptionPane();
		
		descriptionPane.setTitie("Canada");
		String description = "The Canadian national flag ...";
		descriptionPane.setImageView(new ImageView("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=637636387,865039444&fm=200&gp=0.jpg"));
		descriptionPane.setDescription(description);
		
		Scene scene = new Scene(descriptionPane, 750, 400);
		PaneMethod.addSceneInStage(primaryStage, scene, "TextAreaDemo");
	}

	public static void main(String[] args) {
		launch(args);
	}

}
