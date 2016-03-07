/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gusedu.util.HibernateUtil;

/**
 *
 * @author NV55C
 */
public class GenericDaoImpl<Entity, K extends Serializable> implements GenericDao<Entity, K>{
    
   
    private Session session;
    
    
         private Session getHibernateTemplate() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		return session;
	}
         
    
    public void Actualizar(Entity t) {
                Transaction tx = null;
		try {
                        tx = getHibernateTemplate().beginTransaction();
			getHibernateTemplate().update(t);
	 		session.getTransaction().commit();
                        
	 	} catch (HibernateException e) {
                         if (tx != null) {
                            tx.rollback();
                        }
	 		 System.out.println("ERROR "+e.getMessage());
		} finally {
                session.flush();
                session.close();
        }
	
}
    
    @Override
    public void Guardar(Entity t) {
        session = HibernateUtil.getSessionFactory().openSession();
	 Transaction tx = null;
		try {
                        tx = session.beginTransaction();
			session.save(t);
	 		session.getTransaction().commit();
                        
	 	} catch (HibernateException e) {
                         if (tx != null) {
                            tx.rollback();
                        }
	 		 System.out.println("ERROR "+e.getMessage());
		} finally {
                session.flush();
                session.close();
	}
    }
	public void Eliminar(Entity t) {
	 	Transaction tx = null;
		try {
                        tx = getHibernateTemplate().beginTransaction();
			getHibernateTemplate().delete(t);
	 		session.getTransaction().commit();
                        
	 	} catch (HibernateException e) {
                         if (tx != null) {
                            tx.rollback();
                        }
	 		 System.out.println("ERROR "+e.getMessage());
		} finally {
                session.flush();
                session.close();
	}
      }

    @Override
    public Entity Buscar(Class<Entity> type, K id) {
        Entity returnValue = (Entity) getHibernateTemplate().get(type, id);
	session.getTransaction().commit();
	return returnValue;
    }
}