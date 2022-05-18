package it.unirc.tesi.beans.articolo;

import it.unirc.tesi.beans.fornitore.Fornitore;

public class Articolo {
	
	private Integer codice;
	private String codiceRf;     //as codice_riferimento_fornitore
	private Integer codiceFornitore;
	private String descrizione;
	private String stagione;
	private String tabellaTaglie;

	public Articolo() {
		super();
	}

	public Articolo(Fornitore fornitore, String codiceRf, Integer codiceFornitore, String descrizione, String stagione, String tabellaTaglie) {
		super();
		this.codiceRf = codiceRf;
		this.codiceFornitore = fornitore.getCodice();
		this.descrizione = descrizione;
		this.stagione = stagione;
		this.tabellaTaglie = tabellaTaglie;
	}

	public Integer getCodice() {
		return codice;
	}
	
	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	public String getCodiceRf() {
		return codiceRf;
	}

	public void setCodiceRf(String codiceRf) {
		this.codiceRf = codiceRf;
	}

	public Integer getCodiceFornitore() {
		return codiceFornitore;
	}

	public void setCodiceFornitore(Integer codiceFornitore) {
		this.codiceFornitore = codiceFornitore;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getStagione() {
		return stagione;
	}

	public void setStagione(String stagione) {
		this.stagione = stagione;
	}

	public String getTabellaTaglie() {
		return tabellaTaglie;
	}

	public void setTabellaTaglie(String tabellaTaglie) {
		this.tabellaTaglie = tabellaTaglie;
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
		Articolo other = (Articolo) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Articolo [codice=" + codice + ", codiceRf=" + codiceRf + ", codiceFornitore=" + codiceFornitore
				+ ", descrizione=" + descrizione + ", stagione=" + stagione + ", tabellaTaglie=" + tabellaTaglie + "]";
	}
	
	
}