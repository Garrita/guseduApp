/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.UbigeoService;
import com.gusedu.dao.impl.UbigeoServiceImpl;
import com.gusedu.model.Ubdepartamento;
import com.gusedu.model.Ubdistrito;
import com.gusedu.model.Ubprovincia;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class UbigoBean {

    private List<Ubdepartamento> listadepa;
    private List<Ubprovincia> listapro;
    private List<Ubdistrito> listadis;
    private int idDepartamento;
    private int idProvincia;
    private int idDistrito;
    
    UbigeoService ubigeoservice;
    
    
    /**
     * Creates a new instance of UbigoBan
     */
    public UbigoBean() {
        ubigeoservice = new UbigeoServiceImpl();
        idDepartamento=15;
        idProvincia=1;
    }

    public void LISTAR_UBIGEO()
    {
               LISTA_PROVINCIAS();
       LISTA_DISTRITO();
    }
    public void LISTA_PROVINCIAS()
    {
        listapro= ubigeoservice.getAllProvincia(idDepartamento);
        idProvincia=listapro.get(0).getIdProv();
        LISTA_DISTRITO();
   
    }
   public void LISTA_DISTRITO()
    {
    
        listadis= ubigeoservice.getAllDistrito(idProvincia);
   
    }
    
   public void LISTAR_DEPARTAMENTO()
   {
       listadepa = ubigeoservice.getAllDepartamento();
   }
   
    public List<Ubdepartamento> getListadepa() {
        return listadepa;
    }

    public List<Ubprovincia> getListapro() {
        return listapro;
    }

    public List<Ubdistrito> getListadis() {
        return listadis;
    }

    public void setListadis(List<Ubdistrito> listadis) {
        this.listadis = listadis;
    }

    
    
    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    public int getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(int idDistrito) {
        this.idDistrito = idDistrito;
    }
    
    
    
}
