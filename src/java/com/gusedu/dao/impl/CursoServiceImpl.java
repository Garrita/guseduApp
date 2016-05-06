/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao.impl;

import com.gusedu.dao.CursoService;
import com.gusedu.entidad.EInformacionCurso;
import com.gusedu.entidad.EParametro;
import com.gusedu.model.Cliente;
import com.gusedu.model.Persona;
import com.gusedu.model.TipoCliente;
import com.gusedu.model.TipoUsuario;
import com.gusedu.model.Usuario;
import com.gusedu.util.HibernateUtil;
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class CursoServiceImpl implements CursoService{
    
    @PersistenceContext
    EntityManager em;
	
    @SuppressWarnings("unchecked")

    @Override
    public List<String> listarParametros() 
    {
        System.out.println("Se ejecuta listarParametros - CursoServiceImpl");
        List<String> lista= new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query q = session.createSQLQuery("{ CALL SP_ListarParametros() }");
            lista=q.list();
     
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
        return lista;  
    }
 
    @Override
    public boolean registroAlumno(Persona persona) 
    {
        boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            persona.setPerFecCreacion(new Date());
            tx = sesion.beginTransaction();
            sesion.save(persona);
            Cliente cliente = new Cliente();
            TipoCliente tipoCliente = new TipoCliente();
            Usuario usuario = new Usuario();
            String username = persona.getPerNombres().trim().substring(0, 1);
            username = (new StringBuilder()).append(username).append(persona.getPerApellidoP().trim().replaceAll(" ", "")).toString();
            username = username.toLowerCase();
            usuario.setUsuUsuario(username);
            usuario.setUsuPassword("1234");
            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario.setTusCodigo(6);
            usuario.setTipoUsuario(tipoUsuario);
            tipoCliente.setTclCodigo(2);
            cliente.setTipoCliente(tipoCliente);
            cliente.setCliUsuCreacion("Gusedu");
            persona.setPerUsuCreacion("Gusedu");
            cliente.setPersona(persona);
            sesion.save(cliente);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de registroAlumno : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return resultado;
    }
    
    @Override
    public boolean registroInformacionCurso(EInformacionCurso infcur) 
    {
    boolean resultado = false;
     Session session = HibernateUtil.getSessionFactory().openSession();
     try {
         Query q = session.createSQLQuery("{ CALL SP_RegistrarInformacionCurso(:formacad,:tallercurs,:otrotall,:nombredepo,:fechahoradepo,:enviocorreo,:numeroperac,:codigopersona) }");
         q.setParameter("formacad", infcur.getInf_formacad());
         q.setParameter("tallercurs", infcur.getInf_tallercurs());
         q.setParameter("otrotall", infcur.getInf_otrotall());
         q.setParameter("nombredepo", infcur.getInf_nombredepo());
         q.setParameter("fechahoradepo", infcur.getInf_fechahoradepo());
         q.setParameter("enviocorreo", infcur.getInf_enviocorreo());
         q.setParameter("numeroperac", infcur.getInf_numerooperac());
         q.setParameter("codigopersona", infcur.getPer_codigo());
         q.executeUpdate();
         resultado = true;
     }
     catch(Exception e)
     {
         System.out.println("ERROR de SP_RegistrarInformacionCurso : "+e.getMessage());
         resultado=false;
     }
     return resultado;
    }
}
