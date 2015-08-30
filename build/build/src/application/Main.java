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
		//TODO:テスト出力
		System.out.println("start");
		try {
			AnchorPane root = FXMLLoader.load(this.getClass().getResource(StringUtil.ENTRY_PATH));
			//			if (root == null) {
			//				System.out.println("entry");
			//			}
			System.out.println("Main-l25");
			Scene scene = new Scene(root, StringUtil.SCENE_WIDTH, StringUtil.SCENE_HEIGHT);
			//			PerspectiveCamera camera = new PerspectiveCamera(true);
			//			camera.setFieldOfView(60.0);
			//			camera.getTransforms().addAll(
			//					new Translate(0, 0, -180));
			//			camera.setNearClip(1.0d);
			//			camera.setFarClip(1_000_000.0d);
			//			scene.setCamera(camera);
			stage.setScene(scene);
			//TODO:テスト出力
			System.out.println("show");
			stage.show();
		} catch (IOException e) {
			ErrorUtil.getInstance().printLog(e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}