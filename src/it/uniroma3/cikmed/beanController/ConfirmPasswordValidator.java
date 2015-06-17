package it.uniroma3.cikmed.beanController;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("confirmPasswordValidator")
public class ConfirmPasswordValidator implements Validator {
	
	@Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {
		
	  String confermaPassword = value.toString();
 
	  UIInput uiInputPassword = (UIInput) component.getAttributes().get("password");
	  String password = uiInputPassword.getValue().toString();  

	  //Qua ci pensa il required=true della pagina xhtml
	  if (password == null || password.isEmpty() || confermaPassword == null
		|| confermaPassword.isEmpty()) {
			return;
	  }
 
	  //Infine si fa il confronto fra le due password inserite
	  if (!password.equals(confermaPassword)) {
		uiInputPassword.setValid(false);
		throw new ValidatorException(new FacesMessage(
			"Le due password devono coincidere."));
	  }
	}
}
