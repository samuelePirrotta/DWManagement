package it.unirc.tesi.servlet.articolo;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.tesi.beans.articolo.Articolo;
import it.unirc.tesi.beans.articolo.ArticoloDao;
import it.unirc.tesi.beans.fornitore.Fornitore;
import it.unirc.tesi.beans.fornitore.FornitoreDao;

/**
 * Servlet implementation class CercaArticolo
 */
@WebServlet("/CercaArticolo")
public class CercaArticolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CercaArticolo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String c = request.getParameter("codice");
		String d = request.getParameter("descrizione");
		String cf = request.getParameter("codiceFornitore");
		String crf = request.getParameter("codiceRF");
		String s = request.getParameter("stagione");

		Articolo a = new Articolo();
		Fornitore f;
		
		if(c != "")
			a.setCodice(Integer.parseInt(c));
		if(d != "")
			a.setDescrizione(d);
		if(cf != "")
			a.setCodiceFornitore(Integer.parseInt(cf));
		if(crf != "")
			a.setCodiceRf(crf);
		if(s != "")
			a.setStagione(s);	
		
		ArticoloDao aDao = new ArticoloDao();
		Vector<Articolo> articoli = aDao.get(a);
		FornitoreDao fDao = new FornitoreDao();
		Vector<Fornitore> fornitori = new Vector<Fornitore>();
		Vector<Integer> giacenze = new Vector<Integer>();
		for(Articolo iterator: articoli) {
			int codiceFornitore = iterator.getCodiceFornitore();
			f = new Fornitore();
			f.setCodice(codiceFornitore);
			f.setRagioneSociale(fDao.getRagioneSociale(codiceFornitore));	
			fornitori.add(f);
			giacenze.add(aDao.getGiacenzaDeposito(iterator.getCodice(), 1));
		}
		
		request.setAttribute("articoli", articoli);
		request.setAttribute("fornitori", fornitori);
		request.setAttribute("giacenze", giacenze);
		request.getRequestDispatcher("/anagrafica/articolo/cercaArticolo.jsp").forward(request, response);		
	}
}