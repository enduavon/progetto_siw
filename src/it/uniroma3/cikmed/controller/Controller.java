package it.uniroma3.cikmed.controller;

import it.uniroma3.cikmed.controller.action.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller/*")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage = null;
		String actionName = path2action(request);
		Action action;
		
		try {
			action=(Action)Class.forName(actionName).newInstance();
			nextPage = action.perform(request);
		}
		catch (Exception e) {
			nextPage="/error.jsp";
		}
		nextPage=response.encodeURL(nextPage);
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request,response);
		return;
	}
	
	/* Converte una parte della richiesta http (in particolare quella dopo il servlet path
	 * ma prima della query string) in un'unica stringa che corrisponde al nome dell'azione.
	 * Ad es: /createProduct diventa CreateProduct
	 */
	private String path2action(HttpServletRequest request) {
		String modelCommand = request.getPathInfo();
		String actionName = "it.uniroma3.controller.action."+modelCommand.substring(1,2).toUpperCase()+modelCommand.substring(2);
		return actionName;
	}

}
