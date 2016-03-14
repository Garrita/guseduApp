/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao.impl;

import com.gusedu.dao.EventoService;
import com.gusedu.model.Evento;
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
public class EventoServiceImpl implements EventoService, Serializable {

            private static final long serialVersionUID = 1L;
            EntityManager em;
            
            @Override
    public List<Evento> getAll() {
        List<Evento> result = new ArrayList<>();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String sql = "SELECT t FROM Evento t";
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
    public boolean saveEvento(Evento evento) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(evento);
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
    public boolean updateEvento(Evento evento) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.merge(evento);
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
    public boolean deleteEvento(Evento evento) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.delete(evento);
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
    public Evento getByNombre(String nombre) 
    {
        Evento result=null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String sql = "SELECT * FROM Evento AS t WHERE t.tteNombre=:nombre";
            Query q = sesion.createQuery(sql);
            q.setParameter("nombre", nombre);
            result = (Evento)q.uniqueResult();
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
    public Evento getById(Integer idEvento) 
    {      
        Evento eventoservice = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.beginTransaction();
            eventoservice = (Evento) sesion.load(Evento.class, idEvento);
            System.out.println(eventoservice.getDescripcion());

        } catch (Exception e) {


        } finally {
            sesion.flush();
            sesion.close();
        }
        return eventoservice;
    }
    
            @Override
    public boolean SP_InsertarEvento(Evento evento) {
        boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL SP_InsertarEvento(:descripcion) }");
             q.setParameter("descripcion", evento.getDescripcion());
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SP_InsertarEvento : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }    
}
