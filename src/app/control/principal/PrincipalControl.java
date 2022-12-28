package app.control.principal;

import java.net.URL;
import java.util.ResourceBundle;

import app.view.caixa.cadastro.CadastroCaixaView;
import app.view.principal.PrincipalView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class PrincipalControl implements Initializable {

	@FXML private Button bttCaixa;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bttCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				CadastroCaixaView.buildAndShowScreen(PrincipalView.getStage());
			}
		});
	}

}
