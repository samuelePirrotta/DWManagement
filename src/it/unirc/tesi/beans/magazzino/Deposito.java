package it.unirc.tesi.beans.magazzino;

public class Deposito {
	
	private Integer codice;
	private String nome;
	private String indirizzo;
	
	public Deposito() {
		super();
		this.codice = null;
	}
	
	public Deposito(String nome, String indirizzo) {
		this.codice = null;
		this.nome = nome;
		this.indirizzo = indirizzo;
	}
	
	public Integer getCodice() {
		return this.codice;
	}
	
	public void setCodice(Integer codice) {
		this.codice = codice;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getIndirizzo() {
		return this.indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
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
		Deposito other = (Deposito) obj;
		if (codice != other.codice)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Deposito [codice=" + codice + ", nome=" + nome + ", indirizzo=" + indirizzo + "]";
	}
	
	

}
