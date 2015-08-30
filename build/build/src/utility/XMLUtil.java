package utility;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * インスタンスは作れないので、getInstance()で取得する
 */
public class XMLUtil {

	private static XMLUtil xmlUtil = new XMLUtil();

	private Element root;

	private XMLUtil() {
	}

	/**
	 * XMLUtilのインスタンスを返す
	 */
	public static XMLUtil getInstance() {
		return xmlUtil;
	}

	/**
	 * work.xmlを変換したものを返す
	 */
	public Element getRoot() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			System.err.println("root start");

			InputStream resource = this.getClass().getResourceAsStream(StringUtil.WORK_PATH);
			System.out.println("resource");
			if (resource == null) {
				System.out.println("null");
			}
			//URI uri = resource.toURI();
			Document document = builder.parse(resource);

			//			System.out.println("parameter");
			//			System.out.println(uri.toString());
			//			File file = new File(uri);
			//TODO:テスト出力
			//			System.out.println("root get");
			//			//File file = new File(StringUtil.WORK_PATH);
			//			Document document = builder.parse(file);
			root = document.getDocumentElement();
			//			//TODO:テスト出力
			//			System.out.println("root get fin");
		} catch (ParserConfigurationException e) {
			//TODO:テスト出力
			System.out.println(e.toString() + "xml.util-error");
			ErrorUtil.getInstance().printLog(e);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//TODO:テスト出力
			//			Alert alert = new Alert(AlertType.INFORMATION);
			//			alert.setTitle("Show");
			//			alert.getDialogPane().setHeaderText("Header Text");
			//			alert.getDialogPane().setContentText("Content Text");
			//			alert.show();
			//			System.out.println("message after alert#show()");
			System.out.println("finally");
		}
		System.out.println("return");
		return root;
	}

}
