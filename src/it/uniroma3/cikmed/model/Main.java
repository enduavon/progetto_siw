package it.uniroma3.cikmed.model;


import it.uniroma3.cikmed.facade.ClienteFacade;

import java.util.GregorianCalendar;

public class Main {
	
	public static void main(String[] args) {
		ClienteFacade provaFacadeCliente;
		provaFacadeCliente = new ClienteFacade();
		
	provaFacadeCliente.creaCliente("Paolo", "coccia", "coccia92", "ricco", new GregorianCalendar(23, 10, 1990), new GregorianCalendar(21, 9, 2011), "via paolo II", "coccia@gmail.com");	    
	provaFacadeCliente.creaCliente("Andrea", "ciccozzi", "rimba92", "ciccozzi", new GregorianCalendar(13, 8, 1992), new GregorianCalendar(10, 12, 2012), "via luana Pozzi", "ciccozziSoPiuBelloIo@gmail.com");	
	
	}	
}
