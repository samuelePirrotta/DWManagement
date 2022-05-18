package it.unirc.tesi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static Connection conn = null;
	private static final String DbDriver = "oracle.jdbc.driver.OracleDriver";	

	/*---------------------------------------------AWS CONNECTION-----------------------------------------------------*/
	/*
	private static final String DbURL = "jdbc:oracle:thin:@tesidb.cyd3t25mnkd9.eu-south-1.rds.amazonaws.com:1521:ORCL";
	private static final String username = "admin";
	private static final String password = "3751963192";
	*/
	/*---------------------------------------------LOCALHOST CONNECTION-----------------------------------------------------*/
	
	private static final String DbURL = "jdbc:oracle:thin:@localhost:1521:GlobalDB";
	private static final String username = "DWMANAGER";
	private static final String password = "admin";
	

	private DBManager() {}

	/**
	 * Metododo che restituisce true se la connessione è aperta.
	 */
	public static boolean isOpen() {
		// if (conn == null)
		// return false;
		// else
		// return true;
		return (conn != null);
	}

	public static Connection startConnection() {
		if (isOpen())
			return conn;
		try {
			Class.forName(DbDriver);// Carica il Driver del DBMS
			conn = DriverManager.getConnection(DbURL, username, password);// Apertura connessione
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	public static boolean closeConnection() {
		if (!isOpen())
			return true;
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
