package app.control.principal;

import java.net.URL;
import java.util.ResourceBundle;

import app.view.caixa.buscar.BuscarCaixaView;
import app.view.caixa.cadastro.CadastroCaixaView;
import app.view.movcaixa.cadastro.CadastroMovCaixaView;
import app.view.principal.PrincipalView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrincipalControl implements Initializable {

	@FXML private ComboBox<Integer> comboAno;
    @FXML private Hyperlink linkJan;
    @FXML private Hyperlink linkFev;
    @FXML private Hyperlink linkMar;
    @FXML private Hyperlink linkAbr;
    @FXML private Hyperlink linkMai;
    @FXML private Hyperlink linkJun;
    @FXML private Hyperlink linkJul;
    @FXML private Hyperlink linkAgo;
    @FXML private Hyperlink linkSet;
    @FXML private Hyperlink linkOutubro;
    @FXML private Hyperlink linkNov;
    @FXML private Hyperlink linkDes;
    @FXML private Label lblMes;
    @FXML private Label lblAno;
    @FXML private TextField txtCaixa;
    @FXML private Button bttBuscarCaixa;
    @FXML private Button bttCaixa;
	@FXML private Button bttMovCaixa;
	@FXML private Label lblEntradasMes;
    @FXML private Label lblSaidasMes;
    @FXML private Label lblSaldoMes;
    @FXML private Label lblEntradasGeral;
    @FXML private Label lblSaidasGeral;
    @FXML private Label lblSaldoGeral;
    @FXML private TableView<?> tableMovimentos;
    @FXML private TableColumn<?, ?> colDesc;
    @FXML private TableColumn<?, ?> colVal;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		bttBuscarCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				BuscarCaixaView.buildAndShowScreen(PrincipalView.getStage());
			}
		});
		
		bttCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				CadastroCaixaView.buildAndShowScreen(PrincipalView.getStage());
			}
		});
		
		bttMovCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				CadastroMovCaixaView.buildAndShowScreen(PrincipalView.getStage());
			}
		});
	}
	
	

}
