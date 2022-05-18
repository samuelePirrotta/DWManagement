package it.unirc.tesi.beans.movimenti;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import it.unirc.tesi.utils.DBManager;
import it.unirc.tesi.utils.DataConvert;

public class MovimentoDao{
	
	private static Connection conn = null;
	
	public LinkedList<Movimento> getWanted(String [] value) {
		String query = "SELECT * FROM movimento WHERE 1=1";
		conn = DBManager.startConnection();
		Movimento res = null;
		LinkedList<Movimento> result = new LinkedList<Movimento>();
		int cont = 0;
		try {			
			if(value[0] != null) {
				query += " AND numero = ?";
				cont++;
			}
			if(value[1] != null) {
				query += " AND tipologia = ?";
				cont++;
			}
			if(value[2] != null) {
				query += " AND data_carico >= ?";
				cont++;
			}
			if(value[3] != null) {
				query += " AND data_carico <= ?";
				cont++;
			}
			PreparedStatement ps = conn.prepareStatement(query);
			int j = 0;
			for(int i=1; i<=cont; i++) {
				while(value[j] == null) {
					j++;
				}
				ps.setObject(i, value[j]);
				j++;
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res = new Movimento();
				res.setId(rs.getInt("id"));
				res.setNumero(rs.getInt("numero"));
				res.setDataCarico(rs.getDate("data_carico"));
				res.setDataDocumento(rs.getDate("data_documento"));
				res.setTipologia(rs.getString("tipologia"));
				res.setTotaleDocumento(rs.getDouble("totale"));
				res.setSconto(rs.getInt("sconto"));
				result.add(res);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;	
	}
	
	public Movimento get(Movimento mov) {
		String query = "SELECT * FROM movimento WHERE numero=? AND tipologia=?";
		Movimento res = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, mov.getNumero());
			ps.setString(2, mov.getTipologia());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res = new Movimento();
				res.setId(rs.getInt("id"));
				res.setNumero(rs.getInt("numero"));
				res.setDataCarico(rs.getDate("data_carico"));
				res.setDataDocumento(rs.getDate("data_documento"));
				res.setTotaleDocumento(rs.getDouble("totale"));
				res.setSconto(rs.getInt("sconto"));
				res.setTipologia(rs.getString("tipologia"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
		
	public boolean save(Movimento mov) {
		TipologiaMovimentoDao tmDao = new TipologiaMovimentoDao();
		String tipologia = mov.getTipologia();
		TipologiaMovimento tm = tmDao.get(tipologia);
		String query = "INSERT INTO movimento VALUES (?, ?, ?, ?, ?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			String [] generatedKey = new String [] { "id" };
			PreparedStatement ps = conn.prepareStatement(query, generatedKey);
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setObject(2, DataConvert.formattaData(mov.getDataCarico()));
			ps.setObject(3, DataConvert.formattaData(mov.getDataDocumento()));			
			ps.setDouble(4, mov.getTotaleDocumento());
			ps.setInt(5, mov.getSconto());
			ps.setString(6, mov.getTipologia());
			//se il movimento è di una tipologia che si autoincrementa, aggiungo 1 al contatore e aggiorno la tipologia
			if(tm.isAutomatico()) {
				int n = tmDao.incrementaNumero(tipologia);
				ps.setInt(4, n);
			}
			else {
				ps.setInt(4, mov.getNumero());
			}
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
	
	public boolean update(Movimento mov) {
		String query = "UPDATE movimento SET numero=?, data_carico=?, data_documento=?, tipologia=?, totale=?, sconto=? WHERE id=?";
		boolean esito = false;		
		conn = DBManager.startConnection();		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(7, mov.getId());
			ps.setInt(1, mov.getNumero());
			ps.setObject(2, DataConvert.formattaData(mov.getDataCarico()));
			ps.setObject(3, DataConvert.formattaData(mov.getDataDocumento()));
			ps.setString(4, mov.getTipologia());
			ps.setDouble(5, mov.getTotaleDocumento());
			ps.setInt(6, mov.getSconto());
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
	
	public boolean erase(Movimento mov) {
		String query = "DELETE FROM movimento WHERE numero=? AND tipologia=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, mov.getNumero());
			ps.setString(2, mov.getTipologia());
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
		


}
