// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   VisitaServiceImpl.java

package com.gusedu.dao.impl;

import com.gusedu.dao.VisitaService;
import com.gusedu.entidad.Calendario;
import com.gusedu.entidad.EUltimaVisita;
import com.gusedu.entidad.EUltimaVisitaxCliente;
import com.gusedu.model.Cliente;
import com.gusedu.model.Terapia;
import com.gusedu.model.Visita;
import com.gusedu.util.HibernateUtil;
import com.gusedu.util.StaticUtil;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class VisitaServiceImpl
    implements VisitaService {

            EntityManager em;


            public boolean saveVisita(Visita visita) {
                boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(visita);
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
 
/*  42*/        return resultado;
            }

            @Override
            public boolean updateVisita(Visita visita) {
               boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            System.out.println("Pre-Update");
            tx = sesion.beginTransaction();
            sesion.merge(visita);
            tx.commit();
            System.out.println("Post-Update");
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de updateVisita : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
 
/*  63*/        return resultado;
            }

            public boolean deleteVisita(Visita visita) {
/*  67*/        boolean resultado = false;
/*  75*/        return resultado;
            }

            @Override
        public List<EUltimaVisitaxCliente> getVisitasCliente(int clicodigo) 
        {
            System.out.println("Se ejecuta getVisitasCliente - VisitaServiceImpl");
            List<EUltimaVisitaxCliente> resultado = new ArrayList<>();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = sesion.beginTransaction();
                Query q = sesion.createSQLQuery("{ CALL sp_ListarXSesiones(:clicodigo) }");
                q.setParameter("clicodigo", clicodigo);
                List<Object[]> d=q.list();
                for (Object[] result : d) 
                {
                    int codigoVisita = ((int) result[0]);
                    String visita = "Visita " + ((BigInteger)(result[1]));
                    Date fechaCreacion = (Date) (result[2]);
                    double costoTotal = ((double) result[3]);
                    resultado.add(new EUltimaVisitaxCliente(codigoVisita,visita,fechaCreacion,costoTotal));		 
                  }
            } 
            catch(Exception e)
            {
                System.out.println("ERROR de Lista Ultimas Visitas por Cliente : "+e.getMessage());
            }
             return resultado;
        }

            @Override
            public Visita getVisitaById(Integer idVisita) {
                Visita visitaservice = null;
                Session sesion = HibernateUtil.getSessionFactory().openSession();

                try {
                    sesion.beginTransaction();
                    visitaservice = (Visita) sesion.load(Visita.class, idVisita);
                    System.out.println("VisitaServiceImpl - getVisitaById :"+visitaservice.getVisDescripcion());

                } catch (Exception e) {


                } finally {
                    sesion.flush();
                    sesion.close();
                }
                return visitaservice;
            }

           public Visita getLastVisitaCliente(Cliente cliente) {
       
        Visita result = null;
        
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
             Query q = sesion.createQuery("SELECT v FROM Visita v WHERE v.cliente.cliCodigo=:cliente ORDER BY v.visFecCreacion DESC");
            q.setParameter("cliente", cliente.getCliCodigo());
            q.setMaxResults(1);
            result = (Visita) q.uniqueResult();
            System.out.println("getLastVisitaCliente : "+result.getVisCodigo());
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
            Query q = sesion.createQuery("SELECT v FROM Visita v WHERE v.cliente.cliCodigo=:cliente ORDER BY v.visFecCreacion DESC");
            q.setParameter("cliente", cliente.getCliCodigo());
            q.setMaxResults(1);
            result = (Visita) q.uniqueResult();
        } catch (NoResultException e) {
            System.out.println((new StringBuilder()).append("ERROR de buscaVisita: ").append(e.getMessage()).toString());
        }*/
        return result;
    }

            public Visita buscarVisita(Cliente cliente) {
                Visita result = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Date fecha = new Date();
        int mes = fecha.getMonth() + 1;
       int aF1o = fecha.getYear() + 1900;
        int dia = fecha.getDate();
       String mesM = "";
        String diaD = "";
         if (mes < 10) {
             mesM = "0";
                }
         if (dia < 10) {
             diaD = "0";
                }
         String cadena = (new StringBuilder()).append(aF1o).append("-").append(mesM).append(mes).append("-").append(diaD).append(dia).toString();
        
          try {
             //tx = sesion.beginTransaction();
            Query q = sesion.createQuery("SELECT v  FROM Visita v WHERE SUBSTRING(v.visFecCreacion,1,10) =:fecha AND v.cliente.cliCodigo=:cliente");
             q.setParameter("fecha", cadena);
             q.setParameter("cliente", cliente.getCliCodigo());
            Cliente cli = cliente;
             result = (Visita)q.uniqueResult();
              System.out.println("VisitaServiceImpl - buscarVisita "+result.getVisCodigo());

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
             Query q = sesion.createQuery("SELECT v  FROM Visita v WHERE SUBSTRING(v.visFecCreacion,1,10) =:fecha AND v.cliente.cliCodigo=:cliente");
             q.setParameter("fecha", cadena);
             q.setParameter("cliente", cliente.getCliCodigo());
            Cliente cli = cliente;
             result = (Visita)q.uniqueResult();
                }
         catch (NoResultException e) {
            System.out.println((new StringBuilder()).append("ERROR de buscaVisita: ").append(e.getMessage()).toString());
                }*/
         return result;
            }

            public Visita getLastVisitaCliente2(Cliente cliente) {
         List resultlist = new ArrayList();
         Visita result = null;
         return result;
            }

            public double costodeVisita(Date fec_inicial, Date fec_final) {
/* 185*/        double result = 0.0D;
/* 195*/        return result;
            }

            public List<Visita> getVisitabyFechas(Date fec_inicial, Date fec_final) {
/* 201*/        List<Visita> result = new ArrayList();
Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
         String username = StaticUtil.userLogged();
        try {
           // tx = sesion.beginTransaction();
             String sql = "select v from Visita v where v.visUsuCreacion=:usuario and :fec_inicial <= v.visFecCreacion and v.visFecCreacion<=:fec_final order by v.visFecCreacion desc";
       Query q = sesion.createQuery(sql);
                q.setParameter("fec_inicial",fec_inicial);
			q.setParameter("fec_final", fec_final);
			q.setParameter("usuario", username);
                     

        result = q.list();
        for(int i=0;i<result.size();i++)
        {
           System.out.println("VisitaServiceImpl - getVisitabyFechas : "+result.get(i).getCliente().getPersona().getPerNombres());
        }
           // tx.commit();
            
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
 
              
/* 211*/        return result;
            }

            public List<Visita> getVisitas() {
                System.out.println("Se ejecuto getVisitas - VisitaServiceImpl");
         List<Visita> result = new ArrayList();
         Session sesion = HibernateUtil.getSessionFactory().openSession();
 
        try {
 
             String sql = "SELECT v FROM Visita v ORDER BY v.visFecCreacion DESC";
             Query q = sesion.createQuery(sql);
             result = q.list();          
        } catch (Exception e) {
         
                System.out.println("ERROR   : " + e.getMessage());

        } finally {
            sesion.flush();
            sesion.close();
        }
         return result;
            }

    @Transactional
    @Override
    public List<Visita> getVisitasByEmpresa() {
       List<Visita> result = new ArrayList();
         Session sesion = HibernateUtil.getSessionFactory().openSession();
 
        try {
            String username =StaticUtil.userLogged();
            // String sql = "SELECT v FROM Visita v where v.visUsuCreacion=:usuario and v.visFecCreacion >= CURDATE() ORDER BY v.visFecCreacion DESC";
            String sql = "SELECT v FROM Visita v where v.visUsuCreacion=:usuario  ORDER BY v.visFecCreacion DESC";
             Query q = sesion.createQuery(sql);
             q.setParameter("usuario", username);
             result = q.list(); 
            
for(int i=0;i<result.size();i++){
                     System.out.println("PROBANDO:::"+result.get(i).getCliente().getPersona().getPerNombres()+" "
                                                 +result.get(i).getCliente().getPersona().getPerApellidoP()+" "
                                                 +result.get(i).getCliente().getPersona().getPerApellidoM());
}
          
        } catch (Exception e) {
         
                System.out.println("ERROR   : " + e.getMessage());

        } finally {
            sesion.flush();
            sesion.close();
        }
         return result;
    }

    @Override 
    public boolean SPsaveVisitaxTerapia(Visita vis,Terapia ter) {
       boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL CrearVisitaxTerapia(:vis_fec_creacion,:vis_usu_creacion,:cli_codigo,:vis_descripcion,:vis_fec_fin,:USU_CREACION,:TER_DESCRIPCION,:TTE_CODIGO,:TER_COSTO) }");
             q.setParameter("vis_fec_creacion", vis.getVisFecCreacion());
             q.setParameter("vis_usu_creacion", vis.getVisUsuCreacion());
             q.setParameter("cli_codigo", vis.getCliente().getCliCodigo());
             q.setParameter("vis_descripcion", vis.getVisDescripcion());
             q.setParameter("vis_fec_fin", vis.getVisFecFin());
             q.setParameter("USU_CREACION", ter.getTerUsuCreacion());
             q.setParameter("TER_DESCRIPCION", ter.getTerDescripcion());
             q.setParameter("TTE_CODIGO", ter.getTipoTerapia().getTteCodigo());
             q.setParameter("TER_COSTO", ter.getTerCosto());
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SPsaveVisitaxTerapia : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }

    @Override
    public Visita visitaDelDia(Cliente cliente) {
     Visita result = null;
        
       Session sesion = HibernateUtil.getSessionFactory().openSession();     
        try {
            Query q = sesion.createQuery("select v from Visita v where DATE_FORMAT( v.visFecCreacion,'%Y-%m-%d')=curdate() AND cliente.cliCodigo=:cliente ");
            q.setParameter("cliente", cliente.getCliCodigo());
            result = (Visita) q.uniqueResult();
            System.out.println(result.getVisCodigo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return result;
    }

    @Override
    public int visitaProgramada(Date fecha, Cliente cliente) {
        int valor=0;
         Session sesion = HibernateUtil.getSessionFactory().openSession();  
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        String fechaConFormato = sdf.format(fecha);
    
        try {
            Query q = sesion.createQuery("select count(*) from Visita v where DATE_FORMAT( v.visFecCreacion,'%Y-%m-%d')=:fecha AND cliente.cliCodigo=:cliente ");
            q.setParameter("cliente", cliente.getCliCodigo());
            Date nuevaFecha = sdf.parse(fechaConFormato);
            q.setParameter("fecha", fechaConFormato);
            //System.out.println("Valor de count : "+);
            valor = Integer.parseInt( q.uniqueResult().toString());
            System.out.println("Valor de count : "+valor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return valor;
    }

    @Override
    public boolean SPdeleteVisita(int cod_visita) {
         boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL EliminarCita(:codigo_visita) }");
             q.setParameter("codigo_visita", cod_visita);
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SPdeleteVisita : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }

    @Override
    public boolean SP_CrearFactura(boolean llegada, int cli, int vis) {
         boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL Crear_Cabecera(:llegada,:codigo_cliente,:codigo_visita) }");
             q.setParameter("llegada", llegada);
             q.setParameter("codigo_cliente", cli);
             q.setParameter("codigo_visita", vis);
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SP_CrearFactura : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }

    @Override
    public List<Calendario> SP_Calendario(String terapeuta) {
        List<Calendario> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
          String empresa = StaticUtil.userLogged();
             Query q = session.createSQLQuery("{ CALL SP_Calendario(:empresa,:terapeuta) }");
               q.setParameter("empresa",empresa);
               q.setParameter("terapeuta",terapeuta);
          
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
			 String nombre=(String) result[0];
                         Date fec_creacion=(Date) result[1];
                         Date fec_fin=(Date) result[2];
                         int vis_codigo=(int) result[3];
                         int cli_codigo=(int) result[4];
                         String descripcion=(String) result[5];
                         Boolean llegada=(Boolean) result[6];
                lista.add(new Calendario(nombre, fec_creacion, fec_fin,vis_codigo, cli_codigo, descripcion, llegada));
			}
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista;  
    }

    @Override
    public boolean SP_ActualizarDiar(Visita visita) {
         boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL SP_ActualizarDiar(:inicio,:fin,:codigo_visita) }");
             System.out.println("INICIO : "+visita.getVisFecCreacion()+"\nFIN : "+visita.getVisFecFin()+"\nCod_vis : "+visita.getVisCodigo());
             q.setParameter("inicio", visita.getVisFecCreacion());
             q.setParameter("fin", visita.getVisFecFin());
             q.setParameter("codigo_visita", visita.getVisCodigo());
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SP_CrearFactura : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }

    public List<EUltimaVisita> SP_LISTAR_VISITAS(Date fec_inicial,Date fec_final) {
        System.out.println("Se ejecuta SP_LISTAR_VISITAS - VisitaServiceImpl");
         List<EUltimaVisita> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
          String empresa = StaticUtil.userLogged();
             Query q = session.createSQLQuery("{ CALL SP_LISTAR_VISITAS(:empresa,:fec_ini,:fec_fin) }");
               q.setParameter("empresa",empresa);
               q.setParameter("fec_ini",fec_inicial);
               q.setParameter("fec_fin",fec_final);
          
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
			String dni = (String) result[0];
                        String paciente = (String) result[1];
                        Date fec_creacion = (Date) result[2];
                        String usu_crea = (String) result[3];
                        double  costo = (double) result[4];
                        lista.add(new EUltimaVisita(0, dni, null, null, paciente, empresa, fec_creacion, costo, null));
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
