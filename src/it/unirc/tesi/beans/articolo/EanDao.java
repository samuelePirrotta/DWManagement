package it.unirc.tesi.beans.articolo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class EanDao {
	private static Connection conn = null;

	public Vector<Ean> get(int articolo){
		String query = "SELECT * FROM ean WHERE articolo=?";
		Ean res = null;
		Vector<Ean> result = new Vector<Ean>();
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				res = new Ean();
				res.setEan(rs.getString("ean"));
				res.setTaglia(rs.getString("taglia"));
				res.setColore(rs.getString("colore"));
				res.setTabellaTaglie(rs.getString("tabella_taglie"));
				res.setArticolo(rs.getInt("articolo"));
				res.setCosto(rs.getDouble("costo"));
				res.setRicarico(rs.getDouble("ricarico"));
				result.add(res);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} 
		DBManager.closeConnection();
		return result;
	}
	
	public boolean save(Ean ean){
		String query = "INSERT INTO ean VALUES(?, ?, ?, ?, ?, ?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, ean.getEan());
			ps.setString(2, ean.getTaglia());
			ps.setString(3, ean.getColore());
			ps.setString(4, ean.getTabellaTaglie());
			ps.setInt(5, ean.getArticolo());
			ps.setDouble(6, ean.getCosto());
			ps.setDouble(7, ean.getRicarico());
			int tmp = ps.executeUpdate(); 
			if(tmp == 1)
				esito = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean update(Ean ean){
		String query = "UPDATE ean SET taglia=?, colore=?, tabella_taglie=?, costo=?, ricarico=? WHERE ean=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(6, ean.getEan());
			ps.setString(1, ean.getTaglia());
			ps.setString(2, ean.getColore());
			ps.setString(3, ean.getTabellaTaglie());
			ps.setDouble(4, ean.getCosto());
			ps.setDouble(5, ean.getRicarico());
			int tmp = ps.executeUpdate();   
			if(tmp == 1)
				esito = true;
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean erase(String ean){
		String query = "DELETE FROM ean WHERE ean=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, ean);
			int tmp = ps.executeUpdate();
			if (tmp == 1)
				esito = true;
		}catch(SQLException e) {
			e.printStackTrace();
		} 
		DBManager.closeConnection();
		return esito;
	}
	
	public String newEan(int codice) {
		String ean = null;
		conn = DBManager.startConnection();
		try {
			CallableStatement cs = conn.prepareCall("{? = call NUOVOEAN(?)}");
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setInt(2, codice);
			cs.execute();
			ean = cs.getString(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return ean;
	}
	
	public boolean aggiungiEanMagazzino(String ean) {
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			CallableStatement cs = conn.prepareCall("{call AGGIUNGIEAN(?)}");
			cs.setString(1, ean);
			cs.execute();
			int tmp = cs.getUpdateCount();
			if(tmp > 0)
				esito = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public double calcolaPrezzo(String ean, String iva) {
		double prezzo = 0.0;
		conn = DBManager.startConnection();
		try {
			CallableStatement cs = conn.prepareCall("{call CALCOLAPREZZO(?, ?, ?)}");
			cs.setString(1, ean);
			cs.setString(2, iva);
			cs.registerOutParameter(3, Types.DOUBLE);
			cs.execute();
			prezzo = cs.getDouble(3);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return prezzo;
	}
}