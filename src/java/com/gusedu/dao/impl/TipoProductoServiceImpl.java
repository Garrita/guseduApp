/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao.impl;

import com.gusedu.dao.TipoProductoService;
import com.gusedu.model.Producto;
import com.gusedu.model.TipoProducto;
import com.gusedu.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author NV55C
 */
public class TipoProductoServiceImpl implements TipoProductoService{

    @Override
    public List<TipoProducto> getAllTipoProducto() {
         System.out.println("Se ejecuto getAllTipoProducto - TipoProductoServiceImpl");
        List<TipoProducto> result = new ArrayList();
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
              String sql = "SELECT p FROM TipoProducto p";
            Query q = sesion.createQuery(sql);
            result = q.list();
             // tx.commit();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
           return result;
    }
    
}
