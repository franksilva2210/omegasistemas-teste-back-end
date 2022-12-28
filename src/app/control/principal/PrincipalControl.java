package app.control.principal;

import java.net.URL;
import java.util.ResourceBundle;

import app.view.caixa.cadastro.CadastroCaixaView;
import app.view.movimentacaocaixa.MovimentacaoCaixaView;
import app.view.principal.PrincipalView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class PrincipalControl implements Initializable {

	@FXML private Button bttCaixa;
	@FXML private Button bttMovCaixa;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bttCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				CadastroCaixaView.buildAndShowScreen(PrincipalView.getStage());
			}
		});
		
		bttMovCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				MovimentacaoCaixaView.buildAndShowScreen(PrincipalView.getStage());
			}
		});
	}

}
