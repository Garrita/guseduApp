// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   TerapiaServiceImpl.java

package com.gusedu.dao.impl;

import com.gusedu.dao.TerapiaService;
import com.gusedu.model.*;
import com.gusedu.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TerapiaServiceImpl
    implements TerapiaService {

            EntityManager em;


            public boolean saveTerapia(Terapia terapia) {
/*  39*/        boolean resultado = false;
/*  47*/        return resultado;
            }

            @Override
            public boolean updateTerapia(Terapia terapia) {
                boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.merge(terapia);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de updateTerapia : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
 
       return resultado;
            }

            @Override
            public boolean deleteTerapia(Terapia terapia) {
/*  65*/        boolean resultado = false;
/*  73*/        return resultado;
            }

            public List<Terapia> terapiasPorVisita(Visita visita) {

       List<Terapia> result = new ArrayList();
                Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
              String sql = "SELECT t FROM Terapia t WHERE t.visita.visCodigo=:visita order by t.terFecRealizada desc";
            Query q = sesion.createQuery(sql);
           q.setParameter("visita", visita.getVisCodigo());
            result = q.list();
            for(int i=0;i<result.size();i++)
            {
                System.out.println(result.get(i).getTerCodigo());
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
       /* try {
            String sql = "SELECT t FROM Terapia t WHERE t.visita.visCodigo=:visita order by t.terFecRealizada desc";
            Query q = sesion.createQuery(sql);
           q.setParameter("visita", visita.getVisCodigo());
            result = q.list();
                }
        catch (NoResultException e) {
            System.out.println((new StringBuilder()).append("ERROR de getClientesPacientesByUsuario: ").append(e.getMessage()).toString());
                }*/
        return result;
            }

            public List<TipoTerapia>  getTipoTerapias() {
                System.out.println("sE EJECUTA getTipoTerapias - TerapiaServiceImpl");
       List<TipoTerapia>  result = new ArrayList();
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
             String sql = "SELECT t FROM TipoTerapia t";
           Query q = sesion.createQuery(sql);
           result = q.list();

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
      /*  try {
            String sql = "SELECT t FROM TipoTerapia t";
           Query q = sesion.createQuery(sql);
           result = q.list();
                }
       catch (NoResultException e) {
            System.out.println((new StringBuilder()).append("ERROR de getClientesPacientesByUsuario: ").append(e.getMessage()).toString());
                }*/
        return result;
            }

            public TipoTerapia tteById(Integer idTipoTerapia) {
                TipoTerapia tipo;
                Exception exception;
/* 115*/        tipo = null;
/* 116*/        Session sesion = HibernateUtil.getSessionFactory().openSession();
/* 117*/        Transaction tx = null;
/* 119*/        try {
/* 119*/            tx = sesion.beginTransaction();
/* 120*/            tipo = (TipoTerapia)sesion.load(TipoTerapia.class, idTipoTerapia);
/* 121*/            tx.commit();
                }
/* 122*/        catch (Exception e) {
/* 123*/            if (tx != null) {
/* 125*/                tx.rollback();
                    }
/* 127*/            throw new RuntimeException(e);
                }
/* 129*/        finally {
/* 129*/            sesion.close();
                }
 
/* 131*/        return tipo;
            }

            public Terapia terapiaById(Integer idTerapia) 
            {
                Terapia ter;
                Exception exception;
/* 115*/        ter = null;
/* 116*/        Session sesion = HibernateUtil.getSessionFactory().openSession();
/* 117*/        Transaction tx = null;
/* 119*/        try {
/* 119*/            tx = sesion.beginTransaction();
/* 120*/            ter = (Terapia)sesion.load(Terapia.class, idTerapia);
/* 121*/            tx.commit();
                }
/* 122*/        catch (Exception e) {
/* 123*/            if (tx != null) {
/* 125*/                tx.rollback();
                    }
/* 127*/            throw new RuntimeException(e);
                }
/* 129*/        finally {
/* 129*/            sesion.close();
                }
 
/* 131*/        return ter;
            }

            public List<EnfermedadTerapia> getEnfermedadesByTerapia(Terapia terapia) {
/* 141*/        List<EnfermedadTerapia> result = new ArrayList();
/* 149*/        return result;
            }

            public List<SintomaTerapia> getSintomasByTerapia(Terapia terapia) {
/* 155*/       List<SintomaTerapia> result = new ArrayList();
/* 163*/        return result;
            }

            public boolean saveTerapiaPar(TerapiaPar terapiaPar) {
                boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
           sesion.save(terapiaPar);
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
 
/* 185*/        return resultado;
            }

            public List<Par> getTerapiaParesFromTerapia(Terapia terapia) {
/* 191*/        List<Par> result = new ArrayList();
/* 199*/        return result;
            }

            public TerapiaPar TerapiaParByParAndTerapia(Terapia terapia, Par par) {
/* 204*/        TerapiaPar result = null;
/* 213*/        return result;
            }

            public List<Terapia> terapiasPorCliente(Cliente cliente) {
/* 219*/        List<Terapia> result = new ArrayList();
/* 228*/        return result;
            }

            public boolean saveSintomaVisita(SintomaVisita sintomavista) {
                boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(sintomavista);
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
                
/* 251*/        return resultado;
            }

            public boolean saveEnfermedadVisita(EnfermedadVisita enfermedadvista) {
                boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(enfermedadvista);
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
  
/* 274*/        return resultado;
            }

            public List<SintomaVisita> getAllSintomaxVisita(Visita vis) {
                System.out.println("Se ejecuta getAllSintomaxVisita - TerapiaServiceIOmpl");
               List<SintomaVisita> result = new ArrayList();
                
             

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String sql = "SELECT t FROM SintomaVisita t WHERE t.visita.visCodigo=:visita";
/* 287*/        Query q = sesion.createQuery(sql);
/* 288*/        q.setParameter("visita", vis.getVisCodigo());
/* 289*/        result = q.list();
/* 290*/        for (int i = 0; i < result.size(); i++) {
/* 292*/            System.out.println(((SintomaVisita)result.get(i)).getSintoma().getSinDescripcion());
                }
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

/* 301*/        return result;
            }

            public List<EnfermedadVisita> getAllEnfermedadxVisita(Visita vis) {
                System.out.println("Se ejecuta getAllEnfermedadxVisita - TerapiaServiceImpl");
                 List<EnfermedadVisita> result = new ArrayList();
                
             

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
           String sql = "SELECT t FROM EnfermedadVisita t WHERE t.visita.visCodigo=:visita";
/* 314*/        Query q = sesion.createQuery(sql);
/* 315*/        q.setParameter("visita", vis.getVisCodigo());
/* 316*/        result = q.list();
/* 317*/        for (int i = 0; i < result.size(); i++) {
/* 319*/            System.out.println(((EnfermedadVisita)result.get(i)).getEnfermedad().getEnfNombre());
                }
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
        
        
               
/* 328*/        return result;
            }

            @Override
            public List<TerapiaPar> getAllTerapiaParbyTerapia(Terapia terapia) {
                System.out.println("Se ejecuto getAllTerapiaParbyTerapia - TerapiaServiceImpl");
       List<TerapiaPar> result = new ArrayList();
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
              String sql = "SELECT t FROM TerapiaPar t WHERE t.terapia.terCodigo=:terapia";
            Query q = sesion.createQuery(sql);
           q.setParameter("terapia", terapia.getTerCodigo());
           result = q.list();
           for(int j=0;j<result.size();j++)
           {
               System.out.println(result.get(j).getPar().getPuntoByPunCodigoP1().getPunNombre()+"-"+result.get(j).getPar().getPuntoByPunCodigoP2().getPunNombre());
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
       /* try {
           String sql = "SELECT t FROM TerapiaPar t WHERE t.terapia.terCodigo=:terapia";
            Query q = sesion.createQuery(sql);
           q.setParameter("terapia", terapia.getTerCodigo());
           result = q.list();
            System.out.println((new StringBuilder()).append("ID TERAPIA = ").append(terapia.getTerCodigo()).toString());
           System.out.println((new StringBuilder()).append("TAMA\321O PARES : ").append(result.size()).toString());
                }
        catch (NoResultException e) {
            System.out.println((new StringBuilder()).append("ERROR de getAllTerapiaParbyTerapia: ").append(e.getMessage()).toString());
                }*/
       return result;
            }

            public List<TerapiaPar> getAllTerapiaParbyVisita(Visita visita) {
/* 354*/        List<TerapiaPar> result = new ArrayList();
/* 369*/        return result;
            }

            public String getAllParbyAllTerapia(Terapia terapia, Par par) {
/* 374*/        String resul = "";
/* 397*/        return resul;
            }

            public List<Terapia> getAllTerapiabyCliente(Cliente cli) {
/* 404*/        List result = new ArrayList();
/* 414*/        return result;
            }

            public Terapia lastTerapia(Cliente cliente) {
/* 419*/        Terapia result = null;
               /* 428*/        return result;
            }

            public boolean updateTerapiaPar(TerapiaPar terapiapar) {
/* 433*/        boolean resultado = false;
/* 441*/        return resultado;
            }

         
            public List<TerapiaPar> getAllParbyCliente(Cliente cliente) {
                System.out.println("Se ejecuta getAllParbyCliente - TerapiaServiceImpl");
        List<TerapiaPar> result = new ArrayList();
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
              String sql = "SELECT t FROM TerapiaPar t  WHERE t.terapia.visita.cliente.cliCodigo=:cliente";
           Query q = sesion.createQuery(sql);
            q.setParameter("cliente", cliente.getCliCodigo());
            result = q.list();
            for(int i=0;i<result.size();i++)
            {
                System.out.println(result.get(i).getPar().getPuntoByPunCodigoP1().getPunNombre()+" - "+result.get(i).getPar().getPuntoByPunCodigoP2().getPunNombre()+" "+result.get(i).getTerapia().getTerFecRealizada()+
                        result.get(i).getTerapia().getTipoTerapia().getTteNombre() + result.get(i).getTerapia().getTerDescripcion() + result.get(i).getTerapia().getVisita().getVisDescripcion());
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
     /* try {
            String sql = "SELECT t FROM TerapiaPar t  WHERE t.terapia.visita.cliente.cliCodigo=:cliente";
           Query q = sesion.createQuery(sql);
            q.setParameter("cliente", cliente.getCliCodigo());
            result = q.list();
                }
       catch (NoResultException e) {
            System.out.println((new StringBuilder()).append("ERROR : ").append(e.getMessage()).toString());
                */
        return result;
            }

            public boolean SPsaveTerapia(Terapia terapia) {
         boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL CrearTerapia(:descripcion,:fecha_prox,:usu_creacion,:tte_cod,:vis_cod,:ter_costo,:ter_empresa) }");
             q.setParameter("descripcion", terapia.getTerDescripcion());
             q.setParameter("fecha_prox", terapia.getTerFecProxima());
             q.setParameter("usu_creacion", terapia.getTerUsuCreacion());
             q.setParameter("tte_cod", terapia.getTipoTerapia().getTteCodigo());
             q.setParameter("vis_cod", terapia.getVisita().getVisCodigo());
             q.setParameter("ter_costo", terapia.getTerCosto());
             q.setParameter("ter_empresa", terapia.getTerEmpresa());
/* 497*/            q.executeUpdate();
/* 499*/            resultado = true;
                }
/* 501*/        catch (Exception e) {
/* 503*/            System.out.println((new StringBuilder()).append("ERROR: ").append(e.getMessage()).toString());
/* 504*/            resultado = false;
                }
/* 506*/        return resultado;
            }

    @Override
    public boolean lastTerapiabyVisita(Visita vis) {
        
       boolean result=false;
       int count=0;
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
            String sql = "SELECT  count(*) FROM Terapia t WHERE t.visita.visCodigo=:visita  ";
            Query q = sesion.createQuery(sql);
            q.setParameter("visita", vis.getVisCodigo());
             count = Integer.parseInt(q.uniqueResult().toString());
             
             if(count!=0)
             {
                 result=true;
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
                    String sql = "SELECT  count(*) FROM Terapia t WHERE t.visita.visCodigo=:visita  ";
            Query q = sesion.createQuery(sql);
            q.setParameter("visita", vis.getVisCodigo());
             count = Integer.parseInt(q.uniqueResult().toString());
             
             if(count!=0)
             {
                 result=true;
             }
                }
        catch (NoResultException e) {
           System.out.println((new StringBuilder()).append("ERROR : ").append(e.getMessage()).toString());
                }*/
        return result;
    }

    @Override
    public Terapia terapiaByVisita(Visita visita) {
        Terapia result= null;
      Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
             //tx = sesion.beginTransaction();
              String sql = "SELECT t FROM Terapia t WHERE t.visita.visCodigo=:visita";
            Query q = sesion.createQuery(sql);
           q.setParameter("visita", visita.getVisCodigo());
           result = (Terapia) q.uniqueResult();
        //   result = q.list();
           
                System.out.println(result.getTipoTerapia().getTteNombre());
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return result;
    }

    @Override
    public boolean SP_CambiarPrecioTerapia(int ter, int vis, double monto) {
            boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL SP_CambiarPrecioTerapia(:ter,:vis,:montoantiguo) }");
             q.setParameter("ter", ter);
             q.setParameter("vis", vis);
             q.setParameter("montoantiguo", monto);
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SP_UpdateCabecera : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }
}
