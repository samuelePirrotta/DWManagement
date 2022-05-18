package it.unirc.tesi.beans.area;

public class RaggruppamentoMerceologico {
	private Integer id;
	private String nome;
	private int reparto;
	
	public RaggruppamentoMerceologico() {
		super();
		this.id = null;
		// TODO Auto-generated constructor stub
	}
	
	public RaggruppamentoMerceologico(String nome, int reparto) {
		super();
		this.id = null;
		this.nome = nome;
		this.reparto = reparto;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer codice) {
		this.id = codice;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getReparto() {
		return reparto;
	}
	public void setReparto(int reparto) {
		this.reparto = reparto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RaggruppamentoMerceologico other = (RaggruppamentoMerceologico) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "RaggruppamentoMerceologico [codice=" + id + ", nome=" + nome + ", reparto=" + reparto + "]";
	}	
}