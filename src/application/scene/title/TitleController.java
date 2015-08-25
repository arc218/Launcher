package application.scene.title;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import utility.ErrorUtil;
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

	@FXML
	private ImageView imageView;

	@FXML
	private AnchorPane basePane;

	private Element root;

	private HashMap<Integer, HashMap<String, String>> hashMap;

	private int pivot = 0;

	private int size = 0;

	/**
	 * 現在該当するディレクトリを開く
	 */
	private void openDirectory() {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder("open", hashMap.get(pivot + 1).get("path"));
			Process process = processBuilder.start();
			process.waitFor();
			process.destroy();
		} catch (IOException e) {
			ErrorUtil.printLog(e);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handleUp(ActionEvent event) {
		pivot = (pivot + 1) % size;
		initField();
	}

	@FXML
	public void handleDown(ActionEvent event) {
		pivot = (pivot + size - 1) % size;
		initField();
	}

	@FXML
	public void handleEnter(ActionEvent event) {
		openDirectory();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		root = XMLUtil.getInstance().getRoot();

		hashMap = new HashMap<Integer, HashMap<String, String>>();

		//ルート要素の子ノードを取得する
		NodeList rootChildren = root.getChildNodes();
		for (int i = 0; i < rootChildren.getLength(); i++) {
			//i個目の作品情報を取得
			Node node = rootChildren.item(i);
			HashMap<String, String> dataMap = new HashMap<>();
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if (element.getNodeName().equals("work")) {
					NodeList personChildren = node.getChildNodes();
					for (int j = 0; j < personChildren.getLength(); j++) {
						Node personNode = personChildren.item(j);
						if (personNode.getNodeType() == Node.ELEMENT_NODE) {
							setData(personNode, dataMap, "name", "creator", "image", "description", "path");
						}
					}
				}
			}
			if (!dataMap.isEmpty()) {
				hashMap.put((i / 2) + 1, dataMap);
			}
		}
		size = hashMap.size();
		pivot = 0;
		initField();
		initKeyConfig();
	}

	/**
	 * キー情報の初期化
	 */
	private void initKeyConfig() {
		basePane.setOnKeyPressed(event -> {
			KeyCode code = event.getCode();
			if (KeyCode.ENTER.equals(code)) {
				openDirectory();
			} else if (KeyCode.DOWN.equals(code)) {
				pivot = (pivot + size - 1) % size;
				initField();
			} else if (KeyCode.UP.equals(code)) {
				pivot = (pivot + 1) % size;
				initField();
			}
		});
	}

	/**
	 * Fieldの初期化
	 */
	private void initField() {
		HashMap<String, String> map = hashMap.get(pivot + 1);
		workName.setText(map.get("name"));
		creatorName.setText(map.get("creator"));
		descriptionName.setText(map.get("description"));
		imageView.setImage(new Image(map.get("image")));
	}

	/**
	 * 対応する情報をHashMapに格納する
	 */
	private void setData(Node personNode, HashMap<String, String> dataMap, String... args) {
		for (String name : args) {
			if (personNode.getNodeName().equals(name)) {
				dataMap.put(name, personNode.getTextContent());
			}
		}
	}
}
