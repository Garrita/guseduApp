// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   PuntoServiceImpl.java

package com.gusedu.dao.impl;

import com.gusedu.dao.PuntoService;
import com.gusedu.model.Punto;
import com.gusedu.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PuntoServiceImpl
    implements PuntoService, Serializable {

            private static final long serialVersionUID = 1L;
            EntityManager em;


            @Override
            public List<Punto> getAllPuntos() {
                System.out.println("Se eejcuto getAllPuntos - PuntoServiceImpl");
         List<Punto> result = new ArrayList();
                Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
              String sql = "SELECT p FROM Punto p";
           Query q = sesion.createQuery(sql);
            result = q.list();
             // tx.commit();
         
        } catch (Exception e) {
            /*if (tx != null) {
                tx.rollback();
            }   */
            System.out.println(e.getMessage());
        } finally {
           // sesion.flush();
           // sesion.close();
        }
       /* try {
           String sql = "SELECT p FROM Punto p";
           Query q = sesion.createQuery(sql);
            result = q.list();
                }
        catch (NoResultException e) {
            System.out.println((new StringBuilder()).append("ERROR de paresByPunto: ").append(e.getMessage()).toString());
                }*/
           return result;
            }

            public Punto puntoById(Integer id) {
                
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
                 Punto pun=null;
        try {
                    pun= (Punto) sesion.load(Punto.class, id);
                    if(pun!=null){
                        System.out.println(pun.getPunNombre());
                    }
        } catch (Exception e) {
         
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
/*  47*/        return pun;
            }

            public Boolean savePunto(Punto punto) {
/*  52*/    boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(punto);
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

            public Boolean updatePunto(Punto punto) {
/*  65*/         boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.merge(punto);
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

            public Boolean deletePunto(Punto punto) {
/*  78*/      boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.delete(punto);
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

            public Punto puntoByNombre(String nombrePunto) {
         Punto result = null;
         Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
             String sql = "SELECT p FROM Punto p WHERE p.punNombre=:nombre";
             Query q = sesion.createQuery(sql);
             q.setParameter("nombre", nombrePunto);
            result = (Punto)q.uniqueResult();
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
       /* try {
            String sql = "SELECT p FROM Punto p WHERE p.punNombre=:nombre";
             Query q = sesion.createQuery(sql);
             q.setParameter("nombre", nombrePunto);
            result = (Punto)q.uniqueResult();
                }
         catch (NoResultException e) {
             System.out.println((new StringBuilder()).append("ERROR de paresByPunto: ").append(e.getMessage()).toString());
             result = null;
                }*/
         return result;
            }

            public List<Punto> getAllOrdenAlfabeticoAsc() {
/* 109*/        List<Punto> result = new ArrayList();

                Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
              String sql = "SELECT p FROM Punto p  order by p.punNombre asc";
           Query q = sesion.createQuery(sql);
           result = q.list();
            for(int i=0;i<result.size();i++)
            {
                System.out.println(result.get(i).getPunCodigo());
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



       /*try {
            String sql = "SELECT p FROM Punto p  order by p.punNombre asc";
           Query q = sesion.createQuery(sql);
           result = q.list();
                }
       catch (NoResultException e) {
            System.out.println((new StringBuilder()).append("ERROR de paresByPunto: ").append(e.getMessage()).toString());
                }*/
        return result;
            }

            public List<Punto> getAllOrdenAlfabeticoDesc() {
/* 125*/        List<Punto> result = new ArrayList();

                Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
              String sql = "SELECT p FROM Punto p WHERE (p.punOrdenFisico>0) order by p.punNombre desc";
           Query q = sesion.createQuery(sql);
           result = q.list();
            for(int i=0;i<result.size();i++)
            {
                System.out.println(result.get(i).getPunCodigo());
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
/* 132*/        return result;
            }

            public List<Punto> getAllOrdenGoiz() {
/* 138*/          List<Punto> result = new ArrayList();

                Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
              String sql = "SELECT p FROM Punto p WHERE (p.punOrdenFisico>0) order by p.punOrdenFisico asc";
           Query q = sesion.createQuery(sql);
           result = q.list();
            for(int i=0;i<result.size();i++)
            {
                System.out.println(result.get(i).getPunCodigo());
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
/* 145*/        return result;
            }

            public List<Punto> getAllPuntosRastreables() {
                
        List<Punto> result = new ArrayList();
        Session sesion = HibernateUtil.getSessionFactory().openSession();

        try {

           String sql = "SELECT p FROM Punto p WHERE (p.punOrdenFisico>0)";
           Query q = sesion.createQuery(sql);
           result = q.list();
           if(result.size()>0)
           {
                System.out.println(result.get(0).getPunCodigo());
           }
         
        } catch (Exception e) {
            /*if (tx != null) {
                tx.rollback();
            }   */
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return result;
            }

            
    // 0 = ASC , 1= DESC  , 2= GOIZ        
    @Override
    public List<Punto> SP_ORDENAMIENTO_PUNTO(int valor) {
        System.out.println("Se ejecuta SP_ORDENAMIENTO_PUNTO - PuntoServiceImpl");
         List<Punto> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
    
             Query q = session.createSQLQuery("{ CALL SP_ORDENAMIENTO_PUNTO(:valor) }");
             q.setParameter("valor",valor);
			List<Object[]> d=q.list();
			for (Object[] result : d) {	
                            Punto pun = new Punto();
                            
				   int pun_codigo = (int) result[0];
                                   String pun_Nombre=(String) result[1];
                                   int pun_orden_fisico=(int) result[2];
                                   String pun_ubicacion_c=(String) result[3];
                                   String pun_ubicacion_m=(String) result[4];
                                   
                                   pun.setPunCodigo(pun_codigo);
                                   pun.setPunNombre(pun_Nombre);
                                   pun.setPunOrdenFisico(pun_orden_fisico);
                                   pun.setPunUbicacionC(pun_ubicacion_c);
                                   pun.setPunUbicacionM(pun_ubicacion_m);
                                   
                                   lista.add(pun);
                   	}
        } catch (Exception e) {
            System.out.println("Error getALLlistaClientePersona : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista; 
    }
}
