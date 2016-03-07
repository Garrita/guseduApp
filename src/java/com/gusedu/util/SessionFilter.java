// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   SessionFilter.java

package com.gusedu.util;

import com.gusedu.model.TipoUsuario;
import com.gusedu.model.Usuario;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter
    implements Filter {


            public void destroy() {
            }

            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/*  25*/        HttpServletRequest httpRequest = (HttpServletRequest)request;
/*  26*/        HttpServletResponse httpResponse = (HttpServletResponse)response;
/*  27*/        String urlSolicitada = httpRequest.getRequestURI();
/*  28*/        System.out.println((new StringBuilder()).append("urlSolicitada: ").append(urlSolicitada).toString());
/*  29*/        Usuario user = (Usuario)httpRequest.getSession().getAttribute("userLogged");
/*  30*/        if (user == null) {
/*  31*/            if (urlSolicitada.contains("mobile") || urlSolicitada.contains("web") || urlSolicitada.contains("seguridad")) {
/*  32*/                httpResponse.sendRedirect("/gusedu/home.jsf");
/*  33*/                return;
                    } else {
/*  35*/                chain.doFilter(request, response);
/*  36*/                return;
                    }
                }
/*  39*/        if (urlSolicitada.contains("seguridad") && user.getTipoUsuario().getTusCodigo().intValue() != 3) {
/*  43*/            httpResponse.sendRedirect("/gusedu/web/Principal.jsf");
/*  44*/            return;
                } else {
/*  48*/            chain.doFilter(request, response);
/*  49*/            return;
                }
            }

            public void init(FilterConfig filterconfig) throws ServletException {
            }
}
