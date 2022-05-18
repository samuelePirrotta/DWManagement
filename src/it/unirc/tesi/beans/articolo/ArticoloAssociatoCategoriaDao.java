package it.unirc.tesi.beans.articolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unirc.tesi.utils.DBManager;

public class ArticoloAssociatoCategoriaDao {
	
	private static Connection conn = null;
	
	public ArticoloAssociatoCategoria get(int codiceArticolo) {
		String query = "SELECT * FROM articolo_associato_categoria WHERE articolo=?";
		ArticoloAssociatoCategoria res = null;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, codiceArticolo);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				res = new ArticoloAssociatoCategoria();
				res.setArticolo(rs.getInt("articolo"));
				res.setIdCategoria(rs.getInt("id_categoria"));
				res.setIdRaggruppamento(rs.getInt("id_raggruppamento"));
				res.setIdReparto(rs.getInt("id_reparto"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	public boolean save(ArticoloAssociatoCategoria a) {
		String query = "INSERT INTO articolo_associato_categoria VALUES(?, ?, ?, ?)";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, a.getArticolo());
			ps.setInt(2, a.getIdCategoria());
			ps.setInt(3, a.getIdRaggruppamento());
			ps.setInt(4, a.getIdReparto());
			int tmp = ps.executeUpdate();
			if (tmp > 0) {
				esito = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean update(ArticoloAssociatoCategoria a) {
		String query = "UPDATE articolo_associato_categoria SET id_categoria=?, id_raggruppamento=?, id_reparto=? WHERE articolo=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(4, a.getArticolo());
			ps.setInt(1, a.getIdCategoria());
			ps.setInt(2, a.getIdRaggruppamento());
			ps.setInt(3, a.getIdReparto());
			int tmp = ps.executeUpdate();
			if (tmp > 0) {
				esito = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
}