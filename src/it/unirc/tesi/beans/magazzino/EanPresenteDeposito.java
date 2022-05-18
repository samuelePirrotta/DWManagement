package it.unirc.tesi.beans.magazzino;

public class EanPresenteDeposito {
	
	private String ean;
	private Integer deposito;
	private int giacenza;
	private int articolo;
	
	public EanPresenteDeposito() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EanPresenteDeposito(String ean, Integer deposito, int giacenza, int articolo) {
		super();
		this.ean = ean;
		this.deposito = deposito;
		this.giacenza = giacenza;
		this.articolo = articolo;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public Integer getDeposito() {
		return deposito;
	}

	public void setDeposito(Integer deposito) {
		this.deposito = deposito;
	}

	public int getGiacenza() {
		return giacenza;
	}

	public void setGiacenza(int giacenza) {
		this.giacenza = giacenza;
	}

	public int getArticolo() {
		return articolo;
	}

	public void setArticolo(int articolo) {
		this.articolo = articolo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articolo;
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
		EanPresenteDeposito other = (EanPresenteDeposito) obj;
		if (articolo != other.articolo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EanPresenteDeposito [ean=" + ean + ", deposito=" + deposito + ", giacenza=" + giacenza + ", articolo="
				+ articolo + "]";
	}
}