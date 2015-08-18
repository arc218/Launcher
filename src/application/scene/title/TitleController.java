package application.scene.title;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class TitleController implements Initializable {

	@FXML
	private TextField accountField;

	@FXML
	private Button button;

	@FXML
	public void handleLogin(ActionEvent event) {
		accountField.setText("clickLogin");
	}

	@FXML
	public void handleCancel(ActionEvent event) {
		accountField.setText("clickCancel");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		accountField.setText("init");
		button = new Button("Button");

		// ツールチップを設定
		button.setTooltip(new Tooltip("ボタン"));
	}

}
