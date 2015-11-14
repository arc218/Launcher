package utility;

public class PlatformUtil {

	public static String osName = System.getProperty("os.name").toLowerCase();

	/**
	 * OS名を小文字で取得します
	 */
	public static String getOSName() {
		//System.out.println(osName);
		return osName;
		//http://www.atmarkit.co.jp/fjava/javatips/115java020.html
	}

	public static boolean isLinux() {
		return osName.startsWith("linux");
	}

	public static boolean isMac() {
		return osName.startsWith("mac");
	}

	public static boolean isWindows() {
		return osName.startsWith("windows");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}
}
