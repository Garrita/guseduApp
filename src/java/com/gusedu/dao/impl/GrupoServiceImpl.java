/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao.impl;

import com.gusedu.dao.GrupoService;
import com.gusedu.model.Grupo;
import com.gusedu.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author NV55C
 */
public class GrupoServiceImpl implements GrupoService{

    @Override
    public List<Grupo> getAllGrupos() {
        System.out.println("Se ejecuto getAllGrupos - GrupoSertviceImpl");
     Session sesion = HibernateUtil.getSessionFactory().openSession();
        List<Grupo> result = new ArrayList<>();
        try {
             //tx = sesion.beginTransaction();
              String sql = "SELECT g FROM Grupo g";
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
