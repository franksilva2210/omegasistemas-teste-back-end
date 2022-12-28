package app.control.movimentacaocaixa;

import java.net.URL;
import java.util.ResourceBundle;

import app.control.caixa.buscar.BuscarCaixaControl;
import app.model.Movimentacao;
import app.util.ScreensRegisterControl;
import app.view.caixa.buscar.BuscarCaixaView;
import app.view.movimentacaocaixa.MovimentacaoCaixaView;
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
    	
    	datePicker.setOnAction((ActionEvent event) -> {
    		metodo(datePicker);
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
		
		movimento.setData("");
		movimento.setDescricao(txtDescricao.getText());
		movimento.setTipo(choiceTipo.getValue());
		movimento.setValor(Double.parseDouble(txtValor.getText()));
		
		return false;
	}

	@Override
	protected boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
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
	
	//UTILITARIOS -----------------------------
	
	private void metodo(DatePicker datePicker) {
		
	}
}
