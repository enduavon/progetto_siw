<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	  xmlns:f="http://java.sun.com/jsf/core"
	  xmlns:h="http://java.sun.com/jsf/html">
<head>
<title>Prodotto</title>
</head>
<body>
	<f:view>
		<h1>Perfetto, hai inserito il prodotto
			"${prodottoController.prodotto.nome}" nel catalogo prodotti.</h1>
		<h2>Dettagli:</h2>
		<div>Codice: ${prodottoController.prodotto.codice}</div>
		<div>Prezzo: ${prodottoController.prodotto.prezzo}</div>
		<div>Descrizione: ${prodottoController.prodotto.descrizione}</div>
		<%--<div>Quantità: ${prodottoController.prodotto.quantità}</div> 
		<div>
			<h:outputLink value="index.xhtml">Torna alla pagina principale</h:outputLink>
		</div> --%>
	</f:view>
</body>
</html>