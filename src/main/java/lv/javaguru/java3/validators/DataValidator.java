package lv.javaguru.java3.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Anna on 28.01.2016.
 */

public class DataValidator implements Validator {
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value != null && value instanceof String) {
            // let's do validation
            if (!isValidName((String) value)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name must not contain numbers!", null));
            }

        }
    }


    private static boolean isValidName(String name) {

        boolean result = true;

        if(     name.contains("1") ||
                name.contains("2") ||
                name.contains("3") ||
                name.contains("4") ||
                name.contains("5") ||
                name.contains("6") ||
                name.contains("7") ||
                name.contains("8") ||
                name.contains("9") ||
                name.contains("0")
                ){
            result = false;
        }

        return result;

    }
}
