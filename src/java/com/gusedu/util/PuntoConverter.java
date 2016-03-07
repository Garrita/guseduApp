// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   PuntoConverter.java

package com.gusedu.util;

import com.gusedu.dao.PuntoService;
import com.gusedu.dao.impl.PuntoServiceImpl;
import com.gusedu.model.Punto;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("puntoConverter")
public class PuntoConverter
    implements Converter {

            PuntoService puntoService;


            public Object getAsObject(FacesContext context, UIComponent component, String value) {
/*  26*/        System.out.println("ENTRO A getAsObject");
                puntoService = new PuntoServiceImpl();
/*  27*/        if (value == null || value.isEmpty()) {
/*  28*/            return null;
                }
/*  31*/         
/*  32*/       try{			
			Punto punto = puntoService.puntoByNombre(value);
			return punto;
		}catch(Exception e){
			e.printStackTrace();
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El punto elegido no es válido"));
		}
            }

            public String getAsString(FacesContext context, UIComponent component, Object value) {
/*  42*/        if (value == null || value.equals("") || ((Punto)value).getPunCodigo() == null || String.valueOf(((Punto)value).getPunNombre()).equals("")) {
/*  44*/            return "";
                } else {
/*  46*/            System.out.println("ENTRO A getAsString");
/*  47*/            return String.valueOf(((Punto)value).getPunNombre());
                }
            }
}
