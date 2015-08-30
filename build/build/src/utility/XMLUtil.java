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
		try (InputStream resource = this.getClass().getResourceAsStream(StringUtil.WORK_PATH);) {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(resource);
			root = document.getDocumentElement();
			//実際はfianllyでクローズするべき
		} catch (ParserConfigurationException | SAXException | IOException e) {
			ErrorUtil.getInstance().printLog(e);
		} finally {
			//			Alert alert = new Alert(AlertType.INFORMATION);
			//			alert.setTitle("Show");
			//			alert.getDialogPane().setHeaderText("Header Text");
			//			alert.getDialogPane().setContentText("Content Text");
			//			alert.show();
			//			System.out.println("message after alert#show()");
		}
		return root;
	}

}
