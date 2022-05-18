package it.unirc.tesi.beans.articolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class StagioneDao {
	private static Connection conn = null;
	
	//visualizza tutte le stagioni
	public Vector<Stagione> getAll() {
		String query = "SELECT * FROM stagione WHERE 1=1";
		Stagione res = null;
		Vector<Stagione> result = new Vector<Stagione> ();
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res = new Stagione();
				res.setId(rs.getString("id"));
				res.setNome(rs.getString("nome"));
				result.add(res);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public Stagione get(String codice) {
		String query = "SELECT * FROM stagione WHERE id = ?";
		Stagione res = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, codice);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res = new Stagione();
				res.setId(rs.getString("id"));
				res.setNome(rs.getString("nome"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	//create tutto l'oggetto in ingresso
	public boolean save(Stagione s){
		String query = "INSERT INTO stagione VALUES(?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, s.getId());
			ps.setString(2, s.getNome());
			int tmp = ps.executeUpdate();
			if(tmp==1)
				esito = true;
		}catch(SQLException e){
			e.printStackTrace();
		}		
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean update(Stagione st) {
		String query = "UPDATE stagione SET nome=? WHERE id=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, st.getNome());
			ps.setString(2, st.getId());
			int tmp = ps.executeUpdate();
			if(tmp == 1) 
				esito = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean erase(String id){
		String query = "DELETE FROM stagione WHERE id=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			int temp= ps.executeUpdate();
			if(temp==1) 
				esito = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
}