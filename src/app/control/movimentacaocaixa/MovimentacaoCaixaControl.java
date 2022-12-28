package app.control.movimentacaocaixa;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MovimentacaoCaixaControl extends ScreensRegisterControl implements Initializable {
	
	private List<Node> camposObrigatorios = new ArrayList<Node>();
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
    	
    	camposObrigatorios.add(datePicker);
    	camposObrigatorios.add(txtCaixa);
    	camposObrigatorios.add(txtDescricao);
    	camposObrigatorios.add(choiceTipo);
    	camposObrigatorios.add(txtValor);
    	
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
    	
    	System.out.println(validateFields());
    	if(!validateFields()) {
    		MensagemInfoControl.setMsg(getCampoVazio().getId());
    		MensagemInfoView.loadAndShowStage(MovimentacaoCaixaView.getStage());
    	}
    	
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
		movimento.setData(getDataConvertida());
		movimento.setDescricao(txtDescricao.getText());
		movimento.setTipo(choiceTipo.getValue());
		
		try {
			movimento.setValor(Double.parseDouble(txtValor.getText()));
		} catch(NumberFormatException e) {
			movimento.setValor(0.0);
		}
		
		return true;
	}

	@Override
	protected boolean validateFields() {
		for(int i = 0; i < camposObrigatorios.size(); i++) {
			if(validateControl(camposObrigatorios.get(i))) {
				return false;
			}
		}
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
	
	
	
	//UTILITARIOS -----------------------------
	
	private String getDataConvertida() {
		String data = new String();
		
		int dia = datePicker.getValue().getDayOfMonth();
		int mes = datePicker.getValue().getMonth().getValue();
		int ano = datePicker.getValue().getYear();
		
		data = dia + "/" + mes + "/" + ano;
		
		return data;
	}
	
	private boolean validateControl(Node control) {
		ValidaField validField = new ValidaField();
		validField.setControl(control);
		
		validField.validateControl();
		if(validField.getError()) {
			return false;
		} else {
			return true;
		}
	}
	
	private Node getCampoVazio() {
		for(int i = 0; i < camposObrigatorios.size(); i++) {
			if(validateControl(camposObrigatorios.get(i))) {
				return camposObrigatorios.get(i);
			}
		}
		return null;
	}
}
