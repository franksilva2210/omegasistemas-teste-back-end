package app.model;

public class Caixa {
	
	private int id;
	private String descricao;
	private double saldoInicial;
	
	public Caixa() {
		
	}
	
	public Caixa(Integer id, String descricao, double saldoInicial) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.saldoInicial = saldoInicial;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double valInicial) {
		this.saldoInicial = valInicial;
	}
	
	public void printDataConsole() {
		System.out.println("Id: " + id);
		System.out.println("Descricao: " + descricao);
		System.out.println("Valor Inicial: " + saldoInicial);
	}
	
	public void clear() {
		this.id = 0;
		this.descricao = "";
		this.saldoInicial = 0.0;
	}
}
