package app.control.movimentacao.cadastro;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import app.control.caixa.buscar.BuscarCaixaControl;
import app.control.movimentacao.busca.BuscaMovimentacaoControl;
import app.control.msg.confirm.MensagemConfirmacaoControl;
import app.control.msg.info.MensagemInfoControl;
import app.db.dao.MovimentacaoDao;
import app.model.Movimentacao;
import app.util.ScreensRegisterControl;
import app.util.ValidaField;
import app.util.db.ModPersistData;
import app.view.caixa.buscar.BuscarCaixaView;
import app.view.movimentacao.busca.BuscaMovimentacaoView;
import app.view.movimentacao.cadastro.CadastroMovimentacaoView;
import app.view.msg.confirm.MensagemConfirmacaoView;
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

public class CadastroMovimentacaoControl extends ScreensRegisterControl implements Initializable {
	
	final private ObservableList<String> opcoesTipoMov = FXCollections.observableArrayList("Entrada", "Saida");
	
	private Movimentacao movimento;
	private ModPersistData modPersistData;
	
	@FXML private Button bttNovo;
	@FXML private Button bttBuscar;
	@FXML private Button bttRemover;
	@FXML private TextField txtId;
	@FXML private TextField txtCaixa;
	@FXML private Button bttBuscarCaixa;
	@FXML private Hyperlink linkLimparFieldCaixa;
	@FXML private DatePicker datePicker;
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
    	initProperties();
    	
    	bttNovo.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				resetProperties();
				clearDataScreen();
			}
		});
    	
    	bttBuscar.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttBuscarMovimento();
			}
		});
    	
    	bttRemover.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttRemover();
			}
		});
    	
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
				CadastroMovimentacaoView.getStage().close();
			}
		});
	}
    
    /*BOTAO BUSCAR MOVIMENTO*/
    private void bttBuscarMovimento() {
    	BuscaMovimentacaoControl.setMovCaixaSelected(null);
    	BuscaMovimentacaoView.buildAndShowScreen(CadastroMovimentacaoView.getStage());
    	if(BuscaMovimentacaoControl.getMovCaixaSelected() != null) {
    		movimento = BuscaMovimentacaoControl.getMovCaixaSelected();
    		modPersistData = ModPersistData.UPDATE;
    		clearDataScreen();
    		showDataScreen();
    	}
    }
    
    /*BOTAO REMOVER*/
    private void bttRemover() {
    	if(modPersistData == ModPersistData.UPDATE && movimento.getId() != 0) {
    		MensagemConfirmacaoControl.setConfirmOperation(false);
    		MensagemConfirmacaoControl.setMsgConfirm("Deseja realmente remover este movimento?");
    		MensagemConfirmacaoView.loadAndShowStage(CadastroMovimentacaoView.getStage());
    		if(MensagemConfirmacaoControl.getConfirmOperation()) {
    			modPersistData = ModPersistData.DELET;
    			if(processDataPersistence()) {
    				resetProperties();
    				clearDataScreen();
    			}
    		}
    	}
    }
    
    /*BOTAO BUSCAR CAIXA*/
    private void bttBuscarCaixa() {
    	BuscarCaixaControl.setCaixaSelected(null);
    	BuscarCaixaView.buildAndShowScreen(CadastroMovimentacaoView.getStage());
    	
    	if (BuscarCaixaControl.getCaixaSelected() != null) {
    		movimento.setCaixa(BuscarCaixaControl.getCaixaSelected());
    		showFieldCaixa();
    	}
    }
    
    /*BOTAO SALVAR*/
    private void bttSalvar() {
    	if(!processDataInterface())
    		return;
    	
    	if(!processDataObject())
    		return;
    	
    	processDataPersistence();
    	
    	resetProperties();
    	clearDataScreen();
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
	protected boolean processDataPersistence() {
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao();
		
		switch(modPersistData) {
			case INSERT:
				movimentacaoDao.save(movimento);
				if(movimentacaoDao.getError()) {
					MensagemInfoControl.setMsg(movimentacaoDao.getMsgError());
					MensagemInfoView.loadAndShowStage(CadastroMovimentacaoView.getStage());
					return false;
				} else
					return true;
			
			case UPDATE:
				movimentacaoDao.update(movimento);
				if(movimentacaoDao.getError()) {
					MensagemInfoControl.setMsg(movimentacaoDao.getMsgError());
					MensagemInfoView.loadAndShowStage(CadastroMovimentacaoView.getStage());
					return false;
				} else
					return true;
				
			case DELET:
				movimentacaoDao.delete(movimento);
				if(movimentacaoDao.getError()) {
					MensagemInfoControl.setMsg(movimentacaoDao.getMsgError());
					MensagemInfoView.loadAndShowStage(CadastroMovimentacaoView.getStage());
					return false;
				} else
					return true;
				
			default:
				return false;
		}
	}
	
	@Override
	protected boolean validateFields() {
		if(!validaFieldData())
			return false;
		else if(!validaFieldTipo())
			return false;
		else if(!validaFieldCaixa())
			return false;
		else if(!validaFieldDescricao())
			return false;
		else if(!validaFieldValor())
			return false;
		else
			return true;
	}
	
	@Override
	protected boolean extractFields() {
		extractFieldData();
		movimento.setDescricao(txtDescricao.getText());
		movimento.setTipo(choiceTipo.getValue());
		
		try {
			movimento.setValor(Double.parseDouble(txtValor.getText()));
		} catch(NumberFormatException e) {
			movimento.setValor(0.0);
			MensagemInfoControl.setMsg("Campo: Valor. Digite um numero valido");
    		MensagemInfoView.loadAndShowStage(CadastroMovimentacaoView.getStage());
    		return false;
		}
		
		return true;
	}

	@Override
	protected void showDataScreen() {
		txtId.setText(String.valueOf(movimento.getId()));
		showFieldCaixa();
		showFieldData();
		choiceTipo.setValue(movimento.getTipo());
		txtDescricao.setText(movimento.getDescricao());
		txtValor.setText(String.valueOf(movimento.getValor()));
	}

	@Override
	protected void clearDataScreen() {
		this.txtId.clear();
		this.txtCaixa.clear();
		this.datePicker.getEditor().clear();
		this.txtDescricao.clear();
		this.choiceTipo.getSelectionModel().clearSelection();
		this.txtValor.clear();
	}
	
	//PROPRIEDADES ---------------------------

	private void initProperties() {
		movimento = new Movimentacao();
		modPersistData = ModPersistData.INSERT;
	}
	
	private void resetProperties() {
		movimento.clear();
		modPersistData = ModPersistData.INSERT;
	}
	
	//COMPONENTES GRAFICOS -----------------------------
	
	private boolean validaFieldCaixa() {
		ValidaField validField = new ValidaField();
		validField.setControl(txtCaixa);
		
		validField.validateControl();
		if(validField.getError()) {
			MensagemInfoControl.setMsg("Campo Caixa, invalido");
    		MensagemInfoView.loadAndShowStage(CadastroMovimentacaoView.getStage());
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
    		MensagemInfoView.loadAndShowStage(CadastroMovimentacaoView.getStage());
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
    		MensagemInfoView.loadAndShowStage(CadastroMovimentacaoView.getStage());
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
    		MensagemInfoView.loadAndShowStage(CadastroMovimentacaoView.getStage());
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
    		MensagemInfoView.loadAndShowStage(CadastroMovimentacaoView.getStage());
			return false;
		} else {
			return true;
		}
	}
	
	private void showFieldCaixa() {
		txtCaixa.setText(movimento.getCaixa().getDescricao());
	}
	
	private void showFieldData() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(movimento.getData(), dateFormatter);
		datePicker.setValue(localDate);
		datePicker.getEditor().setText(movimento.getData());
	}
	
	private void extractFieldData() {
		String dataFormatada = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try {
			date = sdf.parse(datePicker.getValue().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		dataFormatada = sdf.format(date);
		
		movimento.setData(dataFormatada);
	}
}
