/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao.impl;

import com.gusedu.dao.TerapiaSintomaService;
import com.gusedu.model.Cliente;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaSintoma;
import com.gusedu.util.HibernateUtil;
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
public class TerapiaSintomaServiceImpl implements TerapiaSintomaService {

            EntityManager em;
    
            @Override
        public List<TerapiaSintoma> getAllSintoma() {
                System.out.println("Ser ejecuta getAllSintoma - TerapiaSintomaServiceImpl");
        List<TerapiaSintoma> result = new ArrayList<>();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String sql = "SELECT e FROM TerapiaSintoma e";
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
           // sesion.flush();
            //sesion.close();
        }
        return result;
    }

            @Override
    public TerapiaSintoma getByParameters(Integer terapiasintoma) 
    {      
        TerapiaSintoma terapiasintomaService = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.beginTransaction();
            terapiasintomaService = (TerapiaSintoma) sesion.load(TerapiaSintoma.class, terapiasintoma);
            System.out.println(terapiasintomaService.getTerapia().getTerDescripcion() + " " + terapiasintomaService.getSintoma().getSinDescripcion());
        } catch (Exception e) {


        } finally {
            sesion.flush();
            sesion.close();
        }
        return terapiasintomaService;
    }  
    
            @Override
    public boolean saveTerapiaSintoma(TerapiaSintoma terapiaSintoma) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(terapiaSintoma);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de saveTerapiaSintoma : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }      
        return resultado; 
    }

            @Override
    public boolean updateTerapiaSintoma(TerapiaSintoma terapiaSintoma) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.merge(terapiaSintoma);
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
    public boolean deleteTerapiaSintoma(TerapiaSintoma terapiaSintoma) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.delete(terapiaSintoma);
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
    public List<TerapiaSintoma> getAllTerapiaSintoma(Terapia ter) 
    {
        List<TerapiaSintoma> result = new ArrayList();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String sql = "SELECT t FROM TerapiaSintoma t WHERE t.terapia.terCodigo=:terapia";
            Query q = sesion.createQuery(sql);
            q.setParameter("terapia", ter.getTerCodigo());
            result = q.list();
            for (int i = 0; i < result.size(); i++) 
            {
                System.out.println(((TerapiaSintoma)result.get(i)).getSintoma().getSinDescripcion());
            }
        } catch (Exception e) {
            if (tx != null) 
            {
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
    public TerapiaSintoma buscarTerapiaSintoma(Cliente cliente) 
    {
        TerapiaSintoma result = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            Query q = sesion.createQuery("SELECT ts FROM TerapiaSintoma ts where ts.terapia.visita.cliente.cliCodigo = :cliente");
            q.setParameter("cliente", cliente.getCliCodigo());
            Cliente cli = cliente;
            result = (TerapiaSintoma)q.uniqueResult();
            System.out.println(result.getSintoma().getSinCodigo());    
        } catch (Exception e) {
            System.out.println("ERROR buscarTerapiaSintoma"+e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return result;
    }
}
