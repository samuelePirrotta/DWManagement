package it.unirc.tesi.beans.area;

public class CategoriaMerceologica {
	
	private Integer id;
	private String nome;
	private Integer raggruppamento;
	private Double ricarico;
	
	public CategoriaMerceologica() {
		super();
		this.id = null;
		// TODO Auto-generated constructor stub
	}

	public CategoriaMerceologica(String nome, Integer raggruppamento, Double ricarico) {
		super();
		this.id = null;
		this.nome = nome;
		this.raggruppamento = raggruppamento;
		this.ricarico = ricarico;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getRaggruppamento() {
		return raggruppamento;
	}

	public void setRaggruppamento(Integer raggruppamento) {
		this.raggruppamento = raggruppamento;
	}

	public Double getRicarico() {
		return ricarico;
	}

	public void setRicarico(Double ricarico) {
		this.ricarico = ricarico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaMerceologica other = (CategoriaMerceologica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoriaMerceologica [id=" + id + ", nome=" + nome + ", raggruppamento=" + raggruppamento
				+ ", ricarico=" + ricarico + "]";
	}
}