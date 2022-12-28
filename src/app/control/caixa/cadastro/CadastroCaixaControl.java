package app.control.caixa.cadastro;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.control.caixa.buscar.BuscarCaixaControl;
import app.control.msg.info.MensagemInfoControl;
import app.db.dao.CaixaDao;
import app.model.Caixa;
import app.util.ScreensRegisterControl;
import app.util.ValidaField;
import app.util.db.ModPersistData;
import app.view.caixa.buscar.BuscarCaixaView;
import app.view.caixa.cadastro.CadastroCaixaView;
import app.view.msg.info.MensagemInfoView;
import app.view.principal.PrincipalView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CadastroCaixaControl extends ScreensRegisterControl implements Initializable {

	private List<Node> camposObrigatorios = new ArrayList<Node>();
	private Caixa caixaAtual;
	private ModPersistData modPersistData;
	
	@FXML private Button bttNovo;
	@FXML private Button bttBuscar;
    @FXML private Button bttRemover;
    @FXML private TextField textCodId;
    @FXML private TextField textDescricao;
    @FXML private TextField textValInicial;
    @FXML private Button bttSalvar;
    @FXML private Button bttCancel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initProperties();
		
		camposObrigatorios.add(textDescricao);
		camposObrigatorios.add(textValInicial);
		
		bttNovo.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttNovo();
			}
		});
		
		bttBuscar.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttBuscar();
			}
		});
		
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
	
	/*BOTAO NOVO*/
	private void bttNovo() {
		resetProperties();
		clearDataScreen();
	}

	/*BOTAO SALVAR*/
	private void bttSalvar() {
		if(!processDataInterface())
			return;
		
		processDataPersistence();
		
		clearDataScreen();
		resetProperties();
	}
	
	/*BOTAO BUSCAR*/
	private void bttBuscar() {
		BuscarCaixaControl.setCaixaSelected(null);
		BuscarCaixaView.buildAndShowScreen(CadastroCaixaView.getStage());
		
		if(BuscarCaixaControl.getCaixaSelected() != null) {
			caixaAtual = BuscarCaixaControl.getCaixaSelected();
			modPersistData = ModPersistData.UPDATE;
			showDataScreen();
		}
	}

	@Override
	protected boolean processDataInterface() {
		if(!validateFields() || !extractFields()) {
			return false;
		} else 
			return true;
	}

	@Override
	protected boolean processDataObject() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void processDataPersistence() {
		CaixaDao caixaDao = new CaixaDao();
		
		switch(modPersistData) {
			case NEW:
				caixaDao.save(caixaAtual);
				break;
			
			case UPDATE:
				caixaDao.update(caixaAtual);
				break;
				
			case DELET:
				caixaDao.delete(caixaAtual);
				break;
				
			default:
				break;
		}
	}
	
	@Override
	protected boolean validateFields() {
		if(!validateFieldDescricao() || !validateFieldValInicial()) {
			return false;
		} else 
			return true;
	}
	
	@Override
	protected boolean extractFields() {
	
		caixaAtual.setDescricao(textDescricao.getText());
		
		try {
			caixaAtual.setSaldoInicial(Double.parseDouble(textValInicial.getText()));
		} catch(NumberFormatException e) {
			MensagemInfoControl.setMsg("Campo: Valor Inicial: Nao e possivel a insercao de\n");
			MensagemInfoControl.setMsg(MensagemInfoControl.getMsg() + "letras ou outros caracteres nao numericos");
			MensagemInfoView.loadAndShowStage(CadastroCaixaView.getStage());
			return false;
		}
		
		return true;
	}

	@Override
	protected void showDataScreen() {
		textCodId.setText(String.valueOf(caixaAtual.getId()));
		textDescricao.setText(caixaAtual.getDescricao());
		textValInicial.setText(String.valueOf(caixaAtual.getSaldoInicial()));
	}

	@Override
	protected void clearDataScreen() {
		textCodId.clear();
		textDescricao.clear();
		textValInicial.clear();
	}
	
	private void initProperties() {
		caixaAtual = new Caixa();
		modPersistData = ModPersistData.NEW;
	}
	
	private void resetProperties() {
		caixaAtual.clear();
		modPersistData = ModPersistData.NEW;
	}

	private boolean validateFieldDescricao() {
		ValidaField validField = new ValidaField();
		validField.setControl(textDescricao);
		
		validField.validateControl();
		if(validField.getError()) {
			MensagemInfoControl.setMsg("Campo: Descricao, Invalido!");
			MensagemInfoView.loadAndShowStage(PrincipalView.getStage());
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validateFieldValInicial() {
		ValidaField validField = new ValidaField();
		validField.setControl(textValInicial);
		
		validField.validateControl();
		if(validField.getError()) {
			MensagemInfoControl.setMsg("Campo: Valor Inicial, Invalido!");
			MensagemInfoView.loadAndShowStage(PrincipalView.getStage());
			return false;
		} else {
			return true;
		}
	}
	
}
