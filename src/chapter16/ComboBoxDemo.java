package chapter16;

import chapter15.PaneMethod;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ComboBoxDemo extends Application {
	private String[] flagTitles = {"Canada", "China", "Denmark", 
			"France", "Germany", "India", "Norway", "United Kingdom",
			"United States of America"};
	
	private ImageView[] flagImage = new ImageView[flagTitles.length];
	private String[] imageURL = new String[flagTitles.length];
	private String[] flagDescription = new String[flagTitles.length];
	
	private DescriptionPane descriptionPane = new DescriptionPane();
	
	private ComboBox<String> cbo = new ComboBox<>();
	
	private void initFlagImageAndDescription() {
		imageURL[0] = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=637636387,865039444&fm=200&gp=0.jpg";
		imageURL[1] = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=214407045,844184843&fm=26&gp=0.jpg";
		imageURL[2] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541954363126&di=8d350765a27378b7de8e5c0658aaeb8f&imgtype=0&src=http%3A%2F%2Fs4.sinaimg.cn%2Fmw690%2F001C48szgy6HfIthEIz73%26690";
		imageURL[3] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541954395751&di=56cdbb82e67a13e1fe917f22df227eb1&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F83%2F66%2F55a58PICheG_1024.png";
		imageURL[4] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541954443881&di=e23168caa7e0fd936ec5a2d2829223fa&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160701%2F6c479c1556e943279698c459d13660bd_th.jpg";
		imageURL[5] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541954497278&di=3e847043fb562d32a58d1faa98378d45&imgtype=0&src=http%3A%2F%2Fphoto.orsoon.com%2F180610%2FJPG-180610_826%2F2R5v8oQocy_small.jpg";
		imageURL[6] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541954516710&di=bbd7e3fb0618e5d5c2b83ae9bf871818&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F-4o3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2F1c950a7b02087bf44eebbfcff0d3572c11dfcf95.jpg";
		imageURL[7] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541954535551&di=639c8c4d860352cc8463105a2cc836e7&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201408%2F19%2F20140819154926_vhft3.thumb.700_0.jpeg";
		imageURL[8] = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542549272&di=8677140bf93d314e4ef546f9e46c8f8e&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3D989bfce25b3d26973ade001e3d92d88e%2F58ee3d6d55fbb2fb7b61993a454a20a44623dc54.jpg";
		for(int i = 0; i < flagImage.length; i++) {
			flagImage[i] = new ImageView(imageURL[i]);
			flagDescription[i] = "The description for " + flagTitles[i] + " ...";  
		}
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		initFlagImageAndDescription();
		
		setDisplay(0);
		
		BorderPane pane = new BorderPane();
		
		BorderPane paneForComboBox = new BorderPane();
		paneForComboBox.setLeft(new Label("Select a country: "));
		paneForComboBox.setCenter(cbo);
		pane.setTop(paneForComboBox);
		cbo.setPrefWidth(400);
		cbo.setValue("Canada");
		
		ObservableList<String> items = FXCollections.observableArrayList(flagTitles);
		cbo.getItems().addAll(items);
		pane.setCenter(descriptionPane);
		
		cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));
		
		Scene scene = new Scene(pane);
		primaryStage.setFullScreen(true);
		PaneMethod.addSceneInStage(primaryStage, scene, "ComboBoxDemo");
	}
	
	public void setDisplay(int index) {
		descriptionPane.setTitie(flagTitles[index]);
		descriptionPane.setImageView(flagImage[index]);
		descriptionPane.setDescription(flagDescription[index]);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
