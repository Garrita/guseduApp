/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.PersonaDao;
import dao.PersonaDaoImpl;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.gusedu.model.Persona;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class PersonaBean2 {
 
    private List<Persona>  listapersona;
    private Persona persona;
    PersonaDao personadao;
    
    
    public PersonaBean2() {
        persona = new Persona();
        personadao = new PersonaDaoImpl();
    }

    
    public void SAVE()
    {
        persona.setPerNombres("JOEL_PRUEBA");
        persona.setPerApellidoP("R_PRUEBA");
        persona.setPerApellidoM("G_PRUEBA");
        persona.setPerEdad(22);
        personadao.savePersona(persona);
    }
    
    public void UPDATE()
    {
        persona.setPerCodigo(257);
         persona.setPerNombres("JA_PRUEBA");
        persona.setPerApellidoP("R_PRUEBA");
        persona.setPerApellidoM("G_PRUEBA");
        persona.setPerEdad(22);
        personadao.updatePersona(persona);
    }
    
    public void DELETE()
    {
        persona.setPerCodigo(257);
        personadao.deletePersona(persona);
    }
    
    public void BUSCAR()
    {
        persona = personadao.buscar(1);
        System.out.println("NOMBRE : "+persona.getPerNombres());
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public List<Persona> getListapersona() {
        listapersona = personadao.getAllPersona();
        return listapersona;
    }   
    
}
