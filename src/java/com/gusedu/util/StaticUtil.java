// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   StaticUtil.java

package com.gusedu.util;

import com.gusedu.model.Usuario;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

public class StaticUtil {

            public static final String PATTERN_NUMEROS = ".*[^0-9].*";


            public static void correctMesage(String titulo, String mensaje) {
/*  26*/        FacesContext context = FacesContext.getCurrentInstance();
/*  27*/        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje));
            }

            public static void errorMessage(String titulo, String mensaje) {
/*  32*/        FacesContext context = FacesContext.getCurrentInstance();
/*  33*/        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, mensaje));
            }

            public static String userLogged() {
/*  38*/        FacesContext context = FacesContext.getCurrentInstance();
/*  39*/        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
/*  40*/        String username = ((Usuario)(Usuario)request.getSession().getAttribute("userLogged")).getUsuEmpresa();
/*  41*/        return username;
            }
            
            public static String usuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        String username = ((Usuario)(Usuario)request.getSession().getAttribute("userLogged")).getUsuUsuario();
        return username;
            }
            
    public static String usuario_nombre() {
           FacesContext context = FacesContext.getCurrentInstance();
           HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
           Usuario usuario = (Usuario)request.getSession().getAttribute("userLogged");
           String nom="";
           if(usuario.getPersona()!=null)
           {
               nom=usuario.getPersona().getPerNombres()+" "+
                       usuario.getPersona().getPerApellidoP()+" "+
                       usuario.getPersona().getPerApellidoM();
           }
        return nom;
    }
    
    public static String usuario_tipo() {
           FacesContext context = FacesContext.getCurrentInstance();
           HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
           Usuario usuario = (Usuario)request.getSession().getAttribute("userLogged");
           String nom=usuario.getTipoUsuario().getTusDescripcion();
        return nom;
    }

            public static Date getFechaActual() {
/*  45*/        Timestamp stamp = new Timestamp(System.currentTimeMillis());
/*  46*/        Date date = new Date(stamp.getTime());
/*  47*/        return date;
            }

            public static void keepMessages() {
/*  51*/        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
/*  52*/        context.getFlash().setKeepMessages(true);
            }

            public static String signoZodiacal(int mes, int dia) {
/*  56*/        switch (mes + 1) {
/*  58*/        case 1: // '\001'
/*  58*/            if (dia >= 21) {
/*  59*/                return "Acuario";
                    } else {
/*  61*/                return "Capricornio";
                    }

/*  63*/        case 2: // '\002'
/*  63*/            if (dia >= 20) {
/*  64*/                return "Piscis";
                    } else {
/*  66*/                return "Acuario";
                    }

/*  68*/        case 3: // '\003'
/*  68*/            if (dia >= 21) {
/*  69*/                return "Aries";
                    } else {
/*  71*/                return "Piscis";
                    }

/*  73*/        case 4: // '\004'
/*  73*/            if (dia >= 21) {
/*  74*/                return "Tauro";
                    } else {
/*  76*/                return "Aries";
                    }

/*  78*/        case 5: // '\005'
/*  78*/            if (dia >= 21) {
/*  79*/                return "G\uFFFDminis";
                    } else {
/*  81*/                return "Tauro";
                    }

/*  84*/        case 6: // '\006'
/*  84*/            if (dia >= 21) {
/*  85*/                return "C\uFFFDncer";
                    } else {
/*  87*/                return "G\uFFFDminis";
                    }

/*  89*/        case 7: // '\007'
/*  89*/            if (dia >= 23) {
/*  90*/                return "Leo";
                    } else {
/*  92*/                return "C\uFFFDncer";
                    }

/*  94*/        case 8: // '\b'
/*  94*/            if (dia >= 23) {
/*  95*/                return "Virgo";
                    } else {
/*  97*/                return "Leo";
                    }

/*  99*/        case 9: // '\t'
/*  99*/            if (dia >= 23) {
/* 100*/                return "Libra";
                    } else {
/* 102*/                return "Virgo";
                    }

/* 104*/        case 10: // '\n'
/* 104*/            if (dia >= 23) {
/* 105*/                return "Escorpio";
                    } else {
/* 107*/                return "Libra";
                    }

/* 109*/        case 11: // '\013'
/* 109*/            if (dia >= 23) {
/* 110*/                return "Sagitario";
                    } else {
/* 112*/                return "Escorpio";
                    }

/* 114*/        case 12: // '\f'
/* 114*/            if (dia >= 22) {
/* 115*/                return "Capricornio";
                    } else {
/* 117*/                return "Sagitario";
                    }
                }
/* 119*/        return null;
            }

            public static Date sumarRestarDiasFecha(Date fecha, int mes, String tiempo) {
/* 124*/        Calendar calendar = Calendar.getInstance();
/* 125*/        calendar.setTime(fecha);
/* 126*/        if (tiempo.equals("Dias")) {
/* 128*/            calendar.add(6, mes);
                } else {
/* 131*/            calendar.add(2, mes);
                }
/* 133*/        return calendar.getTime();
            }

            public static long diasRestantes(Date fecha) {
/* 138*/        long MILLSECS_PER_DAY = 0x5265c00L;
/* 139*/        Date hoy = new Date();
/* 140*/        hoy.setHours(0);
/* 141*/        hoy.setMinutes(0);
/* 142*/        hoy.setSeconds(0);
/* 143*/        fecha.setHours(23);
/* 144*/        fecha.setMinutes(59);
/* 145*/        fecha.setSeconds(59);
/* 146*/        long diferencia = (fecha.getTime() - hoy.getTime()) / 0x5265c00L;
/* 147*/        if (diferencia < 0L) {
/* 148*/            diferencia = 0L;
                }
/* 150*/        return diferencia;
            }

            public static boolean esSoloNumero(String texto) {
/* 155*/        Pattern pattern = Pattern.compile(".*[^0-9].*");
/* 156*/        Matcher matcher = pattern.matcher(texto);
/* 157*/        return !matcher.find();
            }

            public static void execute(String dialogVar) {
/* 161*/        RequestContext context = RequestContext.getCurrentInstance();
/* 162*/        context.execute((new StringBuilder()).append("PF('").append(dialogVar).append("').show();").toString());
            }

            public static String calculoIMC(double imc) {
/* 168*/        String salida = "";
/* 169*/        if (imc <= 16D) {
/* 171*/            salida = "Bajo peso (Delgadez severa)";
                }
/* 173*/        if (imc >= 16D && imc <= 16.989999999999998D) {
/* 175*/            salida = "Bajo peso (Delgadez moderada)";
                }
/* 177*/        if (imc >= 17D && imc <= 18.489999999999998D) {
/* 179*/            salida = "Bajo peso (Delgadez leve)";
                }
/* 181*/        if (imc >= 18.5D && imc <= 24.989999999999998D) {
/* 183*/            salida = "Peso normal";
                }
/* 185*/        if (imc >= 25D && imc <= 29.989999999999998D) {
/* 187*/            salida = "Sobrepeso";
                }
/* 189*/        if (imc >= 30D && imc <= 34.990000000000002D) {
/* 191*/            salida = "Obesidad leve";
                }
/* 193*/        if (imc >= 35D && imc <= 39.990000000000002D) {
/* 195*/            salida = "Obesidad media";
                }
/* 197*/        if (imc >= 40D) {
/* 199*/            salida = "Obesidad mórbida";
                }
/* 201*/        return salida;
            }
            
            public static String removeDiacriticalMarks(String string) {
    return Normalizer.normalize(string, Form.NFD)
        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
}
            
            public static String convertirDateToString(Date date)
            {
                String salida="";
                DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		salida = fecha.format(date);
                return salida;
            }
            
            public static Date convertirFecha(String fec)
            {
                	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	 Date date=null;

	try {

		 date = formatter.parse(fec);
	

	} catch (Exception e) {
		e.printStackTrace();
	}
        return date;
            }
                public static String cortar(String cadena,int n)
    {
        String salida="";
        String [] arreglo = cadena.split("\\|");
           for (int i = 0; i < arreglo.length; i++) {
            if(i==n)
            {
                salida=arreglo[i];
            }
        }
          return salida;
    }
            
}
