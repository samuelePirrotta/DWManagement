package it.unirc.tesi.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.tesi.beans.articolo.EanDao;

/**
 * Servlet implementation class EliminaEan
 */
@WebServlet("/EliminaEan")
public class EliminaEan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaEan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ean = request.getParameter("ean");
		int codice = Integer.parseInt(request.getParameter("codice"));
		
		request.setAttribute("codice", codice);
		
		EanDao eDao = new EanDao();
		if(eDao.erase(ean))
			request.getRequestDispatcher("/RichiediModificaArticolo").forward(request, response);
		else {
			response.sendRedirect("/erroreGenerico.html");
		}
	}
}