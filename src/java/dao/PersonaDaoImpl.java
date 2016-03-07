/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import com.gusedu.model.Persona;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gusedu.util.HibernateUtil;

/**
 *
 * @author NV55C
 */
public class PersonaDaoImpl implements PersonaDao{

    GenericDaoImpl dao = new GenericDaoImpl() {};
    
    @Override
    public void savePersona(Persona persona) {
       dao.Guardar(persona);
    }

    @Override
    public void updatePersona(Persona persona) {
      dao.Actualizar(persona);
    }

    @Override
    public void deletePersona(Persona persona) {
        dao.Eliminar(persona);
    }

    @Override
    public List<Persona> getAllPersona() {
        List<Persona> persona = new ArrayList<Persona>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            persona = session.createQuery("from Persona").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return persona;
    }

    @Override
    public Persona buscar(int id) {
           Persona pe= (Persona) dao.Buscar(Persona.class,id);
           return pe;
    }
    
}
