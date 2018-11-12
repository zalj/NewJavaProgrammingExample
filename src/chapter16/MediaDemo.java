package chapter16;

import java.awt.Toolkit;

import chapter15.PaneMethod;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MediaDemo extends Application {
	private static final String MEDIA_URL = "http://upos-hz-mirrorks3u.acgvideo.com/upgcxcode/26/28/2482826/2482826-1-16.mp4?e=ig8euxZM2rNcNbRghwuVhoMg7wRMhwdEto8g5X10ugNcXBB_&deadline=1542052731&gen=playurl&nbs=1&oi=3735562649&os=ks3u&platform=html5&trid=22dd5d5f910642b18286b698781f5a23&uipk=5&upsig=e1637ca3517e8d5076fcb5f053114bc0";

	Media media = new Media(MEDIA_URL);
	MediaPlayer mediaPlayer = new MediaPlayer(media);
	
	@Override
	public void start(Stage primaryStage) {
		MediaView mediaView = new MediaView(mediaPlayer);
		
		Button playButton = new Button(">");
		playButton.setOnAction(e -> {
			if(playButton.getText().equals(">")) {
				mediaPlayer.play();
				playButton.setText("||");
			}
			else {
				mediaPlayer.pause();
				playButton.setText(">");
			}
		});
		
		Button rewindButton = new Button("<<");
		rewindButton.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));
		
		Slider slVolume = new Slider();
		slVolume.setPrefWidth(150);
		slVolume.setMaxWidth(Region.USE_PREF_SIZE);
		slVolume.minWidth(30);
		slVolume.setValue(50);
		mediaPlayer.volumeProperty().bind(slVolume.valueProperty().divide(100));
		
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(playButton, rewindButton, new Label("Volume"), slVolume);
		
		TextField tfURL = new TextField();
		
		HBox hBox2 = new HBox(10);
		hBox2.setAlignment(Pos.BASELINE_LEFT);
		hBox2.getChildren().addAll(new Label("Enter a URL: "), tfURL);
		
		BorderPane pane = new BorderPane();
		pane.setCenter(mediaView);
		pane.setBottom(hBox);
		pane.setTop(hBox2);
		
		tfURL.setOnAction(e -> {
			mediaPlayer.pause();
			try {
				Media newMedia = new Media(tfURL.getText());
				mediaPlayer = new MediaPlayer(newMedia);
				playButton.setText(">");
				pane.getChildren().clear();
				pane.setCenter(mediaView);
				pane.setBottom(hBox);
				pane.setTop(hBox2);
			} catch (Exception e2) {
				mediaPlayer = new MediaPlayer(new Media(MEDIA_URL));
				playButton.setText(">");
			}
		});
		
		Scene scene = new Scene(pane, Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 50, Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 80);
		primaryStage.setResizable(false);
		primaryStage.setMaximized(true);
		PaneMethod.addSceneInStage(primaryStage, scene, "MediaDemo");
	}

	public static void main(String[] args) {
		launch(args);
	}

}
