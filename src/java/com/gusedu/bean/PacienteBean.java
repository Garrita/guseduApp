/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.ClienteService;
import com.gusedu.dao.PersonaService;
import com.gusedu.dao.impl.ClienteServiceImpl;
import com.gusedu.dao.impl.PersonaServiceImpl;
import com.gusedu.entidad.EUltimaVisita;
import com.gusedu.model.Cliente;
import com.gusedu.model.Persona;
import com.gusedu.model.TipoCliente;
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class PacienteBean {

    /**
     * Creates a new instance of PacienteBean
     */
    
     private List<EUltimaVisita> clientesxfecha;
     private String query;
     
     private Cliente cliente;
     ClienteService clienteService;
     PersonaService personaService;
     
    public PacienteBean() {
        cliente = new Cliente();
        cliente.setPersona(new Persona());
        cliente.setTipoCliente(new TipoCliente());
         clienteService = new ClienteServiceImpl();
         personaService = new PersonaServiceImpl();
         
         llenarPacientes();
         query="";
    }

    public void llenarPacientes()
    {
        String username = StaticUtil.userLogged();
        clientesxfecha = clienteService.getListaUltimaVisita(username);
        System.out.println("TAM : "+clientesxfecha.size());
    }
    
    public String PRINCIPAL()
    {
        llenarPacientes();
        return "/web/Principal?faces-redirect=true";
    }
    public List<EUltimaVisita> getClientesxfecha() {
        return clientesxfecha;
    }

    public void setClientesxfecha(List<EUltimaVisita> clientesxfecha) {
        this.clientesxfecha = clientesxfecha;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
    public void filtrarBusqueda() {
        String username = StaticUtil.userLogged();
        System.out.println("Entro a filtrar Busqueda :O");
        clientesxfecha = clienteService.getListaUltimaVisita(username);
        List filtrados = new ArrayList();
        Iterator iterator = clientesxfecha.iterator();
        do {
            if (!iterator.hasNext()) {
                break;
            }
            EUltimaVisita c = (EUltimaVisita) iterator.next();
            if (c.getApellidoPPersona().toLowerCase().contains(query.toLowerCase()) || c.getApellidoMPersona().toLowerCase().contains(query.toLowerCase()) || c.getNombresPersona().toLowerCase().contains(query.toLowerCase())) {
                filtrados.add(c);
            }
        } while (true);
        clientesxfecha = filtrados;
        enviarQuery();
    }

    public void enviarQuery() {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("query", query);
        System.out.println("Enviando query....");
    }

    public void cargaCliente(int codcliente) {
        cliente = clienteService.getClienteById(codcliente);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("cliente", getCliente());
        VisitaBean objetoBean = (VisitaBean) fc.getExternalContext().getSessionMap().get("visitaBean");
        objetoBean.lastvisita(getCliente());
    }
    public void editar2() {

        if (personaService.updatePersona(cliente.getPersona())) {
            FacesContext fc = FacesContext.getCurrentInstance();
            VisitaBean objetoBean = (VisitaBean) fc.getExternalContext().getSessionMap().get("visitaBean");
            ScheduleView oscheduleBean = new ScheduleView();
            objetoBean.probando();
            oscheduleBean.llenarCalendario();
            StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente");
            StaticUtil.keepMessages();
            cliente = new Cliente();
            cliente.setPersona(new Persona());
        } else {
            StaticUtil.errorMessage("Error", "No se pudo actualizar");
            StaticUtil.keepMessages();
        } 
        String username = StaticUtil.userLogged();
        clientesxfecha = clienteService.getListaUltimaVisita(username);
    }
                
                            public void listado() {
        
        String username = StaticUtil.userLogged();
                clientesxfecha = clienteService.getListaUltimaVisita(username);
            }
}
