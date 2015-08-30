package utility;

import java.io.File;

/**
 *主に定数を管理するUtil
 *
 *「..」を使うことによって相対位置で自分より上にあるディレクトリも指定できるが、通常のファイルの場合でしか読み込めない。すなわち、jarファイル内のファイルは読み込めない。
 *@see http://www.ne.jp/asahi/hishidama/home/tech/java/resource.html
 *@see http://stackoverflow.com/questions/18055189/why-my-uri-is-not-hierarchical
 *@see http://stackoverflow.com/questions/19602727/how-to-reference-javafx-fxml-files-in-resource-folder
 */
public class StringUtil {

	/** タイトル */
	public static final String TITLE = "Launcher";

	/** スタート画面のパス */
	public static final String ENTRY_PATH = "scene/menu/Menu.fxml";

	/** 横の長さ */
	public static final int SCENE_WIDTH = 800;

	/** 縦の長さ */
	public static final int SCENE_HEIGHT = 600;

	/** 構造ファイルのパス	 */
	public static final String WORK_PATH = "/work.xml";

	/**
	 * 相対パスを絶対パスに変換して表示する
	 * @param relativePath - 相対パス
	 */
	public static void pathConfiguration(String relativePath) {
		File file = new File(relativePath);
		String absolutePath = file.getAbsolutePath();
		System.out.println("File：" + absolutePath);
	}

	/** 画像の幅 */
	public static final int IMAGE_WIDTH = 540;

	/** 画像の高さ */
	public static final int IMAGE_HEIGHT = 300;

}
