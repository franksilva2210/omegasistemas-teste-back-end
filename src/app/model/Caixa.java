package app.model;

public class Caixa {
	private Integer id;
	private String descricao;
	private Double valInicial;
	
	public Caixa(Integer id, String descricao, Double valInicial) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valInicial = valInicial;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getValInicial() {
		return valInicial;
	}
	
	public void setValInicial(Double valInicial) {
		this.valInicial = valInicial;
	}
	
	
}
