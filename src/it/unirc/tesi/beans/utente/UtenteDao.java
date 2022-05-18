package it.unirc.tesi.beans.utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unirc.tesi.utils.DBManager;

public class UtenteDao {
	
	private static Connection conn;
	
	public Utente get(String user) {
		String query = "SELECT * FORM utente WHERE username=?";
		Utente u = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				u = new Utente();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setCategoria(rs.getString("categoria"));
				u.setReparto(rs.getString("reparto"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return u;
	}
	
	public boolean save(Utente u) {
		String query = "INSERT INTO utente VALUES(?, ?, ?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3,u.getReparto());
			ps.setString(4, u.getCategoria());
			int tmp = ps.executeUpdate();
			if(tmp > 0) {
				esito = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean update(Utente u) {
		String query = "UPDATE utente SET password=?, reparto=?, categoria=? WHERE username=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(4, u.getUsername());
			ps.setString(1, u.getPassword());
			ps.setString(2, u.getReparto());
			ps.setString(3, u.getCategoria());
			int tmp = ps.executeUpdate();
			if(tmp > 0) 
				esito = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean erase(String user) {
		String query = "DELETE FROM utente WHERE username=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user);
			int tmp = ps.executeUpdate();
			if(tmp > 0)
				esito = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean login(Utente u) {
        String query = "SELECT * FROM utente WHERE username=? and password=?";
        boolean res = false;
        PreparedStatement ps;
        conn = DBManager.startConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                res = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBManager.closeConnection();
        return res;
    }
}