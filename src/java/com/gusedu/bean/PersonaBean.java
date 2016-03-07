package com.gusedu.bean;

import com.gusedu.dao.impl.PersonaServiceImpl;
import com.gusedu.model.Persona;
import com.gusedu.dao.PersonaService;
import com.gusedu.util.StaticUtil;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

// Referenced classes of package com.gusedu.bean:
//            PacienteBean
@ManagedBean
@SessionScoped
public class PersonaBean {

    PersonaService personaService;
    private List<Persona> listaTerapeutas;
    private Persona persona;

    public PersonaBean() {
        persona = new Persona();
        personaService = new PersonaServiceImpl();
        // listaTerapeutas = personaService.getTerapeutas();
        LISTAR_TERAPEUTA();
        
    }
    
    public void LISTAR_TERAPEUTA()
    {
         listaTerapeutas = personaService.SP_ListarTerapeutas();
    }

    public List<Persona> getListaTerapeutas() {
        return listaTerapeutas;
    }

    public void setListaTerapeutas(List<Persona> listaTerapeutas) {
        this.listaTerapeutas = listaTerapeutas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

   

    

    public void registroPacienteV2() {
        String empresa = StaticUtil.userLogged();
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
         PacienteBean objetoBean = (PacienteBean)fc.getExternalContext().getSessionMap().get("pacienteBean");

        UbigoBean ubigeoBean = (UbigoBean) fc.getExternalContext().getSessionMap().get("ubigoBean");
        persona.setPerDepartamento(ubigeoBean.getIdDepartamento());
        persona.setPerProvincia(ubigeoBean.getIdProvincia());
        persona.setPerDistrito(ubigeoBean.getIdDistrito());

        if (personaService.validarDni(persona.getPerDni()) == false) {
            StaticUtil.errorMessage("Advertencia", "Ya existe una persona registrada con este DNI");
            StaticUtil.keepMessages();
            return;
        }

        if (personaService.registroPaciente(persona, empresa)) {
            persona = new Persona();
            StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente al paciente");
            StaticUtil.keepMessages();
            context.execute("PF('dlg1').hide();");
        } else {
            System.out.println("Error Fatal");
            persona = new Persona();

        }
            objetoBean.listado();

    }

    public void cancel() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlg1').hide();");
        persona = new Persona();
    }

    public void inicializar() {
        persona = new Persona();
    }

    public void valida() {
        FacesContext fc = FacesContext.getCurrentInstance();

        //query= fc.getExternalContext().getSessionMap().get("query").toString();
        if (fc.getExternalContext().getSessionMap().get("query") != null) {
            String query = fc.getExternalContext().getSessionMap().get("query").toString();
            if (StaticUtil.esSoloNumero(query)) {
                persona.setPerDni(query);
                persona.setPerNombres("");
            } else {
                persona.setPerNombres((new StringBuilder()).append(query.substring(0, 1).toUpperCase()).append(query.substring(1).toLowerCase()).toString());
                persona.setPerDni("");
            }
        }

    }

    public void validaDni(String dni) {

        System.out.println("Hola ^_^");
        boolean valor = personaService.validarDni(dni);
        if (valor) {
            System.out.println("It's true");
        } else {
            System.out.println("It's false");
        }

    }

    public void validaEdad() {
        int e = persona.getPerFecNacimiento().getYear() + 1900;
        Date fec = new Date();
        int anio = fec.getYear() + 1900;
        int ed = anio - e;
        persona.setPerEdad(ed);
        System.out.println("Edad : " + ed);
    }

}
