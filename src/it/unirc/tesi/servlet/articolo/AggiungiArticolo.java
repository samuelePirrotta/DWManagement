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
 * Servlet implementation class AggiungiArticolo
 */
@WebServlet("/AggiungiArticolo")
public class AggiungiArticolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiArticolo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer codice = Integer.parseInt(request.getParameter("codice"));
		String descrizione = request.getParameter("descrizione");
		String codiceRf = request.getParameter("codiceRF");
		Integer codiceFornitore = Integer.parseInt(request.getParameter("fornitore"));
		String stagione = request.getParameter("stagione");
		String tabellaTaglie = request.getParameter("tabellaTaglie");
		Integer categoria = Integer.parseInt(request.getParameter("categoria"));
		Integer raggruppamento = Integer.parseInt(request.getParameter("raggruppamento"));
		Integer reparto = Integer.parseInt(request.getParameter("reparto"));
		
		Articolo a = new Articolo();
		a.setCodice(codice);
		a.setCodiceRf(codiceRf);
		a.setCodiceFornitore(codiceFornitore);
		a.setDescrizione(descrizione);
		a.setStagione(stagione);
		a.setTabellaTaglie(tabellaTaglie);
		
		ArticoloAssociatoCategoria ac = new ArticoloAssociatoCategoria();
		ac.setArticolo(codice);
		ac.setIdCategoria(categoria);
		ac.setIdRaggruppamento(raggruppamento);
		ac.setIdReparto(reparto);		
			
		ArticoloDao aDao = new ArticoloDao();
		int esito = aDao.save(a);
		
		ArticoloAssociatoCategoriaDao acDao = new ArticoloAssociatoCategoriaDao();
		boolean result = acDao.save(ac);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		if(esito>0 && result)
			response.getWriter().write("true");
		else 
			response.sendRedirect("/erroreGenerico.html");
	}
}