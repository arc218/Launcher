package utility;

/**
 *主に定数を管理するUtil
 */
public class StringUtil {

	/** タイトル */
	public static final String TITLE = "Launcher";

	/** スタート画面のパス */
	//public static final String ENTRY_PATH = "scene/menu/Menu.fxml";
	public static final String ENTRY_PATH = "scene/title/Title.fxml";

	/** 横の長さ */
	public static final int SCENE_WIDTH = 800;

	/** 縦の長さ */
	public static final int SCENE_HEIGHT = 600;

	/** 構造ファイルのパス	 */
	public static final String WORK_PATH = "src/work.xml";

	/** JVMの作業ディレクトリを返す */
	public static void getCurrentDirectory() {
		System.out.println(System.getProperty("user.dir"));
	}

	/**
	 * スタックトレースを表示する
	 */
	public static void printStackTrace() {
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		System.out.println("呼び出し元：" + ste.getClassName() + "#" + ste.getMethodName() + ":" + ste.getLineNumber());

	}

	public static final int IMAGE_WIDTH = 300;

	public static final int IMAGE_HEIGHT = 400;

}
