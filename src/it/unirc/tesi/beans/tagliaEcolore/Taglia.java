package it.unirc.tesi.beans.tagliaEcolore;

public class Taglia {
	
	private String nome;
	private String tabella;
	
	public Taglia() {
		super();
	}

	public Taglia(String nome, String tabella) {
		super();
		this.nome = nome;
		this.tabella = tabella;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTabella() {
		return tabella;
	}

	public void setTabella(String tabella) {
		this.tabella = tabella;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tabella == null) ? 0 : tabella.hashCode());
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
		Taglia other = (Taglia) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tabella == null) {
			if (other.tabella != null)
				return false;
		} else if (!tabella.equals(other.tabella))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Taglia [nome=" + nome + ", tabella=" + tabella + "]";
	}
}