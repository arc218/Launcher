package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.ErrorUtil;
import utility.StringUtil;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle(StringUtil.TITLE);
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource(StringUtil.ENTRY_PATH));
			Scene scene = new Scene(root, StringUtil.SCENE_WIDTH, StringUtil.SCENE_HEIGHT);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			ErrorUtil.printLog(e);
		}
	}

	public static void main(String [] args) {
		launch(args);
	}
}
