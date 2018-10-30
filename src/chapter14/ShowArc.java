package chapter14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowArc extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = new Pane();
		
		Arc arc1 = new Arc(150, 100, 80, 80, 30, 35);
		arc1.setFill(Color.RED);
		arc1.setType(ArcType.ROUND);
		pane.getChildren().add(new Text(210, 40, "arc1: round"));
		pane.getChildren().add(arc1);
		
		Arc arc2 = new Arc(150, 100, 80, 80, 30 + 90, 35);
		arc2.setFill(Color.WHITE);
		arc2.setType(ArcType.OPEN);
		arc2.setStroke(Color.BLACK);
		pane.getChildren().add(new Text(20, 40, "arc2: open"));
		pane.getChildren().add(arc2);
		
		Arc arc3 = new Arc(150, 100, 80, 80, 30 + 180, 35);
		arc3.setFill(Color.WHITE);
		arc3.setType(ArcType.CHORD);
		arc3.setStroke(Color.BLACK);
		pane.getChildren().add(new Text(20, 179, "arc3: chord"));
		pane.getChildren().add(arc3);
		
		Arc arc4 = new Arc(150, 100, 80, 80, 30 + 270, 35);
		arc4.setFill(Color.GREEN);
		arc4.setType(ArcType.CHORD);
		arc4.setStroke(Color.BLACK);
		pane.getChildren().add(new Text(210, 170, "arc4: chord"));
		pane.getChildren().add(arc4);
		
		Scene scene = new Scene(pane, 300, 200);
		primaryStage.setTitle("ShowArc");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
