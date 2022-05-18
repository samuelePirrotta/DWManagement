package it.unirc.tesi.beans.movimenti;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class TipologiaMovimentoDao {
	
	private static Connection conn = null;
	
	public Vector<TipologiaMovimento> getAll() {
		String query = "SELECT * FROM tipologia_movimento";
		TipologiaMovimento res = null;
		Vector<TipologiaMovimento> result = new Vector<TipologiaMovimento>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new TipologiaMovimento();
				res.setCodice(rs.getString("codice"));
				res.setNome(rs.getString("nome"));
				res.setCausale(rs.getString("causale"));
				res.setContatore(rs.getInt("contatore"));
				res.setAutomatico(rs.getBoolean("automatico"));
				result.add(res);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public TipologiaMovimento get(String codice) {
		String query = "SELECT * FROM tipologia_movimento WHERE codice = ?";
		TipologiaMovimento res = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, codice);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res = new TipologiaMovimento();
				res.setCodice(rs.getString("codice"));
				res.setNome(rs.getString("nome"));
				res.setCausale(rs.getString("causale"));
				res.setContatore(rs.getInt("contatore"));
				res.setAutomatico(rs.getBoolean("automatico"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public boolean save (TipologiaMovimento tipMov) {
		String query = "INSERT INTO tipologia_movimento VALUES (?, ?, ?, ?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tipMov.getCodice());
			ps.setString(2, tipMov.getNome());
			ps.setString(3, tipMov.getCausale());
			ps.setInt(4, tipMov.getContatore());
			ps.setBoolean(5, tipMov.isAutomatico());
			int tmp = ps.executeUpdate();
			if(tmp == 1) {
				esito = true;				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return esito;
	}
	
	public boolean update (TipologiaMovimento tipMov) {
		String query = "UPDATE tipologia_movimento SET nome=?, causale=?, automatico=? WHERE codice=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(4, tipMov.getCodice());
			ps.setString(1, tipMov.getNome());
			ps.setString(2, tipMov.getCausale());
			ps.setBoolean(3, tipMov.isAutomatico());
			int tmp = ps.executeUpdate();
			if(tmp == 1) {
				esito = true;				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return esito;
	}
	
	public boolean erase (String codice) {
		String query = "DELETE FROM tipologia_movimento WHERE codice=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, codice);
			int tmp = ps.executeUpdate();
			if(tmp == 1) {
				esito = true;				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return esito;
	}	
	
	public int incrementaNumero (String n) {
		int numero = 0;
		conn = DBManager.startConnection();
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call progressivotipologia(?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, n);
			cs.execute();
			numero = cs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return numero;
	}
}