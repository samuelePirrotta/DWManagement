package it.unirc.tesi.beans.area;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class RepartoDao {
	
	private static Connection conn = null;
	
	public Vector<Reparto> getAll() {
		String query = "SELECT * FROM reparto";
		Reparto res = null;
		Vector<Reparto> result = new Vector<Reparto>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new Reparto();
				res.setId(rs.getInt("id"));
				res.setNome(rs.getString("nome"));
				result.add(res);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public Reparto get(String nome){
		String query = "SELECT * FROM reparto WHERE nome=?";
		Reparto res = null;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res = new Reparto();
				res.setId(rs.getInt("id"));
				res.setNome(rs.getString("nome"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public boolean save(Reparto rep){
		String query = "INSERT INTO reparto VALUES(?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setString(2, rep.getNome());
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				esito = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean update(Reparto rp){
		String query = "UPDATE reparto SET nome=? WHERE id=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, rp.getNome());
			ps.setInt(2, rp.getId());
			int tmp = ps.executeUpdate();   
			if(tmp == 1) {
				esito = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean erase(String nome){
		String query = "DELETE FROM reparto WHERE nome=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, nome);
			int tmp = ps.executeUpdate();
			if(tmp ==1) {
				esito = true;
			}			
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
}
