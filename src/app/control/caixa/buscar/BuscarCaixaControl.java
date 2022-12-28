package app.control.caixa.buscar;

import java.net.URL;
import java.util.ResourceBundle;

import app.db.dao.CaixaDao;
import app.model.Caixa;
import app.view.caixa.buscar.BuscarCaixaView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class BuscarCaixaControl implements Initializable {
	
	private ObservableList<Caixa> listCaixas = FXCollections.observableArrayList();
	
	private static Caixa caixaSelected;
	
	@FXML private TableView<Caixa> tabCaixas;
    @FXML private TableColumn<Caixa, Integer> colCodId;
    @FXML private TableColumn<Caixa, String> ColDesc;
    @FXML private TableColumn<Caixa, Double> colValInicial;
    @FXML private Button bttSelecionar;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		search();
		
		colCodId.setCellValueFactory(new PropertyValueFactory<Caixa, Integer>("id"));
		ColDesc.setCellValueFactory(new PropertyValueFactory<Caixa, String>("descricao"));
		colValInicial.setCellValueFactory(new PropertyValueFactory<Caixa, Double>("saldoInicial"));
		
		tabCaixas.setItems(listCaixas);
		
		bttSelecionar.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				selection();
			}
		});
	}
	
	public static Caixa getCaixaSelected() {
		return caixaSelected;
	}
	
    public static void setCaixaSelected(Caixa caixaSelected) {
		BuscarCaixaControl.caixaSelected = caixaSelected;
	}
    
    /*BOTAO SELECIONAR*/
	private void selection() {
		Caixa caixaSelected = tabCaixas.getSelectionModel().getSelectedItem();
		if(caixaSelected != null) {
			BuscarCaixaControl.caixaSelected = caixaSelected;
			BuscarCaixaView.getStage().close();
		}
	}
	
	private void search() {
		CaixaDao caixaDao = new CaixaDao();
		listCaixas.clear();
		listCaixas.addAll(caixaDao.consultAll());
		tabCaixas.refresh();
	}
}
