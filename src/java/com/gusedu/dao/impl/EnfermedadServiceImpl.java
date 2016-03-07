// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   EnfermedadServiceImpl.java

package com.gusedu.dao.impl;

import com.gusedu.dao.*;
import com.gusedu.model.*;
import com.gusedu.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EnfermedadServiceImpl implements EnfermedadService, Serializable {

            private static final long serialVersionUID = 1L;
            EntityManager em;


    @Override
    public List<Enfermedad> getAll() {
        System.out.println("Se ejecuta getAll - EnfermedadServiceImpl");
        List<Enfermedad> result = new ArrayList<>();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String sql = "SELECT e FROM Enfermedad e";
            Query q = sesion.createQuery(sql);
            result = q.list();
            //tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("Error : "+e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return result;
    }
    
            @Override
    public List<EnfermedadPar> getAllPar() {
        System.out.println("Se ejecuta getAllPar - EnfermedadServiceImpl");
        List<EnfermedadPar> result = new ArrayList<>();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String sql = "SELECT e FROM EnfermedadPar e";
            Query q = sesion.createQuery(sql);
            result = q.list();
            for (int i = 0; i < result.size(); i++) {
                System.out.println("getAllPar : " +result.get(i).getEnfermedad().getEnfNombre()+ " " + result.get(i).getPar().getPuntoByPunCodigoP1().getPunNombre() + " " + result.get(i).getPar().getPuntoByPunCodigoP2().getPunNombre());
            }
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
    public boolean saveEnfermedad(Enfermedad enfermedad) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(enfermedad);
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
    public boolean saveEnfermedadPar(EnfermedadPar enfermedadPar) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(enfermedadPar);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de saveEnfermedadPar : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }      
        return resultado; 
    }


            @Override
    public boolean updateEnfermedadPar(EnfermedadPar enfermedadPar) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.merge(enfermedadPar);
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
    public boolean deleteEnfermedadPar(EnfermedadPar enfermedadPar) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.delete(enfermedadPar);
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
    public Enfermedad getById(Integer idEnfermedad) 
    {      
        Enfermedad enfermedadservice = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.beginTransaction();
            enfermedadservice = (Enfermedad) sesion.load(Enfermedad.class, idEnfermedad);
            System.out.println(enfermedadservice.getEnfNombre());

        } catch (Exception e) {


        } finally {
            sesion.flush();
            sesion.close();
        }
        return enfermedadservice;
    }

            public EnfermedadPar getByIdPar(Integer idEnfermedadPar) {
/* 113*/        return (EnfermedadPar)em.find(EnfermedadPar.class, idEnfermedadPar);
            }
    
            @Override
    public EnfermedadPar getByParameters(Integer enfermedadpar) 
    {      
        EnfermedadPar enfermedadservice = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.beginTransaction();
            enfermedadservice = (EnfermedadPar) sesion.load(EnfermedadPar.class, enfermedadpar);
            System.out.println(enfermedadservice.getEnfermedad().getEnfNombre()+enfermedadservice.getPar().getPuntoByPunCodigoP1().getPunNombre()+
                    enfermedadservice.getPar().getPuntoByPunCodigoP2().getPunNombre());

        } catch (Exception e) {


        } finally {
            sesion.flush();
            sesion.close();
        }
        return enfermedadservice;
    }        
            
            @Override
    public boolean updateEnfermedad(Enfermedad enfermedad) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.merge(enfermedad);
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
    public boolean deleteEnfermedad(Enfermedad enfermedad) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.delete(enfermedad);
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

            public boolean saveSintomaPar(SintomaPar sintomaPar) {
/* 162*/        boolean resultado = false;
/* 170*/        return resultado;
            }

            public boolean deleteSintomaPar(SintomaPar sintomaPar) {
/* 175*/        boolean resultado = false;
/* 184*/        return resultado;
            }

            public SintomaPar getByParameters(Sintoma sintoma, Par par) {
/* 189*/        SintomaPar result = null;
/* 200*/        return result;
            }

            public Enfermedad getByNombre(String nombre) {
                Enfermedad result=null;
               

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String sql = "SELECT e FROM Enfermedad e WHERE e.enfNombre=:nombre";
/* 212*/        Query q = sesion.createQuery(sql);
/* 213*/        q.setParameter("nombre", nombre);
/* 214*/        result = (Enfermedad)q.uniqueResult();
            //tx.commit();
          
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
/* 224*/        return result;
            }

            public boolean saveEnfermedadTerapia(EnfermedadTerapia enfermedadTerapia) {
/* 229*/        boolean resultado = false;
/* 237*/        return resultado;
            }

            public boolean deleteEnfermedadTerapia(EnfermedadTerapia enfermedadTerapia) {
/* 242*/        boolean resultado = false;
/* 250*/        return resultado;
            }
    
            @Override
    public List<Enfermedad> getEnfermedadbyEnfer(String codigo) 
    {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Enfermedad> result = new ArrayList<>();
        try {
             String sql = "SELECT e FROM Enfermedad e WHERE e.enfNombre =:codigo";
             Query q = sesion.createQuery(sql);
             q.setParameter("usuario", codigo);
             result =  q.list();      
             for(int j=0;j<result.size();j++)
             {
                 System.out.println(result.get(j).getEnfCodigo());
             }        
        } catch (Exception e) 
        {
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }      
        return result;
    }
}
