// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   SintomaServiceImpl.java

package com.gusedu.dao.impl;

import com.gusedu.dao.SintomaService;
import com.gusedu.entidad.ESintomaTerapia;
import com.gusedu.model.SintomaPar;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaTerapia;
import com.gusedu.util.HibernateUtil;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SintomaServiceImpl implements SintomaService, Serializable {

    private static final long serialVersionUID = 1L;
    EntityManager em;


            @Override
    public List<Sintoma> getAll() 
    {
        List<Sintoma> result= new ArrayList();

        boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
             String sql = "SELECT s FROM Sintoma s";
/*  40*/        Query q = sesion.createQuery(sql);
/*  41*/        result = q.list();
            //tx.commit();
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
        return result;
    }
    
    @Override
        public List<SintomaPar> getAllPar() {
        List<SintomaPar> result = new ArrayList<>();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String sql = "SELECT s FROM SintomaPar s";
            Query q = sesion.createQuery(sql);
            result = q.list();
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i).getSintoma().getSinDescripcion()+ " " + result.get(i).getPar().getPuntoByPunCodigoP1().getPunNombre() + " " + result.get(i).getPar().getPuntoByPunCodigoP2().getPunNombre());
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
    public boolean saveSintoma(Sintoma sintoma) 
    {
        boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(sintoma);
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
    public boolean updateSintoma(Sintoma sintoma) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.merge(sintoma);
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
    public boolean deleteSintoma(Sintoma sintoma) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.delete(sintoma);
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
    public Sintoma getById(Integer idSintoma) 
    {
        Sintoma sintomaservice = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.beginTransaction();
            sintomaservice = (Sintoma) sesion.load(Sintoma.class, idSintoma);
            System.out.println(sintomaservice.getSinDescripcion());

        } catch (Exception e) {


        } finally {
            sesion.flush();
            sesion.close();
        }
        return sintomaservice;
    }

            public Sintoma getByNombre(String nombre) {
                Sintoma result=null;
                
              

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
             String sql = "SELECT s FROM Sintoma s WHERE s.sinDescripcion=:nombre";
/* 120*/        Query q = sesion.createQuery(sql);
/* 121*/        q.setParameter("nombre", nombre);
/* 123*/        result = (Sintoma)q.uniqueResult();
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
 
/* 132*/        return result;
            }

            public boolean saveSintomaTerapia(SintomaTerapia sintomaTerapia) {
/* 137*/        boolean resultado = false;
/* 145*/        return resultado;
            }

            public Sintoma lastSintoma() {
/* 150*/        Sintoma result = null;
/* 161*/        return result;
            }         
    
            @Override
    public boolean saveSintomaPar(SintomaPar sintomaPar) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(sintomaPar);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de saveSintomaPar : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }      
        return resultado; 
    }


            @Override
    public boolean updateSintomaPar(SintomaPar sintomaPar) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.merge(sintomaPar);
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
    public boolean deleteSintomaPar(SintomaPar sintomaPar) 
    {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.delete(sintomaPar);
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
    public SintomaPar getByParameters(Integer sintomapar) 
    {      
        SintomaPar sintomaservice = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {
            sesion.beginTransaction();
            sintomaservice = (SintomaPar) sesion.load(SintomaPar.class, sintomapar);
            System.out.println(sintomaservice.getSintoma().getSinDescripcion()+sintomaservice.getPar().getPuntoByPunCodigoP1().getPunNombre()+
                    sintomaservice.getPar().getPuntoByPunCodigoP2().getPunNombre());

        } catch (Exception e) {


        } finally {
            sesion.flush();
            sesion.close();
        }
        return sintomaservice;
    }        

    @Override
    public List<ESintomaTerapia> SP_MatrizSintomaxTerapia(int cod_cli) {
        System.out.println("Se ejecuta SP_MatrizSintomaxTerapia - SintomaServiceImpl");
        List<ESintomaTerapia> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
          
             Query q = session.createSQLQuery("{ CALL SP_MatrizSintomaxTerapia(:cod_cli) }");
             q.setParameter("cod_cli", cod_cli);
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
				String fecha = ((String) result[0]);
				String sintoma = (String) result[1];
                                Boolean estado = (Boolean) result[2];
				lista.add(new ESintomaTerapia(fecha, sintoma, estado));
			}
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista;     
    }
}
