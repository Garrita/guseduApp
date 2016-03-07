// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   EnfermedadConverter.java

package com.gusedu.util;

import com.gusedu.dao.EnfermedadService;
import com.gusedu.dao.impl.EnfermedadServiceImpl;
import com.gusedu.model.Enfermedad;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("enfermedadConverter")
public class EnfermedadConverter
    implements Converter {

            EnfermedadService enfermedadService;


            public Object getAsObject(FacesContext context, UIComponent component, String value) {
                enfermedadService = new EnfermedadServiceImpl();
/*  24*/        if (value == null || value.isEmpty()) {
/*  25*/            return null;
                }
              try {
			Enfermedad enfermedad = enfermedadService.getByNombre(value);
			return enfermedad;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La enfermedad elegida no es correcta"));
		}
            }

            public String getAsString(FacesContext context, UIComponent component, Object value) {
/*  40*/        if (value == null || value.equals("") || ((Enfermedad)value).getEnfCodigo() == null || String.valueOf(((Enfermedad)value).getEnfNombre()).equals("")) {
/*  43*/            return "";
                } else {
/*  45*/            return String.valueOf(((Enfermedad)value).getEnfNombre());
                }
            }
}
