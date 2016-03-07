/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.ClienteService;
import com.gusedu.dao.FacturaService;
import com.gusedu.dao.PersonaService;
import com.gusedu.dao.TerapiaService;
import com.gusedu.dao.UsuarioService;
import com.gusedu.dao.VisitaService;
import com.gusedu.dao.impl.ClienteServiceImpl;
import com.gusedu.dao.impl.FacturaServiceImpl;
import com.gusedu.dao.impl.PersonaServiceImpl;
import com.gusedu.dao.impl.TerapiaServiceImpl;
import com.gusedu.dao.impl.UsuarioServiceImpl;
import com.gusedu.dao.impl.VisitaServiceImpl;
import com.gusedu.entidad.Calendario;
import com.gusedu.entidad.cabecera_factura;
import com.gusedu.entidad.detalle_factura;
import com.gusedu.entidad.EUltimaVisitaxCliente;
import com.gusedu.model.Cliente;
import com.gusedu.model.Persona;
import com.gusedu.model.Terapia;
import com.gusedu.model.TipoCliente;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;
import com.gusedu.util.StaticUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Joel Romero Guillén
 */
@ManagedBean
@SessionScoped
public class ScheduleView {

    /**
     * Creates a new instance of ScheduleView
     */
    
    public ScheduleModel eventModel;   
    public ScheduleModel lazyEventModel;
    public ScheduleEvent event = new DefaultScheduleEvent();
    
    VisitaService visitaService;
    TerapiaService terapiaService;
    private List<Visita> listaVisita;
    
    private Cliente cli;
    private String nombre;
    private int codigo;
    private boolean calendar;
    
    private Visita visita;
    private Terapia terapia;
    private EUltimaVisitaxCliente sesion;
    private cabecera_factura cab_fact;
    
    private List<detalle_factura> lista_detfact;
    
    ClienteService clienteService;
    PersonaService personaservice;
    UsuarioService usuarioservice;
    FacturaService facturaService;
    
    private String terapeuta;
    
    public ScheduleView() {
        visitaService = new VisitaServiceImpl();
        terapiaService = new TerapiaServiceImpl();
        clienteService= new ClienteServiceImpl();
        personaservice = new PersonaServiceImpl();
        usuarioservice = new UsuarioServiceImpl();
        facturaService = new FacturaServiceImpl();
        cli = new Cliente();
        cli.setPersona(new Persona());
        cli.setTipoCliente(new TipoCliente());
        nombre="";
        
        visita = new Visita();
        visita.setCliente(new Cliente());
        
        terapia = new Terapia();
        terapia.setTipoTerapia(new TipoTerapia());
        terapia.setVisita(new Visita());
        calendar=false;
        
        sesion = new EUltimaVisitaxCliente();
        cab_fact= new cabecera_factura();
        terapeuta="";
        llenarCalendario();
    }
    
    public void llenarCalendario()
    {
        eventModel = new DefaultScheduleModel();
        
        //listaVisita=visitaService.getVisitasByEmpresa();   
        List<Calendario> listaCalendario = visitaService.SP_Calendario(terapeuta);
        for ( int i=0;i<listaCalendario.size();i++)
        {
             Date fecE= listaCalendario.get(i).getFec_creacion();
             Date fecF= listaCalendario.get(i).getFec_fin();
             String pac= listaCalendario.get(i).getNombre();
             Visita v = new Visita();
             v.setVisCodigo(listaCalendario.get(i).getVis_codigo());
             v.setVisFecCreacion(fecF);
             v.setVisFecFin(fecF);
             v.setVisDescripcion(listaCalendario.get(i).getDescripcion());
             v.setVisLlegada(listaCalendario.get(i).getLlegada());
             Cliente cli = new Cliente();
             cli.setCliCodigo(listaCalendario.get(i).getCli_codigo());
             v.setCliente(cli);
             eventModel.addEvent(new DefaultScheduleEvent(pac,fecE,fecF,v));

        }
    }
    
    public void cleaner()
    {
        cli = new Cliente();
        cli.setPersona(new Persona());
        cli.setTipoCliente(new TipoCliente());
        
        visita = new Visita();
        visita.setCliente(new Cliente());
        
        terapia = new Terapia();
        terapia.setTipoTerapia(new TipoTerapia());
        terapia.setVisita(new Visita());
    }

    public String getTerapeuta() {
        return terapeuta;
    }

    public void setTerapeuta(String terapeuta) {
        this.terapeuta = terapeuta;
    }
    
 

    public boolean isCalendar() {
        return calendar;
    }

    public void setCalendar(boolean calendar) {
        this.calendar = calendar;
    }

  
    public void paciente()
    { 
        cli = clienteService.getClienteById(codigo);
    }

    
    public void prueba(){
        listaVisita=visitaService.getVisitas(); 
          for ( int i=0;i<listaVisita.size();i++)
        {
             Date fecE= listaVisita.get(i).getVisFecCreacion();
             Date fecA= listaVisita.get(i).getVisFecCreacion();
             fecA.setDate(26);
             System.out.println("VISITA : "+fecE + "-"+fecA);
        }
    }
    
    public List<Visita> getListaVisita() {
        return listaVisita;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    public Terapia getTerapia() {
        return terapia;
    }

    public void setTerapia(Terapia terapia) {
        this.terapia = terapia;
    }

    
    
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    public void setLazyEventModel(ScheduleModel lazyEventModel) {
        this.lazyEventModel = lazyEventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
    
 
     
    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null)
        {    
            
            eventModel.addEvent(event);
            // saveVisita();
            saveVisitaSP();
             llenarCalendario();
        } else{
            eventModel.updateEvent(event);
        }
        cancel();
        event = new DefaultScheduleEvent();
    }
    
    public void cancel()
    {
        visita = new Visita();
        visita.setCliente(new Cliente());
        
        terapia = new Terapia();
        terapia.setTipoTerapia(new TipoTerapia());
        terapia.setVisita(new Visita());
    }
    public void before(SelectEvent selectEvent)
    {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
        Date fecha = new Date();

        
        if (cli.getCliCodigo() == null || cli.getCliCodigo() == 0) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Cuidado", "Seleccione un Paciente");
            addMessage(message);
            return;
        }
        int a = visitaService.visitaProgramada(event.getStartDate(), cli);
        if(a==1)
        {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Cuidado", "Ya existe una cita del paciente "+cli.getPersona().getPerNombres()+" "+cli.getPersona().getPerApellidoP()+" "+cli.getPersona().getPerApellidoM()+" para la fecha seleccionada");
            addMessage(message);
            return ;
        }
       /* if(event.getStartDate().before(fecha))
        {
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Cuidado", "No puede elegir días pasados de la fecha de hoy");
            addMessage(message);
            return ;
        }*/
        visita = new Visita();
        visita.setCliente(new Cliente());
        visita.setVisFecCreacion(event.getStartDate());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(event.getEndDate());
        calendar.add(Calendar.MINUTE, 30);
        visita.setVisFecFin(calendar.getTime());
        System.out.println("FECHA INICIO : " + visita.getVisFecCreacion() + "|| FECHA FIN : " + visita.getVisFecFin());
        System.out.println("DESCRIPCION : "+visita.getVisDescripcion());
        RequestContext context = RequestContext.getCurrentInstance();
       /* RequestContext.getCurrentInstance().update("formCalendario");
        RequestContext.getCurrentInstance().update("pnlCitas");
        RequestContext.getCurrentInstance().update("eventDetails");*/
        context.execute("PF('eventDialog').show();");
             
       
    }
    
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        
        visita =(Visita)event.getData();
         cli=clienteService.getClienteById(visita.getCliente().getCliCodigo());
      //  cli= visita.getCliente(); 
         
       // visita
        /**************         N° de Sesiones                     ***********/
        List<EUltimaVisitaxCliente> lis= visitaService.getVisitasCliente(cli.getCliCodigo());
        for(int i=0;i<lis.size();i++)
        {
            if(lis.get(i).getVisitaCodigo()==visita.getVisCodigo())
            {
                setSesion(lis.get(i));
            }
        }
        /*********************************************************************/
       
        visita.setVisFecCreacion(event.getStartDate());
        visita.setVisFecFin(event.getEndDate());
       calendar=false;
       FacesContext fc = FacesContext.getCurrentInstance();
       fc.getExternalContext().getSessionMap().put("visActual", visita);
       fc.getExternalContext().getSessionMap().put("ultimavisita", visita);
       
       //VisitaBean vbean = new VisitaBean();
       //vbean.changeVisita(visita);
        //------- Terapia ---------//
        terapia = terapiaService.terapiaByVisita(visita);
        fc.getExternalContext().getSessionMap().put("terActual", terapia);
    }
     
    public void change()
            {
                 System.out.println("PRE-EDIT : "+calendar);
                if(calendar==false)
                {
                    int a=usuarioservice.buscarporUsuario(terapia.getTerDescripcion());
                    terapia.setTerDescripcion(""+a);
                    calendar=true;
                    System.out.println("POST-EDIT : "+calendar);
                }else
                {
                    calendar=false;
                     
                 /*   terapia.setTerUsuCreacion(cortar(ust, 0));
                    terapia.setTerDescripcion(cortar(ust, 1));*/
                    
                    Persona p = personaservice.getPersonaById(Integer.parseInt(terapia.getTerDescripcion()));
                    terapia.setTerUsuCreacion(p.getPerNombres()+" "+p.getPerApellidoP()+" "+p.getPerApellidoM());
                    String ust=usuarioservice.buscarporCodigo(Integer.parseInt(terapia.getTerDescripcion()));
                    terapia.setTerDescripcion(ust);
                    terapiaService.updateTerapia(terapia);  
                    //visitaService.updateVisita(visita);
                     terapia = terapiaService.terapiaByVisita(visita);
                }
            }
            
    
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
         visita.setVisFecCreacion(event.getStartDate());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(event.getEndDate()); 
        calendar.add(Calendar.MINUTE, 30);
        visita.setVisFecFin(calendar.getTime());
        System.out.println("FECHA INICIO : "+ visita.getVisFecCreacion() +"|| FECHA FIN : "+visita.getVisFecFin());
    }
     
    
    public void saveVisita()
    {
         visita.setVisPresencial(Boolean.valueOf(true));
        visita.setVisPrioridad(Integer.valueOf(2));
        visita.setVisEstado(Integer.valueOf(1));
        visita.setCliente(cli);
           String usuarioCreacion = StaticUtil.userLogged();
         visita.setVisUsuCreacion(usuarioCreacion);
         visita.setVisCostoTotal(Double.valueOf(0.0D));
        visitaService.saveVisita(visita);
        visita= new Visita();
    }
    
    public String cortar(String cadena,int n)
    {
        String salida="";
        String [] arreglo = cadena.split("\\|");
           for (int i = 0; i < arreglo.length; i++) {
            if(i==n)
            {
                salida=arreglo[i];
            }
        }
          return salida;
    }
    
    public void saveVisitaSP()
    {
        visita.setVisLlegada(false);
        visita.setVisPresencial(true);
        visita.setVisPrioridad(2);
        visita.setVisEstado(1);
        visita.setCliente(cli);
        String usuarioCreacion = StaticUtil.userLogged();
        visita.setVisUsuCreacion(usuarioCreacion);
        visita.setVisCostoTotal(0.0);
        terapia.setTerCosto(0.0);
        
        String ust=terapia.getTerUsuCreacion();
        terapia.setTerUsuCreacion(cortar(ust, 0));
        terapia.setTerDescripcion(cortar(ust, 1));
        
        visitaService.SPsaveVisitaxTerapia(visita, terapia);
    }
            
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         System.out.println("Se movio la cita D: \nNueva Entrada : "+event.getScheduleEvent().getStartDate()+"\nNueva Salida : "+event.getScheduleEvent().getEndDate());
         Visita v = (Visita) event.getScheduleEvent().getData();
         Visita v1 = visitaService.getVisitaById(v.getVisCodigo());
         v1.setVisFecCreacion(event.getScheduleEvent().getStartDate());
         v1.setVisFecFin(event.getScheduleEvent().getEndDate());
         visitaService.updateVisita(v1);
        // visitaService.SP_ActualizarDiar(v1);
         Terapia ter = terapiaService.terapiaByVisita(v);
         ter.setTerFecRealizada(event.getScheduleEvent().getStartDate());
         terapiaService.updateTerapia(ter);
         System.out.println("ID VISITA : "+ v.getVisCodigo() );
         addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        System.out.println("Se movio la cita D: \nNueva Entrada : "+event.getScheduleEvent().getStartDate()+"\nNueva Salida : "+event.getScheduleEvent().getEndDate()); 
        Visita v = (Visita) event.getScheduleEvent().getData();
         Visita v1 = visitaService.getVisitaById(v.getVisCodigo());
         v1.setVisFecCreacion(event.getScheduleEvent().getStartDate());
         v1.setVisFecFin(event.getScheduleEvent().getEndDate());
        
      visitaService.updateVisita(v1);
        
        Terapia ter = terapiaService.terapiaByVisita(v);
        ter.setTerFecRealizada(event.getScheduleEvent().getStartDate());
        terapiaService.updateTerapia(ter);
        System.out.println("ID VISITA : "+ v.getVisCodigo() );
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
     public void ELIMINARCITA()
    {
       if(visitaService.SPdeleteVisita(visita.getVisCodigo()))
       {
           System.out.println("SI ELIMINIO");
           llenarCalendario();
       }else
       {
           System.out.println("No elimino");
       }
    }
    public void DELETE()
    {
        System.out.println("Paciente : "+visita.getCliente().getCliCodigo());
        System.out.println("Visita : " +visita.getVisCodigo());
    }
    
    public void ATRACA()
    {
        System.out.println("COLO : ");
    }

    public void CREAR_FACTURA()
    {
        visitaService.SP_CrearFactura(visita.getVisLlegada(), cli.getCliCodigo(), visita.getVisCodigo());
    }
    
    public EUltimaVisitaxCliente getSesion() {
        return sesion;
    }

    public void setSesion(EUltimaVisitaxCliente sesion) {
        this.sesion = sesion;
    }

    public List<detalle_factura> getLista_detfact() {
  
        return lista_detfact;
    }

    public void setLista_detfact(List<detalle_factura> lista_detfact) {
        this.lista_detfact = lista_detfact;
    }

    public cabecera_factura getCab_fact() {
        return cab_fact;
    }

    public void setCab_fact(cabecera_factura cab_fact) {
        this.cab_fact = cab_fact;
    }
    
    public void BUSCARFACTURA()
    {
        System.out.println("Codigo Cliente : "+cli.getCliCodigo()+"\nFec : "+visita.getVisFecCreacion());
        cab_fact= facturaService.SP_ObtenerCabecera(cli.getCliCodigo(),visita.getVisFecCreacion());
        LISTAR();
    }
    
    public void BUSCARFACTURA_EXTERNO(int cli_codigo)
    {
       cli.setCliCodigo(cli_codigo);
       cab_fact= facturaService.SP_ObtenerCabecera(cli.getCliCodigo(),new Date());
       lista_detfact=facturaService.SP_ListaDetalle(cli.getCliCodigo(),new Date());
      
    }
    
    public void UPDATE_FACTURA()
    {
        facturaService.SP_UpdateCabecera(cli.getCliCodigo(), cab_fact.getFactura_real());
        System.out.println("CLIENTE : "+cli.getCliCodigo()+"\nFctura Real : "+cab_fact.getFactura_real());
    }
    
    public void LISTAR()
    {
               lista_detfact=facturaService.SP_ListaDetalle(cli.getCliCodigo(),visita.getVisFecCreacion());
    }
      public void onRowEdit(RowEditEvent event) {
        detalle_factura detfact ;
        detfact = (detalle_factura) event.getObject();
        double val = terapia.getTerCosto();
        terapia.setTerCosto(detfact.getPrecio_unitario());
         terapiaService.updateTerapia(terapia);
          System.out.println("TERAPIA : "+terapia.getTerCodigo()+"-VISITA : "+visita.getVisCodigo()+"-Valor :"+val);
         terapiaService.SP_CambiarPrecioTerapia(terapia.getTerCodigo(), visita.getVisCodigo(), val);
         BUSCARFACTURA();
    }
}
