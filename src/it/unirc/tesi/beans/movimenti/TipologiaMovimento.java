package it.unirc.tesi.beans.movimenti;

public class TipologiaMovimento {
	
	private String codice;
	private String nome;
	private String causale;
	private Integer contatore;
	private char automatico;
	
	public TipologiaMovimento() {
		super();
		this.contatore = 0;
	}

	public TipologiaMovimento(String codice, String nome, String causale, char automatico) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.causale = causale;
		this.contatore = 0;
		this.automatico = automatico;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public Integer getContatore() {
		return contatore;
	}

	public void setContatore(Integer contatore) {
		this.contatore = contatore;
	}

	public boolean isAutomatico() {
		if(automatico=='1')
			return true;
		else
			return false;
	}

	public void setAutomatico(boolean automatico) {
		if(automatico)
			this.automatico = '1';
		else
			this.automatico = '0';
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
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
		TipologiaMovimento other = (TipologiaMovimento) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipologiaMovimento [codice=" + codice + ", nome=" + nome + ", causale=" + causale + ", contatore="
				+ contatore + ", automatico=" + automatico + "]";
	}
}
