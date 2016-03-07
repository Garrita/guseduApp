// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   SintomaConverter.java

package com.gusedu.util;

import com.gusedu.dao.SintomaService;
import com.gusedu.dao.impl.SintomaServiceImpl;
import com.gusedu.model.Sintoma;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("sintomaConverter")
public class SintomaConverter
    implements Converter {

            SintomaService sintomaService;


            public Object getAsObject(FacesContext context, UIComponent component, String value) {
/*  24*/        if (value == null || value.isEmpty()) {
/*  25*/            return null;
                }
               try {			
			Sintoma sintoma;
/*  29*/        sintomaService = new SintomaServiceImpl();
/*  30*/        sintoma = sintomaService.getByNombre(value);
			return sintoma;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El sintoma no es correcto"));
		}
            }

            public String getAsString(FacesContext context, UIComponent component, Object value) {
/*  41*/        if (value == null || value.equals("") || ((Sintoma)value).getSinCodigo() == null || String.valueOf(((Sintoma)value).getSinDescripcion()).equals("")) {
/*  44*/            return "";
                } else {
/*  46*/            return String.valueOf(((Sintoma)value).getSinDescripcion());
                }
            }
}
