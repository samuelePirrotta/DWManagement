package it.unirc.tesi.beans.magazzino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class DepositoDao {
	
	private static Connection conn = null;
	
	public Vector<Deposito> getAll() {
		String query = "SELECT * FROM deposito";
		Deposito res = null;
		Vector<Deposito> result = new Vector<Deposito>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new Deposito();
				res.setCodice(rs.getInt("codice"));
				res.setNome(rs.getString("nome"));
				res.setIndirizzo(rs.getString("indirizzo"));
				result.add(res);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public Deposito get(Deposito dep) {
		String query = "SELECT * FROM deposito WHERE codice=? OR nome=?";
		Deposito res = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, dep.getCodice());
			ps.setString(2, dep.getNome());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res = new Deposito();
				res.setCodice(rs.getInt("codice"));
				res.setNome(rs.getString("nome"));
				res.setIndirizzo(rs.getString("indirizzo"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public boolean save(Deposito dep) {
		String query = "INSERT INTO deposito VALUES (?, ?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setString(2,  dep.getNome());
			ps.setString(3, dep.getIndirizzo());
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
	
	public boolean update(Deposito dep) {
		String query = "UPDATE deposito SET  nome = ?, indirizzo = ? WHERE codice = ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(3, dep.getCodice());
			ps.setString(1, dep.getNome());
			ps.setString(2, dep.getIndirizzo());		
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
	
	public boolean erase(int codice) {
		String query = "DELETE FROM deposito WHERE codice = ?";
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