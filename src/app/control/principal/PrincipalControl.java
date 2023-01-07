package app.control.principal;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	private Integer filtro[] = {0, 0};

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
    @FXML private Hyperlink linkOut;
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
			configAnoFiltro();
		});
		
		linkJan.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkJan.getText());
				configMesFiltro();
			}
		});
		
		linkFev.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkFev.getText());
				configMesFiltro();
			}
		});
		
		linkMar.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkMar.getText());
				configMesFiltro();
			}
		});
		
	    linkAbr.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkAbr.getText());
				configMesFiltro();
			}
		});
	    
	    linkMai.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkMai.getText());
				configMesFiltro();
			}
		});
	    
	    linkJun.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkJun.getText());
				configMesFiltro();
			}
		});
	    
	    linkJul.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkJul.getText());
				configMesFiltro();
			}
		});
	    
	    linkAgo.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkAgo.getText());
				configMesFiltro();
			}
		});
	    
	    linkSet.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkSet.getText());
				configMesFiltro();
			}
		});
	    
	    linkOut.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkOut.getText());
				configMesFiltro();
			}
		});
	    
	    linkNov.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkNov.getText());
				configMesFiltro();
			}
		});
	    
	    linkDes.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				lblMes.setText(linkDes.getText());
				configMesFiltro();
			}
		});
		
		bttBuscarCaixa.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				buscarCaixa();
				configMesFiltro();
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
				consultarMovimentos();
			}
		});
		
		bttConsultMovs.setOnMouseClicked((MouseEvent mouse) -> {
			if(mouse.getClickCount() == 1) {
				consultarMovimentos();
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
	private void consultarMovimentos() {
		listMovimentacaoCaixa.clear();
		listMovimentacaoCaixa.addAll(getListMovimentosComFiltro());
		tableMovimentos.refresh();
		showBalancoMes();
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
		
		DecimalFormat formatador = new DecimalFormat("#.##");
		
		lblEntradasMes.setText(String.valueOf(formatador.format(entradas)));
		lblSaidasMes.setText(String.valueOf(formatador.format(saidas)));
		lblSaldoMes.setText(String.valueOf(formatador.format(saldoGeral)));
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
		
		DecimalFormat formatador = new DecimalFormat("#.##");
		
		lblEntradasGeral.setText(String.valueOf(formatador.format(entradas)));
		lblSaidasGeral.setText(String.valueOf(formatador.format(saidas)));
		lblSaldoGeral.setText(String.valueOf(formatador.format(saldoGeral)));
	}
	
	private void configMesFiltro() {
		if(lblMes.getText().equals(linkJan.getText())) {
			filtro[0] = 1;
		} else if(lblMes.getText().equals(linkFev.getText())) {
			filtro[0] = 2;
		} else if(lblMes.getText().equals(linkMar.getText())) {
			filtro[0] = 3;
		} else if(lblMes.getText().equals(linkAbr.getText())) {
			filtro[0] = 4;
		} else if(lblMes.getText().equals(linkMai.getText())) {
			filtro[0] = 5;
		} else if(lblMes.getText().equals(linkJun.getText())) {
			filtro[0] = 6;
		} else if(lblMes.getText().equals(linkJul.getText())) {
			filtro[0] = 7;
		} else if(lblMes.getText().equals(linkAgo.getText())) {
			filtro[0] = 8;
		} else if(lblMes.getText().equals(linkSet.getText())) {
			filtro[0] = 9;
		} else if(lblMes.getText().equals(linkOut.getText())) {
			filtro[0] = 10;
		} else if(lblMes.getText().equals(linkNov.getText())) {
			filtro[0] = 11;
		} else if(lblMes.getText().equals(linkDes.getText())) {
			filtro[0] = 12;
		}
	}
	
	private void configAnoFiltro() {
		filtro[1] = comboAno.getValue();
	}
	
	private List<Movimentacao> getListMovimentosComFiltro() {
		MovimentacaoDao movDao = new MovimentacaoDao();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataMovimento = null;
		
		List<Movimentacao> listMovimentacaoTotalCaixa = movDao.consultAll(caixaAtual.getId());
		List<Movimentacao> listMovimentacaoCaixaFiltrada = new ArrayList<Movimentacao>();
		
		for(int i = 0; i < listMovimentacaoTotalCaixa.size(); i++) {
			dataMovimento = LocalDate.parse(listMovimentacaoTotalCaixa.get(i).getData(), dateFormatter);
			if(varificaDataComFiltro(dataMovimento)) {
				listMovimentacaoCaixaFiltrada.add(listMovimentacaoTotalCaixa.get(i));
			}
		}
		
		return listMovimentacaoCaixaFiltrada;
	}
	
	private boolean varificaDataComFiltro(LocalDate dataMovimento) {
		if(dataMovimento.getMonth().getValue() == filtro[0] && dataMovimento.getYear() == filtro[1])
			return true;
		else
			return false;
	}
}