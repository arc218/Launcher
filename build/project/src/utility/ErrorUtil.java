package utility;

import java.io.File;

/**
 *エラー処理関連
 */
public class ErrorUtil {

	//private static final String PATH = "/log.xml";

	/**
	 * 例外を{@code ErrorUtil.path}に出力します
	 * @param e - Exception
	 * @see www.ytp.ne.jp/tech/java/sineruka/jdk14logging.html
	 */
	public void printLog(Exception e) {
		//TODO:テスト出力
		//		System.out.println(e.toString());
		//		Logger logger = Logger.getLogger("");
		//		FileHandler fh = null;
		//		try {
		//			fh = new FileHandler(this.getClass().getResource(PATH).toString(), true);
		//			if (fh != null) {
		//				fh.setFormatter(new java.util.logging.XMLFormatter());
		//				logger.addHandler(fh);
		//				logger.log(Level.SEVERE, e.getClass().toString(), e);
		//			}
		//		} catch (SecurityException | IOException error) {
		//			//このエラーが起きるとlogに出力できない
		//			error.printStackTrace();
		//		} finally {
		//			if (fh != null) {
		//				fh.close();
		//			}
		//		}

		StringUtil.printStackTrace();
		e.printStackTrace();
	}

	private ErrorUtil() {

	}

	public static ErrorUtil getInstance() {
		return new ErrorUtil();
	}

	/**
	 * 相対パスを絶対パスに変換して表示する
	 * @param relativePath - 相対パス
	 */

	public static void pathConfiguration(String relativePath) {
		File file = new File(relativePath);
		String absolutePath = file.getAbsolutePath();
		System.out.println("File：" + absolutePath);
	}

}
