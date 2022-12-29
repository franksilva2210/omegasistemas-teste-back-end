package app.model;

public class Movimentacao {
	
	private int id;
	private String data;
	private String tipo;
	private Caixa caixa;
	private String descricao;
	private Double valor;
	
	public Movimentacao() {
		
	}
	
	public Movimentacao(int id, String data, String tipo, Caixa caixa, String desc, Double valor) {
		super();
		this.id = id;
		this.data = data;
		this.tipo = tipo;
		this.caixa = caixa;
		this.descricao = desc;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Caixa getCaixa() {
		return caixa;
	}
	
	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String desc) {
		this.descricao = desc;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public void printDataConsole() {
		System.out.println("ID: " + id);
		System.out.println("Data: " + data);
		System.out.println("Tipo: " + tipo);
		System.out.println("Id Caixa: " + caixa.getId());
		System.out.println("Descrição: " + descricao);
		System.out.println("Valor: " + valor);
	}
	
	public void clear() {
		this.id = 0;
		this.data = "";
		this.tipo = "";
		this.caixa.clear();
		this.descricao = "";
		this.valor = 0.0;
	}
	
	public void calcLancamento() {
		if(tipo.equals("Entrada")) 
			calcEntrada();
		else if(tipo.equals("Saida"))
			calcSaida();
	}
	
	private void calcEntrada() {
		caixa.setSaldoInicial(caixa.getSaldoInicial() + valor);
	}
	
	private void calcSaida() {
		caixa.setSaldoInicial(caixa.getSaldoInicial() - valor);
	}
}
