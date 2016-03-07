// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   TerapiaParServiceImpl.java

package com.gusedu.dao.impl;

import com.gusedu.dao.TerapiaParService;
import com.gusedu.model.TerapiaPar;
import com.gusedu.util.HibernateUtil;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TerapiaParServiceImpl
    implements TerapiaParService {

            EntityManager em;


            public boolean saveTerapia(TerapiaPar terapia) {
/*  23*/        boolean resultado = false;
/*  25*/        try {
/*  25*/            em.persist(terapia);
/*  26*/            resultado = true;
                }
/*  27*/        catch (Exception e) {
/*  28*/            System.out.println((new StringBuilder()).append("ERROR: ").append(e.getMessage()).toString());
/*  29*/            resultado = false;
                }
/*  31*/        return resultado;
            }

            public boolean updateTerapia(TerapiaPar Terapia) {
                boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.merge(Terapia);
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

/*  53*/        return resultado;
            }

            public boolean deleteTerapia(TerapiaPar terapia) {
/*  58*/        boolean resultado = false;
/*  60*/        try {
/*  60*/            em.remove(terapia);
/*  61*/            resultado = true;
                }
/*  62*/        catch (Exception e) {
/*  63*/            System.out.println((new StringBuilder()).append("ERROR: ").append(e.getMessage()).toString());
/*  64*/            resultado = false;
                }
/*  66*/        return resultado;
            }

    @Override
    public boolean SPsaveTerapiaPar(TerapiaPar terapiapar) {
       boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL CrearPrePar(:cod_terapia,:cod_par) }");
             q.setParameter("cod_terapia", terapiapar.getTerapia().getTerCodigo());
             q.setParameter("cod_par", terapiapar.getPar().getParCodigo());
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SPsaveTerapiaPar : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }
    
            @Override
    public TerapiaPar getByParameters(Integer enfermedadpar) 
    {      
        TerapiaPar terapiaService = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.beginTransaction();
            terapiaService = (TerapiaPar) sesion.load(TerapiaPar.class, enfermedadpar);
            System.out.println(terapiaService.getTerapia().getTerDescripcion() +"-"+terapiaService.getPar().getPuntoByPunCodigoP1().getPunNombre()+" " +
                    terapiaService.getPar().getPuntoByPunCodigoP2().getPunNombre());

        } catch (Exception e) {


        } finally {
            sesion.flush();
            sesion.close();
        }
        return terapiaService;
    }        
          
            @Override
    public boolean deleteTerapiaPar(TerapiaPar terapiaPar) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.delete(terapiaPar);
            tx.commit();
           
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de deleteTerapiaPar : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return resultado;
    }
}
