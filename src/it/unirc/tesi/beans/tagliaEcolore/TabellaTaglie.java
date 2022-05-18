package it.unirc.tesi.beans.tagliaEcolore;

public class TabellaTaglie {

	private String id;
	private String nazione;
	
	public TabellaTaglie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TabellaTaglie(String id, String nazione) {
		super();
		this.id = id;
		this.nazione = nazione;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
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
		TabellaTaglie other = (TabellaTaglie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TabellaTaglie [id=" + id + ", nazione=" + nazione + "]";
	}	
}