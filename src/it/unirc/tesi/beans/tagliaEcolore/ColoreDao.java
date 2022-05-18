package it.unirc.tesi.beans.tagliaEcolore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class ColoreDao {
	private static Connection conn= null;
	
	public Vector<Colore> getAll () {
		String query = "SELECT * FROM colore";
		Colore res = null;
		Vector<Colore> result = new Vector<Colore>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new Colore();
				res.setNome(rs.getString("nome"));
				result.add(res);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public Colore get(String nome){
		String query = "SELECT * FROM colore WHERE nome=?";
		Colore res = null;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				res = new Colore();
			res.setNome(rs.getString(nome));

		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	public boolean save(Colore colore){
		String query = "INSERT INTO colore VALUES(?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, colore.getNome());
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito = true;

		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;

	}

	public boolean update(String nome, String pre){
		String query = "UPDATE colore SET nome=? WHERE nome=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, nome);
			ps.setString(2, pre);
			int tmp = ps.executeUpdate();   
			if(tmp == 1)
				esito = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}

	public boolean erase(String nome){
		String query = "DELETE FROM colore WHERE nome=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, nome);
			int tmp = ps.executeUpdate();
			if(tmp ==1)
				esito = true;
		}catch(SQLException e) {
			e.printStackTrace();
		} 
		DBManager.closeConnection();
		return esito;
	}
}