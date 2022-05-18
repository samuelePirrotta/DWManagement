package it.unirc.tesi.beans.fornitore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class FornitoreDao {
	
	private static Connection conn = null;
	
	public Vector<Fornitore> getAll() {
		String query = "SELECT * FROM fornitore WHERE 1=1";
		Fornitore res = null;
		Vector<Fornitore> result = new Vector<Fornitore>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new Fornitore();
				res.setCodice(rs.getInt("codice"));
				res.setRagioneSociale(rs.getString("ragione_sociale"));
				res.setIndirizzo(rs.getString("indirizzo"));
				res.setPartitaIVA(rs.getString("partita_iva"));
				res.setNote(rs.getString("note"));
				result.add(res);
			}			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;		
	}
	
	public String getRagioneSociale(int codice) {
		String query = "SELECT * FROM fornitore WHERE codice=?";
		String result = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, codice);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("ragione_sociale");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public Fornitore get(Fornitore forn) {
		String query = "SELECT * FROM fornitore WHERE codice = ?";
		Fornitore res = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, forn.getCodice());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res = new Fornitore();
				res.setCodice(rs.getInt("codice"));
				res.setRagioneSociale(rs.getString("ragione_sociale"));
				res.setIndirizzo(rs.getString("indirizzo"));
				res.setPartitaIVA(rs.getString("partita_IVA"));
				res.setNote(rs.getString("note"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public boolean save(Fornitore forn) {
		String query = "INSERT INTO fornitore VALUES (?, ?, ?, ?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setString(2, forn.getRagioneSociale());			
			ps.setString(3, forn.getPartitaIVA());
			ps.setString(4, forn.getIndirizzo());
			if(forn.getNote() != null)
				ps.setString(5, forn.getNote());
			else
				ps.setNull(5, java.sql.Types.VARCHAR);
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;					
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean update(Fornitore forn) {
		String query = "UPDATE fornitore SET ragione_sociale = ?, indirizzo = ?, partita_IVA = ?, note = ?"
				+ "WHERE codice = ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, forn.getRagioneSociale());
			ps.setString(2, forn.getIndirizzo());
			ps.setString(3, forn.getPartitaIVA());
			ps.setString(4, forn.getNote());
			ps.setInt(5, forn.getCodice());
			int tmp = ps.executeUpdate();
			if(tmp == 1) {
				esito = true;				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean delete(int codice) {
		String query = "DELETE FROM fornitore WHERE codice = ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, codice);
			int tmp = ps.executeUpdate();
			if (tmp == 1) {
				esito = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
}
