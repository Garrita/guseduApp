/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.ClienteService;
import com.gusedu.dao.FacturaService;
import com.gusedu.dao.PersonaService;
import com.gusedu.dao.ProductoService;
import com.gusedu.dao.TerapiaService;
import com.gusedu.dao.UsuarioService;
import com.gusedu.dao.VisitaService;
import com.gusedu.dao.impl.ClienteServiceImpl;
import com.gusedu.dao.impl.FacturaServiceImpl;
import com.gusedu.dao.impl.PersonaServiceImpl;
import com.gusedu.dao.impl.ProductoServiceImpl;
import com.gusedu.dao.impl.TerapiaServiceImpl;
import com.gusedu.dao.impl.UsuarioServiceImpl;
import com.gusedu.dao.impl.VisitaServiceImpl;
import com.gusedu.entidad.Calendario;
import com.gusedu.entidad.cabecera_factura;
import com.gusedu.entidad.detalle_factura;
import com.gusedu.entidad.EUltimaVisitaxCliente;
import com.gusedu.model.Cliente;
import com.gusedu.model.Persona;
import com.gusedu.model.Producto;
import com.gusedu.model.Terapia;
import com.gusedu.model.TipoCliente;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;
import com.gusedu.util.StaticUtil;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
    private List<Producto> listandoProductos;
    
    ClienteService clienteService;
    PersonaService personaservice;
    UsuarioService usuarioservice;
    FacturaService facturaService;
    ProductoService productoService;
    
    private String terapeuta;
    
    private double valorTerapia;
    private boolean valorEvento;
    private String tipoevento;
    private double mont;
    
    public String toShort(Date fecha)
    {
        String cadena;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if(fecha==null)
        {
            cadena= "fail";
        }else
        {
            cadena=sdf.format(fecha);
        }
        return cadena;
    }
        
    public ScheduleView() {
        visitaService = new VisitaServiceImpl();
        terapiaService = new TerapiaServiceImpl();
        clienteService= new ClienteServiceImpl();
        personaservice = new PersonaServiceImpl();
        usuarioservice = new UsuarioServiceImpl();
        facturaService = new FacturaServiceImpl();
        productoService = new ProductoServiceImpl();
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
        
        valorTerapia=150;
        valorEvento=false;
        mont=0;
        validarCalendario();
        LISTANDO_PRODUCTOS();
    }
    
    public void validarCalendario()
    {
        if(!"Terapeuta".equals(StaticUtil.usuario_tipo()))
        {
            terapeuta="";
        }else
        {
            terapeuta=StaticUtil.usuario_nombre();
        }
        llenarCalendario();
    }
    
    public void llenarCalendario()
    {
        eventModel = new DefaultScheduleModel();
        System.out.println("Nombre : "+StaticUtil.usuario_nombre()+"\nTipo : "+StaticUtil.usuario_tipo());
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
             if(listaCalendario.get(i).getCli_codigo()!=0)
             {
                Cliente cli = new Cliente();
                cli.setCliCodigo(listaCalendario.get(i).getCli_codigo());
                v.setCliente(cli);
             }
             DefaultScheduleEvent evento = new DefaultScheduleEvent(pac, fecE, fecF, v);
             evento.setStyleClass(listaCalendario.get(i).getTte_codigo());   
             eventModel.addEvent(evento);

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

    public List<Producto> getListandoProductos() {
        return listandoProductos;
    }

    public void setListandoProductos(List<Producto> listandoProductos) {
        this.listandoProductos = listandoProductos;
    }

    public String getTerapeuta() {
        return terapeuta;
    }

    public void setTerapeuta(String terapeuta) {
        this.terapeuta = terapeuta;
    }

    public String getTipoevento() {
        return tipoevento;
    }

    public void setTipoevento(String tipoevento) {
        this.tipoevento = tipoevento;
    }

    public double getMont() {
        return mont;
    }

    public void setMont(double mont) {
        this.mont = mont;
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

    public boolean isValorEvento() {
        return valorEvento;
    }

    public void setValorEvento(boolean valorEvento) {
        this.valorEvento = valorEvento;
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
        System.out.println("Valor de Evento : "+ valorEvento);
        RequestContext context = RequestContext.getCurrentInstance();
        if(valorEvento)
        {
            System.out.println("Va ha registrar Eventos");
            visita = new Visita();
            visita.setVisFecCreacion(event.getStartDate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(event.getEndDate());
            calendar.add(Calendar.MINUTE, 30);
            visita.setVisFecFin(calendar.getTime());
            context.execute("PF('dlgEvento').show();");
        }else
        {
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
        
       /* RequestContext.getCurrentInstance().update("formCalendario");
        RequestContext.getCurrentInstance().update("pnlCitas");
        RequestContext.getCurrentInstance().update("eventDetails");*/
        context.execute("PF('eventDialog').show();");
        }
       
             
       
    }
    
    public void CHANGE_VALUE()
    {
        isValorEvento();
    }
    
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
         
        visita =(Visita)event.getData();
        visita.setVisFecCreacion(event.getStartDate());
        visita.setVisFecFin(event.getEndDate());
        calendar=false;
        
        RequestContext context = RequestContext.getCurrentInstance();
           if(visita.getCliente()==null)
           {
               visita.setVisPrioridad(visitaService.SP_Selecionar_TipoEvento(visita.getVisCodigo()));
               tipoevento=event.getTitle();
               context.execute("PF('dlgEventoDetalle').show();");
           }else
           {
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
       
       FacesContext fc = FacesContext.getCurrentInstance();
       fc.getExternalContext().getSessionMap().put("visActual", visita);
       fc.getExternalContext().getSessionMap().put("ultimavisita", visita);
        System.out.println("FLAG : "+visita.getVisLlegada());
       //VisitaBean vbean = new VisitaBean();
       //vbean.changeVisita(visita);
        //------- Terapia ---------//
        terapia = terapiaService.terapiaByVisita(visita);
        fc.getExternalContext().getSessionMap().put("terActual", terapia);
        context.execute("PF('eventDialog2').show();");
           }
         
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
                    if (visita.getCliente() != null) {
                        Persona p = personaservice.getPersonaById(Integer.parseInt(terapia.getTerDescripcion()));
                        terapia.setTerUsuCreacion(p.getPerNombres() + " " + p.getPerApellidoP() + " " + p.getPerApellidoM());
                        String ust = usuarioservice.buscarporCodigo(Integer.parseInt(terapia.getTerDescripcion()));
                        terapia.setTerDescripcion(ust);
                        terapiaService.updateTerapia(terapia);
                        terapia = terapiaService.terapiaByVisita(visita);
                        visita.setVisPrioridad(0);
                    }
                    
                    //visitaService.updateVisita(visita); ARREGLAR
                    visitaService.SP_ActualizaCita_DescEvento(visita);
                    if(visita.getVisPrioridad()!=0)
                    {
                        tipoevento=visitaService.SP_SelectEvento(visita.getVisPrioridad());
                        System.out.println("Tipo Evento : "+tipoevento);
                       
                    }
                     llenarCalendario();
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
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se movio la Cita", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         System.out.println("Se movio la cita D: \nNueva Entrada : "+event.getScheduleEvent().getStartDate()+"\nNueva Salida : "+event.getScheduleEvent().getEndDate());
         Visita v = (Visita) event.getScheduleEvent().getData();
         Visita v1 = visitaService.getVisitaById(v.getVisCodigo());
         v1.setVisFecCreacion(event.getScheduleEvent().getStartDate());
         v1.setVisFecFin(event.getScheduleEvent().getEndDate());
         visitaService.updateVisita(v1);
        // visitaService.SP_ActualizarDiar(v1);
                 if(v1.getCliente().getCliCodigo()==0)
        {
            System.out.println("Se mueve el evento");
        }else
        {
           Terapia ter = terapiaService.terapiaByVisita(v);
         ter.setTerFecRealizada(event.getScheduleEvent().getStartDate());
         terapiaService.updateTerapia(ter);
        } 
         System.out.println("ID VISITA : "+ v.getVisCodigo() );
         addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La Cita cambio de horario", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        System.out.println("Se movio la cita D: \nNueva Entrada : "+event.getScheduleEvent().getStartDate()+"\nNueva Salida : "+event.getScheduleEvent().getEndDate()); 
        Visita v = (Visita) event.getScheduleEvent().getData();
         Visita v1 = visitaService.getVisitaById(v.getVisCodigo());
         v1.setVisFecCreacion(event.getScheduleEvent().getStartDate());
         v1.setVisFecFin(event.getScheduleEvent().getEndDate());
        
      visitaService.updateVisita(v1);
        
        if(v1.getCliente().getCliCodigo()==0)
        {
            System.out.println("Se mueve el evento");
        }else
        {
             Terapia ter = terapiaService.terapiaByVisita(v);
        ter.setTerFecRealizada(event.getScheduleEvent().getStartDate());
        terapiaService.updateTerapia(ter);
        }  
          
           
  
        System.out.println("ID VISITA : "+ v.getVisCodigo() );
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void LISTANDO_PRODUCTOS()
    {
        listandoProductos = productoService.getAllProductos();
    }
    
     public void ELIMINARCITA()
    {
       if(visitaService.SPdeleteVisita(visita.getVisCodigo()))
       {
           System.out.println("SI ELIMINIO");
           llenarCalendario();
           //LISTANDO_PRODUCTOS();
           FacesContext fc = FacesContext.getCurrentInstance();
           ProductoBean objetoBean = (ProductoBean)fc.getExternalContext().getSessionMap().get("productoBean");
           objetoBean.LISTAR_PRODUCTOS();
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

    public double getValorTerapia() {
        return valorTerapia;
    }

    public void setValorTerapia(double valorTerapia) {
        this.valorTerapia = valorTerapia;
    }
    
    
    public void REFRESCAR()
    {
        RequestContext.getCurrentInstance().update("formFactura");
    }
    
    public void BUSCARFACTURA()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        System.out.println("Codigo Cliente : "+cli.getCliCodigo()+"\nFec : "+visita.getVisFecCreacion()+"\nCod : "+visita.getVisCodigo());
        cab_fact= facturaService.SP_ObtenerCabecera(cli.getCliCodigo(),visita.getVisFecCreacion(),visita.getVisCodigo());
        if(cab_fact!=null)
        {
            mont=cab_fact.getMonto();
            System.out.println("Monto ::: "+mont);
            fc.getExternalContext().getSessionMap().put("cod_fact_monto", mont);
            LISTAR();
        }else
        {
            lista_detfact = new ArrayList<>();
            
        PagoBean objetoBean = (PagoBean)fc.getExternalContext().getSessionMap().get("pagoBean");
        objetoBean.Limpiar();
        }
    }
    
    public void BUSCARFACTURA_EXTERNO(int cli_codigo)
    {
        FacesContext fc = FacesContext.getCurrentInstance();
       cli.setCliCodigo(cli_codigo);
       cab_fact= facturaService.SP_ObtenerCabecera(cli.getCliCodigo(),new Date(),0);
       lista_detfact = new ArrayList<>();
       
       if(cab_fact!=null)
       {
           mont=cab_fact.getMonto();
           
           if(cab_fact.isDelivery())
           {
               cab_fact.setUbicacion_contacto(StaticUtil.cortar(cab_fact.getUbicacion_contacto(), 0)+","+StaticUtil.cortar(cab_fact.getUbicacion_contacto(), 2)+"-"+StaticUtil.cortar(cab_fact.getUbicacion_contacto(), 4)+"-"+StaticUtil.cortar(cab_fact.getUbicacion_contacto(), 6));
          
           }
           fc.getExternalContext().getSessionMap().put("cod_fact_monto", mont);
              LISTAR();
       }else
       {
            lista_detfact = new ArrayList<>();
           
        PagoBean objetoBean = (PagoBean)fc.getExternalContext().getSessionMap().get("pagoBean");
        objetoBean.Limpiar();
       }
    
    }
    
    public void UPDATE_FACTURA()
    {
        facturaService.SP_UpdateCabecera(cli.getCliCodigo(), cab_fact.getFactura_real());
        System.out.println("CLIENTE : "+cli.getCliCodigo()+"\nFctura Real : "+cab_fact.getFactura_real());
    }
    
    public void LISTAR()
    {
               lista_detfact=facturaService.SP_ListaDetalle(cab_fact.getCod_factura());
                       FacesContext fc = FacesContext.getCurrentInstance();
        //VisitaBean objetoBean = (VisitaBean)fc.getExternalContext().getSessionMap().get("visitaBean");
        PagoBean pBean = (PagoBean)fc.getExternalContext().getSessionMap().get("pagoBean");
        System.out.println(cab_fact.getCod_factura());
        fc.getExternalContext().getSessionMap().put("cab_fact", cab_fact.getCod_factura());
        //objetoBean.llenarlista(cab_fact.getCod_factura());
        int a = cab_fact.getCod_factura();
        System.out.println("VALOR  DE A : "+a);
        pBean.llenarlista(a);
        
    }
      public void onRowEdit(RowEditEvent event) {
        detalle_factura detfact ;
        detfact = (detalle_factura) event.getObject();
        double val = terapia.getTerCodigo();
        if(val!=0)
        {
            double m =terapia.getTerCosto();
            terapia.setTerCosto(detfact.getPrecio_unitario());
         terapiaService.updateTerapia(terapia);
          System.out.println("TERAPIA : "+terapia.getTerCodigo()+"-VISITA : "+visita.getVisCodigo()+"-Valor :"+m);
         terapiaService.SP_CambiarPrecioTerapia(terapia.getTerCodigo(), visita.getVisCodigo(), m);
         BUSCARFACTURA();
        }
    }
      
    public void REGISTRAR_PRECIO_TERAPIA()
    {
        terapia.setTerCosto(valorTerapia);
        terapiaService.updateTerapia(terapia);
        terapiaService.SP_CambiarPrecioTerapia(terapia.getTerCodigo(), visita.getVisCodigo(), 0);
    }
    
    public void INSERTAR_EVENTO()
    {
        visitaService.SP_Insertar_Eventos(visita);
        llenarCalendario();
    }
    
    public double DEVOLVER_MONTO()
    {
        return cab_fact.getMonto();
    }
    
    public void EXPORTART( ) throws IOException 
    {
         FacesContext fc = FacesContext.getCurrentInstance();
 
        ExternalContext ec = fc.getExternalContext();
        
                
        ServletContext  context = (ServletContext) ec.getContext();
 
        try
        {


            List<detalle_factura> list = new ArrayList<>();
            list.add(new detalle_factura());
            
            
                list.addAll(lista_detfact);
                        
           JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);
 
                Map<String, Object> parametros = new HashMap<String, Object>() ;
                parametros.put("ItemDataSource", itemsJRBean);
                parametros.put("cliente", cab_fact.getCliente());
                parametros.put("fecha", StaticUtil.convertirDateToString(cab_fact.getFecha()));
                parametros.put("total", cab_fact.getMonto());
                
 File reportfile = new File(context.getRealPath("/factura/Factura.jasper"));
     
          
  byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), parametros,itemsJRBean);   
            
                ec.setResponseContentType("application/pdf");
                ec.setResponseContentLength(bytes.length);
                ec.setResponseHeader("Content-Disposition","inline;filename=\"reporte.pdf\"");//attachment - inline
                OutputStream output = ec.getResponseOutputStream();
                output.write(bytes, 0, bytes.length);
            fc.responseComplete();
        System.out.println("Sending to browser...");  
        }
        catch(JRException e)
        {
            System.out.println("Error de Exportación : "+e.getMessage());
        }
    }
}
