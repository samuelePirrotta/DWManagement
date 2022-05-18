package it.unirc.tesi.servlet.articolo;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.tesi.beans.area.CategoriaMerceologica;
import it.unirc.tesi.beans.area.CategoriaMerceologicaDao;
import it.unirc.tesi.beans.area.RaggruppamentoMerceologico;
import it.unirc.tesi.beans.area.RaggruppamentoMerceologicoDao;
import it.unirc.tesi.beans.area.Reparto;
import it.unirc.tesi.beans.area.RepartoDao;
import it.unirc.tesi.beans.articolo.ArticoloDao;
import it.unirc.tesi.beans.fornitore.Fornitore;
import it.unirc.tesi.beans.fornitore.FornitoreDao;
import it.unirc.tesi.beans.tagliaEcolore.TabellaTaglie;
import it.unirc.tesi.beans.tagliaEcolore.TabellaTaglieDao;

/**
 * Servlet implementation class AggiungiArticolo
 */
@WebServlet("/RichiediAggiungiArticolo")
public class RichiediAggiungiArticolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiediAggiungiArticolo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FornitoreDao fDao = new FornitoreDao();
		Vector<Fornitore> fornitori = fDao.getAll();
		
		TabellaTaglieDao tDao = new TabellaTaglieDao();
		Vector<TabellaTaglie> tabelle = tDao.getAll();
		
		CategoriaMerceologicaDao cmDao = new CategoriaMerceologicaDao();
		Vector<CategoriaMerceologica> categorie = cmDao.getAll();
		
		RaggruppamentoMerceologicoDao rmDao = new RaggruppamentoMerceologicoDao();
		Vector<RaggruppamentoMerceologico> raggruppamenti = rmDao.getAll();
		
		RepartoDao rDao = new RepartoDao();
		Vector<Reparto> reparti = rDao.getAll();
		
		ArticoloDao aDao = new ArticoloDao();
		int cod = aDao.succArticolo();
		
		request.setAttribute("tabelle", tabelle);
		request.setAttribute("fornitori", fornitori);
		request.setAttribute("categorie", categorie);
		request.setAttribute("raggruppamenti", raggruppamenti);
		request.setAttribute("reparti", reparti);
		request.setAttribute("cod", cod);
		request.getRequestDispatcher("/anagrafica/articolo/nuovoArticolo.jsp").forward(request, response);
	}

}
