function collapse(element) {
	$(element).toggleClass('active');
}

function changeIcon(element, icon){
	$(element).find("i").toggleClass(icon);
}

//Aggiunge una riga alla tabella delle varianti
function addRow(idTable){	
	var table = document.getElementById(idTable);	
	var colonne = table.getElementsByTagName('th').length; 
	var tbody = table.getElementsByTagName('tbody')[0];
	var tr = document.createElement('tr');

	//var codice = document.getElementById("codice").value;	
    
	//AJAX per chiedere il valore alla servlet
	var xhr = new XMLHttpRequest();	

	var url = "RichiediAggiungiEan";
	var codice = document.getElementById('codice').value;
	var params = "codice="+codice;
    
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {        	
        	//crea la cella Ean
        	var ean = document.createElement('td');	
        	ean.setAttribute("id", "ean");
        	ean.appendChild(document.createTextNode(xhr.responseText));
        	tr.appendChild(ean);
        	
        	//crea le celle della riga e le rende editabili
        	for (var i=1; i<colonne-1; i++) {
        		var td = document.createElement('td');
        		td.setAttribute("contenteditable", "true");
        		td.setAttribute("onmousemove", "up(this);");
        		tr.appendChild(td);
        	}
        	        	
        	//crea il pulsante annulla
        	var td = document.createElement('td');
        	td.setAttribute("align", "center");
        	var btn = document.createElement('input');
        	btn.setAttribute("type", "submit");
        	btn.setAttribute("value", "Annulla");
        	btn.setAttribute("onclick", "deleteRow(this)");
        	btn.classList.add("btn");
        	td.appendChild(btn);
        	tr.appendChild(td);
        }
    }
    xhr.open('GET', url+"?"+params, true);
    xhr.send(null);
    
    //Aggiunge la riga al body della tabella
	tbody.appendChild(tr);
}

function deleteRow(btn) {
	//risale alla riga del bottone che è stato premuto
    var row = btn.parentNode.parentNode;
    //risale al body della tabella ed elimina la riga corrispondente al bottone premuto
    row.parentNode.removeChild(row);
}

//Salva gli ean inseriti
function saveEan(idTable) {
	var table = document.getElementById(idTable);
	var tbody = table.getElementsByTagName('tbody')[0];
	var rowNumber = tbody.getElementsByTagName('tr').length;
	
	var ean = new Array;
	var taglia = new Array;
	var colore = new Array;
	var costo = new Array;
	var ricarico = new Array;
	
	for(var i=0; i<rowNumber; i++) {		
		//seleziona l'i-esima riga
		var row = tbody.getElementsByTagName('tr')[i];
		
		//prende i valori dei parametri dalle celle della riga
		var e = row.getElementsByTagName('td')[0].childNodes[0].nodeValue;
		var t = row.getElementsByTagName('td')[1].childNodes[0].nodeValue;
		var col = row.getElementsByTagName('td')[2].childNodes[0].nodeValue;
		var cost = row.getElementsByTagName('td')[3].childNodes[0].nodeValue;
		var r = row.getElementsByTagName('td')[4].childNodes[0].nodeValue;
		
		ean.push(e);
		taglia.push(t);
		colore.push(col);
		costo.push(cost);
		ricarico.push(r);			
	}
	
	var codice = document.getElementById('codice').value;
	var tabellaTaglie = document.getElementById('tabellaTaglie').value;
	
	//crea la variabile con i parametri da passare alla servlet
	var params = "ean="+ean+"&taglia="+taglia+"&colore="+colore+"&costo="+costo+"&ricarico="+ricarico+"&codice="+codice+"&tabellaTaglie="+tabellaTaglie;
	var http = new XMLHttpRequest();
	var url = "AggiungiEan";
	http.open('GET', url+"?"+params, true);
	http.send(null);
	http.onreadystatechange = function() {
		if (http.readyState == 4 && http.status == 200) {
			if(http.responseText == "true") {
				location.href="/anagrafica/anagrafica.jsp";
			}
			else {
				location.href="/erroreGenerico.html";
			}
		}
	}		
}

//Salva un articolo
function saveArticle(idTable) {	
	var codice = document.getElementById('codice').value;
	var descrizione = document.getElementById('descrizione').value;
	var fornitore = document.getElementById('fornitore').value;
	var codiceRf = document.getElementById('codiceRf').value;
	var stagione = document.getElementById('stagione').value;
	var tabellaTaglie = document.getElementById('tabellaTaglie').value;
	var categoria = document.getElementById('categoria').value;
	var raggruppamento = document.getElementById('raggruppamento').value;
	var reparto = document.getElementById('reparto').value;
	
	var params = "codice="+codice+"&descrizione="+descrizione+"&fornitore="+fornitore+"&codiceRf="+codiceRf+"&stagione="+stagione+"&tabellaTaglie="+tabellaTaglie+"&categoria="+categoria+"&raggruppamento="+raggruppamento+"&reparto="+reparto;
	var url = "AggiungiArticolo";
	
	var http = new XMLHttpRequest();
	http.open('GET', url+"?"+params, true);
	http.send(null);
	
	saveEan(idTable);
}

//Cancella un'articolo chiedendo prima conferma
function deleteArticle(codice) {
	if(!confirm("Sei sicuro di voler eliminare questo articolo?")) {
		return false;
	}
	else {
		var http = new XMLHttpRequest();
		var url = "/EliminaArticolo";
		http.open("GET", url+"?codice="+codice);
		http.send(null);
		http.onreadystatechange = function() {
			if (http.readyState == 4 && http.status == 200) {
				if(http.responseText == "true") {
					location.href="/RichiediCercaArticolo";
				}
				else {
					location.href="/erroreGenerico.html";
				}
			}
		}		
	}
}

//Forza in maisolo i campi input
function upper(ustr)
{
    var str = ustr.value;
    var selstart = ustr.selectionStart;

    ustr.value = str.toUpperCase();

    ustr.selectionStart = selstart;
    ustr.selectionEnd = selstart;
}

//Forza in maiuscolo i campi td
function up(a) {		
	var str = a.childNodes[0].nodeValue;			
	a.childNodes[0].nodeValue = str.toUpperCase();		 
}


/*function sendData() {
	var http = new XMLHttpRequest();
	var url = "AggiungiArticolo";
	var codice = document.getElementById('codice').value;
	var params = "codice="+codice;
	console.log(params);
	http.open("GET", url+"?"+params, true);
	http.onreadystatechange = function() {//Call a function when the state changes.
		if(http.readyState == 4 && http.status == 200) {
			alert(http.responseText);
		}
	}
	http.send(null);
}*/