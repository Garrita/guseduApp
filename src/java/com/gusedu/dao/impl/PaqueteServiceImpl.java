/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao.impl;

import com.gusedu.dao.PaqueteService;
import com.gusedu.entidad.Paquete;
import com.gusedu.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author NV55C
 */
public class PaqueteServiceImpl implements PaqueteService{

    @Override
    public List<Paquete> SP_ListarPaquetes() {
               List<Paquete> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
          
             Query q = session.createSQLQuery("{ CALL SP_ListarPaquetes() }");

          
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
                        
                          int cod_paquete= ((int) result[0] );
                          String nombre  = (String) result[1] ;
                          int cantidad= ((int) result[2] );

                        
                        
                        lista.add(new Paquete(cod_paquete, nombre, cantidad));
			}
        } catch (Exception e) {
            System.out.println("Error SP_ListarPaquetes : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista;
    }
    
}
