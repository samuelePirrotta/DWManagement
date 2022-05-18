package it.unirc.tesi.beans.movimenti;

import java.util.Date;

public class Movimento {
	
	private Integer id;
	private int numero;
	private Date dataCarico;
	private Date dataDocumento;
	private Double totaleDocumento;
	private int sconto;
	private String tipologia;
	
	public Movimento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movimento(Date dataCarico, Date dataDocumento, int numero, String tipologia) {
		super();
		this.dataCarico = dataCarico;
		this.dataDocumento = dataDocumento;
		this.tipologia = tipologia;
		TipologiaMovimentoDao tmDao = new TipologiaMovimentoDao();
		TipologiaMovimento tm = new TipologiaMovimento();
		tm = tmDao.get(tipologia);
		if(tm.isAutomatico()) {
			this.numero = tm.getContatore()+1;
			tm.setContatore(this.numero);
		}
		else {
			this.numero = numero;
		}		
	}

	public Date getDataCarico() {
		return dataCarico;
	}

	public void setDataCarico(Date dataCarico) {
		this.dataCarico = dataCarico;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public Double getTotaleDocumento() {
		return totaleDocumento;
	}

	public void setTotaleDocumento(Double totaleDocumento) {
		this.totaleDocumento = totaleDocumento;
	}

	public int getSconto() {
		return sconto;
	}

	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		Movimento other = (Movimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movimento [dataCarico=" + dataCarico + ", dataDocumento=" + dataDocumento + ", numero=" + numero
				+ ", totaleDocumento=" + totaleDocumento + ", sconto=" + sconto + ", tipologia=" + tipologia + "]";
	}	
}