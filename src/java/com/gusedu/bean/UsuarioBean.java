package com.gusedu.bean;

import com.gusedu.dao.PersonaService;
import com.gusedu.dao.TipoUsuarioService;
import com.gusedu.dao.UsuarioService;
import com.gusedu.dao.impl.PersonaServiceImpl;
import com.gusedu.dao.impl.TipoUsuarioServiceImpl;
import com.gusedu.dao.impl.UsuarioServiceImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;


import org.primefaces.context.RequestContext;

import com.gusedu.model.Persona;
import com.gusedu.model.TipoUsuario;
import com.gusedu.model.Usuario;
import com.gusedu.util.StaticUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 
	UsuarioService usuarioservice;
	
	 
	TipoUsuarioService tipousuarioservice;
	
	 
	PersonaService personaservice;
	
	private Usuario usuario;
	private Persona persona;
	
	private List<Usuario> usuarios;
	
	private String query;
	private String queryTU;
	private boolean mesM;
	private int cantM;
	private Date fechaM;
	private String tiempo;
	private long diasRestantes;
	
	private TipoUsuario tipousuario;
	private List<TipoUsuario> tipousuarios;
	
	public UsuarioBean()
	{
            usuarioservice = new UsuarioServiceImpl();
            tipousuarioservice = new TipoUsuarioServiceImpl();
            personaservice = new PersonaServiceImpl();
            
		mesM=true; cantM=0;tiempo="Dias";
		usuario = new Usuario(); 
		persona = new Persona();
                 
                
		usuario.setTipoUsuario(new TipoUsuario());
                usuario.setPersona(new Persona());
		tipousuario = new TipoUsuario();
		if(usuario.getUsuCodigo()!=null){
			mesM=usuario.getUsuActivo() ? false : true;
		}
	}
	
	@PostConstruct
	public void listarusuarios()
	{
		/*if (query != null) {
			if (!query.isEmpty()) {
				return usuarios;
			}
		}*/
		usuarios=usuarioservice.getAll();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String preAdd() {
		usuario = new Usuario();
		usuario.setTipoUsuario(new TipoUsuario());
		return "pm:agregarUsuario?transition=flip";
	}
	//-----------Parte Web
	public void preAdd2() {
		usuario = new Usuario();
		usuario.setTipoUsuario(new TipoUsuario());
	}
	public void add2() {
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "Este codigo de usuario ya esta registrado");
			return;
		}
		if (usuarioservice.saveUsuario(usuario)) {
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente el usuario");
			listarusuarios();
			usuario = new Usuario();
			usuario.setTipoUsuario(new TipoUsuario());
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('dlgAddUsuario').hide();");
		} else {
			StaticUtil.errorMessage("Error", "No se pudo registrar el usuario");
 
		}
	}
	public String add() {
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "Este codigo de usuario ya esta registrado");
			return null;
		}
		if (usuarioservice.saveUsuario(usuario)) {
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente el usuario");
			usuario = new Usuario();
			return "pm:datosUsuario?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo registrar el usuario");
			return null;
		}
	}
	
	
	public String preUpdate(int idUsuario) {
		usuario = usuarioservice.getUsuarioeById(idUsuario);
		fechaM=usuario.getUsuFecFinm();
		mesM=usuario.getUsuActivo() ? false : true;
		diasRestantes=StaticUtil.diasRestantes(fechaM);
		//datosClinicos?faces-redirect=true
		return "editarUsuario?faces-redirect=true";
	}
	
	public void preUpdate2(int idUsuario) {
                System.out.println("ID USER : "+idUsuario);
		usuario = usuarioservice.getUsuarioeById(idUsuario);
		fechaM=usuario.getUsuFecFinm();
		mesM=usuario.getUsuActivo() ? false : true;
		diasRestantes=StaticUtil.diasRestantes(fechaM);
		//datosClinicos?faces-redirect=true
		//return "editarUsuario?faces-redirect=true";
	}
	
	public String update() {
		if (usuarioservice.updateUsuario(usuario)) {
			StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente el usuario");
			//		return "consultarPacientes?faces-redirect=true";
			return "gestionUsuario?faces-redirect=true";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar el usuario");
			return null;
		}
	}
	
	public void update2() {
		if (usuarioservice.updateUsuario(usuario)) {
			StaticUtil.correctMesage("�xito", "Se ha actualizado correctamente el usuario");
			listarusuarios();
			//		return "consultarPacientes?faces-redirect=true";
			//return "gestionUsuario?faces-redirect=true";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar el usuario");
			//return null;
		}
	}
	public void preDelete(int idUsuario) {
		usuario = usuarioservice.getUsuarioeById(idUsuario);
	}

	public void delete() {
		
		usuario.setUsuActivo(false);
		usuario.setUsuFecFinm(new Date());
		if (usuarioservice.updateUsuario(usuario)) {
			StaticUtil.correctMesage("Éxito", "Se ha dado debaja al usuario");
		 
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar el usuario");
 
		}
		usuario = new Usuario();
		usuario.setTipoUsuario(new TipoUsuario());
	}
	
	public void cancelar() {
		persona = new Persona();
		usuario = new Usuario();
		usuario.setTipoUsuario(new TipoUsuario());
		
		tipousuario = new TipoUsuario();
	}
	
	public void filtrarBusqueda() {
		usuarios = usuarioservice.getAll();
		List<Usuario> filtrados = new ArrayList<>();
		for (Usuario u : usuarios) {
			if(u.getPersona()==null)
			{
				if (u.getUsuEmpresa().toLowerCase().contains(query.toLowerCase()) || 
				        u.getUsuUsuario().toLowerCase().contains(query.toLowerCase()) ) {
						filtrados.add(u);
					}
			}else
			{
				if (u.getUsuEmpresa().toLowerCase().contains(query.toLowerCase()) || 
				        u.getUsuUsuario().toLowerCase().contains(query.toLowerCase()) ||
				        u.getPersona().getPerNombres().contains(query.toLowerCase()) ||
				        u.getPersona().getPerApellidoP().contains(query.toLowerCase())) {
						filtrados.add(u);
					}
			}
		}
		usuarios = filtrados;
	}


	   public List<TipoUsuario> getTipousuarios() {
		   if (queryTU != null) {
				if (!queryTU.isEmpty()) {
					return tipousuarios;
				}
			}
		  return tipousuarioservice.getAll();
	    }

	public TipoUsuario getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;
	}

	public String preAddTU() {
		tipousuario = new TipoUsuario();
		return "pm:agregarTipoUsuario?transition=flip";
	}
	public void preAddTU2() {
		tipousuario = new TipoUsuario();
		//return "pm:agregarTipoUsuario?transition=flip";
	}
	public String AddTU() {
		if (esRepetidoTU()) {
			StaticUtil.errorMessage("Error", "El nombre est� duplicado");
			return null;
		}
		
		if (tipousuarioservice.saveTipoUsuario(tipousuario)) {
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente el tipo de Usuario");
			System.out.println("Exito : "+ tipousuario.getTusDescripcion());
			tipousuario = new TipoUsuario();
			return "pm:datosTipoUsuario?transition=flip";
		} else {
			System.out.println("Fail");
			StaticUtil.errorMessage("Error", "No se pudo registrar el tipo de usuario");
			return null;
		}
	}
	public void AddTU2() {
		if (esRepetidoTU()) {
			StaticUtil.errorMessage("Error", "El nombre est� duplicado");
			return;
			//return null;
		}
		
		if (tipousuarioservice.saveTipoUsuario(tipousuario)) {
			StaticUtil.correctMesage("�xito", "Se ha registrado correctamente el tipo de Usuario");
			tipousuario = new TipoUsuario();
			//return "pm:datosTipoUsuario?transition=flip";
		} else {
			System.out.println("Fail");
			StaticUtil.errorMessage("Error", "No se pudo registrar el tipo de usuario");
			//return null;
		}
	}
	public String preUpdateTU(int idTipoUsuario) {
		tipousuario = tipousuarioservice.getUsuarioeById(idTipoUsuario);
		return "pm:editarTipoUsuario?transition=flip";
	}
	
	public String updateTU() {
		if (tipousuarioservice.updateTipoUsuario(tipousuario)) {
			StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente el tipo usuario");
			return "pm:datosTipoUsuario?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar el tipo de usuario");
			return null;
		}
	}
	
	public void updateTU2() {
		if (tipousuarioservice.updateTipoUsuario(tipousuario)) {
			StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente el tipo usuario");
			//return "pm:datosTipoUsuario?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar el tipo de usuario");
			//return null;
		}
	}
	
	public String preDatosUsuario(int idTipoUsuario){
		usuario = usuarioservice.getUsuarioeById(idTipoUsuario);
		if(usuario.getPersona()!=null)
		{
			persona=personaservice.getPersonaById(usuario.getPersona().getPerCodigo());
		}
		return "registrarUsuPersona?faces-redirect=true";
	}
	public void preDatosUsuario2(int idTipoUsuario){
		usuario = usuarioservice.getUsuarioeById(idTipoUsuario);
		persona = new Persona();
		if(usuario.getPersona()!=null)
		{
                       System.out.println("DATOS DE PERSONA : ");
			persona=personaservice.getPersonaById(usuario.getPersona().getPerCodigo());
 
		} 
	}
	
	public String DatosUsuario()
	{
		if(persona.getPerCodigo()==null)
		{
			if (personaservice.savePersona(persona)) {
				StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente los datos del usuario");
				usuario.setPersona(persona);
				usuarioservice.updateUsuario(usuario);
				
				persona = new Persona();
				usuario = new Usuario();
				usuario.setTipoUsuario(new TipoUsuario());
				return "gestionUsuario?faces-redirect=true";
			} else {
				StaticUtil.errorMessage("Error", "No se pudo registrar los datos del usuario");
				return null;
			}
		}else
		{
			if (personaservice.updatePersona(persona)) {
				StaticUtil.correctMesage("Éxito", "Se ha modificado correctamente los datos del usuario");
				persona = new Persona();
				usuario = new Usuario();
				usuario.setTipoUsuario(new TipoUsuario());
				return "gestionUsuario?faces-redirect=true";
			} else {
				StaticUtil.errorMessage("Error", "No se pudo registrar los datos del usuario");
				return null;
			}
		}

	}
	
	public void DatosUsuario2()
	{
		if(persona.getPerCodigo()==null)
		{
			if (personaservice.savePersona(persona)) {
				StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente los datos del usuario");
				usuario.setPersona(persona);
				usuarioservice.updateUsuario(usuario);
				
				persona = new Persona();
				usuario = new Usuario();
				usuario.setTipoUsuario(new TipoUsuario());
				//return "gestionUsuario?faces-redirect=true";
			} else {
				StaticUtil.errorMessage("Error", "No se pudo registrar los datos del usuario");
				//return null;
			}
		}else
		{
			if (personaservice.updatePersona(persona)) {
				StaticUtil.correctMesage("Éxito", "Se ha modificado correctamente los datos del usuario");
				persona = new Persona();
				usuario = new Usuario();
				usuario.setTipoUsuario(new TipoUsuario());
				//return "gestionUsuario?faces-redirect=true";
			} else {
				StaticUtil.errorMessage("Error", "No se pudo registrar los datos del usuario");
				//return null;
			}
		}

	}
	
	public void preDeleteTU(int idTipoUsuario) {
		tipousuario = tipousuarioservice.getUsuarioeById(idTipoUsuario);
	}

	public void deleteTU() {
		if (tipousuarioservice.deleteTipoUsuario(tipousuario)) {
			StaticUtil.correctMesage("Éxito", "Se ha eliminado correctamente el tipo de usuario");
		 
		} else {
			StaticUtil.errorMessage("Error", "No se pudo eliminar el tipo de usuario");
 
		}
		tipousuario = new TipoUsuario();
	}
	public void filtrarBusquedaTU() {
		tipousuarios = tipousuarioservice.getAll();
		
		List<TipoUsuario> filtrados = new ArrayList<>();
		for (TipoUsuario u : tipousuarios) {
			if (u.getTusDescripcion().toLowerCase().contains(queryTU.toLowerCase())) {
				filtrados.add(u);
			}
		}
		tipousuarios = filtrados;
	}


	public String getQueryTU() {
		return queryTU;
	}

	public void setQueryTU(String queryTU) {
		this.queryTU = queryTU;
	}
	
	public boolean esRepetido() {
		boolean repetido = false;
		for (Usuario u : usuarioservice.getAll()) {  
			if (u.getUsuUsuario().equalsIgnoreCase(usuario.getUsuUsuario())) {
				repetido = true;
				return repetido;
			}
		}
		return repetido;
	}
	
	public boolean esRepetidoTU() {
		boolean repetido = false;
		for (TipoUsuario tu : tipousuarioservice.getAll()) {  
			if (tu.getTusDescripcion().equalsIgnoreCase(tipousuario.getTusDescripcion())) {
				repetido = true;
				return repetido;
			}
		}
		return repetido;
	}
	
	public void addMessage()
	{
		mesM=usuario.getUsuActivo() ? false : true;
		if(mesM)
		{
			cantM=0;
		}else
		{
			cantM=1;
			usuario.setUsuFecFinm(new Date());
			cargarFecha();
		}
	}
	
	public void Aumento()
	{
		mesM=usuario.getUsuActivo() ? false : true;
		if(mesM)
		{
			cantM=0;
			usuario.setUsuFecFinm(new Date());
		}else
		{
			cantM=1;
			usuario.setUsuFecFinm(fechaM);
			cargarFecha();
		}
	}
	public void cargarFecha()
	{
		usuario.setUsuFecFinm(StaticUtil.sumarRestarDiasFecha(StaticUtil.getFechaActual(), cantM,tiempo));
	}
	public void aumentarMembresia(){
		usuario.setUsuFecFinm(StaticUtil.sumarRestarDiasFecha(fechaM, cantM,tiempo));
	}

	public void activarDemonio()
	{
		System.out.println("Inicio de Daemon.");
        long delay = 20;// demoraantes de la primera ejecucion
        long period = 180; //periodo entre ejecuciones ( en segundos)
        startUpTimer(delay, period);
        System.out.println("Daemon iniciado");
	}

	public void PruebaSP()
	{
		System.out.println("Estado : "+usuario.getUsuActivo());
		/*EmailService ser = new EmailService();
		ser.ejecutarStoredProcedure();*/
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		EmployeeDao dao = (EmployeeDao) ctx.getBean("employeeDao"); 
		//calling stored procedure using DAO method 
		System.out.println("Employee name for id 103 is : " + dao.getEmployeeName(103));*/
		}
	
	public boolean isMesM() {
		return mesM;
	}

	public void setMesM(boolean mesM) {
		this.mesM = mesM;
	}

	public int getCantM() {
		return cantM;
	}

	public void setCantM(int cantM) {
		this.cantM = cantM;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
    public  void startUpTimer(long delay, long period)
    {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() { 
                List<Usuario> listmembresia = usuarioservice.getAllFinMembresia(0);
                List<Usuario> listacorreo = usuarioservice.getAllFinMembresia(6);
                Usuario u = new Usuario();
                for(int i=0;i<listmembresia.size();i++)
                {
                	u=listmembresia.get(i);
                    u.setUsuActivo(false);
                    usuarioservice.updateUsuario(u);
                }
                for(int j=0;j<listacorreo.size();j++)
                {
                	//EmailService obj = new EmailService();
                	
            		//obj.enviarEmail(listacorreo.get(j).getPersona().getPerCorreo());
            		System.out.println("Correo : "+listacorreo.get(j).getPersona().getPerCorreo());
                }
            }
        };
        //Inicia el timer con demora inicial y periodo especificados en milisegundos
        timer.schedule(task, delay*1000, period*1000);
    }

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Date getFechaM() {
		return fechaM;
	}

	public void setFechaM(Date fechaM) {
		this.fechaM = fechaM;
	}

	public long getDiasRestantes() {
		return diasRestantes;
	}

	public void setDiasRestantes(long diasRestantes) {
		this.diasRestantes = diasRestantes;
	}
        
        public void ActualizarEstado(Usuario usu)
        {
            System.out.println("USUARIO : "+usu.getUsuActivo());
            usuarioservice.updateUsuario(usu);
        }
}
