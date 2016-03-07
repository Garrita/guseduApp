package com.gusedu.dao.impl;

import com.gusedu.dao.TipoUsuarioService;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


import com.gusedu.model.TipoUsuario;
import com.gusedu.util.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class TipoUsuarioServiceImpl implements TipoUsuarioService,Serializable{

	
	@PersistenceContext
	EntityManager em;
	
	
	
	public boolean saveTipoUsuario(TipoUsuario tipousuario) {
		boolean resultado = false;
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(tipousuario);
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
		return resultado;
	}


	public boolean updateTipoUsuario(TipoUsuario tipousuario) {
		boolean resultado = false;
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.merge(tipousuario);
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
		return resultado;
	}

	
	public boolean deleteTipoUsuario(TipoUsuario tipousuario) {
		boolean resultado = false;
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.delete(tipousuario);
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
		return resultado;
	}

	@SuppressWarnings("unchecked")
	
	public List<TipoUsuario> getAll() {
		          List<TipoUsuario> result = new ArrayList<>();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = sesion.beginTransaction();
                String sql = "SELECT tu FROM TipoUsuario tu";
                Query q = sesion.createQuery(sql);
                result = q.list();
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                    System.out.println("ERROR de getAll : " + e.getMessage());
                }
                System.out.println(e.getMessage());
            } finally {
                sesion.flush();
                sesion.close();
            }
        return result;
	}

	@Override
	public TipoUsuario getUsuarioeById(Integer idTipoUsuario) {
            TipoUsuario tipousuario=null;
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx =sesion.beginTransaction();
            tipousuario = (TipoUsuario) sesion.load(TipoUsuario.class, idTipoUsuario);
            System.out.println("ID Tus : "+tipousuario.getTusDescripcion());
            tx.commit();
        } catch (Exception e) {
            if(tx!=null)
            {
                tx.rollback();
            }
            throw  new RuntimeException(e);
        } finally {
            sesion.flush();
            sesion.close();
        }
        return tipousuario;
	}

}
