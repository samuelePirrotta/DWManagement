package it.unirc.tesi.beans.area;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class CategoriaMerceologicaDao {
	
	private Connection conn = null;
	
	public Vector<CategoriaMerceologica> getForRaggruppamento(int id) {
		String query = "SELECT * FROM categoria_merceologica WHERE raggruppamento=?";
		CategoriaMerceologica res = null;
		Vector<CategoriaMerceologica> result = new Vector<CategoriaMerceologica>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new CategoriaMerceologica();
				res.setId(rs.getInt("id"));
				res.setNome(rs.getString("nome"));
				res.setRaggruppamento(rs.getInt("raggruppamento"));
				res.setRicarico(rs.getDouble("ricarico"));
				result.add(res);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public Vector<CategoriaMerceologica> getAll() {
		String query = "SELECT * FROM categoria_merceologica";
		CategoriaMerceologica res = null;
		Vector<CategoriaMerceologica> result = new Vector<CategoriaMerceologica>();
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new CategoriaMerceologica();
				res.setId(rs.getInt("id"));
				res.setNome(rs.getString("nome"));
				res.setRaggruppamento(rs.getInt("raggruppamento"));
				res.setRicarico(rs.getDouble("ricarico"));
				result.add(res);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}	
	
	public CategoriaMerceologica get(int id) {
		String query = "SELECT * FROM categoria_merceologica WHERE nome=?";
		CategoriaMerceologica res = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res = new CategoriaMerceologica();
				res.setId(rs.getInt("id"));
				res.setNome(rs.getString("nome"));
				res.setRaggruppamento(rs.getInt("raggruppamento"));
				res.setRicarico(rs.getDouble("ricarico"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}

	public boolean save(CategoriaMerceologica cm) {
		String query = "INSERT INTO categoria_merceologica VALUES(?, ?, ?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setString(2, cm.getNome());
			ps.setInt(3, cm.getRaggruppamento());
			if(cm.getRicarico()!=null)
				ps.setDouble(4, cm.getRicarico());
			else
				ps.setNull(4, java.sql.Types.INTEGER);
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				esito = true;				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean update(CategoriaMerceologica cm) {
		String query = "UPDATE categoria_merceologica SET nome=?, raggruppamento=?, ricarico=? WHERE id=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(4, cm.getId());
			ps.setString(1, cm.getNome());
			ps.setInt(2, cm.getRaggruppamento());
			if(cm.getRicarico()!=null)
				ps.setDouble(3, cm.getRicarico());
			else
				ps.setNull(3, java.sql.Types.INTEGER);
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				esito = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean erase(int id) {
		String query = "DELETE FROM categoria_merceologica WHERE id=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			int tmp = ps.executeUpdate();
			if(tmp==1) {
				esito = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
}