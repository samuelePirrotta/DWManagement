<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.unirc.tesi.beans.articolo.Articolo"%>
<%@ page import="it.unirc.tesi.beans.fornitore.Fornitore"%>
<%@ page import="java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WH Management - Cerca Articolo</title>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" type="text/css" href="/bootstrap/css/bootstrap.css">
<!-- Font Awesome CSS -->
<link rel="stylesheet" type="text/css" href="/fontawesome/css/all.css">
<!-- Our Custom CSS -->
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" type="text/css" href="/css/style-header.css">
<link rel="stylesheet" type="text/css" href="/css/style-mask.css">
<script type="text/javascript" src="/js/function.js"></script>
</head>
<body>

	<div id="header">
		<p>
		<h1>Cerca Articolo</h1>
		<button class="icon-btn" id="home-btn" onclick="location.href='/home.jsp'">	
			<i class="fa fa-home fa-2x"></i> 
		</button>		
	</div>
	
	<div id=content>
		<div id="mask">
			<form name="datiArticolo" action="/CercaArticolo" method="post" >
				<div class="row">
					<div class="col-md-3">
						<label>Codice</label>
						<input type="text" name="codice" class="width-80">
					</div>
					<div class="col-md-6">
						<label>Descrizione</label>
						<input type="text" name="descrizione" onkeyup="upper(this);" maxlength="80" class="width-80">
					</div>
					<div class="col-md-3">
						<label>Codice Fornitore</label>
						<input type="text" name="codiceFornitore" class="width-60">
					</div>
				</div>
				<div class="row" id="space"></div>
				<div class="row">					
					<div class="col-md-7">
						<label>Codice Riferimento Fornitore</label>
						<input type="text" name="codiceRF" onkeyup="upper(this);" maxlength="20" class="width-60">
					</div>
					<div class="col-md-3">
						<label>Stagione</label>
						<input type="text" name="stagione" onkeyup="upper(this);" maxlength="4" class="width-60">
					</div>
				</div>
				<div class="row" id="space"></div>
				<div class="row">
					<div class="col-md-8"></div>
					<div class="col-md-2">
						<input type="button" class="btn width-60" onclick="location.href='/anagrafica/anagrafica.jsp'; return(false);" value="Annulla" />
					</div>
					<div class="col-md-2">
						<input type="submit" value="Cerca" class="btn width-60"/>
					</div>
				</div>			
			</form>
		</div>
		<div class="table">
			<%
				Vector<Articolo> articoli;
				Vector<Fornitore> fornitori;
				Vector<Integer> giacenze;
				if (request.getAttribute("articoli") == null) {
					//out.write("Errore generico riprova più tardi");
					return;
				} else {
					articoli = (Vector<Articolo>) request.getAttribute("articoli");
					fornitori = (Vector<Fornitore>) request.getAttribute("fornitori");
					giacenze = (Vector<Integer>) request.getAttribute("giacenze");
			%>
				<table class="table">	
					<tr id="intestazione">
						<td>Codice</td>
						<td>Descrizione</td>
						<td>Fornitore</td>
						<!-- <td>Giacenza</td> -->
						<td>Stagione</td>
						<td>Tabella Taglie</td>
					</tr>
					<% 
						int i=0; 
						for (Articolo a : articoli) {  
					%>
						<tr id="dati" onclick="location.href='/RichiediModificaArticolo?codice=<%=a.getCodice()%>'" >
							<td><%=a.getCodice()%></td>
							<td><%=a.getDescrizione()%></td>
							<td><%=fornitori.get(i).getRagioneSociale()%></td>
							<!-- <td><%=giacenze.get(i) %></td> -->
							<td><%=a.getStagione()%></td>
							<td><%=a.getTabellaTaglie()%></td>
						</tr>
					<% i++; } %>
				</table>
			<% } %>			
		</div>		
	</div>
	<%@ include file="/includeFile/footer.txt" %>
	
	
</body>
</html>