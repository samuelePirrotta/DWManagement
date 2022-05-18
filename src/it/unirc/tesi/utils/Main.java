package it.unirc.tesi.utils;

import java.util.Vector;

import it.unirc.tesi.beans.articolo.Stagione;
import it.unirc.tesi.beans.articolo.StagioneDao;



public class Main {
	public static void main(String[] args) {
		
		StagioneDao sDao = new StagioneDao();
		Vector<Stagione> st = sDao.getAll();
		for (Stagione s: st) {
			System.out.println(s.toString());
		}
	}	
}