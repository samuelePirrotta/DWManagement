package it.unirc.tesi.beans.tagliaEcolore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class TabellaTaglieDao {
	
	private static Connection conn = null;
	
	public Vector<TabellaTaglie> getAll() {
		String query = "SELECT * FROM tabella_taglie";
		TabellaTaglie res = null;
		Vector<TabellaTaglie> result = new Vector<TabellaTaglie>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new TabellaTaglie();
				res.setId(rs.getString("Id"));
				res.setNazione(rs.getString("nazione"));
				result.add(res);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public TabellaTaglie get(String nazione) {
		String query = "SELECT * FROM tabella_taglie WHERE nazione=?";
		TabellaTaglie res = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, nazione);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res = new TabellaTaglie();
				res.setId(rs.getString("Id"));
				res.setNazione(rs.getString("nazione"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public boolean save(TabellaTaglie tbTg) {
		String query = "INSERT INTO tabella_taglie VALUES(?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tbTg.getId());
			ps.setString(2, tbTg.getNazione());
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito = true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean update(TabellaTaglie tbTg) {
		String query = "UPDATE tabella_taglie SET nazione=? WHERE id=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tbTg.getNazione());
			ps.setString(2, tbTg.getId());
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean erase(String nazione) {
		String query = "DELETE FROM tabella_taglie WHERE nazione=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, nazione);
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;		
	}

}