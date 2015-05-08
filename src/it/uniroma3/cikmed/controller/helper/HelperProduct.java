package it.uniroma3.cikmed.controller.helper;

import javax.servlet.http.HttpServletRequest;

public class HelperProduct {
	public boolean isValid (HttpServletRequest request) {
		String nome = request.getParameter("name");
		String prezzo = request.getParameter("price");
		String codice = request.getParameter("id");
		boolean errori = false;
		
		
		if(nome.isEmpty()) {
			errori=true;
			request.setAttribute("nomeError", "Campo obbligatorio!");
		}
		
		 try {
	         Double.parseDouble(prezzo);
	     } catch(Exception e) {
	         errori=true;
	         request.setAttribute("prezzoError","Deve essere un numero.");
	     }

	     if(codice.isEmpty()) {
	         errori=true;
	         request.setAttribute("codiceError","Campo Obbligatorio!");
	     }
	     
	     return errori;
	}

}
