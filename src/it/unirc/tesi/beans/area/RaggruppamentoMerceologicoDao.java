package it.unirc.tesi.beans.area;
	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class RaggruppamentoMerceologicoDao {
	
	private static Connection conn = null;
	
	public Vector<RaggruppamentoMerceologico> getForReparto(int rp) {
		String query = "SELECT * FROM raggruppamento_merceologico WHERE reparto=?";
		RaggruppamentoMerceologico res = null;
		Vector<RaggruppamentoMerceologico> result = new Vector<RaggruppamentoMerceologico>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, rp);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new RaggruppamentoMerceologico();
				res.setId(rs.getInt("id"));
				res.setNome(rs.getString("nome"));
				res.setReparto(rs.getInt("reparto"));
				result.add(res);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public Vector<RaggruppamentoMerceologico> getAll() {
		String query = "SELECT * FROM raggruppamento_merceologico";
		RaggruppamentoMerceologico res = null;
		Vector<RaggruppamentoMerceologico> result = new Vector<RaggruppamentoMerceologico>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new RaggruppamentoMerceologico();
				res.setId(rs.getInt("id"));
				res.setNome(rs.getString("Nome"));
				res.setReparto(rs.getInt("reparto"));
				result.add(res);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public RaggruppamentoMerceologico get(String nome){
		String query = "SELECT * FROM raggruppamento_merceologico WHERE nome=?";
		RaggruppamentoMerceologico r = null;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				r = new RaggruppamentoMerceologico();
				r.setNome(rs.getString("nome"));
				r.setId(rs.getInt("id"));
				r.setReparto(rs.getInt("reparto"));; 
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return r;
	}
	
	public boolean save(RaggruppamentoMerceologico rm){
		String query = "INSERT INTO raggruppamento_merceologico VALUES(?, ?, ?)";
		boolean esito = true;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setString(2, rm.getNome());
			ps.setInt(3, rm.getReparto());
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
	
	public boolean update(RaggruppamentoMerceologico rm){
		String query = "UPDATE raggruppamento_merceologico SET nome=?, reparto=? WHERE id=?";
		boolean esito= true;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, rm.getNome());
			ps.setInt(2, rm.getReparto());
			ps.setInt(3, rm.getId());
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
	
	public boolean erase(int id){
		String query = "DELETE FROM raggruppamento_merceologico WHERE id=?";
		boolean esito= true;
		conn = DBManager.startConnection();
		try{
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				esito = true;				
			}
		}catch(SQLException e){
			
		}
		DBManager.closeConnection();
		return esito;
	}
}