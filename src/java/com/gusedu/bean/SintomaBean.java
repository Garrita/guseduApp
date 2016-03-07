/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.SintomaService;
import com.gusedu.dao.impl.SintomaServiceImpl;
import com.gusedu.model.Sintoma;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class SintomaBean {

    private Sintoma datos;
    private List<Sintoma> listar;
    private SintomaService sintomaservice;
    
    public SintomaBean() 
    {
        sintomaservice = new SintomaServiceImpl();
        inicializar();
        LISTAR_SINTOMAS();
    }

    public void inicializar()
    {
        datos = new Sintoma();
    }
    
 
    public void LISTAR_SINTOMAS(){
        listar = sintomaservice.getAll();
    }
    
    /**
     * @return the datos
     */
    public Sintoma getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(Sintoma datos) {
        this.datos = datos;
    }

    /**
     * @return the listar
     */
    public List<Sintoma> getListar() {
        return listar;
    }

    /**
     * @param listar the listar to set
     */
    public void setListar(List<Sintoma> listar) {
        this.listar = listar;
    }
    
    public void AGREGAR()
    {
        System.out.println("Ingresando sintoma...");
        sintomaservice.saveSintoma(datos);      
        datos = new Sintoma();
        listarSintoma();
    }
    
    public void ACTUALIZAR()
    {
        System.out.println("Actualizando sintoma...");
        sintomaservice.updateSintoma(datos);
        datos = new Sintoma();
        listarSintoma();
    }
    
    public void ELIMINAR()
    {
        System.out.println("Eliminando sintoma...");
        sintomaservice.deleteSintoma(datos);
        datos = new Sintoma();
        listarSintoma();
    }
    
    public void BUSCARXID(int codigo)
    {
        datos = sintomaservice.getById(codigo);
        System.out.println("Descripción: " + datos.getSinDescripcion());
    }
    
    public void BuscarEliminar(int codigo)
    {
        BUSCARXID(codigo);
        ELIMINAR();
    }
    
    public void listarSintoma()
    {
         listar = sintomaservice.getAll();
    }
}
