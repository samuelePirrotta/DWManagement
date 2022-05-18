package it.unirc.tesi.beans.utente;

import java.util.Vector;

public class Utente {
	private String username;
	private String password;
	private String categoria;
	private String reparto;
	private Vector<Integer> depositi;
	
	public Utente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Utente(String username, String password, String categoria, Vector<Integer> depositi, String reparto) {
		super();
		this.username = username;
		this.password = password;
		this.categoria = categoria;
		this.depositi = depositi;
		this.reparto = reparto;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
		
	public String getReparto() {
		return reparto;
	}

	public void setReparto(String reparto) {
		this.reparto = reparto;
	}

	public Vector<Integer> getDepositi() {
		return depositi;
	}
	
	public void setDepositi(Vector<Integer> depositi) {
		this.depositi = depositi;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Utente other = (Utente) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + ", password=" + password + ", categoria=" + categoria + ", reparto="
				+ reparto + ", depositi=" + depositi + "]";
	}
}