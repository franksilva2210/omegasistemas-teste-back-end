package app.control.caixa.cadastro;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.model.Caixa;
import app.util.ScreensRegisterControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CadastroCaixaControl extends ScreensRegisterControl implements Initializable {

	List<Node> camposObrigatorios = new ArrayList<Node>();
	
	private Caixa caixaAtual;
	
	@FXML private Button bttBuscar;
    @FXML private Button bttRemover;
    @FXML private TextField textDescricao;
    @FXML private TextField textValInicial;
    @FXML private Button bttSalvar;
    @FXML private Button bttCancel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		camposObrigatorios.add(textDescricao);
		camposObrigatorios.add(textValInicial);
		
		bttSalvar.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttSalvar();
			}
		});
	}

	private void bttSalvar() {
		
	}

	@Override
	protected boolean processDataInterface() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean processDataObject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void processDataPersistence() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected boolean extractFields() {
	
		caixaAtual.setDescricao(textDescricao.getText());
		caixaAtual.setValInicial(Double.valueOf(textValInicial.getText()));
		
		
		return false;
	}

	@Override
	protected boolean validateFields() {
		
		for(int i = 0; i < camposObrigatorios.size(); i++) {
			
		}
		
		return false;
	}

	@Override
	protected void showDataScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void clearDataScreen() {
		// TODO Auto-generated method stub
		
	}

	
}
