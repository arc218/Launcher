package application.scene.title;

import java.net.URL;
import java.util.ResourceBundle;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import utility.XMLUtil;

public class TitleController implements Initializable {

	@FXML
	private Text workName;

	@FXML
	private Text creatorName;

	@FXML
	private Text descriptionName;

	@FXML
	private Button defButton;

	@FXML
	private Button upButton;

	@FXML
	private Button downButton;

	private Element root;

	@FXML
	public void handleUp(ActionEvent event) {
	}

	@FXML
	public void handleDown(ActionEvent event) {
	}

	@FXML
	public void handleDef(ActionEvent event) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root = XMLUtil.getInstance().getRoot();
		System.out.println("ノード名：" + root.getNodeName());

		//ルート要素の属性を取得する
		System.out.println("ルート要素の属性：" + root.getAttribute("name"));

		//ルート要素の子ノードを取得する
		NodeList rootChildren = root.getChildNodes();

		System.out.println("子要素の数：" + rootChildren.getLength());
		System.out.println("------------------");

		for (int i = 0; i < rootChildren.getLength(); i++) {
			Node node = rootChildren.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if (element.getNodeName().equals("work")) {
					NodeList personChildren = node.getChildNodes();

					for (int j = 0; j < personChildren.getLength(); j++) {
						Node personNode = personChildren.item(j);
						if (personNode.getNodeType() == Node.ELEMENT_NODE) {

							if (personNode.getNodeName().equals("name")) {
								System.out.println("名前：" + personNode.getTextContent());
							} else if (personNode.getNodeName().equals("creator")) {
								System.out.println("製作者:" + personNode.getTextContent());
							} else if (personNode.getNodeName().equals("image")) {
								System.out.println("画像:" + personNode.getTextContent());
							} else if (personNode.getNodeName().equals("description")) {
								System.out.println("説明:" + personNode.getTextContent());
							} else if (personNode.getNodeName().equals("path")) {
								System.out.println("パス:" + personNode.getTextContent());
							}

						}
					}
					System.out.println("------------------");
				}
			}

		}

		//		String nodeName = item.getNodeName();
		//		System.out.println(nodeName);
		//		Node child = root.getFirstChild();
		//		NamedNodeMap nodeMap = child.getAttributes();
		//		Node namedItem = nodeMap.getNamedItem("name");
		//		System.out.println(namedItem.getNodeName());
		//		System.out.println(namedItem.getNodeValue());
	}

}
