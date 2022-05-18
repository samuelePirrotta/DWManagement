package it.unirc.tesi.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.tesi.beans.articolo.Articolo;
import it.unirc.tesi.beans.articolo.ArticoloAssociatoCategoria;
import it.unirc.tesi.beans.articolo.ArticoloAssociatoCategoriaDao;
import it.unirc.tesi.beans.articolo.ArticoloDao;

/**
 * Servlet implementation class ModificaArticolo
 */
@WebServlet("/ModificaArticolo")
public class ModificaArticolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaArticolo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer codice = Integer.parseInt(request.getParameter("codice"));
		String descrizione = request.getParameter("descrizione");
		Integer codiceFornitore = Integer.parseInt(request.getParameter("fornitore"));
		String codiceRf =  request.getParameter("codiceRf");
		String stagione = request.getParameter("stagione");
		String tabellaTaglie = request.getParameter("tabellaTaglie");
		Integer categoria = Integer.parseInt(request.getParameter("categoria"));
		Integer raggruppamento = Integer.parseInt(request.getParameter("raggruppamento"));
		Integer reparto = Integer.parseInt(request.getParameter("reparto"));
		
		Articolo a = new Articolo();
		a.setCodice(codice);
		a.setDescrizione(descrizione);
		a.setCodiceFornitore(codiceFornitore);
		a.setCodiceRf(codiceRf);
		a.setStagione(stagione);
		a.setTabellaTaglie(tabellaTaglie);
		
		ArticoloAssociatoCategoria ac = new ArticoloAssociatoCategoria();
		ac.setArticolo(codice);
		ac.setIdCategoria(categoria);
		ac.setIdRaggruppamento(raggruppamento);
		ac.setIdReparto(reparto);
			
		ArticoloDao aDao = new ArticoloDao();
		boolean b = aDao.update(a);
		
		ArticoloAssociatoCategoriaDao acDao = new ArticoloAssociatoCategoriaDao();
		boolean c = acDao.update(ac);
		
		if(b && c)
			response.sendRedirect("/RichiediModificaArticolo?codice="+codice);
		else 
			response.sendRedirect("/erroreGenerico.html");
	}
}