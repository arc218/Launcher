package utility;

import java.io.File;
import java.io.IOException;

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
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File(StringUtil.WORK_PATH);
			Document document = builder.parse(file);
			root = document.getDocumentElement();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			ErrorUtil.printLog(e);
		}
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
		return root;
	}

}
