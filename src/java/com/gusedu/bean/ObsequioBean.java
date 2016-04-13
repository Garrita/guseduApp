/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.ProductoService;
import com.gusedu.dao.impl.ProductoServiceImpl;
import com.gusedu.entidad.Obsequio;
import java.util.Date;
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
public class ObsequioBean {

    private List<Obsequio> listaObsequios;
    private Date fec_inicio;
    private Date fec_final;
    ProductoService productoService;
    
    public ObsequioBean() {
        productoService = new ProductoServiceImpl();
         fec_inicio = new Date();
        fec_final = new Date();
    }

    
    public void ELIMINAR_OBSEQUIO(Obsequio obs)
    {
       productoService.SP_EliminarObsequio(obs.getObsequio_id(), obs.getCantidad(), obs.getId_producto());
       FacesContext fc = FacesContext.getCurrentInstance();
       ProductoBean objetoBean = (ProductoBean)fc.getExternalContext().getSessionMap().get("productoBean");
       llenarListadeProducto();
       objetoBean.LISTAR_PRODUCTOS();
    }
    
    
    public void llenarLista()
    {
        listaObsequios=productoService.SP_ListarObsequio(fec_inicio, fec_final);
    }
    
    public void llenarListadeProducto()
    {
        fec_inicio = new Date();
        fec_final = new Date();
        listaObsequios=productoService.SP_ListarObsequio(fec_inicio, fec_final);
    }
    
    public List<Obsequio> getListaObsequios() {
        return listaObsequios;
    }

    public Date getFec_inicio() {
        return fec_inicio;
    }

    public void setFec_inicio(Date fec_inicio) {
        this.fec_inicio = fec_inicio;
    }

    public Date getFec_final() {
        return fec_final;
    }

    public void setFec_final(Date fec_final) {
        this.fec_final = fec_final;
    }

    
    
}
