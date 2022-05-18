package it.unirc.tesi.beans.fornitore;

public class Fornitore {
	
	private Integer codice;
	private String ragioneSociale;
	private String indirizzo;
	private String partitaIVA;
	private String note;
	
	public Fornitore () {
		super();
		this.codice = null;
		this.ragioneSociale = null;
		this.indirizzo = null;
		this.partitaIVA = null;
		this.note = null;
	}
	
	public Fornitore(String ragioneSociale, String indirizzo, String partitaIVA) {
		this.codice = null;
		this.ragioneSociale = ragioneSociale;
		this.indirizzo = indirizzo;
		this.partitaIVA = partitaIVA;
	}

	public Integer getCodice() {
		return codice;
	}

	public void setCodice(Integer codice) {
		this.codice = codice;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getPartitaIVA() {
		return partitaIVA;
	}

	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
		Fornitore other = (Fornitore) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fornitore [codice=" + codice + ", ragioneSociale=" + ragioneSociale + ", indirizzo=" + indirizzo
				+ ", partitaIVA=" + partitaIVA + ", note=" + note + "]";
	}
}