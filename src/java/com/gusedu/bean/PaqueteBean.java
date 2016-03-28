/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.PaqueteService;
import com.gusedu.dao.impl.PaqueteServiceImpl;
import com.gusedu.entidad.Paquete;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class PaqueteBean {

    private List<Paquete> paquetes; 
    PaqueteService paqueteService;
    
    public PaqueteBean() {
        paqueteService = new PaqueteServiceImpl();
    }

    public void ListarPaquetes(){
        paquetes =paqueteService.SP_ListarPaquetes();
    }
   
    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }
    
    
}
