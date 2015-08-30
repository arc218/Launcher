package application.scene.menu;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.StringJoiner;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import utility.PlatformUtil;
import utility.StringUtil;
import utility.XMLUtil;

public class MenuController implements Initializable {

	/** 作品名 */
	@FXML
	private Text workName;

	/** 製作者名 */
	@FXML
	private Text creatorName;

	/** 作品の説明 */
	@FXML
	private Text descriptionText;

	/** 開くボタン */
	@FXML
	private Button defButton;

	/** 画像を読み込むビュー */
	@FXML
	private ImageView imageView;

	/** ベースパネル */
	@FXML
	private AnchorPane basePane;

	/** 作品リスト */
	@FXML
	private ListView<String> listView;

	private Element root;

	private HashMap<Integer, HashMap<String, String>> hashMap;

	private int pivot = 0;

	private int size = 0;

	private ObservableList<String> listRecords = FXCollections.observableArrayList();

	private Timeline timeline;

	@FXML
	public void handleUp(ActionEvent event) {
		changeOverFile();
	}

	/**
	 * 一つ下のファイルに移動する
	 */
	private void changeOverFile() {
		if (pivot == 0) {
			listView.getSelectionModel().select(1);
		} else {
			pivot = (pivot + size - 1) % size;
			listView.getSelectionModel().select(pivot + 1);
		}
		initField();
		//		int index = listView.getSelectionModel().getSelectedIndex();
	}

	@FXML
	public void handleDown(ActionEvent event) {
		changeUnderFile();
	}

	/**
	 * 一つ上のファイルに移動する
	 */
	private void changeUnderFile() {
		pivot = (pivot + 1) % size;
		listView.getSelectionModel().select(pivot - 1);
		initField();
	}

	@FXML
	public void handleEnter(ActionEvent event) {
		openDirectory();
	}

	/**
	 * 現在該当するディレクトリを開く
	 */
	private void openDirectory() {
		try {
			ProcessBuilder processBuilder = new ProcessBuilder("open", hashMap.get(pivot + 1).get("path"));
			Process process = processBuilder.start();
			process.waitFor();
			process.destroy();
		} catch (IOException | InterruptedException e) {
			//ErrorUtil.getInstance().printLog(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TODO:テスト出力
		System.out.println("controller-initialize");
		//
		//		//root = XMLUtil.getInstance().getRoot();
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
		//TODO:テスト出力
		System.out.println("initField");
		initField();
		initKeyConfig();
		initListView();
		//画面サイズの設定
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(StringUtil.IMAGE_HEIGHT);
		imageView.setFitWidth(StringUtil.IMAGE_WIDTH);
		//	使用できるフォントの表示
		//		List<String> string = Font.getFamilies();
		//		for (String string2 : string) {
		//			System.out.println(string2);
		//		}

		if (PlatformUtil.isMac()) {
			descriptionText.setFont(Font.font("YuGothic"));
		} else if (PlatformUtil.isWindows()) {
			descriptionText.setFont(Font.font("Meiryo"));
		}

		//TODO:テスト出力
		System.out.println("init fin");
	}

	/**
	 * ListViewの初期化処理
	 */
	private void initListView() {
		listView.setItems(listRecords);
		listView.getSelectionModel().selectFirst();
		//		listView.getSelectionModel().selectedItemProperty().addListener(listener -> {
		//			pivot = listView.getSelectionModel().getSelectedIndex();
		//			initField();
		//		});

		listView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				openDirectory();
			} else {
				pivot = listView.getSelectionModel().getSelectedIndex();
				initField();
			}
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
		//StringUtil.printStackTrace();
		HashMap<String, String> map = hashMap.get(pivot + 1);
		if (map != null) {
			workName.setText("作品名:" + map.get("name"));
			creatorName.setText("製作者:" + map.get("creator"));
			String descriptionValue = map.get("description");
			int limit = 30;//1行に何文字まで表示するか
			if (descriptionValue.length() < limit) {
				descriptionText.setText(descriptionValue);
			} else {
				String crlf = System.getProperty("line.separator");
				StringJoiner joiner = new StringJoiner(crlf);
				for (int i = 0; limit * i + limit < descriptionValue.length(); i++) {
					joiner.add(descriptionValue.substring(limit * i, limit * i + limit));
				}
				descriptionText.setText(joiner.toString());
			}

			//フェード関連の処理
			//			imageView.setImage(new Image(split[0]));
			//			FadeTransition fadeout = new FadeTransition(new Duration(500));
			//			fadeout.setNode(imageView);
			//			fadeout.setToValue(0.0);
			//			fadeout.setOnFinished(event -> {
			//				basePane.getChildren().remove(imageView);
			//			});
			//
			//			FadeTransition fadein = new FadeTransition(new Duration(500));
			//			fadein.setNode(imageView);
			//			fadein.setToValue(1.0);

			//画像のパスを分割
			String[] split = map.get("image").split(",");
			imageView.setImage(new Image(split[0]));

			//すでにアニメーションが行われていた場合削除する
			if (timeline != null) {
				timeline.stop();
			}

			timeline = new Timeline(new KeyFrame(new Duration(1000), event -> {
				imageView.setImage(new Image(split[1]));
			}), new KeyFrame(new Duration(2000), event -> {
				imageView.setImage(new Image(split[2]));
			}), new KeyFrame(new Duration(3000), event -> {
				imageView.setImage(new Image(split[0]));
			}));
			//無限ループ
			timeline.setCycleCount(Timeline.INDEFINITE);

			timeline.play();
		} else {
			System.out.println("error:" + pivot);
			//			再現方法:1にカーソルがあるが、実際は6が表示されているときに6を開き、その後下に行こうとすると発生する
			//			対策として最初から最後に移動できないようにした
		}
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
				if (personNode.getNodeName().equals("image")) {
					//TODO:全部の作品の画像数があっているかの確認(フィールドにパラメータを設置して対応)
				}
			}
		}
	}

}
