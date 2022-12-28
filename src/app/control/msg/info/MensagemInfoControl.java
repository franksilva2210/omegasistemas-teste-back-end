package app.control.msg.info;

import java.net.URL;
import java.util.ResourceBundle;

import app.view.msg.info.MensagemInfoView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MensagemInfoControl implements Initializable {
	
	private static String msg;
	
	@FXML private Text msgInfo;
    @FXML private Button bttOk;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		msgInfo.setText(msg);
		
		bttOk.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				MensagemInfoView.getStage().close();
			}
		});
	}

	public static String getMsg() {
		return msg;
	}

	public static void setMsg(String msg) {
		MensagemInfoControl.msg = msg;
	}
	
}
