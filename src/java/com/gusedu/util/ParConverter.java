// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   PuntoConverter.java

package com.gusedu.util;

import com.gusedu.dao.ParService;
import com.gusedu.dao.impl.ParServiceImpl;
import com.gusedu.entidad.ParX;
import com.gusedu.model.Par;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("parConverter")
public class ParConverter
    implements Converter {

            ParService parService;


            public Object getAsObject(FacesContext context, UIComponent component, String value) {
/*  26*/        System.out.println("ENTRO A getAsObject");
                parService = new ParServiceImpl();
/*  27*/        if (value == null || value.isEmpty() || value.equals("null")) {
/*  28*/            return null;
                }
/*  31*/        System.out.println("Value: " + value);
/*  32*/       try{			
			ParX par = new ParX();
                        Par p = new Par();
                        p= parService.SP_FIND_PAR(value);
                        if(p.getParCodigo()!=null || p.getParCodigo()!=0)
                        {
                            par.setCod_par(p.getParCodigo());
                        }
                        
			return par;
		}catch(Exception e){
			e.printStackTrace();
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El par elegido no es válido"));
		}
            }

            public String getAsString(FacesContext context, UIComponent component, Object value) {
/*  42*/        if (value == null || value.equals("") || ((ParX)value)== null || String.valueOf(((ParX)value).getNombre()).equals("")) {
/*  44*/            return "";
                } else {
/*  46*/            System.out.println("ENTRO A getAsString");
/*  47*/            return String.valueOf(((ParX)value).getNombre());
                }
            }
}
