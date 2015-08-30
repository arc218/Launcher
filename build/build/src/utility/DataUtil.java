package utility;

import java.lang.management.ManagementFactory;
import java.util.List;

import javafx.scene.text.Font;

public class DataUtil {
	/**
	 * 使用できるフォントの表示
	 */
	public static void printAvailableFontFamilies() {
		List<String> familyList = Font.getFamilies();
		for (String family : familyList) {
			System.out.println(family);
		}
	}

	/**
	 * デバッグモードかどうかを検知する(エクリプス上)
	 * @return true : デバッグモード
	 * @see http://did2memo.net/2013/08/30/eclipse-java-in-debug-mode/
	 */
	public static boolean isDebugMode() {
		boolean isDebugMode = ManagementFactory.getRuntimeMXBean().getInputArguments()
				.toString().contains("-agentlib:jdwp");
		return isDebugMode;
	}

	/** JVMの作業ディレクトリを返す
	 * @return */
	public static String getCurrentDirectory() {
		//System.out.println(System.getProperty("user.dir"));
		return System.getProperty("user.dir");
	}
}
