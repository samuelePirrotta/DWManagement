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
import it.unirc.tesi.beans.articolo.Articolo;
import it.unirc.tesi.beans.articolo.ArticoloAssociatoCategoria;
import it.unirc.tesi.beans.articolo.ArticoloAssociatoCategoriaDao;
import it.unirc.tesi.beans.articolo.ArticoloDao;
import it.unirc.tesi.beans.articolo.Ean;
import it.unirc.tesi.beans.articolo.EanDao;
import it.unirc.tesi.beans.fornitore.Fornitore;
import it.unirc.tesi.beans.fornitore.FornitoreDao;
import it.unirc.tesi.beans.magazzino.Deposito;
import it.unirc.tesi.beans.magazzino.DepositoDao;
import it.unirc.tesi.beans.magazzino.EanPresenteDeposito;
import it.unirc.tesi.beans.magazzino.EanPresenteDepositoDao;
import it.unirc.tesi.beans.tagliaEcolore.TabellaTaglie;
import it.unirc.tesi.beans.tagliaEcolore.TabellaTaglieDao;

/**
 * Servlet implementation class RichiediModificaArticolo
 */
@WebServlet("/RichiediModificaArticolo")
public class RichiediModificaArticolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiediModificaArticolo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer codice = Integer.parseInt(request.getParameter("codice"));
		
		ArticoloDao aDao = new ArticoloDao();
		Articolo a = aDao.cerca(codice);
		
		ArticoloAssociatoCategoriaDao acDao = new ArticoloAssociatoCategoriaDao();
		ArticoloAssociatoCategoria ac = acDao.get(codice);
		
		FornitoreDao fDao = new FornitoreDao();
		Vector<Fornitore> fornitori = fDao.getAll();
		
		TabellaTaglieDao tDao = new TabellaTaglieDao();
		Vector<TabellaTaglie> tabelle = tDao.getAll();
		
		EanDao eDao = new EanDao();
		Vector<Ean> ean = eDao.get(codice);
		
		DepositoDao depDao = new DepositoDao();
		Vector<Deposito> depositi = depDao.getAll();
		
		EanPresenteDepositoDao edDao = new EanPresenteDepositoDao();
		Vector<EanPresenteDeposito> giacenze = new Vector<EanPresenteDeposito>(); 
		for(Deposito d: depositi) {
			Vector<EanPresenteDeposito> tmp = edDao.getArticoliForDeposito(d.getCodice(), codice);
			for(EanPresenteDeposito ed: tmp) {
				giacenze.add(ed);
			}
		}
		
		CategoriaMerceologicaDao cmDao = new CategoriaMerceologicaDao();
		Vector<CategoriaMerceologica> categorie = cmDao.getAll();
		
		RaggruppamentoMerceologicoDao rmDao = new RaggruppamentoMerceologicoDao();
		Vector<RaggruppamentoMerceologico> raggruppamenti = rmDao.getAll();
		
		RepartoDao rDao = new RepartoDao();
		Vector<Reparto> reparti = rDao.getAll();
		
		request.setAttribute("articolo", a);
		request.setAttribute("articoloCategoria", ac);
		request.setAttribute("tabelle", tabelle);
		request.setAttribute("fornitori", fornitori);
		request.setAttribute("categorie", categorie);
		request.setAttribute("raggruppamenti", raggruppamenti);
		request.setAttribute("reparti", reparti);
		request.setAttribute("depositi", depositi);
		request.setAttribute("giacenze", giacenze);
		request.setAttribute("ean", ean);
		
		request.getRequestDispatcher("/anagrafica/articolo/modificaArticolo.jsp").forward(request, response);
	}
}