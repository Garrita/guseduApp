/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao.impl;

import com.gusedu.dao.EstadisticaService;
import com.gusedu.entidad.Estadistica;
import com.gusedu.util.HibernateUtil;
import com.gusedu.util.StaticUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author NV55C
 */
public class EstadisticaImpl implements EstadisticaService{

    @Override
    public List<Estadistica> pacienteMesActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estadistica> visitaMesActual() {
        List<Estadistica> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
          String empresa = StaticUtil.userLogged();
             Query q = session.createSQLQuery("{ CALL Estadistica_Visita_MesActual() }");
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
				BigInteger CANTIDAD = ((BigInteger) result[0]);
				String NOMBRE = (String) result[1];
				lista.add(new Estadistica(CANTIDAD, NOMBRE));
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
    public List<Estadistica> pacienteMeses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estadistica> visitaMeses() {
         List<Estadistica> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
          String empresa = StaticUtil.userLogged();
             Query q = session.createSQLQuery("{ CALL Estadistica_Visita_Meses() }");
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
				BigInteger CANTIDAD = ((BigInteger) result[0]);
				String FECHA = (String) result[1];
				lista.add(new Estadistica(CANTIDAD, FECHA));
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
    public List<Estadistica> pacienteAnual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estadistica> visitaAnual() {
        List<Estadistica> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
          String empresa = StaticUtil.userLogged();
             Query q = session.createSQLQuery("{ CALL Estadistica_Visita_Anual() }");
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
				BigInteger CANTIDAD = ((BigInteger) result[0]);
				String FECHA = (String) result[1];
				lista.add(new Estadistica(CANTIDAD, FECHA));
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
