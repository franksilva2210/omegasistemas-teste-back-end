package app.model;

public class Caixa {
	private int id;
	private String descricao;
	private double valInicial;
	
	public Caixa() {
		
	}
	
	public Caixa(Integer id, String descricao, double valInicial) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valInicial = valInicial;
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

	public double getValInicial() {
		return valInicial;
	}

	public void setValInicial(double valInicial) {
		this.valInicial = valInicial;
	}
	
	public void printDataConsole() {
		System.out.println("Id: " + id);
		System.out.println("Descricao: " + descricao);
		System.out.println("Valor Inicial: " + valInicial);
	}
}
