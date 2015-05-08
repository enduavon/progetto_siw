package it.uniroma3.cikmed.controller.action;

import it.uniroma3.cikmed.controller.helper.HelperProduct;
import it.uniroma3.cikmed.model.Prodotto;
import it.uniroma3.cikmed.facade.ProdottoFacade;

import javax.servlet.http.HttpServletRequest;

public class CreateProduct implements Action {

	public String perform(HttpServletRequest request) {
		HelperProduct helper = new HelperProduct();
		
		/* verifico che i parametri inseriti nella richiesta http (tramite la newProduct.jsp) 
		 * siano validi. Se lo sono, creo il prodotto con quei parametri e li setto nella
		 * richiesta, mostrando al client i dati del prodotto inserito; 
		 * altrimenti torno alla pagina di inserimento newProduct
		 * (come fare per il "catalogo"?)
		 */
		if (helper.isValid(request)) {
			String nome = request.getParameter("nomeProdotto");
			float prezzo = Float.parseFloat(request.getParameter("prezzo"));
			String codice = request.getParameter("codice");
			String descrizione = request.getParameter("descrizione");
			int quantità = Integer.parseInt("quantità");
			
			ProdottoFacade facade = new ProdottoFacade();
			
			Prodotto prodottoDaInserire = facade.creaProdotto(codice, nome, descrizione, 
					prezzo, quantità); 
			request.setAttribute("product", prodottoDaInserire);
			return "/confirmProduct.jsp";			
		}
		else
		return "/newProduct.jsp";
	}

}
