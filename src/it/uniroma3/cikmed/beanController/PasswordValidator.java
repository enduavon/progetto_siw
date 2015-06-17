package it.uniroma3.cikmed.beanController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/*In JSF, non c'è un modo per verificare campi/componenti multipli. 
 * Per fare ciò, bisogna crearsi un Validator personale che implementi l'interfaccia Validator e faccia l'override
 * del metodo validate, in cui scriveremo i controlli sulla password di input per la registrazione. 
 */
@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {
	
	private static final String PASSWORD_PATTERN = "(/^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})";
 	
	private Pattern pattern;
	private Matcher matcher;
	
	public PasswordValidator(){
		  pattern = Pattern.compile(PASSWORD_PATTERN);
	}
 
 
	@Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {
		
	  matcher = pattern.matcher(value.toString());	

	  
	  //si vede se la password inserita matcha il pattern impostato 
	  if(!matcher.matches()){
		  
			FacesMessage msg = 
				new FacesMessage("Password non validawjwifw.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}

	}
}