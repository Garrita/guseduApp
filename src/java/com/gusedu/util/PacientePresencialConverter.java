// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   EnfermedadConverter.java

package com.gusedu.util;

import com.gusedu.dao.EnfermedadService;
import com.gusedu.dao.VisitaService;
import com.gusedu.dao.impl.EnfermedadServiceImpl;
import com.gusedu.dao.impl.VisitaServiceImpl;
import com.gusedu.entidad.PacientePresencial;
import com.gusedu.model.Enfermedad;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("pacientePresencialConverter")
public class PacientePresencialConverter
    implements Converter {

    VisitaService visitaService;
    
         public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                
                visitaService = new VisitaServiceImpl();
                List<PacientePresencial> list=visitaService.SP_ListarPacienteEnEspera("");
                PacientePresencial ps = new PacientePresencial();
                 for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).getPosicion()==Integer.parseInt(value))
                    {
                        ps=list.get(i);
                    }
                }
                return ps;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((PacientePresencial) object).getPosicion());
        }
        else {
            return null;
        }
    }   
}
