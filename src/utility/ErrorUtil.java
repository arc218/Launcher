package utility;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorUtil {

	private static final String PATH = "src/log.xml";

	/**
	 * 例外を{@code ErrorUtil.path}に出力します
	 * @param e - Exception
	 */
	public static void printLog(Exception e) {
		Logger logger = Logger.getLogger("");
		FileHandler fh = null;
		try {
			fh = new FileHandler(PATH, true);
			fh.setFormatter(new java.util.logging.XMLFormatter());
			logger.addHandler(fh);
			logger.log(Level.SEVERE, e.getClass().toString(), e);
		} catch (SecurityException | IOException error) {
			//このエラーが起きるとlogに出力できない
			error.printStackTrace();
		} finally {
			fh.close();
		}
		e.printStackTrace();
	}

	public static void pathConfiguration(String relativePath) {
		File file = new File(relativePath);
		String absolutePath = file.getAbsolutePath();
		System.out.println("File：" + absolutePath);
	}
}
