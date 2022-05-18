<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="it.unirc.tesi.beans.fornitore.Fornitore"%>
<%@ page import="it.unirc.tesi.beans.tagliaEcolore.TabellaTaglie"%>
<%@ page import="it.unirc.tesi.beans.area.CategoriaMerceologica"%>
<%@ page import="it.unirc.tesi.beans.area.RaggruppamentoMerceologico"%>
<%@ page import="it.unirc.tesi.beans.area.Reparto"%>
<%@ page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>WH Management - Nuovo Articolo</title>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" type="text/css"
	href="/bootstrap/css/bootstrap.css">
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
		<h1>Nuovo Articolo</h1>
		<button class="icon-btn" id="home-btn"
			onclick="location.href='/home.jsp'">
			<i class="fa fa-home fa-2x"></i>
		</button>	
	</div>
	<div id="content">
		<div id="mask">
			<%
				Vector<Fornitore> fornitori;
				Vector<TabellaTaglie> tabelle;
				Vector<CategoriaMerceologica> categorie;
				Vector<RaggruppamentoMerceologico> raggruppamenti;
				Vector<Reparto> reparti;
				Integer cod;
				if (request.getAttribute("fornitori") == null || request.getAttribute("tabelle") == null || request.getAttribute("cod") == null) {
					//out.write("Errore generico riprova più tardi");
					return;
				} else {
					fornitori = (Vector<Fornitore>) request.getAttribute("fornitori");
					tabelle = (Vector<TabellaTaglie>) request.getAttribute("tabelle");
					categorie = (Vector<CategoriaMerceologica>) request.getAttribute("categorie");
					raggruppamenti = (Vector<RaggruppamentoMerceologico>) request.getAttribute("raggruppamenti");
					reparti = (Vector<Reparto>) request.getAttribute("reparti");
					cod = (Integer) (request.getAttribute("cod"));
					//session.setAttribute("codice", cod);
			%>
			<form name="datiArticolo" action="javascript: saveArticle('eanTable')" method="get">
				<div class="row">
					<div class="col-md-3">
						<label>Codice</label>
						<input type="text" id="codice" name="codice" value="<%=cod%>" readonly="readonly" />
					</div>
					<div class="col-md-5">
						<label>Descrizione</label>
						<input type="text" id="descrizione" name="descrizione" onkeyup="upper(this);" maxlength="80" class="width-80" />			
					</div>
					<div class="col-md-4">
						<label>Fornitore</label>
						<select id="fornitore" name="fornitore">
							<% for (Fornitore f: fornitori) { %>
								<option value="<%=f.getCodice()%>"><%=f.getRagioneSociale()%></option>
							<% } %>
						</select>
					</div>
				</div>
				<div class="row" id="space"></div>
				<div class="row">
					<div class="col-md-5">
						<label>Codice Riferimento Fornitore</label>
						<input type="text" id="codiceRf" name="codiceRF" onkeyup="upper(this);" maxlength="20" class="width-60">					
					</div>
					<div class=col-md-3>
						<label>Stagione</label>			
						<input type="text" id="stagione" name="stagione" onkeyup="upper(this);" maxlength="4">
					</div>
					<div class=col-md-4>
						<label>Tabella Taglie</label>
						<select id="tabellaTaglie" name="tabellaTaglie">
							<% for (TabellaTaglie t: tabelle) { %>
									<option value="<%=t.getId()%>"><%=t.getNazione()%></option>
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
									<option value="<%=c.getId()%>"><%=c.getNome()%></option>
							<% } %>							
						</select>
					</div>
					<div class="col-md-4">
						<label>Raggruppamento</label>
						<select id="raggruppamento" name="raggruppamento" class="width-60">
							<% for (RaggruppamentoMerceologico r: raggruppamenti) { %>
									<option value="<%=r.getId()%>"><%=r.getNome()%></option>
							<% } %>							
						</select>
					</div>
					<div class="col-md-4">
						<label>Reparto</label>
						<select id="reparto" name="reparto" class="width-60">
							<% for (Reparto r: reparti) { %>
									<option value="<%=r.getId()%>"><%=r.getNome()%></option>
							<% } %>							
						</select>
					</div>
				</div>
				<div class="row" id="space"></div>		
				<div class="row">
					<div class="col-md-8"></div>
					<div class="col-md-2">
						<input type="button" class="btn width-60" onclick="location.href='/anagrafica/anagrafica.jsp'" value="Annulla" />
					</div>
					<div class="col-md-2">						
						<input type="submit" class="btn width-60" value="Salva" />
					</div>
				</div>
			</form>
		</div>
		<% } %>
	</div>
	<div class="table">
		<div class="row" id="space">	
			<button class="btn" onclick="addRow('eanTable')">Aggiungi Variante</button>
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
	<%@ include file="/includeFile/footer.txt" %>
</body>
</html>