package app.model;

public class Movimentacao {
	
	private int id;
	private String data;
	private String tipo;
	private Caixa caixa;
	private String descrica;
	private Double valor;
	
	public Movimentacao() {
		
	}
	
	public Movimentacao(int id, String data, String tipo, Caixa caixa, String descrica, Double valor) {
		super();
		this.id = id;
		this.data = data;
		this.tipo = tipo;
		this.caixa = caixa;
		this.descrica = descrica;
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
	
	public String getDescrica() {
		return descrica;
	}
	
	public void setDescrica(String descrica) {
		this.descrica = descrica;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
