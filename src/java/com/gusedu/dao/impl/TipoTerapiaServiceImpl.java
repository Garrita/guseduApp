/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao.impl;

import com.gusedu.dao.TipoTerapiaService;
import com.gusedu.model.TipoTerapia;
import com.gusedu.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class TipoTerapiaServiceImpl implements TipoTerapiaService, Serializable {

            private static final long serialVersionUID = 1L;
            EntityManager em;


            @Override
    public List<TipoTerapia> getAll() {
        List<TipoTerapia> result = new ArrayList<>();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String sql = "SELECT t FROM TipoTerapia t";
            Query q = sesion.createQuery(sql);
            result = q.list();
            //tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println(e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return result;
    }
    
            @Override
    public boolean saveTipoTerapia(TipoTerapia tipoterapia) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(tipoterapia);
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

            @Override
    public boolean updateTipoTerapia(TipoTerapia tipoterapia) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.merge(tipoterapia);
            tx.commit();
           
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

            @Override
    public boolean deleteTipoTerapia(TipoTerapia tipoterapia) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.delete(tipoterapia);
            tx.commit();
           
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

            @Override
    public TipoTerapia getByNombre(String nombre) 
    {
        TipoTerapia result=null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String sql = "SELECT * FROM TipoTerapia AS t WHERE t.tteNombre=:nombre";
            Query q = sesion.createQuery(sql);
            q.setParameter("nombre", nombre);
            result = (TipoTerapia)q.uniqueResult();
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
          return result;
    }
    
            @Override
    public TipoTerapia getById(Integer idTipoTerapia) 
    {      
        TipoTerapia tipoterapiaservice = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.beginTransaction();
            tipoterapiaservice = (TipoTerapia) sesion.load(TipoTerapia.class, idTipoTerapia);
            System.out.println(tipoterapiaservice.getTteNombre());

        } catch (Exception e) {


        } finally {
            sesion.flush();
            sesion.close();
        }
        return tipoterapiaservice;
    }

    @Override
    public boolean SP_InsertarTipoterapia(TipoTerapia tipoterapia) {
        boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL SP_InsertarTipoterapia(:nombre) }");
             q.setParameter("nombre", tipoterapia.getTteNombre());
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SP_InsertarTipoterapia : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }
}
