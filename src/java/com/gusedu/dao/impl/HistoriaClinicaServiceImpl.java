// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   HistoriaClinicaServiceImpl.java

package com.gusedu.dao.impl;

import com.gusedu.dao.HistoriaClinicaService;
import com.gusedu.model.HistoriaClinica;
import com.gusedu.model.Visita;
import com.gusedu.util.HibernateUtil;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HistoriaClinicaServiceImpl
    implements HistoriaClinicaService {

            EntityManager em;


          public boolean saveHistoriaClinica(HistoriaClinica historiaClinica) {
        boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(historiaClinica);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de saveHistoriaClinica : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }

        return resultado;
    }

            public HistoriaClinica getHistoriaByVisita(Visita visita) {
                HistoriaClinica result=null;
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            
            String sql = "SELECT hc FROM HistoriaClinica hc WHERE hc.visita.visCodigo=:visita";
            Query q = sesion.createQuery(sql);
            q.setParameter("visita", visita.getVisCodigo());
            result = (HistoriaClinica)q.uniqueResult();
          
        } catch  (Exception e) {
            if(tx!=null)
            {
                tx.rollback();System.out.println(e.getMessage());
            }System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
/*  74*/        return result;
            }
}
