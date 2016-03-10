/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.TipoTerapiaService;
import com.gusedu.dao.impl.TipoTerapiaServiceImpl;
import com.gusedu.model.TipoTerapia;
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class TipoTerapiaBean {

    private TipoTerapia datos;
    private List<TipoTerapia> listar;
    private TipoTerapiaService tipoterapiaservice;
    
    public TipoTerapiaBean() 
    {
        tipoterapiaservice = new TipoTerapiaServiceImpl();
        datos = new TipoTerapia();
        inicializar();
    }

    public void inicializar()
    {
        datos = new TipoTerapia();
    }
    
    public void listarTipoTerapia()
    {
         listar = tipoterapiaservice.getAll();
    }
    
    @PostConstruct
    public void init()
    {
         listar = tipoterapiaservice.getAll();
    }
    
    public void listado()
    {
         listar = tipoterapiaservice.getAll();
    }

    public TipoTerapia getDatos() {
        return datos;
    }

    public void setDatos(TipoTerapia datos) {
        this.datos = datos;
    }

    public List<TipoTerapia> getListar() {
        return listar;
    }

    public void setListar(List<TipoTerapia> listar) {
        this.listar = listar;
    }
    
    public void AGREGAR()
    {
        System.out.println("Ingresando tipo terapia...");
        tipoterapiaservice.SP_InsertarTipoterapia(datos);
        //tipoterapiaservice.saveTipoTerapia(datos);      
        datos = new TipoTerapia();
        listarTipoTerapia();
    }
    
    public void ACTUALIZAR()
    {
        System.out.println("Actualizando tipo terapia...");
        tipoterapiaservice.updateTipoTerapia(datos);
        datos = new TipoTerapia();
        listarTipoTerapia();
    }
    
    public void ELIMINAR()
    {
        System.out.println("Eliminando tipo terapia...");
        tipoterapiaservice.deleteTipoTerapia(datos);
        datos = new TipoTerapia();
        listarTipoTerapia();
    }
    
    public void BUSCARXID(int codigo)
    {
        datos = tipoterapiaservice.getById(codigo);
        System.out.println("Nombre: " + datos.getTteNombre());
    }
    
    public void BuscarEliminar(int codigo)
    {
        BUSCARXID(codigo);
        ELIMINAR();
    }
}
