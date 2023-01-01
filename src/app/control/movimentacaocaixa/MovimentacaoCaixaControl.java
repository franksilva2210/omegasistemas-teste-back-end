package app.control.movimentacaocaixa;

import java.net.URL;
import java.util.ResourceBundle;

import app.control.caixa.buscar.BuscarCaixaControl;
import app.control.msg.info.MensagemInfoControl;
import app.db.dao.MovimentacaoDao;
import app.model.Movimentacao;
import app.util.ScreensRegisterControl;
import app.util.ValidaField;
import app.util.db.ModPersistData;
import app.view.caixa.buscar.BuscarCaixaView;
import app.view.movimentacaocaixa.MovimentacaoCaixaView;
import app.view.msg.info.MensagemInfoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MovimentacaoCaixaControl extends ScreensRegisterControl implements Initializable {
	
	final private ObservableList<String> opcoesTipoMov = FXCollections.observableArrayList("Entrada", "Saida");
	
	private Movimentacao movimento;
	private ModPersistData modPersistData;
	
	@FXML private DatePicker datePicker;
	@FXML private TextField txtCaixa;
	@FXML private Button bttBuscarCaixa;
	@FXML private Hyperlink linkLimparFieldCaixa;
    @FXML private TextField txtDescricao;
    @FXML private ChoiceBox<String> choiceTipo;
    @FXML private TextField txtValor;
    @FXML private Label lblValorTotal;
    @FXML private Label lblTipo;
    @FXML private Label lblValMov;
    @FXML private Button bttSalvar;
    @FXML private Button bttCancel;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	initializeProperties();
    	
    	choiceTipo.setItems(opcoesTipoMov);
    	
    	bttBuscarCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttBuscarCaixa();
			}
		});
    	
    	linkLimparFieldCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				txtCaixa.clear();
			}
		});
    	
    	bttSalvar.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttSalvar();
			}
		});
    	
    	bttCancel.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				MovimentacaoCaixaView.getStage().close();
			}
		});
	}
    
    /*BOTAO BUSCAR CAIXA*/
    private void bttBuscarCaixa() {
    	BuscarCaixaControl.setCaixaSelected(null);
    	BuscarCaixaView.buildAndShowScreen(MovimentacaoCaixaView.getStage());
    	
    	if (BuscarCaixaControl.getCaixaSelected() != null) {
    		movimento.setCaixa(BuscarCaixaControl.getCaixaSelected());
    		modPersistData = ModPersistData.UPDATE;
    		preencheFieldCaixa();
    	}
    }
    
    /*BOTAO SALVAR*/
    private void bttSalvar() {
    	if(!processDataInterface())
    		return;
    	
    	if(processDataObject())
    		return;
    }
    
    @Override
	protected boolean processDataInterface() {
    	if(!validateFields())
    		return false;
    	else if(!extractFields())
    		return false;
    	else
    		return true;
	}

	@Override
	protected boolean processDataObject() {
		movimento.calcNovoSaldoCaixa();
		return true;
	}

	@Override
	protected void processDataPersistence() {
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
		
		switch(modPersistData) {
			case NEW:
				movimentacaoDao.save(movimento);
				break;
			
			case UPDATE:
				movimentacaoDao.update(movimento);
				break;
				
			case DELET:
				movimentacaoDao.delete(movimento);
				break;
				
			default:
				break;
		}
	}
	
	@Override
	protected boolean validateFields() {
		if(!validaFieldCaixa())
			return false;
		else if(!validaFieldData())
			return false;
		else if(!validaFieldDescricao())
			return false;
		else if(!validaFieldTipo())
			return false;
		else if(!validaFieldValor())
			return false;
		else
			return true;
	}
	
	@Override
	protected boolean extractFields() {
		movimento.setData(String.valueOf(datePicker.getValue()));
		movimento.setDescricao(txtDescricao.getText());
		movimento.setTipo(choiceTipo.getValue());
		
		try {
			movimento.setValor(Double.parseDouble(txtValor.getText()));
		} catch(NumberFormatException e) {
			movimento.setValor(0.0);
			MensagemInfoControl.setMsg("Campo: Valor. Digite um numero valido");
    		MensagemInfoView.loadAndShowStage(MovimentacaoCaixaView.getStage());
    		return false;
		}
		
		return true;
	}

	@Override
	protected void showDataScreen() {
		
	}

	@Override
	protected void clearDataScreen() {
		
	}
	
	//PROPRIEDADES ---------------------------

	private void initializeProperties() {
		movimento = new Movimentacao();
		modPersistData = ModPersistData.NEW;
	}
	
	//COMPONENTES GRAFICOS -----------------------------
	
	private boolean validaFieldCaixa() {
		ValidaField validField = new ValidaField();
		validField.setControl(txtCaixa);
		
		validField.validateControl();
		if(validField.getError()) {
			MensagemInfoControl.setMsg("Campo Caixa, invalido");
    		MensagemInfoView.loadAndShowStage(MovimentacaoCaixaView.getStage());
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validaFieldData() {
		ValidaField validField = new ValidaField();
		validField.setControl(datePicker);
		
		validField.validateControl();
		if(validField.getError()) {
			MensagemInfoControl.setMsg("Campo Data, invalido");
    		MensagemInfoView.loadAndShowStage(MovimentacaoCaixaView.getStage());
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validaFieldDescricao() {
		ValidaField validField = new ValidaField();
		validField.setControl(txtDescricao);
		
		validField.validateControl();
		if(validField.getError()) {
			MensagemInfoControl.setMsg("Campo Descrição, invalido");
    		MensagemInfoView.loadAndShowStage(MovimentacaoCaixaView.getStage());
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validaFieldTipo() {
		ValidaField validField = new ValidaField();
		validField.setControl(choiceTipo);
		
		validField.validateControl();
		if(validField.getError()) {
			MensagemInfoControl.setMsg("Campo Tipo, invalido");
    		MensagemInfoView.loadAndShowStage(MovimentacaoCaixaView.getStage());
			return false;
		} else {
			return true;
		}
	}
	
	private boolean validaFieldValor() {
		ValidaField validField = new ValidaField();
		validField.setControl(txtValor);
		
		validField.validateControl();
		if(validField.getError()) {
			MensagemInfoControl.setMsg("Campo Valor, invalido");
    		MensagemInfoView.loadAndShowStage(MovimentacaoCaixaView.getStage());
			return false;
		} else {
			return true;
		}
	}
	
	//UTILITARIOS -----------------------------
	
	private void preencheFieldCaixa() {
		txtCaixa.setText(movimento.getCaixa().getDescricao());
	}
	
}
