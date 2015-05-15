<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catalogo prodotti:</title>
</head>
<body>
	<f:view>
		<ul>
			<c:forEach var="prodotto" items="${prodotti}">
				<li>${prodotti.nome}</li>

				<li><h:commandButton id="submit"
action="#{prodottoController.getProduct}" value="Submit" /></li>

			</c:forEach>
		</ul>

	</f:view>
</body>
</html>