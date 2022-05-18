package it.unirc.tesi.beans.magazzino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class EanPresenteDepositoDao {
	
	private static Connection conn = null;
	
	//prende tutti gli ean presenti in un determinato deposito
	public Vector<EanPresenteDeposito> getForDeposito(int deposito) {
		String query = "SELECT * FROM ean_presente_deposito WHERE deposito=?";
		EanPresenteDeposito res = null;
		Vector<EanPresenteDeposito> result = new Vector<EanPresenteDeposito>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, deposito);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new EanPresenteDeposito();
				res.setEan(rs.getString("ean"));
				res.setDeposito(rs.getInt("deposito"));
				res.setGiacenza(rs.getInt("giacenza"));
				res.setArticolo(rs.getInt("articolo"));
				result.add(res);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	//prende tutti gli articoli che hanno un ean con giacenza diversa da zero in un determinato deposito
	public Vector<EanPresenteDeposito> getGiacenzeForDeposito(int deposito) {
		String query = "SELECT * FROM ean_presente_deposito WHERE deposito=?";
		EanPresenteDeposito res = null;
		Vector<EanPresenteDeposito> result = new Vector<EanPresenteDeposito>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, deposito);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int g = rs.getInt("giacenza");
				if(g != 0) {
					res = new EanPresenteDeposito();
					res.setEan(rs.getString("ean"));
					res.setDeposito(rs.getInt("deposito"));
					res.setGiacenza(g);
					res.setArticolo(rs.getInt("articolo"));
					result.add(res);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	//prende gli articoli presenti in tutta l'anagrafica
	public Vector<EanPresenteDeposito> getAll() {
		String query = "SELECT * FROM ean_presente_deposito";
		EanPresenteDeposito res = null;
		Vector<EanPresenteDeposito> result = new Vector<EanPresenteDeposito>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new EanPresenteDeposito();
				res.setEan(rs.getString("ean"));
				res.setDeposito(rs.getInt("deposito"));
				res.setGiacenza(rs.getInt("giacenza"));
				res.setArticolo(rs.getInt("articolo"));
				result.add(res);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	//prende gli articoli con un ean con giacenza diversa da zero presenti in anagrafica
	public Vector<EanPresenteDeposito> getAllGiacenze() {
		String query = "SELECT * FROM ean_presente_deposito";
		EanPresenteDeposito res = null;
		Vector<EanPresenteDeposito> result = new Vector<EanPresenteDeposito>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int g = rs.getInt("giacenza");
				if(g > 0) {
					res = new EanPresenteDeposito();
					res.setEan(rs.getString("ean"));
					res.setDeposito(rs.getInt("deposito"));
					res.setGiacenza(g);
					res.setArticolo(rs.getInt("articolo"));
					result.add(res);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	//restituisce tutti gli ean con giacenza diversa da zero di un articolo in un deposito
	public Vector<EanPresenteDeposito> getGiacenzeArticoliForDeposito(int deposito, int art) {
		String query = "SELECT * FROM ean_presente_deposito WHERE articolo=? AND deposito=?";
		EanPresenteDeposito res = null;
		Vector<EanPresenteDeposito> result = new Vector<EanPresenteDeposito>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, art);
			ps.setInt(2, deposito);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int g = rs.getInt("giacenza");
				if(g != 0) {
					res = new EanPresenteDeposito();
					res.setEan(rs.getString("ean"));
					res.setDeposito(rs.getInt("deposito"));
					res.setGiacenza(g);
					res.setArticolo(rs.getInt("articolo"));
					result.add(res);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	//restituisce tutti gli ean associati ad un articolo in un deposito
	public Vector<EanPresenteDeposito> getArticoliForDeposito(int deposito, int art) {
		String query = "SELECT * FROM ean_presente_deposito WHERE articolo=? AND deposito=?";
		EanPresenteDeposito res = null;
		Vector<EanPresenteDeposito> result = new Vector<EanPresenteDeposito>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, art);
			ps.setInt(2, deposito);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new EanPresenteDeposito();
				res.setEan(rs.getString("ean"));
				res.setDeposito(rs.getInt("deposito"));
				res.setGiacenza(rs.getInt("giacenza"));
				res.setArticolo(rs.getInt("articolo"));
				result.add(res);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
		
	public boolean erase (String e) {
		String query = "DELETE FROM ean_presente_deposito WHERE ean=?";
		boolean esito = false;
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, e);
			int tmp = ps.executeUpdate();
			if(tmp > 0) 
				esito = true;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
}