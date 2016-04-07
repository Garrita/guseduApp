/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.util;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author NV55C
 */
@FacesConverter("pacienteConverter")
public class PacienteConverter implements Converter{

    /**
     * Creates a new instance of PacienteConverter
     */
    public PacienteConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         if(value != null && value.trim().length() > 0) {
            try {
               
                return value;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         if(value != null) {
            return value.toString();
        }
        else {
            return null;
        }
    }
    
}
