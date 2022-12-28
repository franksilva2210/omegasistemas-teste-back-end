package app.control.movimentacaocaixa;

import java.net.URL;
import java.util.ResourceBundle;

import app.control.caixa.buscar.BuscarCaixaControl;
import app.model.Caixa;
import app.util.ScreensRegisterControl;
import app.view.caixa.buscar.BuscarCaixaView;
import app.view.movimentacaocaixa.MovimentacaoCaixaView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MovimentacaoCaixaControl extends ScreensRegisterControl implements Initializable {
	
	private Caixa caixaAtual;
	
	@FXML private Button bttBuscarCaixa;
	@FXML private Label lblNomeCaixa;
    @FXML private Label lblValSaldoAtual;
    @FXML private Button bttEntrada;
    @FXML private Button bttSaida;
    @FXML private Button bttFechar;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	initializeProperties();
    	
    	bttBuscarCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttBuscarCaixa();
			}
		});
	}
    
    /*BOTAO BUSCAR CAIXA*/
    private void bttBuscarCaixa() {
    	BuscarCaixaControl.setCaixaSelected(null);
    	BuscarCaixaView.buildAndShowScreen(MovimentacaoCaixaView.getStage());
    	
    	if (BuscarCaixaControl.getCaixaSelected() != null) {
    		caixaAtual = BuscarCaixaControl.getCaixaSelected();
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
	protected void showDataScreen() {
		lblNomeCaixa.setText(caixaAtual.getDescricao());
		lblValSaldoAtual.setText(String.valueOf(caixaAtual.getSaldoInicial()));
	}

	@Override
	protected void clearDataScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean extractFields() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean validateFields() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void initializeProperties() {
		caixaAtual = new Caixa();
	}
}
