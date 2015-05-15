<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html>
<html>
<head>
<title>Inserisci nuovo prodotto:</title>
</head>
<body>
	<f:view>
		<h:form>
			<div>
				Name:
				<h:inputText value="#{ProdottoController.nome}" required="true"
					requiredMessage="Nome obbligatorio" id="nome" />
				<strong><h:message for="nome" /></strong>
			</div>
			<div>
				Code:
				<h:inputText value="#{ProdottoController.codice}" required="true"
					requiredMessage="Codice obbligatorio" id="codice" />
				<h:message for="codice" />
			</div>
			<div>
				Price:
				<h:inputText value="#{ProdottoController.prezzo}" required="true"
					requiredMessage="Prezzo obbligatorio"
					converterMessage="Il prezzo deve essere un numero!" id="prezzo" />
				<h:message for="prezzo" />
			</div>
			<div>
				Description:
				<h:inputTextarea value="#{ProdottoController.descrizione}"
					required="false" cols="20" rows="5" /> 
			</div>
			<div>
				<h:commandButton value="Submit"
					action="#{prodottoController.creaProdotto}" />
			</div>
		</h:form>

	</f:view>
</body>
</html>
