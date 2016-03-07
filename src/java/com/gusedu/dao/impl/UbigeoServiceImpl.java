/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao.impl;

import com.gusedu.dao.UbigeoService;
import com.gusedu.model.Producto;
import com.gusedu.model.Ubdepartamento;
import com.gusedu.model.Ubdistrito;
import com.gusedu.model.Ubprovincia;
import com.gusedu.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author NV55C
 */
public class UbigeoServiceImpl implements UbigeoService{

    @Override
    public List<Ubdepartamento> getAllDepartamento() {
        System.out.println("Se ejecuta getAllDepartamento - UbigeoServiceImpl");
         List<Ubdepartamento> result = new ArrayList();
         Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "SELECT u FROM Ubdepartamento u";
            Query q = sesion.createQuery(sql);
            result = q.list();
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
           return result;
    }

    @Override
    public List<Ubprovincia> getAllProvincia(int idProvincia) {
        System.out.println("Se ejecuta getAllProvincia - UbigeoServiceImpl");
         List<Ubprovincia> result = new ArrayList();
         Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "SELECT u FROM Ubprovincia u where ubdepartamento.idDepa=:iddepa";
            Query q = sesion.createQuery(sql);
            q.setParameter("iddepa", idProvincia);
            result = q.list();
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
           return result;
    }

    @Override
    public List<Ubdistrito> getAllDistrito(int idDepartamento) {
        System.out.println("Se ejecuta getAllDistrito - UbigeoServiceImpl");
         List<Ubdistrito> result = new ArrayList();
         Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {
            String sql = "SELECT u FROM Ubdistrito u where ubprovincia.idProv=:idprov";
            Query q = sesion.createQuery(sql);
            q.setParameter("idprov", idDepartamento);
            result = q.list();
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
           return result;
    }
    
}
