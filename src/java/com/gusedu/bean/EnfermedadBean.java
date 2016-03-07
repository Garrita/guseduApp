/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.EnfermedadService;
import com.gusedu.dao.impl.EnfermedadServiceImpl;
import com.gusedu.model.Enfermedad;
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class EnfermedadBean {

    private Enfermedad datos;
    private List<Enfermedad> listar;
    private EnfermedadService enfermedadservice;
    private String query;
    
    public EnfermedadBean() 
    {
        enfermedadservice = new EnfermedadServiceImpl();
        datos = new Enfermedad();
        query = "";
        listarEnfermedad();
    }

    public void inicializar()
    {
        datos = new Enfermedad();
        listar = enfermedadservice.getAll();
    }
    
    public void listarEnfermedad()
    {
         listar = enfermedadservice.getAll();
    }
    
    /*@PostConstruct
    public void init()
    {
         listar = enfermedadservice.getAll();
    }*/
    
    /**
     * @return the datos
     */
    public Enfermedad getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(Enfermedad datos) {
        this.datos = datos;
    }

    /**
     * @return the listar
     */
    public List<Enfermedad> getListar() { 
        return  listar;
    }

    public void listado()
    {
         listar = enfermedadservice.getAll();
    }
    /**
     * @param listar the listar to set
     */
    public void setListar(List<Enfermedad> listar) {
        this.listar = listar;
    }
    
    public void AGREGAR()
    {
        System.out.println("Ingresando enfermedad...");
        enfermedadservice.saveEnfermedad(datos);      
        datos = new Enfermedad();
        listarEnfermedad();
    }
    
    public void ACTUALIZAR()
    {
        System.out.println("Actualizando enfermedad...");
        enfermedadservice.updateEnfermedad(datos);
        datos = new Enfermedad();
        listarEnfermedad();
    }
    
    public void ELIMINAR()
    {
        System.out.println("Eliminando enfermedad...");
        enfermedadservice.deleteEnfermedad(datos);
        datos = new Enfermedad();
        listarEnfermedad();
    }
    
    public void BUSCARXID(int codigo)
    {
        datos = enfermedadservice.getById(codigo);
        System.out.println("Descripción: " + datos.getEnfNombre());
    }
    
    public void BuscarEliminar(int codigo)
    {
        BUSCARXID(codigo);
        ELIMINAR();
    }
    
    public String getQuery() 
    {
        return query;
    }
    
    public void setQuery(String query) 
    {
       this.query = query;
    }
    public void filtrarBusqueda() 
   {
        String username = StaticUtil.userLogged();
        System.out.println("Entro a filtrar Busqueda :O");
        listar = enfermedadservice.getEnfermedadbyEnfer(username);
        List filtrados = new ArrayList();
        Iterator iterator = listar.iterator();
        do {
            if (!iterator.hasNext()) {
                break;
                    }
            Enfermedad c = (Enfermedad)iterator.next();
            if (c.getEnfNombre().toLowerCase().contains(query.toLowerCase())) 
            {
                filtrados.add(c);
            }
           } while (true);
        listar = filtrados;
        enviarQuery();
    }
    public void enviarQuery() 
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("query", query);
        System.out.println("Enviando query....");
    }   
}
