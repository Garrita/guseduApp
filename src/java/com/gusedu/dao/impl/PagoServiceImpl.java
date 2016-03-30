/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao.impl;

import com.gusedu.dao.PagoService;
import com.gusedu.entidad.EPago;
import com.gusedu.model.Pago;
import com.gusedu.model.TipoPago;
import com.gusedu.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Joel Romero
 */
public class PagoServiceImpl implements PagoService{

    @Override
    public List<TipoPago> allTipoPagos() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<TipoPago> result = new ArrayList<>();
        try {
             String sql = "from TipoPago";
             Query q = sesion.createQuery(sql);
             result =  q.list();
         
        } catch (Exception e) {
            /*if (tx != null) {
                tx.rollback();
            }  */
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }      
        return result;
    }

    @Override
    public boolean InsertPago(Pago pago) {
        boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.save(pago);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de InsertPago : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return resultado;
    }

    @Override
    public boolean UpdatePago(Pago pago) {
       boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.merge(pago);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de InsertPago : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return resultado;
    }

    @Override
    public boolean DeletePago(Pago pago) {
            boolean resultado = false;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();

            sesion.delete(pago);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de InsertPago : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return resultado;
    }

    @Override
    public Pago obtenerPago(int visita) {
        Pago pago = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            pago = (Pago) sesion.load(Pago.class, visita);
            System.out.println("ID VISITA : " + pago.getFactura().getCodFactura() + " TIPO PAGO : "+pago.getTipoPago().getTpagoCodigo());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            sesion.flush();
            sesion.close();
        }
        return pago;
    }

    @Override
    public List<Pago> allPagos() {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Pago> result = new ArrayList<>();
        try {
             String sql = "from Pago";
             Query q = sesion.createQuery(sql);
             result =  q.list();
         
        } catch (Exception e) {
            /*if (tx != null) {
                tx.rollback();
            }  */
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }      
        return result;
    }

    @Override
    public List<Pago> allPagosByVisita(int visita) {
        System.out.println("Se ejecuta allPagosByVisita - PagoServiceImpl");
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Pago> result = new ArrayList<>();
        try {
             String sql = "from Pago where factura.codFactura=:cod_visita";
             Query q = sesion.createQuery(sql);
             q.setParameter("cod_visita", visita);
             result =  q.list();
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i).getTipoPago().getNombre());
            }
        } catch (Exception e) {
            /*if (tx != null) {
                tx.rollback();
            }  */
            System.out.println("Error de allPagosByVisita : "+e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }      
        return result;
    }

    @Override
    public List<EPago> allReport(Date fec_ini, Date fec_fin) {
         List<EPago> resultado  = new ArrayList<>();
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL SP_Caja(:fec_ini,:fec_fin) }");
             q.setParameter("fec_ini", fec_ini);
             q.setParameter("fec_fin", fec_fin);
             List<Object[]> d=q.list();
            for (Object[] result : d) {
		 String paciente = ((String) result[0]);
                 Date visita = (Date) (result[1]);
                 String tipopago = (String) (result[2]);
                 double monto = (double) (result[3]);		
		 resultado.add(new EPago(paciente, visita, tipopago, monto));
		 
	      }
         }
         catch(Exception e)
         {
             System.out.println("ERROR de allReport : "+e.getMessage());
         }
         return resultado;
    }

    @Override
    public boolean SP_ValidarPagos(int pag,int fac, double monto) {
        boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL SP_ValidarPagos(:cod_pag,:cod_fac,:val_mon) }");
             q.setParameter("cod_pag", pag);
             q.setParameter("cod_fac", fac);
             q.setParameter("val_mon", monto);
             resultado = Boolean.parseBoolean( q.uniqueResult().toString());
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SP_ValidarPagos : "+e.getMessage());
             resultado=false;
         } finally {
            session.flush();
            session.close();
        }
         return resultado;
    }
    
}
