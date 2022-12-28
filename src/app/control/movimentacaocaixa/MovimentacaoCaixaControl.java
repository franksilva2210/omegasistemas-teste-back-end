package app.control.movimentacaocaixa;

import java.net.URL;
import java.util.ResourceBundle;

import app.control.caixa.buscar.BuscarCaixaControl;
import app.control.msg.info.MensagemInfoControl;
import app.model.Movimentacao;
import app.util.ScreensRegisterControl;
import app.util.ValidaField;
import app.view.caixa.buscar.BuscarCaixaView;
import app.view.movimentacaocaixa.MovimentacaoCaixaView;
import app.view.msg.info.MensagemInfoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MovimentacaoCaixaControl extends ScreensRegisterControl implements Initializable {
	
	final private ObservableList<String> opcoesTipoMov = FXCollections.observableArrayList("Entrada", "Saida");
	
	private Movimentacao movimento;
	
	@FXML private DatePicker datePicker;
	@FXML private TextField txtCaixa;
	@FXML private Button bttBuscarCaixa;
    @FXML private TextField txtDescricao;
    @FXML private ChoiceBox<String> choiceTipo;
    @FXML private TextField txtValor;
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
    	
    	bttSalvar.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttSalvarMovimento();
			}
		});
    	
    	datePicker.setOnAction((ActionEvent event) -> {
    		
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
    		showDataScreen();
    	}
    }
    
    /*BOTAO SALVAR*/
    private void bttSalvarMovimento() {
    	
    	if(!processDataInterface())
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void processDataPersistence() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected boolean extractFields() {
		movimento.setData(getDataConvertida());
		movimento.setDescricao(txtDescricao.getText());
		movimento.setTipo(choiceTipo.getValue());
		
		try {
			movimento.setValor(Double.parseDouble(txtValor.getText()));
		} catch(NumberFormatException e) {
			movimento.setValor(0.0);
			MensagemInfoControl.setMsg("Nao e permitido o uso de caracteres\nnao numericos");
    		MensagemInfoView.loadAndShowStage(MovimentacaoCaixaView.getStage());
			return false;
		}
		
		return true;
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
	protected void showDataScreen() {
		txtCaixa.setText(movimento.getCaixa().getDescricao());
	}

	@Override
	protected void clearDataScreen() {
		// TODO Auto-generated method stub
		
	}
	
	private void initializeProperties() {
		movimento = new Movimentacao();
	}
	
	//PROPRIEDADES ---------------------------
	
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
	
	private String getDataConvertida() {
		String data = new String();
		
		int dia = datePicker.getValue().getDayOfMonth();
		int mes = datePicker.getValue().getMonth().getValue();
		int ano = datePicker.getValue().getYear();
		
		data = dia + "/" + mes + "/" + ano;
		
		return data;
	}
	
	
}
