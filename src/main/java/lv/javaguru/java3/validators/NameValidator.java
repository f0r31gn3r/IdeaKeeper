package lv.javaguru.java3.validators;

import lv.javaguru.java3.core.services.users.UserValidator;
import lv.javaguru.java3.core.services.users.UserValidatorImpl;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Anna on 28.01.2016.
 */

public class NameValidator implements Validator {

    private UserValidator userValidator = new UserValidatorImpl();


    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null) {

            try{
                userValidator.validateName((String) value);
            } catch (Exception e){
                String validatorMessage1 = e.getLocalizedMessage();
                String validatorMessage2 = e.getMessage();

                System.out.println("Message1: " + validatorMessage1);
                System.out.println("Message2: " + validatorMessage2);

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, validatorMessage1, null));
            }

        }
    }
}
