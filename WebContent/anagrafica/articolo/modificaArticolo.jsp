<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="it.unirc.tesi.beans.articolo.Articolo"%>
<%@ page import="it.unirc.tesi.beans.articolo.Ean"%>
<%@ page import="it.unirc.tesi.beans.fornitore.Fornitore"%>
<%@ page import="it.unirc.tesi.beans.tagliaEcolore.TabellaTaglie"%>
<%@ page import="it.unirc.tesi.beans.area.CategoriaMerceologica"%>
<%@ page import="it.unirc.tesi.beans.area.RaggruppamentoMerceologico"%>
<%@ page import="it.unirc.tesi.beans.area.Reparto"%>
<%@page import="it.unirc.tesi.beans.magazzino.EanPresenteDeposito"%>
<%@page import="it.unirc.tesi.beans.magazzino.Deposito"%>
<%@page import="it.unirc.tesi.beans.articolo.ArticoloAssociatoCategoria"%>
<%@ page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>WH Management - Modifica Articolo</title>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap.css">
<!-- Font Awesome CSS -->
<link rel="stylesheet" type="text/css" href="/fontawesome/css/all.css">
<!-- Our Custom CSS -->
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" type="text/css" href="/css/style-header.css">
<link rel="stylesheet" type="text/css" href="/css/style-mask.css">
<!-- Java Script File -->
<script type="text/javascript" src="/js/function.js"></script>
</head>
<body>
	<div id="header">
		<p>
		<h1>Modifica Articolo</h1>
		<button class="icon-btn" id="home-btn"
			onclick="location.href='/home.jsp'">
			<i class="fa fa-home fa-2x"></i>
		</button>	
	</div>
	<div id="content">
		<%
			Vector<Fornitore> fornitori;
			Vector<TabellaTaglie> tabelle;
			Vector<Ean> ean;
			Vector<CategoriaMerceologica> categorie;
			Vector<RaggruppamentoMerceologico> raggruppamenti;
			Vector<Reparto> reparti;
			Vector<Deposito> depositi;
			Vector<EanPresenteDeposito> giacenze;
			Articolo a;
			ArticoloAssociatoCategoria ac;
			if (request.getAttribute("fornitori") == null || request.getAttribute("tabelle") == null || request.getAttribute("articolo") == null) {
				//out.write("Errore generico riprova piÃ¹ tardi");
				return;
			} else {
				fornitori = (Vector<Fornitore>) request.getAttribute("fornitori");
				tabelle = (Vector<TabellaTaglie>) request.getAttribute("tabelle");
				ean = (Vector<Ean>) request.getAttribute("ean");
				categorie = (Vector<CategoriaMerceologica>) request.getAttribute("categorie");
				raggruppamenti = (Vector<RaggruppamentoMerceologico>) request.getAttribute("raggruppamenti");
				reparti = (Vector<Reparto>) request.getAttribute("reparti");
				depositi = (Vector<Deposito>) request.getAttribute("depositi");
				giacenze = (Vector<EanPresenteDeposito>) request.getAttribute("giacenze");
				a = (Articolo) request.getAttribute("articolo");
				ac = (ArticoloAssociatoCategoria) request.getAttribute("articoloCategoria");
		%>			
		<div id="mask">
			<form name="datiArticolo" action="/ModificaArticolo" method="post">
				<div class="row">
					<div class="col-md-3">
						<label>Codice</label>
						<input type="text" id="codice" name="codice" value="<%=a.getCodice()%>" readonly="readonly" />
					</div>
					<div class="col-md-5">
						<label>Descrizione</label>
						<input type="text" id="descrizione" name="descrizione" class="width-80" onkeyup="upper(this);" maxlength="80" value="<%=a.getDescrizione()%>" /> 			
					</div>
					<div class="col-md-4">
						<label>Fornitore</label>
						<select id="fornitore" name="fornitore">
							<% for (Fornitore f: fornitori) { %>
								<option value="<%=f.getCodice()%>" <% if(f.getCodice()==a.getCodiceFornitore()) out.print("selected"); %>><%=f.getRagioneSociale()%></option>
							<% } %>
						</select>
					</div>
				</div>
				<div class="row" id="space"></div>
				<div class="row">
					<div class="col-md-5">
						<label>Codice Riferimento Fornitore</label>
						<input type="text" id="codiceRf" name="codiceRf" class="width-60" onkeyup="upper(this);" maxlength="20" value="<%=a.getCodiceRf()%>" />					
					</div>
					<div class=col-md-3>
						<label>Stagione</label>			
						<input type="text" id="stagione" name="stagione" onkeyup="upper(this);" maxlength="4" value="<%=a.getStagione()%>" />
					</div>
					<div class=col-md-4>
						<label>Tabella Taglie</label>
						<select id="tabellaTaglie" name="tabellaTaglie">
							<% for (TabellaTaglie t: tabelle) { %>
									<option value="<%=t.getId()%>" <% if(t.getId()==a.getTabellaTaglie()) out.print("selected"); %>><%=t.getNazione()%></option>
							<% } %>							
						</select>
					</div>
				</div>	
				<div class="row" id="space"></div>
				<div class="row">
					<div class="col-md-4">
						<label>Categoria</label>
						<select id="categoria" name="categoria" class="width-60">
							<% for (CategoriaMerceologica c: categorie) { %>
									<option value="<%=c.getId()%>" <% if(c.getId()==ac.getIdCategoria()) out.print("selected"); %>><%=c.getNome()%></option>
							<% } %>							
						</select>
					</div>
					<div class="col-md-4">
						<label>Raggruppamento</label>
						<select id="raggruppamento" name="raggruppamento" class="width-60">
							<% for (RaggruppamentoMerceologico r: raggruppamenti) { %>
									<option value="<%=r.getId()%>" <% if(r.getId()==ac.getIdRaggruppamento()) out.print("selected"); %>><%=r.getNome()%></option>
							<% } %>							
						</select>
					</div>
					<div class="col-md-4">
						<label>Reparto</label>
						<select id="reparto" name="reparto" class="width-60">
							<% for (Reparto r: reparti) { %>
									<option value="<%=r.getId()%>" <% if(r.getId()==ac.getIdReparto()) out.print("selected"); %>><%=r.getNome()%></option>
							<% } %>							
						</select>
					</div>
				</div>
				<div class="row" id="space"></div>		
				<div class="row">
					<div class="col-md-6"></div>
					<div class="col-md-2">
						<input type="button" class="btn width-60" value="Annulla" onclick="location.href='/RichiediCercaArticolo'" />
					</div>
					<div class="col-md-2">						
						<input type="submit" class="btn width-60" value="Modifica" />
					</div>
					<div class="col-md-2">			
						<input type="button" class="btn width-60" onClick="deleteArticle(<%=a.getCodice()%>)" value="Elimina" />
					</div>
				</div>
			</form>
		</div>				
		<div class="table">
			<div class="row">
				<div class="col-md-5"></div>
				<div class="col-md-1"><label class="label ">Varianti</label></div>
				<div class="col-md-5"></div>
			</div>
			<table class="table">
				<thead>
					<tr id="intestazione">
						<th>EAN</th>
						<th>Taglia</th>
						<th>Colore</th>
						<th>Costo</th>
						<th>Ricarico %</th>
						<% for(Deposito d: depositi) { %>
							<th>Giacenza <%=d.getNome()%></th>
						<% } %>
					</tr>
				</thead>
				<tbody>
					<% for(Ean e: ean) { %>
						<tr>
							<td><%=e.getEan()%></td>
							<td contenteditable="true" onkeyup="upper(this);"><%=e.getTaglia()%></td>
							<td contenteditable="true" onkeyup="upper(this);"><%=e.getColore()%></td>
							<td contenteditable="true"><%=e.getCosto()%></td>
							<td contenteditable="true"><%=e.getRicarico()%></td>
							<% for(Deposito d: depositi) { 
								for(EanPresenteDeposito it: giacenze) {
									if(it.getDeposito()==d.getCodice() && it.getEan().equals(e.getEan())) {
							%>
										<td style="text-align: center;"><%=it.getGiacenza()%></td>
							<% } } } %>
						</tr>
					<% } %>
				</tbody>
			</table>
		</div>
		<div class="table">	
			<div class="row" id="space">	
				<div class="col-md-2"><button class="btn width-80" onclick="addRow('eanTable')">Aggiungi Variante</button></div>
				<div class="col-md-8"></div>
				<div class="col-md-2">
					<input type="button" class="btn width-80" onclick="javascript: saveEan('eanTable');" value="Salva" />
				</div>
			</div>	
			<table class="table" id="eanTable">
				<thead>
					<tr id="intestazione">
						<th>EAN</th>
						<th>Taglia</th>
						<th>Colore</th>
						<th>Costo</th>
						<th>Ricarico %</th>
						<th></th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	<% } %>
	</div>
	<%@ include file="/includeFile/footer.txt" %>
</body>
</html>