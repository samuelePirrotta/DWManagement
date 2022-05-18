package it.unirc.tesi.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.tesi.beans.articolo.Articolo;
import it.unirc.tesi.beans.articolo.ArticoloDao;

/**
 * Servlet implementation class EliminaArticolo
 */
@WebServlet("/EliminaArticolo")
public class EliminaArticolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaArticolo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer codice = Integer.parseInt(request.getParameter("codice"));
		
		Articolo a = new Articolo();
		a.setCodice(codice);
		
		ArticoloDao aDao = new ArticoloDao();
		boolean esito = aDao.delete(a);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		if(esito)
			response.getWriter().write("true");
		else 
			response.sendRedirect("/erroreGenerico.html");
	}
}