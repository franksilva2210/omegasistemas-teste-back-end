package app.control.movimentacao.busca;

import java.net.URL;
import java.util.ResourceBundle;

import app.db.dao.MovimentacaoDao;
import app.model.Movimentacao;
import app.view.movimentacao.busca.BuscaMovimentacaoView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class BuscaMovimentacaoControl implements Initializable {
	
	private ObservableList<Movimentacao> listMovimentacaoCaixa = FXCollections.observableArrayList();
	private static Movimentacao movCaixaSelected;
	
	@FXML private TableView<Movimentacao> tableMovimentos;
	@FXML private TableColumn<Movimentacao, Integer> colId;
	@FXML private TableColumn<Movimentacao, String> colDesc;
	@FXML private TableColumn<Movimentacao, Double> colValor;
	@FXML private TableColumn<Movimentacao, String> colTipo;
	@FXML private TableColumn<Movimentacao, String> colData;
	@FXML private Button bttSelecionar;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		consultaMovimentos();
		
		colId.setCellValueFactory(new PropertyValueFactory<Movimentacao, Integer>("id"));
		colDesc.setCellValueFactory(new PropertyValueFactory<Movimentacao, String>("descricao"));
		colValor.setCellValueFactory(new PropertyValueFactory<Movimentacao, Double>("valor"));
		colTipo.setCellValueFactory(new PropertyValueFactory<Movimentacao, String>("tipo"));
		colData.setCellValueFactory(new PropertyValueFactory<Movimentacao, String>("data"));
		
		tableMovimentos.setItems(listMovimentacaoCaixa);
		
		bttSelecionar.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				bttSelecionar();
			}
		});
	}

	public static Movimentacao getMovCaixaSelected() {
		return movCaixaSelected;
	}

	public static void setMovCaixaSelected(Movimentacao movCaixaSelected) {
		BuscaMovimentacaoControl.movCaixaSelected = movCaixaSelected;
	}

	/*BOTAO SELECIONAR*/
	private void bttSelecionar() {
		Movimentacao movCaixaSelected = tableMovimentos.getSelectionModel().getSelectedItem();
		if(movCaixaSelected != null) {
			BuscaMovimentacaoControl.movCaixaSelected = movCaixaSelected;
			BuscaMovimentacaoView.getStage().close();
		}
	}
	
	private void consultaMovimentos() {
		MovimentacaoDao movDao = new MovimentacaoDao();
		listMovimentacaoCaixa.clear();
		listMovimentacaoCaixa.addAll(movDao.consultAll());
		tableMovimentos.refresh();
	} 
}
