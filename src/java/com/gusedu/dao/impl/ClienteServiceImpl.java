// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   ClienteServiceImpl.java

package com.gusedu.dao.impl;

import com.gusedu.model.Cliente;
import com.gusedu.dao.ClienteService;
import com.gusedu.entidad.ClientePersona;
import com.gusedu.entidad.EUltimaVisita;
import com.gusedu.util.HibernateUtil;
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClienteServiceImpl
    implements ClienteService {

            EntityManager em;


            public boolean saveCliente(Cliente cliente) {
/*  35*/        boolean resultado = false;
/*  37*/        try {
/*  37*/            em.persist(cliente);
/*  38*/            resultado = true;
                }
/*  39*/        catch (Exception e) {
/*  40*/            System.out.println((new StringBuilder()).append("ERROR: ").append(e.getMessage()).toString());
/*  41*/            resultado = false;
                }
/*  43*/        return resultado;
            }

            @Override
    public boolean updateCliente(Cliente cliente) {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            if (cliente.getPersona().getPerFecNacimiento() != null) {
                cliente.getPersona().setPerSignoZodiacal(StaticUtil.signoZodiacal(cliente.getPersona().getPerFecNacimiento().getMonth(), cliente.getPersona().getPerFecNacimiento().getDate()));
            }
             sesion.merge(cliente);
             tx.commit();
            resultado = true;
 
        } catch (Exception e) {
             System.out.println("Error : "+e.getMessage());
            if (tx != null) {
                tx.rollback();
               
            }
            System.out.println("Error : "+e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
       return resultado;
    }

            public boolean deleteCliente(Cliente cliente) {
/*  76*/        boolean resultado = false;
/*  78*/        try {
/*  78*/            em.remove(em.getReference(Cliente.class, cliente.getCliCodigo()));
/*  79*/            resultado = true;
                }
/*  80*/        catch (Exception e) {
/*  81*/            System.out.println((new StringBuilder()).append("ERROR: ").append(e.getMessage()).toString());
/*  82*/            resultado = false;
                }
/*  84*/        return resultado;
            }

            public List<Cliente>  getClientes() {
/*  90*/        List<Cliente>  result = new ArrayList();
/*  97*/        return result;
            }

            public List<Cliente>  getClientesPacientes() {
/* 103*/        List<Cliente>  result = new ArrayList();
/* 112*/        return result;
            }


            @Override
    public List<EUltimaVisita>  getListaUltimaVisita(String cliUsuCreacion) 
    {
        System.out.println("Se ejecuta getListaUltimaVisita - ClienteServiceImpl");
        List<EUltimaVisita> resultado = new ArrayList<>();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Query q = sesion.createSQLQuery("{ CALL sp_ListarUltimaVisita(:cliUsuCreacion) }");
            q.setParameter("cliUsuCreacion", cliUsuCreacion);
            List<Object[]> d=q.list();
            for (Object[] result : d) 
            {
                int idCliente = ((int) result[0]);
                String dni = ((String) result[1]);
                String apellidoP = ((String) result[2]);
                String apellidoM = ((String) result[3]);
                String nombres = ((String) result[4]);
                String telmovil = ((String) result[5]);
                Date ultimavisita = (Date) (result[6]);
                double costoTotal = ((double) result[7]);
                String tipoCliente = ((String) result[8]);
		resultado.add(new EUltimaVisita(idCliente,dni,apellidoP,apellidoM,nombres,telmovil,ultimavisita,costoTotal,tipoCliente));		 
	      }
        } 
        catch(Exception e)
        {
            System.out.println("ERROR de Lista Ultima Visita : "+e.getMessage());
        }
         return resultado;
    }
            
     
            
            
    @Override
    public List<Cliente> getClientesPacientesByUsuario(String usuario) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Cliente> result = new ArrayList<>();
        
               
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
             String sql = "SELECT c FROM Cliente c WHERE c.tipoCliente.tclDescripcion =:paciente AND c.cliUsuCreacion=:usuario order by c.persona.perFecCreacion desc";
             Query q = sesion.createQuery(sql);
            // q.setEntity("cliente", Cliente.class);
             q.setParameter("paciente", "Paciente");
             q.setParameter("usuario", usuario);
             result =  q.list();
       
          for(int j=0;j<result.size();j++)
             {
                 System.out.println("ClienteServiceImpl - getClientesPacientesByUsuario "+result.get(j).getPersona().getPerDni());
             }
             // tx.commit();
         
        } catch (Exception e) {
            /*if (tx != null) {
                tx.rollback();
            }  */
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }      
  
        
       /* try {
            String sql = "SELECT c FROM Cliente c WHERE c.tipoCliente.tclDescripcion =:paciente AND c.cliUsuCreacion=:usuario order by c.persona.perFecCreacion desc";
            Query q = sesion.createQuery(sql);
            q.setParameter("paciente", "Paciente");
            q.setParameter("usuario", usuario);
            result = q.list();
            System.out.println("TAMAÑO : "+result.size());
        } catch (NoResultException e) {
            System.out.println((new StringBuilder()).append("ERROR de getClientesPacientesByUsuario: ").append(e.getMessage()).toString());
        }*/
        return result;
    }

            public Cliente getClienteById(Integer idCliente) {
                Cliente cliente=null;
                Session sesion = HibernateUtil.getSessionFactory().openSession();
                //Transaction tx = null;
                try {
                    //tx = sesion.beginTransaction();
                    cliente = (Cliente) sesion.load(Cliente.class, idCliente);
                    if(cliente!=null)
                    {
                        cliente.getPersona().getPerNombres();
                        cliente.getTipoCliente().getTclCodigo();
                    }
                    //System.out.println(cliente.getPersona().getPerNombres());
                    //tx.commit();
                 } catch (Exception e) {
                   /* if (tx != null) {
                        tx.rollback();
                    }*/
                     System.out.println("ERROR getClienteById : "+e.getMessage());
                    throw new RuntimeException(e);
                } finally {
                    sesion.flush();
                    sesion.close();
                }
                return cliente;
            }

            public Cliente getClienteByIdPersona(Integer idPersona) {
/* 141*/        Cliente result = null;
/* 149*/        return result;
            }

            public List<Cliente>  ordenar() {
/* 155*/        List<Cliente>  result = new ArrayList();
/* 162*/        return result;
            }

            public Cliente lastClient() {
/* 167*/        Cliente result = null;
/* 175*/        return result;
            }

    @Override
    public List<ClientePersona> listaClientePersona(String nom, String ap_p,String ap_m,String dni) {
         List<ClientePersona> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
         
             Query q = session.createSQLQuery("{ CALL SP_BusquedaPaciente(:nom,:ap_p,:ap_m,:dni) }");
               q.setParameter("nom",nom);
               q.setParameter("ap_p",ap_p);
               q.setParameter("ap_m",ap_m);
               q.setParameter("dni",dni);
			List<Object[]> d=q.list();
			for (Object[] result : d) {				
				   int cli_codigo = (int) result[0];
                                   String per_dni=(String) result[1];
                                   String paciente=(String) result[2];
                                   lista.add(new ClientePersona(cli_codigo, per_dni, paciente));
                   	}
        } catch (Exception e) {
            System.out.println("Error listaClientePersona : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista;  
    }

    @Override
    public List<ClientePersona> getALLlistaClientePersona() {
        System.out.println("Se ejecuto getALLlistaClientePersona - ClienteServicEimpl");
        List<ClientePersona> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
         String empresa = StaticUtil.userLogged();
             Query q = session.createSQLQuery("{ CALL SP_ListaClientePaciente(:empresa) }");
               q.setParameter("empresa",empresa);
			List<Object[]> d=q.list();
			for (Object[] result : d) {				
				   int cli_codigo = (int) result[0];
                                   String per_dni=(String) result[1];
                                   String paciente=(String) result[2];
                                   lista.add(new ClientePersona(cli_codigo, per_dni, paciente));
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
