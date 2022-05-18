package it.unirc.tesi.beans.articolo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import it.unirc.tesi.utils.DBManager;

public class ArticoloDao {
	
	private static Connection conn = null;
	
	public Articolo cerca(Integer codice) {
		String query = "SELECT * FROM articolo WHERE codice=?";
		conn = DBManager.startConnection();
		Articolo res = null;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, codice);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				res = new Articolo();
				res.setCodice(rs.getInt("codice"));
				res.setCodiceRf(rs.getString("codice_riferimento_fornitore"));
				res.setCodiceFornitore(rs.getInt("codice_fornitore"));
				res.setDescrizione(rs.getString("descrizione"));
				res.setStagione(rs.getString("stagione"));
				res.setTabellaTaglie(rs.getString("tabella_taglie"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return res;
	}
	
	/*query parametrica scalabile, utilizza un HashMap per rendere la query variabile nel numero e tipo di campi utilizzato per la ricerca,
	 * in base ai campi riempiti dall'utente nella gui, verrà effettuata la ricerca parametrizzata solo sui campi che contengono valori
	 */	
	public Vector<Articolo> get(Articolo art) {
		String query = "SELECT * FROM articolo WHERE 1=1";
		conn = DBManager.startConnection();
		Articolo res = null;
		Vector<Articolo> result = new Vector<Articolo>();
		/* Creo l'HashMap nel modo più generico possibile, utilizzando String per la chiave che riporta il nome dell'attributo della tabella
		 * del DB e come valore un generico Object 
		 */
		HashMap<String, Object> hm = new HashMap<String, Object>();
		// Setto tutti i possibili campi dell'HashMap
		hm.put("codice", art.getCodice());
		hm.put("codice_riferimento_fornitore", art.getCodiceRf());
		hm.put("codice_fornitore", art.getCodiceFornitore());
		hm.put("descrizione", art.getDescrizione());
		hm.put("stagione", art.getStagione());
		// questo contatore conta quanti parametri sono presenti all'interno della query
		int cont = 1;
		// Scorro l'HashMap e se il valore cossispondente ad un elemento non è nullo aggiungo il parametro alla query
		for(Entry<String, Object> e: hm.entrySet()) {			
			if(e.getValue() != null) {
				if(e.getKey().equals("descrizione") || e.getKey().equals("stagione") || e.getKey().equals("codice_riferimento_fornitore")) {
					query += " AND "+e.getKey()+" LIKE ?";
				}
				else {
					query += " AND "+e.getKey()+"=?";
				}
			}	
		}
		System.out.println(query);
		/* Preparo lo statement di SQL, scorro l'HashMap e se il valore contenuto in un elemento non è nullo lo setto come valore del suo
		 * corrispondente parametro
		 */
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			for(Entry<String, Object> e: hm.entrySet()) {
				if(e.getValue() != null) {
					if(e.getKey().equals("descrizione") || e.getKey().equals("stagione") || e.getKey().equals("codice_riferimento_fornitore")) {
						String s = "%"+e.getValue()+"%";
						ps.setString(cont, s);
					}
					else
						ps.setObject(cont, e.getValue());
					cont++;
				}
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				res = new Articolo();
				res.setCodice(rs.getInt("codice"));
				res.setCodiceRf(rs.getString("codice_riferimento_fornitore"));
				res.setCodiceFornitore(rs.getInt("codice_fornitore"));
				res.setDescrizione(rs.getString("descrizione"));
				res.setStagione(rs.getString("stagione"));
				res.setTabellaTaglie(rs.getString("tabella_taglie"));
				result.add(res);			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public int save(Articolo art) {
		String query = "INSERT INTO articolo VALUES (?, ?, ?, ?, ?, ?)";
		int esito = 0;
		conn = DBManager.startConnection();
		try {
			String [] generatedKey = new String [] { "codice" };
			PreparedStatement ps = conn.prepareStatement(query, generatedKey);
			ps.setInt(1, art.getCodice());
			ps.setString(2, art.getDescrizione());
			ps.setInt(3, art.getCodiceFornitore());
			ps.setString(4, art.getCodiceRf());
			ps.setString(5, art.getStagione());	
			ps.setString(6, art.getTabellaTaglie());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				esito = rs.getInt(1);
			}
			}catch (SQLException e) {
				e.printStackTrace();
		}
		DBManager.closeConnection();
		return esito;
	}
	
	public boolean update(Articolo art) {
		String query = "UPDATE articolo SET CODICE_RIFERIMENTO_FORNITORE = ?, codice_fornitore = ?, descrizione = ?,"
				+ "stagione = ?, tabella_taglie = ? WHERE codice = ?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(6, art.getCodice());
			ps.setString(1, art.getCodiceRf());
			ps.setInt(2, art.getCodiceFornitore());
			ps.setString(3, art.getDescrizione());
			ps.setString(4, art.getStagione());
			ps.setString(5, art.getTabellaTaglie());
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
	
	public boolean delete(Articolo art) {
		String query = "DELETE FROM articolo WHERE codice=?";
		boolean esito = false;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, art.getCodice());
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
	
	public int getGiacenzaDeposito (int articolo, int deposito) {
		String query = "SELECT sum(giacenza) as giacenza FROM ean_presente_deposito WHERE articolo=? AND deposito=?";
		int result = 0;
		conn = DBManager.startConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, articolo);
			ps.setInt(2, deposito);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("giacenza");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
	
	public int succArticolo () {
		int result = 0;
		conn = DBManager.startConnection();
		try {
			CallableStatement cs = conn.prepareCall("{? = call succarticolo}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.execute();
			result = cs.getInt(1);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		DBManager.closeConnection();
		return result;
	}
}