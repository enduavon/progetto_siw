<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html>
<html>
<head>
<title>Prodotto</title>
</head>
<body>
	<f:view>
		<h1>${ProdottoController.prodotto.nome}</h1>
		<h2>Dettagli</h2>
		<div>Codice: ${ProdottoController.prodotto.codice}</div>
		<div>Prezzo: ${ProdottoController.prodotto.prezzo}</div>
		<div>Descrizione: ${prodottoController.prodotto.descrizione}</div>
		<div>Quantità: ${prodottoController.prodotto.quantità}</div>
	</f:view>
</body>
</html>
