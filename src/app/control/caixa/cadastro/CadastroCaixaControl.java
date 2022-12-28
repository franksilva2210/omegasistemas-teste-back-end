package app.control.caixa.cadastro;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.control.msg.info.MensagemInfoControl;
import app.model.Caixa;
import app.util.ScreensRegisterControl;
import app.util.ValidaField;
import app.view.caixa.cadastro.CadastroCaixaView;
import app.view.msg.info.MensagemInfoView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CadastroCaixaControl extends ScreensRegisterControl implements Initializable {

	List<Node> camposObrigatorios = new ArrayList<Node>();
	
	private Caixa caixaAtual = new Caixa();
	
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
		
		bttCancel.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				CadastroCaixaView.getStage().close();
			}
		});
	}

	private void bttSalvar() {
		if(processDataInterface() == false)
			return;
		
		caixaAtual.printDataConsole();
	}

	@Override
	protected boolean processDataInterface() {
		if(validateFields()) {
			extractFields();
			return true;
		}
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
	protected boolean validateFields() {
		ValidaField validField = new ValidaField();
		for(int i = 0; i < camposObrigatorios.size(); i++) {
			validField.setControl(camposObrigatorios.get(i));
			validField.validateControl();
			if(validField.getError()) {
				MensagemInfoControl.setMsg(camposObrigatorios.get(i).getId());//Por enquanto mensagem não tratada
				MensagemInfoView.loadAndShowStage(CadastroCaixaView.getStage());
				return false;
			}
			validField.clear();
		}
		return true;
	}
	
	@Override
	protected void extractFields() {
	
		caixaAtual.setDescricao(textDescricao.getText());
		
		try {
			caixaAtual.setValInicial(Double.parseDouble(textValInicial.getText()));
		} catch(NumberFormatException e) {
				MensagemInfoControl.setMsg("Campo: Valor Inicial: Nao e possivel a insercao de\n");
				MensagemInfoControl.setMsg(MensagemInfoControl.getMsg() + "letras ou outros caracteres nao numericos");
				MensagemInfoView.loadAndShowStage(CadastroCaixaView.getStage());
		}
		
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
