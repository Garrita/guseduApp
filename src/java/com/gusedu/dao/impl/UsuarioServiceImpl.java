/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao.impl;

import com.gusedu.dao.UsuarioService;
import com.gusedu.model.TipoUsuario;
import com.gusedu.model.Usuario;
import com.gusedu.util.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author NV55C
 */
public class UsuarioServiceImpl implements UsuarioService,Serializable{

    @Override
    public boolean saveUsuario(Usuario usuario) {
        boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            usuario.setUsuFecCreacion(new Date());
            usuario.setUsuPrecio(0);
            sesion.save(usuario);
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

    @Override
    public boolean updateUsuario(Usuario usuario) {
        boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.merge(usuario);
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

    @Override
    public boolean deleteUsuario(Usuario usuario) {
        boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.delete(usuario);
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

    @Override
    public List<Usuario> getAll() {
       List<Usuario>  result= new ArrayList<>();
               

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
           // tx = sesion.beginTransaction();
             String sql = "SELECT u FROM Usuario u";
            Query q = sesion.createQuery(sql);
            result = q.list();
           // tx.commit();
        } catch (Exception e) {
          /*  if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de getAll : " + e.getMessage());
            }*/
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        } 
        return result;
    }

    @Override
    public Usuario getUsuarioeById(Integer idUsuario) {
        Usuario usuario = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            System.out.println("Empezara busqueda");
            tx =sesion.beginTransaction();
            System.out.println("Ya va ha buscar");
            usuario = (Usuario) sesion.load(Usuario.class, idUsuario);
            System.out.println("USUARIO : "+usuario.getTipoUsuario().getTusDescripcion());
            System.out.println("Lo encontro");
            tx.commit();
        } catch (Exception e) {
            System.out.println("ERROR : "+e.getMessage());
            if(tx!=null)
            {
                tx.rollback();
            }
            throw  new RuntimeException(e);
        } finally {
            sesion.flush();
            sesion.close();
        }
        return usuario;
    }

    @Override
    public List<TipoUsuario> getAllTipoUsuarios() {
        List<TipoUsuario>  result= new ArrayList<>();
               

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
            //tx = sesion.beginTransaction();
             String sql = "SELECT tu FROM TipoUsuario tu";
            Query q = sesion.createQuery(sql);
            result = q.list();
            //tx.commit();
        } catch (Exception e) {
          /*  if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de getAll : " + e.getMessage());
            }
            System.out.println(e.getMessage());*/
        } finally {
            sesion.flush();
            sesion.close();
        }
        return result;
    }

    @Override
    public List<Usuario> getAllFinMembresia(int n) {
        List<Usuario> result = new ArrayList<>();
		List<Usuario> data = getAll();
		Date fecha = new Date();long diferencia ;
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
		for(int i=0;i<data.size();i++){
		 diferencia = ( data.get(i).getUsuFecFinm().getTime() - fecha.getTime() )/MILLSECS_PER_DAY;
		 if(diferencia==n)
			{
				result.add(data.get(i));
			}
		}
        return result;
    }

    @Override
    public void updateUsuarioFinMembresia() {
        List<Usuario> lista = getAllFinMembresia(2);
		Usuario usuario = new Usuario();
		usuario = getAllFinMembresia(2).get(0);
	 
		updateUsuario(usuario);
    }

    @Override
    public int buscarporUsuario(String usuUsuario) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        int valor=0;
        try {
           // tx = sesion.beginTransaction();
             String sql = "select u.persona.perCodigo from Usuario u where u.usuUsuario=:cuenta_usuario";
            Query q = sesion.createQuery(sql);
            q.setParameter("cuenta_usuario", usuUsuario);
            valor=(int) q.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        } 
       return valor;
        
    }

    @Override
    public String buscarporCodigo(int codigo) {
         Session sesion = HibernateUtil.getSessionFactory().openSession();
        String valor="";
        try {
           // tx = sesion.beginTransaction();
             String sql = "select u.usuUsuario from Usuario u where u.persona.perCodigo=:cuenta_usuario";
            Query q = sesion.createQuery(sql);
            q.setParameter("cuenta_usuario", codigo);
            valor=(String) q.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        } 
       return valor;
    }
    
        @Override
    public boolean SP_registro(String usuario,String empresa) {
            boolean result=false;
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
         
             Query q = session.createSQLQuery("{ CALL SP_logueo(:user,:empresa) }");
               q.setParameter("user",usuario);
               q.setParameter("empresa",empresa);
               q.executeUpdate();
               result=true;
        } catch (Exception e) {
            System.out.println("Error SP_registro : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return result;  
    }
}
