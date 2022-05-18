package it.unirc.tesi.beans.articolo;

public class Ean {
	private String ean;
	private String taglia;
	private String colore;
	private String tabellaTaglie; //as tabella_taglie
	private int articolo;
	private double costo;
	private double ricarico;
	
	public Ean() {
		super();
		this.ean = null;
		this.taglia = null;
		this.colore = null;
		this.tabellaTaglie = null;
		this.articolo = 0;
		this.costo = 0.0;
		this.ricarico = 0.0;
	}

	public Ean(String taglia, String colore, String tabellaTaglie, int articolo, double costo,
			double ricarico) {
		super();
		this.ean = null;
		this.taglia = taglia;
		this.colore = colore;
		this.tabellaTaglie = tabellaTaglie;
		this.articolo = articolo;
		this.costo = costo;
		this.ricarico = ricarico;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getTaglia() {
		return taglia;
	}

	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getTabellaTaglie() {
		return tabellaTaglie;
	}

	public void setTabellaTaglie(String tabellaTaglie) {
		this.tabellaTaglie = tabellaTaglie;
	}

	public int getArticolo() {
		return articolo;
	}

	public void setArticolo(int articolo) {
		this.articolo = articolo;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getRicarico() {
		return ricarico;
	}

	public void setRicarico(double ricarico) {
		this.ricarico = ricarico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ean == null) ? 0 : ean.hashCode());
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
		Ean other = (Ean) obj;
		if (ean == null) {
			if (other.ean != null)
				return false;
		} else if (!ean.equals(other.ean))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ean [ean=" + ean + ", taglia=" + taglia + ", colore=" + colore + ", tabellaTaglie=" + tabellaTaglie
				+ ", articolo=" + articolo + ", costo=" + costo + ", ricarico=" + ricarico + "]";
	}	
	
	//algoritmo del calcolo del codice EAN, implementato tramite una funzione PL\SQL su Oracle
	/*public String calcolaEan(int a) {
		String pre = "8000";
		String b = Integer.toString(a);
		String articolo = "";
		if(b.length()==1)
			articolo = "00000"+b;
		else if(b.length()==2)
			articolo = "0000"+b;
		else if(b.length()==3)
			articolo = "000"+b;
		else if(b.length()==4)
			articolo = "00"+b;
		else if(b.length()==5)
			articolo = "0"+b;
		else 
			articolo = b;
		int c = (int) (Math.random()*((999-100)+100));
		int d = (int) (Math.random()*((999-100)+100));
		int e = (int) (Math.random()*((899-100)+100));
		int f = 0;
		if(c<100)
			if(c+d<100)
				f = c+d+e;
			else 
				f = c+d;
		else 
			f = c;
		String post = Integer.toString(f);
		System.out.println(post);
		String ean= pre+articolo+post;
		return ean;
	}*/
}