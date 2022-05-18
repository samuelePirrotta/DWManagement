package it.unirc.tesi.beans.tagliaEcolore;

public class Colore {
	
	private String nome;
	
	public Colore() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Colore(String nome) {
		super();
		this.nome = nome;
	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Colore other = (Colore) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Colore [nome=" + nome + "]";
	}
}