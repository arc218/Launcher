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

	/**
	 * JVMの作業ディレクトリを返す
	 */
	public static void getCurrentDirectory() {
		System.out.println(System.getProperty("user.dir"));
	}

}
