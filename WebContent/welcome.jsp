<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BENVENUTO IN E-CICCOZZONE</title>
</head>
<body>
	<h1>qui potrai trovare tutti i prodotti che cercavi</h1>
	<div>
		<h:commandButton value="mostra catalogo prodotti"
			action="#{ProdottoController.listProducts}" />
	</div>
</body>
</html>