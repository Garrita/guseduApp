// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   ParServiceImpl.java

package com.gusedu.dao.impl;

import com.gusedu.dao.ParService;
import com.gusedu.entidad.ParX;
import com.gusedu.model.*;
import com.gusedu.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ParServiceImpl
    implements ParService {

            private static final long serialVersionUID = 1L;
            EntityManager em;


            public List<Par> getAllPares() {
        List<Par> result = new ArrayList();
          Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
              String sql = "SELECT p FROM Par p";
           Query q = sesion.createQuery(sql);
            result = q.list();
       
           
                 for (int i = 0; i < result.size(); i++) {
                    System.out.println(result.get(i).getPuntoByPunCodigoP1().getPunNombre()+"-"+
                                  result.get(i).getPuntoByPunCodigoP2().getPunNombre()+ 
                                  result.get(i).getGrupo().getGruNombre()  );
                            }
             // tx.commit();
         
        } catch (Exception e) {
  
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return result;
            }

            public Par parById(Integer id) {
                Par par=null;
                Session sesion = HibernateUtil.getSessionFactory().openSession();
                Transaction tx = null;
                try {
                    tx = sesion.beginTransaction();
                    par = (Par) sesion.load(Par.class, id);
                    tx.commit();
                    System.out.println(par.getPuntoByPunCodigoP1().getPunNombre()+"||"+par.getPuntoByPunCodigoP2().getPunNombre()+"||"+par.getGrupo().getGruNombre());
                } catch (Exception e) {
                    if (tx != null) {
                        tx.rollback();
                    }
                    throw new RuntimeException(e);
                } finally {
                    sesion.flush();
                    sesion.close();
                }
         return par;
            }

            @Override
    public Boolean savePar(Par par) {
        boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(par);
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
        /*  78*/ return resultado;
    }

            public Boolean updatePar(Par par) {
       boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.merge(par);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR   : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return resultado;
            }

            public Boolean deletePar(Par par) {
/*  96*/         boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.delete(par);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR   : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return resultado;
            }

            public List<Enfermedad>  getEnfermedades(Par par) {
/* 110*/        List<Enfermedad>  result = new ArrayList();
/* 118*/        return result;
            }

            public List<Sintoma> getSintomas(Par par) {
/* 124*/        List<Sintoma> result = new ArrayList();
/* 132*/        return result;
            }

            public Par parByPuntos(Punto p1, Punto p2, Grupo g) {
        Par result = null;
        
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
                String sql = "SELECT p FROM Par p WHERE p.puntoByPunCodigoP1.punCodigo=:p1 AND p.puntoByPunCodigoP2.punCodigo=:p2 AND p.grupo.gruCodigo=:g";
            Query q = sesion.createQuery(sql);
            q.setParameter("p1", p1.getPunCodigo());
            q.setParameter("p2", p2.getPunCodigo());
            q.setParameter("g", g.getGruCodigo());
            result = (Par)q.uniqueResult();

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
            String sql = "SELECT p FROM Par p WHERE p.puntoByPunCodigoP1.punCodigo=:p1 AND p.puntoByPunCodigoP2.punCodigo=:p2 AND p.grupo.gruCodigo=:g";
            Query q = sesion.createQuery(sql);
            q.setParameter("p1", p1.getPunCodigo());
            q.setParameter("p2", p2.getPunCodigo());
            q.setParameter("g", g.getGruCodigo());
            result = (Par)q.uniqueResult();
                }
        catch (NoResultException e) {
            System.out.println((new StringBuilder()).append("ERROR de paresByPunto: ").append(e.getMessage()).toString());
                }*/
        return result;
            }

            public List<Par> getAllParesOrderByP1() {
        List<Par> result = new ArrayList();
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
             
                try {
          String sql="SELECT p FROM Par p ORDER BY p.puntoByPunCodigoP1.punNombre ASC";

                    Query q = sesion.createQuery(sql);
                   result = q.list();
                         for (int i = 0; i < result.size(); i++) {
                        System.out.println(result.get(i).getPuntoByPunCodigoP1().getPunNombre()+"|"+result.get(i).getPuntoByPunCodigoP2().getPunNombre() +"|"+result.get(i).getGrupo().getGruNombre());
                    }
                
                } catch (Exception e) {
                } finally {
                    sesion.flush();
                    sesion.close();
                }
                   return result;
            }

            public List<Par> getAllParesOrderByP2() {
       List<Par> result = new ArrayList();
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
             
                try {
          String sql="SELECT p FROM Par p ORDER BY p.puntoByPunCodigoP2.punNombre ASC";

                    Query q = sesion.createQuery(sql);
                   result = q.list();
                          for (int i = 0; i < result.size(); i++) {
                        System.out.println(result.get(i).getPuntoByPunCodigoP1().getPunNombre()+"|"+result.get(i).getPuntoByPunCodigoP2().getPunNombre()+"|"+result.get(i).getGrupo().getGruNombre());
                    }
                
                } catch (Exception e) {
                } finally {
                    sesion.flush();
                    sesion.close();
                }
                   return result;
            }

            public List<Par> getAllParesOrderGoiz() {
                 List<Par> result = new ArrayList();
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
             
                try {
          String sql="SELECT p FROM Par p ORDER BY p.puntoByPunCodigoP1.punOrdenFisico ASC";

                    Query q = sesion.createQuery(sql);
                   result = q.list();
                            for (int i = 0; i < result.size(); i++) {
                        System.out.println(result.get(i).getPuntoByPunCodigoP1().getPunNombre()+"|"+result.get(i).getPuntoByPunCodigoP2().getPunNombre()+"|"+result.get(i).getGrupo().getGruNombre());
                    }
                
                } catch (Exception e) {
                } finally {
                    sesion.flush();
                    sesion.close();
                }
                   return result;
            }

            public List<EnfermedadPar> parByEnfermedad(Enfermedad enfermedad) {
/* 197*/        List<EnfermedadPar> result = new ArrayList();
/* 205*/        return result;
            }

            public List<SintomaPar> parBySintoma(Sintoma sintoma) {
/* 211*/        List<SintomaPar> result = new ArrayList();
/* 219*/        return result;
            }

            public List<Par> getParesBySintoma(Sintoma sintoma) {
/* 225*/        List<Par> result = new ArrayList();
/* 233*/        return result;
            }

            public List<Par> getParesByEnfermedad(Enfermedad enfermedad) {
/* 239*/        List<Par> result = new ArrayList();
/* 247*/        return result;
            }

            public List<Par> paresByTerapia(Terapia terapia) {
/* 253*/        List<Par> result = new ArrayList();
/* 261*/        return result;
            }

            public List<Par>  paresByPunto(Punto p1) {
                System.out.println("Se ejecuta paresByPunto - ParServiceImpl");
        List<Par>  result = new ArrayList();
                Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
             String sql = "SELECT p FROM Par p WHERE p.puntoByPunCodigoP1.punCodigo=:p1 and p.grupo.gruCodigo<>:g1";
           Query q = sesion.createQuery(sql);
           q.setParameter("p1", p1.getPunCodigo());
            q.setParameter("g1", Integer.valueOf(1));
            result = q.list();
                for ( int i=0;i<result.size();i++)
                {
                    System.out.println(result.get(i).getPuntoByPunCodigoP2().getPunNombre());
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
           String sql = "SELECT p FROM Par p WHERE p.puntoByPunCodigoP1.punCodigo=:p1 and p.grupo.gruCodigo<>:g1";
           Query q = sesion.createQuery(sql);
           q.setParameter("p1", p1.getPunCodigo());
            q.setParameter("g1", Integer.valueOf(1));
            result = q.list();
                }
        catch (NoResultException e) {
            System.out.println((new StringBuilder()).append("ERROR de paresByPunto: ").append(e.getMessage()).toString());
                }*/
       return result;
            }

    @Override
    public List<Par> SP_ORDENAMIENTO_PAR(int valor) {
        System.out.println("Se ejecuta SP_ORDENAMIENTO_PAR - ParServiceImpl");
        List<Par> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
    
             Query q = session.createSQLQuery("{ CALL SP_ORDENAMIENTO_PAR(:val) }");
             q.setParameter("val",valor);
			List<Object[]> d=q.list();
			for (Object[] result : d) {	
                                Par p = new Par();
                                Punto p1 = new Punto();
                                Punto p2 = new Punto();
                                Grupo g = new Grupo();
                                 int par_codigo = (int) result[0];   
                                 int pun1_cod= (int) result[1]; 
                                 String nom1 =(String) result[2]; 
                                 int pun2_cod= (int) result[3]; 
                                 String nom2 =(String) result[4]; 
                                 int gru_cod= (int) result[5];
                                 String nom_gru =(String) result[6];
                                 p.setParCodigo(par_codigo);
                                 p1.setPunCodigo(pun1_cod);
                                 p1.setPunNombre(nom1);
                                 p2.setPunCodigo(pun2_cod);
                                 p2.setPunNombre(nom2);
                                 g.setGruCodigo(gru_cod);
                                 g.setGruNombre(nom_gru);
                                 
                                 p.setGrupo(g);
                                 p.setPuntoByPunCodigoP1(p1);
                                 p.setPuntoByPunCodigoP2(p2);
                                   lista.add(p);
                   	}
        } catch (Exception e) {
            System.out.println("Error SP_ORDENAMIENTO_PAR : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista; 
    }

    @Override
    public List<ParX> SP_LISTAR_PARES() {
        List<ParX> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
    
             Query q = session.createSQLQuery("{ CALL SP_LISTAR_PARES() }");

			List<Object[]> d=q.list();
			for (Object[] result : d) {	
                                 int par_codigo = (int) result[0];   
                                 String par_nom= (String) result[1]; 
                          
                                   lista.add(new ParX(par_codigo, par_nom));
                   	}
        } catch (Exception e) {
            System.out.println("Error SP_LISTAR_PARES : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista; 
    }

    @Override
    public Par SP_FIND_PAR(String nom_par) {
       Par obj = new Par();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
         
             Query q = session.createSQLQuery("{ CALL SP_FIND_PAR(:nom_par) }");
               q.setParameter("nom_par",nom_par);
			Object[] d =   (Object[]) q.uniqueResult();
                         int cod_par=(int) d[0];

                         obj.setParCodigo(cod_par);

        } catch (Exception e) {
            System.out.println("Error en SP_FIND_PAR : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return obj;  
    }


}
