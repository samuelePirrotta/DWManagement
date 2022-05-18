package it.unirc.tesi.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.tesi.beans.articolo.Ean;
import it.unirc.tesi.beans.articolo.EanDao;

/**
 * Servlet implementation class AggiungiEan
 */
@WebServlet("/AggiungiEan")
public class AggiungiEan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiEan() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ean = request.getParameter("ean").split(",");
		String[] taglia = request.getParameter("taglia").split(",");
		String[] colore = request.getParameter("colore").split(",");
		String tabella = request.getParameter("tabellaTaglie");
		Integer articolo = Integer.parseInt(request.getParameter("codice"));
		String[] costo = request.getParameter("costo").split(",");
		String[] ricarico = request.getParameter("ricarico").split(",");
		int cont = 0;
		
		for(int i=0; i<ean.length; i++) {
			Ean e = new Ean();
			e.setEan(ean[i]);
			e.setTaglia(taglia[i]);
			e.setColore(colore[i]);
			e.setTabellaTaglie(tabella);
			e.setArticolo(articolo);
			e.setCosto(Double.parseDouble(costo[i]));
			e.setRicarico(Double.parseDouble(ricarico[i]));
						
			EanDao eDao = new EanDao(); 
			boolean esito = eDao.save(e);
			eDao.aggiungiEanMagazzino(ean[i]);
			if(esito) {
				cont++;
			}
		}
		System.out.println(cont);
		System.out.println(ean.length);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		if(cont == ean.length)
			response.getWriter().write("true");
		else 
			response.sendRedirect("/erroreGenerico.html");
	}
}