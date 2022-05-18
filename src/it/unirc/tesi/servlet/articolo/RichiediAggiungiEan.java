package it.unirc.tesi.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.tesi.beans.articolo.EanDao;

/**
 * Servlet implementation class RichiediAggiungiEan
 */
@WebServlet("/RichiediAggiungiEan")
public class RichiediAggiungiEan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiediAggiungiEan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int codice = Integer.parseInt(request.getParameter("codice"));
		
		EanDao eDao = new EanDao();
		String ean = eDao.newEan(codice);
			
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(ean);
	}
}