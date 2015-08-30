package utility;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *エラー処理関連
 */
public class ErrorUtil {

	private static final String PATH = "/log.xml";

	/**
	 * 例外を{@code ErrorUtil.path}に出力します
	 * @param e - Exception
	 * @see www.ytp.ne.jp/tech/java/sineruka/jdk14logging.html
	 */
	public void printLog(Exception e) {
		Logger logger = Logger.getLogger("");
		FileHandler fh = null;
		try {
			fh = new FileHandler(this.getClass().getResourceAsStream(PATH).toString(), false);
			if (fh != null) {
				fh.setFormatter(new java.util.logging.XMLFormatter());
				logger.addHandler(fh);
				logger.log(Level.SEVERE, e.getClass().toString(), e);
			}
		} catch (SecurityException | IOException error) {
			//このエラーが起きるとlogに出力できない
			error.printStackTrace();
		} finally {
			if (fh != null) {
				fh.close();
			}
		}
		printStackTrace();
		e.printStackTrace();
	}

	private ErrorUtil() {

	}

	public static ErrorUtil getInstance() {
		return new ErrorUtil();
	}

	/** スタックトレースを表示する*/
	public static void printStackTrace() {
		System.out.println("---printStackTrace---");
		StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
		System.out.println("呼び出し元：" + ste.getClassName() + "#" + ste.getMethodName() + ":" + ste.getLineNumber());
	}

}
