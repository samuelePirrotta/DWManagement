<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>WH Management - Anagrafica</title>

<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" type="text/css"
	href="/bootstrap/css/bootstrap.css">
<!-- Font Awesome CSS -->
<link rel="stylesheet" type="text/css" href="/fontawesome/css/all.css">
<!-- Our Custom CSS -->
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" type="text/css" href="/css/style-home.css">
<!--  Our JavaScript File -->
<script type="text/javascript" src="/js/function.js"></script>
</head>
<body>
	<div class="wrapper">

		<%@ include file="/includeFile/navBar.txt"%>

		<!-- Page Content Holder -->
		<div id="content">
			<div class="container">
				<div class="row">
					<!--  -------------------------------ARTICOLI----------------------------------------------- -->
					<div class="col-md-3 text-center">
						<label class="label">Articoli</label>						
						<a href="/RichiediAggiungiArticolo">
							<div class="service">
								<i class="fas fa-plus fa-2x" area-hidden="true"></i>
								<h4>Nuovo</h4>
							</div>
						</a>
						<a href="/RichiediCercaArticolo">
							<div class="service">
							<i class="fas fa-search fa-2x" area-hidden="true"></i>
							<h4>Cerca</h4>
							</div>
						</a>
					</div>
					<!--  -------------------------------TAGLIE & COLORI----------------------------------------------- -->
					<div class="col-md-3 text-center">
						<label class="label">Taglie & Colori</label>
						<a href="#">
							<div class="service">
								<i class="fas fa-table fa-2x" area-hidden="true"></i>
								<h4>Tabelle Taglie</h4>
							</div>
						</a>
						<a href="#">
							<div class="service">
								<i class="fas fa-palette fa-2x" area-hidden="true"></i>
								<h4>Colori</h4>
							</div>
						</a>	
					</div>	
					<!--  -------------------------------AREE----------------------------------------------- -->
					<div class="col-md-3 text-center">						
						<label class="label">Aree</label> 
						<a href="#">
							<div class="service">
								<i class="fas fa-flag fa-2x" area-hidden="true"></i>
								<h4>Reparti</h4>
							</div>
						</a>
						<a href="#">
							<div class="service">
								<i class="fas fa-layer-group fa-2x" area-hidden="true"></i>
								<h4>Raggruppamenti</h4>
							</div>
						</a>
						<a href="#">
							<div class="service">
								<i class="fas fa-tshirt fa-2x" area-hidden="true"></i>
								<h4>Categorie</h4>
							</div>
						</a>						
					</div>	
					<!--  -------------------------------FORNITORI----------------------------------------------- -->		
					<div class="col-md-3 text-center">
						<label class="label">Fornitori</label> 
						<a href="#">
							<div class="service">
								<i class="fas fa-id-badge fa-2x" area-hidden="true"></i>
								<h4>Nuovo</h4>
							</div>
						</a>
						<a href="#">
							<div class="service">
								<i class="fas fa-search fa-2x" area-hidden="true"></i>
								<h4>Cerca</h4>
							</div>
						</a>
					</div>		
				</div>		
			</div>
		</div>
		<div id="rigth-bar">
			<p>
				<button type="button" class="icon-btn" id="userCollapse"
					onclick="collapse(user)">
					<i class="fa fa-user fa-2x"></i>
				</button>
			</p>
			<p>
				<button type="button" class="icon-btn" id="notifyCollapse"
					onclick="collapse(notify), changeIcon(notifyCollapse, 'fa-envelope-open')">
					<i class="fa fa-envelope fa-2x"></i>
				</button>
			</p>
		</div>
		<div id="boxes-bar">
			<div class="mask" id="user">
				<ul class="list-unstyled">
					<li><a href="index.html">Logout</a>
					<li><a href="#">Profilo Utente</a>
				</ul>
			</div>
			<br>
			<div class="mask" id="notify">
				<div class="title">Notifiche:</div>
				<div id="notify-cont">
					<a href="notifiche.html">Questa è una notifica molto molto molto molto molto lunga</a> 
					<a href="notifiche.html">Questa è una notifica</a> 
					<a href="notifiche.html">Questa è una notifica</a> 
					<a href="notifiche.html">Questa è una notifica</a> 
					<a href="notifiche.html">Questa è una notifica</a> 
					<a href="notifiche.html">Questa è una notifica</a> 
					<a href="notifiche.html">Questa è una notifica</a> 
					<a href="notifiche.html">Questa è una notifica</a> 
					<a href="notifiche.html">Questa è una notifica</a> 
					<a href="notifiche.html">Questa è una notifica</a> 
					<a href="notifiche.html">Questa è una notifica</a>
				</div>
			</div>
		</div>			
	</div>
	<%@ include file="/includeFile/footer.txt"%>

	<!-- jQuery CDN - Slim version (=without AJAX) -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<!-- Popper.JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
		integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
		crossorigin="anonymous"></script>
	<!-- Bootstrap JS -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
		integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#sidebarCollapse').on('click', function() {
				$('#sidebar').toggleClass('active');
				$(this).toggleClass('active');
			});
		});
	</script>
</body>
</html>