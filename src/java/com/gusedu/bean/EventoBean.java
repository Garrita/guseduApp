/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.EventoService;
import com.gusedu.dao.impl.EventoServiceImpl;
import com.gusedu.model.Evento;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class EventoBean {

     private Evento datos;
    private List<Evento> listar;
    private EventoService eventoservice;
    
    public EventoBean() 
    {
        eventoservice = new EventoServiceImpl();
        datos = new Evento();
        inicializar();
    }

    public void inicializar()
    {
        datos = new Evento();
    }
    
    public void listarEvento()
    {
         listar = eventoservice.getAll();
    }
    
    @PostConstruct
    public void init()
    {
         listar = eventoservice.getAll();
    }
    
    public void listado()
    {
         listar = eventoservice.getAll();
    }

    public Evento getDatos() {
        return datos;
    }

    public void setDatos(Evento datos) {
        this.datos = datos;
    }

    public List<Evento> getListar() {
        return listar;
    }

    public void setListar(List<Evento> listar) {
        this.listar = listar;
    }
    
    public void AGREGAR()
    {
        System.out.println("Ingresando evento...");
        //eventoservice.SP_InsertarEvento(datos);
        eventoservice.saveEvento(datos);      
        datos = new Evento();
        listarEvento();
    }
    
    public void ACTUALIZAR()
    {
        System.out.println("Actualizando evento...");
        eventoservice.updateEvento(datos);
        datos = new Evento();
        listarEvento();
    }
    
    public void ELIMINAR()
    {
        System.out.println("Eliminando evento...");
        eventoservice.deleteEvento(datos);
        datos = new Evento();
        listarEvento();
    }
    
    public void BUSCARXID(int codigo)
    {
        datos = eventoservice.getById(codigo);
        System.out.println("Nombre: " + datos.getDescripcion());
    }
    
    public void BuscarEliminar(int codigo)
    {
        BUSCARXID(codigo);
        ELIMINAR();
    }
    
}
