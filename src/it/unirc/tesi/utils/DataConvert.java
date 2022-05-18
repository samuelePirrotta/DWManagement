package it.unirc.tesi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConvert {
	private static SimpleDateFormat formato;
	
	//Riceve una data in formato SQL yyyy/mm/gg e la converte in una stringa in formato dd/mm/yyyy
	public static String sqlToJava(Date data) {
		String result = null;
		formato = new SimpleDateFormat("yyyy/MM/dd");
		formato.setLenient(false);
		try {			
			String tmp = formato.format(data);
			Date d = formato.parse(tmp);
			formato = new SimpleDateFormat("dd/MM/yyyy");
			result = formato.format(d);			
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	//Riceve una stringa che rappresenta una data in formato dd/mm/yyyy e la converte in una data in formato SQL yyyy/mm/dd
	public static Date javaToSql(String data) {
		Date result = null;
		formato = new SimpleDateFormat("dd/MM/yyyy");
		formato.setLenient(false);
		try {
			result = formato.parse(data);
			String tmp = formato.format(result);
			result = formato.parse(tmp);
			formato = new SimpleDateFormat("yyyy/MM/dd");
			tmp = formato.format(result);
			result = formato.parse(tmp);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//Riceve una stringa che rappresenta una data in formato dd/mm/yyyy e la converte in una stringa che rappresenta una data in formato SQL yyyy/mm/dd
	public static String javaToStringSql(String data) {
		String result = null;
		Date d = null;
		formato = new SimpleDateFormat("dd/MM/yyyy");
		formato.setLenient(false);
		try {
			d = formato.parse(data);
			String tmp = formato.format(d);
			d = formato.parse(tmp);
			formato = new SimpleDateFormat("yyyy/MM/dd");
			result = formato.format(d);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// riceve una data in formato SQL "yyyy/mm/gg" e la converte in una striga
	public static String formattaData(Date date) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		String prova = formato.format(date);
		return prova;
	}

}
