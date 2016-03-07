// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   AuthServiceImpl.java

package com.gusedu.dao.impl;

import com.gusedu.dao.AuthService;
import com.gusedu.model.Usuario;
import com.gusedu.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

public class AuthServiceImpl implements AuthService {


            @Override
            public Usuario find(String username, String password) {
/*  23*/        Usuario result = null;
/*  25*/        Session sesion = HibernateUtil.getSessionFactory().openSession();
 try {
             //tx = sesion.beginTransaction();
        String sql = "SELECT u FROM Usuario u WHERE u.usuUsuario=:usuario AND u.usuPassword=:password";
        Query query = sesion.createQuery(sql);
         query.setString("usuario", username);
         query.setString("password", password);
         result=(Usuario)query.uniqueResult();
         System.out.println("Usuario : "+ result.getTipoUsuario().getTusDescripcion());
         if(result.getPersona()!=null)
         {
             System.out.println("Persona : "+result.getPersona().getPerNombres());
         }
     
             // tx.commit();
         
        } catch (Exception e) {
            /*if (tx != null) {
                tx.rollback();
            }   */
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }



/*  51*/        return result;
            }
}
