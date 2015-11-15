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
		stage.setResizable(false);//画面サイズを変更させない
		try {
			AnchorPane root = FXMLLoader.load(this.getClass().getResource(StringUtil.ENTRY_PATH));
			if (root != null) {
				Scene scene = new Scene(root, StringUtil.SCENE_WIDTH, StringUtil.SCENE_HEIGHT);
				stage.setScene(scene);
				stage.show();
			} else {
				System.out.println("wrong pass");
			}
		} catch (IOException e) {
			ErrorUtil.getInstance().printLog(e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}