package app.control.principal;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import app.control.caixa.buscar.BuscarCaixaControl;
import app.db.dao.MovimentacaoDao;
import app.model.Caixa;
import app.model.Movimentacao;
import app.view.caixa.buscar.BuscarCaixaView;
import app.view.caixa.cadastro.CadastroCaixaView;
import app.view.movimentacao.cadastro.CadastroMovimentacaoView;
import app.view.principal.PrincipalView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PrincipalControl implements Initializable {
	
	private ObservableList<Integer> opcoesAno = FXCollections.observableArrayList(2022, 2023);
	private ObservableList<Movimentacao> listMovimentacaoCaixa = FXCollections.observableArrayList();

	private Caixa caixaAtual = new Caixa();
	
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
	@FXML private Button bttConsultMovs;
	@FXML private Label lblEntradasMes;
    @FXML private Label lblSaidasMes;
    @FXML private Label lblSaldoMes;
    @FXML private Label lblEntradasGeral;
    @FXML private Label lblSaidasGeral;
    @FXML private Label lblSaldoGeral;
    @FXML private TableView<Movimentacao> tableMovimentos;
    @FXML private TableColumn<Movimentacao, String> colDesc;
    @FXML private TableColumn<Movimentacao, String> coltipo;
    @FXML private TableColumn<Movimentacao, Double> colVal;
    @FXML private TableColumn<Movimentacao, String> colData;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showBalancoGeral();
		
		comboAno.setItems(opcoesAno);
		comboAno.setOnAction((ActionEvent event) -> {
			selecionarOpcaoAno();
		});
		
		linkJan.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkJan.getText());
			}
		});
		
		linkFev.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkFev.getText());
			}
		});
		
		linkMar.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkMar.getText());
			}
		});
		
	    linkAbr.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkAbr.getText());
			}
		});
	    
	    linkMai.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkMai.getText());
			}
		});
	    
	    linkJun.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkJun.getText());
			}
		});
	    
	    linkJul.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkJul.getText());
			}
		});
	    
	    linkAgo.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkAgo.getText());
			}
		});
	    
	    linkSet.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkSet.getText());
			}
		});
	    
	    linkOutubro.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkOutubro.getText());
			}
		});
	    
	    linkNov.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkNov.getText());
			}
		});
	    
	    linkDes.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkDes.getText());
			}
		});
		
		bttBuscarCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				buscarCaixa();
			}
		});
		
		bttCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				CadastroCaixaView.buildAndShowScreen(PrincipalView.getStage());
			}
		});
		
		bttMovCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				CadastroMovimentacaoView.buildAndShowScreen(PrincipalView.getStage());
				showBalancoGeral();
			}
		});
		
		bttConsultMovs.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				
			}
		});
		
		colDesc.setCellValueFactory(new PropertyValueFactory<Movimentacao, String>("descricao"));
		coltipo.setCellValueFactory(new PropertyValueFactory<Movimentacao, String>("tipo"));
		colVal.setCellValueFactory(new PropertyValueFactory<Movimentacao, Double>("valor"));
		colData.setCellValueFactory(new PropertyValueFactory<Movimentacao, String>("data"));
		
		tableMovimentos.setItems(listMovimentacaoCaixa);
	}
	
	/*COMBOBOX ANO*/
	private void selecionarOpcaoAno() {
		Integer ano = comboAno.getValue();
		if(ano != null) {
			lblAno.setText(String.valueOf(ano));
		}
	}
	
	/*BOTAO BUSCAR CAIXA*/
	private void buscarCaixa() {
		BuscarCaixaControl.setCaixaSelected(null);
		BuscarCaixaView.buildAndShowScreen(PrincipalView.getStage());
		if(BuscarCaixaControl.getCaixaSelected() != null) {
			caixaAtual = BuscarCaixaControl.getCaixaSelected();
			txtCaixa.setText(caixaAtual.getDescricao());
		}
	}
	
	/*BOTAO CONSULTAR MOVIMENTOS*/
	private void bttConsultarMovimentos() {
		MovimentacaoDao movDao = new MovimentacaoDao();
		
		List<Movimentacao> listMovimentacaoTotalCaixa = movDao.consultAll(caixaAtual.getId());
		
		
	}
	
	private void pesquisarMovimentos() {
		MovimentacaoDao movDao = new MovimentacaoDao();
		listMovimentacaoCaixa.clear();
		listMovimentacaoCaixa.addAll(movDao.consultAll(caixaAtual.getId()));
		tableMovimentos.refresh();
	}
	
	private void showBalancoMes() {
		Double entradas = new Double(0);
		Double saidas = new Double(0);
		Double saldoGeral = new Double(0);
		
		for(int i = 0;  i < listMovimentacaoCaixa.size(); i++) {
			if(listMovimentacaoCaixa.get(i).getTipo().equals("Entrada")) {
				entradas = entradas + listMovimentacaoCaixa.get(i).getValor();
			} else if(listMovimentacaoCaixa.get(i).getTipo().equals("Saida")) {
				saidas = saidas + listMovimentacaoCaixa.get(i).getValor();
			}
		}
		
		saldoGeral = entradas - saidas;
		
		lblEntradasMes.setText(String.valueOf(entradas));
		lblSaidasMes.setText(String.valueOf(saidas));
		lblSaldoMes.setText(String.valueOf(saldoGeral));
	}
	
	private void showBalancoGeral() {
		MovimentacaoDao movDao = new MovimentacaoDao();
		List<Movimentacao> listMovimentacaoGeral = movDao.consultAll();
		
		Double entradas = new Double(0);
		Double saidas = new Double(0);
		Double saldoGeral = new Double(0);
		
		for(int i = 0; i < listMovimentacaoGeral.size(); i++) {
			if(listMovimentacaoGeral.get(i).getTipo().equals("Entrada")) {
				entradas = entradas + listMovimentacaoGeral.get(i).getValor();
			} else if(listMovimentacaoGeral.get(i).getTipo().equals("Saida")) {
				saidas = saidas + listMovimentacaoGeral.get(i).getValor();
			}
		}
		
		saldoGeral = entradas - saidas;
		
		lblEntradasGeral.setText(String.valueOf(entradas));
		lblSaidasGeral.setText(String.valueOf(saidas));
		lblSaldoGeral.setText(String.valueOf(saldoGeral));
	}
}