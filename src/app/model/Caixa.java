package app.model;

public class Caixa {
	
	private int id;
	private String descricao;
	private double saldo;
	
	public Caixa() {
		
	}
	
	public Caixa(Integer id, String descricao, double saldoInicial) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.saldo = saldoInicial;
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

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double val) {
		this.saldo = val;
	}
	
	public void printDataConsole() {
		System.out.println("Id: " + id);
		System.out.println("Descricao: " + descricao);
		System.out.println("Valor Inicial: " + saldo);
	}
	
	public void clear() {
		this.id = 0;
		this.descricao = "";
		this.saldo = 0.0;
	}
}
