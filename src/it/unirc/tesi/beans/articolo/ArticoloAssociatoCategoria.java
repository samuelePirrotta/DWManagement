package it.unirc.tesi.beans.articolo;

public class ArticoloAssociatoCategoria {
	
	private int articolo;
	private int idCategoria;
	private int idRaggruppamento;
	private int idReparto;
	
	public ArticoloAssociatoCategoria() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArticoloAssociatoCategoria(int articolo, int idCategoria, int idRaggruppamento, int idReparto) {
		super();
		this.articolo = articolo;
		this.idCategoria = idCategoria;
		this.idRaggruppamento = idRaggruppamento;
		this.idReparto = idReparto;
	}

	public int getArticolo() {
		return articolo;
	}

	public void setArticolo(int articolo) {
		this.articolo = articolo;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdRaggruppamento() {
		return idRaggruppamento;
	}

	public void setIdRaggruppamento(int idRaggruppamento) {
		this.idRaggruppamento = idRaggruppamento;
	}

	public int getIdReparto() {
		return idReparto;
	}

	public void setIdReparto(int idReparto) {
		this.idReparto = idReparto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articolo;
		result = prime * result + idCategoria;
		result = prime * result + idRaggruppamento;
		result = prime * result + idReparto;
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
		ArticoloAssociatoCategoria other = (ArticoloAssociatoCategoria) obj;
		if (articolo != other.articolo)
			return false;
		if (idCategoria != other.idCategoria)
			return false;
		if (idRaggruppamento != other.idRaggruppamento)
			return false;
		if (idReparto != other.idReparto)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticoloAssociatoCategoria [articolo=" + articolo + ", idCategoria=" + idCategoria
				+ ", idRaggruppamento=" + idRaggruppamento + ", idReparto=" + idReparto + "]";
	}
}