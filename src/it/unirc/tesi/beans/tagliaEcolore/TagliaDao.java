package it.unirc.tesi.beans.tagliaEcolore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class TagliaDao {
	
	private static Connection conn = null;
	
	public Vector<Taglia> visualizzaTabella (String tabella) {
		String query = "SELECT * FROM taglia where tabella_taglie=?";
		Taglia res = null;
		Vector<Taglia> result = new Vector<Taglia>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tabella);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new Taglia();
				res.setNome(rs.getString("nome"));
				res.setTabella(rs.getString("tabella_taglie"));
				result.add(res);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public Taglia get(String codice) {
		String query = "SELECT * FROM taglia WHERE nome=?";
		Taglia res = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, codice);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res = new Taglia();
				res.setNome(rs.getString("nome"));
				res.setTabella(rs.getString("tabella_taglie"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public boolean save(Taglia t) {
		String query = "INSERT INTO taglia VALUES(?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, t.getNome());
			ps.setString(2, t.getTabella());
			int tmp = ps.executeUpdate();
			if(tmp == 1)
				esito = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean erase(Taglia t) {
		String query = "DELETE FROM taglia WHERE nome=? AND tabella_taglie=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, t.getNome());
			ps.setString(2, t.getTabella());
			int tmp = ps.executeUpdate();
			if(tmp == 1)
				esito = true;
		}catch(SQLException e) {
		e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
}