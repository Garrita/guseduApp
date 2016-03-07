/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.EnfermedadService;
import com.gusedu.dao.impl.EnfermedadServiceImpl;
import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadPar;
import com.gusedu.model.Par;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class EnfermedadParBean {

    private EnfermedadPar datos;
    public List<EnfermedadPar> listar;
    public EnfermedadService enfermedadservice;
    
    public EnfermedadParBean() 
    {
        enfermedadservice = new EnfermedadServiceImpl();
        inicializar();
        listarPar();
    }

    
    public void inicializar()
    {
        datos = new EnfermedadPar();
        datos.setEnfermedad(new Enfermedad());
        datos.setPar(new Par());
    }
    /**
     * @return the datos
     */
    public EnfermedadPar getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(EnfermedadPar datos) {
        this.datos = datos;
    }

    /**
     * @return the listar
     */
    public List<EnfermedadPar> getListar() {
        return  listar;
    }

    public void listarPar()
    {
         listar = enfermedadservice.getAllPar();
    }
    /**
     * @param listar the listar to set
     */
    public void setListar(List<EnfermedadPar> listar) {
        this.listar = listar;
    }
    
    public void AGREGAR()
    {
        System.out.println("Ingresando enfermedad par...");
        System.out.println("Enfermedad  "+ datos.getEnfermedad().getEnfCodigo());
        System.out.println("Par : "+ datos.getPar().getParCodigo());
        enfermedadservice.saveEnfermedadPar(datos);      
        datos = new EnfermedadPar();
        datos.setEnfermedad(new Enfermedad());
        datos.setPar(new Par());
        listarPar();
    }
    
    public void ACTUALIZAR()
    {
        System.out.println("Actualizando enfermedad par...");
        enfermedadservice.updateEnfermedadPar(datos);
        datos = new EnfermedadPar();
        datos.setEnfermedad(new Enfermedad());
        datos.setPar(new Par());
        listarPar();
    }
    
    public void ELIMINAR()
    {
        System.out.println("Eliminando enfermedad par...");
        enfermedadservice.deleteEnfermedadPar(datos);
        datos = new EnfermedadPar();
        datos.setEnfermedad(new Enfermedad());
        datos.setPar(new Par());
        listarPar();
    }
    
    public void BUSCARXID(int enfpar)
    {
        datos = enfermedadservice.getByParameters(enfpar);
        System.out.println("Enfermedad: " + datos.getEnfermedad().getEnfNombre() + "\n" +
                           "Par:" + datos.getPar().getPuntoByPunCodigoP1().getPunNombre()+"-"+datos.getPar().getPuntoByPunCodigoP2().getPunNombre());
    }
    
    public void BuscarEliminar(int codigo)
    {
        BUSCARXID(codigo);
        ELIMINAR();
    }
}
