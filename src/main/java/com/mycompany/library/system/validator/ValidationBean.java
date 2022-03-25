/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.library.system.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author swiat
 */
@ManagedBean
public class ValidationBean {

    public Validator getDateValidator() {
        return (context, component, value) -> {
            if (isNothing(value)) {
                return;
            }
            if (!isValidDate(value.toString())) {
                throw new ValidatorException(new FacesMessage("Date is invalid!"));
            }
        };
    }

    public Validator getTextValidator() {
        return (context, component, value) -> {
            int length = value.toString().length();
            if (length > 3 && length < 64) {
                return;
            }
            throw new ValidatorException(new FacesMessage("Input length must be between 3 and 64 characters.!"));
        };
    }

    public Validator getISBNValidator() {
        return (context, component, value) -> {
            String isbn = value.toString();
            int length = isbn.length();
            if (length != 17) {
                throw new ValidatorException(new FacesMessage("ISBN needs to have 13 digits in 5 parts!"));
            }
            String pattern = "^\\d{3}\\-\\d-\\d{6}-\\d{2}-\\d$";
            if (isbn.matches(pattern)) {
                return;
            }
            throw new ValidatorException(new FacesMessage("Incorrect ISBN format!"));
        };
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private boolean isNothing(Object obj) {
        if (obj == null) {
            return true;
        }
        return obj.toString().equals("");
    }

}
