package app.control.msg.confirm;

import java.net.URL;
import java.util.ResourceBundle;

import app.view.msg.confirm.MensagemConfirmacaoView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MensagemConfirmacaoControl implements Initializable {

	private static String msgConfirm;
	private static boolean confirmOperation;
	
	@FXML private Text txtMsgConfirm;
    @FXML private Button bttOk;
    @FXML private Button bttCancel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtMsgConfirm.setText(msgConfirm);
		
		bttOk.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttOk();
			}
		});
		
		bttCancel.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttCancel();
			}
		});
	}

	public static String getMsgConfirm() {
		return msgConfirm;
	}

	public static void setMsgConfirm(String msgConfirm) {
		MensagemConfirmacaoControl.msgConfirm = msgConfirm;
	}
	
	public static boolean getConfirmOperation() {
		return confirmOperation;
	}

	public static void setConfirmOperation(boolean confirmOperation) {
		MensagemConfirmacaoControl.confirmOperation = confirmOperation;
	}

	/*BOTAO OK*/
	private void bttOk() {
		confirmOperation = true;
		MensagemConfirmacaoView.getStage().close();
	}
	
	/*BOTAO CANCELAR*/
	private void bttCancel() {
		confirmOperation = false;
		MensagemConfirmacaoView.getStage().close();
	}

}
