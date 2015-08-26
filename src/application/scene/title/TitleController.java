package application.scene.title;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.StringJoiner;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import utility.ErrorUtil;
import utility.StringUtil;
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

	@FXML
	private ListView<String> listView;

	private Element root;

	private HashMap<Integer, HashMap<String, String>> hashMap;

	private int pivot = 0;

	private int size = 0;

	private ObservableList<String> listRecords = FXCollections.observableArrayList();

	/**
	 * 現在該当するディレクトリを開く
	 */
	private void openDirectory() {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder("open", hashMap.get(pivot + 1).get("path"));
			Process process = processBuilder.start();
			//			process.waitFor();
			//			process.destroy();
		} catch (IOException e) {
			ErrorUtil.printLog(e);
		} //catch (InterruptedException e) {
			//			e.printStackTrace();
			//		}
	}

	@FXML
	public void handleUp(ActionEvent event) {
		changeOverFile();
	}

	private void changeOverFile() {
		System.out.println("bef:" + pivot);
		pivot = (pivot + size - 1) % size;
		System.out.println("aft:" + pivot);
		listView.getSelectionModel().select(pivot + 1);
		initField();
		//		int index = listView.getSelectionModel().getSelectedIndex();
		//		System.out.println(listRecords.size());
	}

	@FXML
	public void handleDown(ActionEvent event) {
		changeUnderFile();
	}

	private void changeUnderFile() {
		pivot = (pivot + 1) % size;
		listView.getSelectionModel().select(pivot - 1);
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
		initListView();
	}

	/**
	 * ListViewの初期化処理
	 */
	private void initListView() {
		listView.setItems(listRecords);
		listView.getSelectionModel().selectFirst();
		listView.getSelectionModel().selectedItemProperty().addListener(listener -> {
			pivot = listView.getSelectionModel().getSelectedIndex();
			initField();
		});
	}

	/**
	 * キー情報の初期化
	 */
	private void initKeyConfig() {
		listView.setOnKeyPressed(event -> {
			KeyCode code = event.getCode();
			if (KeyCode.ENTER.equals(code)) {
				openDirectory();
			} else if (KeyCode.DOWN.equals(code)) {
				changeUnderFile();
			} else if (KeyCode.UP.equals(code)) {
				changeOverFile();
			}
		});
	}

	/**
	 * Fieldの初期化
	 */
	private void initField() {
		HashMap<String, String> map = hashMap.get(pivot + 1);
		workName.setText("作品名:" + map.get("name"));
		creatorName.setText("製作者:" + map.get("creator"));
		String descriptionValue = map.get("description");
		int limit = 15;//1行に何文字まで表示するか
		if (descriptionValue.length() < limit) {
			descriptionName.setText(descriptionValue);
		} else {
			String crlf = System.getProperty("line.separator");
			StringJoiner joiner = new StringJoiner(crlf);
			for (int i = 0; limit * i + limit < descriptionValue.length(); i++) {
				joiner.add(descriptionValue.substring(limit * i, limit * i + limit));
			}
			descriptionName.setText(joiner.toString());
		}
		//imageView.setPreserveRatio(true);
		imageView.setFitHeight(StringUtil.IMAGE_HEIGHT);
		imageView.setFitWidth(StringUtil.IMAGE_WIDTH);
		imageView.setImage(new Image(map.get("image")));
	}

	/**
	 * 対応する情報をHashMapに格納する
	 */
	private void setData(Node personNode, HashMap<String, String> dataMap, String... args) {
		for (String name : args) {
			if (personNode.getNodeName().equals(name)) {
				dataMap.put(name, personNode.getTextContent());

				if (personNode.getNodeName().equals("name")) {
					listRecords.add(personNode.getTextContent());
				}
			}
		}
	}
}
