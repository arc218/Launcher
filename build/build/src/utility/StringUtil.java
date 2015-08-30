package utility;

/**
 *主に定数を管理するUtil
 *@see http://stackoverflow.com/questions/19602727/how-to-reference-javafx-fxml-files-in-resource-folder
 */
public class StringUtil {

	/** タイトル */
	public static final String TITLE = "Launcher";

	/** スタート画面のパス(デモ用) */
	public static final String ENTRY_PATH = "scene/menu/Menu.fxml";

	/** スタート画面のパス(本番用) */
	//public static final String ENTRY_PATH = "/src/scene/menu/Menu.fxml";
	//public static final String ENTRY_PATH = "scene/title/Title.fxml";

	/** 横の長さ */
	public static final int SCENE_WIDTH = 800;

	/** 縦の長さ */
	public static final int SCENE_HEIGHT = 600;

	/** 構造ファイルのパス	 */
	public static final String WORK_PATH = "/work.xml";
	//public static final String WORK_PATH = "/work.xml";

	/** JVMの作業ディレクトリを返す */
	public static void getCurrentDirectory() {
		System.out.println(System.getProperty("user.dir"));
	}

	/** スタックトレースを表示する*/
	public static void printStackTrace() {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		System.out.println("呼び出し元：" + ste.getClassName() + "#" + ste.getMethodName() + ":" + ste.getLineNumber());

	}

	/** 画像の幅 */
	public static final int IMAGE_WIDTH = 540;

	/** 画像の高さ */
	public static final int IMAGE_HEIGHT = 300;

}
