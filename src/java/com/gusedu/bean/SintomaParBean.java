/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.SintomaService;
import com.gusedu.dao.impl.SintomaServiceImpl;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaPar;
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
public class SintomaParBean {

    private SintomaPar datos;
    public List<SintomaPar> listar;
    public SintomaService sintomaservice;
    
    public SintomaParBean() 
    {
        sintomaservice = new SintomaServiceImpl();
        inicializar();
        listarPar();
    }


 
    
    
    public void inicializar()
    {
        datos = new SintomaPar();
        datos.setSintoma(new Sintoma());
        datos.setPar(new Par());
    }
    /**
     * @return the datos
     */
    public SintomaPar getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(SintomaPar datos) {
        this.datos = datos;
    }

    /**
     * @return the listar
     */
    public List<SintomaPar> getListar() {
        return  listar;
    }

    public void listarPar()
    {
         listar = sintomaservice.getAllPar();
    }
    /**
     * @param listar the listar to set
     */
    public void setListar(List<SintomaPar> listar) {
        this.listar = listar;
    }
    
    public void AGREGAR()
    {
        System.out.println("Ingresando sintoma par...");
        System.out.println("Sintoma  "+ datos.getSintoma().getSinCodigo());
        System.out.println("Par : "+ datos.getPar().getParCodigo());
        sintomaservice.saveSintomaPar(datos);      
        datos = new SintomaPar();
        datos.setSintoma(new Sintoma());
        datos.setPar(new Par());
        listarPar();
    }
    
    public void ACTUALIZAR()
    {
        System.out.println("Actualizando sintoma par...");
        sintomaservice.updateSintomaPar(datos);
        datos = new SintomaPar();
        datos.setSintoma(new Sintoma());
        datos.setPar(new Par());
        listarPar();
    }
    
    public void ELIMINAR()
    {
        System.out.println("Eliminando sintoma par...");
        sintomaservice.deleteSintomaPar(datos);
        datos = new SintomaPar();
        datos.setSintoma(new Sintoma());
        datos.setPar(new Par());
        listarPar();
    }
    
    public void BUSCARXID(int enfpar)
    {
        datos = sintomaservice.getByParameters(enfpar);
        System.out.println("Sintoma: " + datos.getSintoma().getSinDescripcion() + "\n" +
                           "Par:" + datos.getPar().getPuntoByPunCodigoP1().getPunNombre()+"-"+datos.getPar().getPuntoByPunCodigoP2().getPunNombre());
    }
    
    public void BuscarEliminar(int codigo)
    {
        BUSCARXID(codigo);
        ELIMINAR();
    }
    
}
