/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.estadistica.Reporte;
import com.gusedu.estadistica.ReporteImpl;
import com.gusedu.estadistica.ReporteService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class ReporteEstBean {

    
    ReporteService reporteservice;
    
    public List<Reporte> listaTerapiasByterapeutas;
	public List<Reporte> listarProductos;
	
	private double costoT;
	private double costoP;
	private double costoTotal;
    
    public ReporteEstBean() {
        reporteservice = new ReporteImpl();
        setCostoP(0);
		setCostoT(0);
		setCostoTotal(0);
        
    }

    public List<Reporte> getListaTerapiasByterapeutas() {
        return listaTerapiasByterapeutas;
    }

    public void setListaTerapiasByterapeutas(List<Reporte> listaTerapiasByterapeutas) {
        this.listaTerapiasByterapeutas = listaTerapiasByterapeutas;
    }

    public List<Reporte> getListarProductos() {
        return listarProductos;
    }

    public void setListarProductos(List<Reporte> listarProductos) {
        this.listarProductos = listarProductos;
    }

    public double getCostoT() {
        return costoT;
    }

    public void setCostoT(double costoT) {
        this.costoT = costoT;
    }

    public double getCostoP() {
        return costoP;
    }

    public void setCostoP(double costoP) {
        this.costoP = costoP;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
    
    
    
        public void listarEstadoDiario()
        {
            costoP=0;
            costoT=0;
            costoTotal=0;
            listaTerapiasByterapeutas=reporteservice.listarTerapiaByterapeutas();
		listarProductos=reporteservice.listarProductos();
		for(int i=0;i<listaTerapiasByterapeutas.size();i++)
		{
                    costoT+=listaTerapiasByterapeutas.get(i).getCosto();
			//setCostoT(getCostoT() + listaTerapiasByterapeutas.get(i).getCosto());
		}
		
		for(int i=0;i<listarProductos.size();i++)
		{
                        costoP+=listarProductos.get(i).getCosto();
			//setCostoP(getCostoP() + listarProductos.get(i).getCosto());
		}
                costoTotal=costoP+costoT;
		//setCostoTotal(getCostoT()+getCostoP());
                RequestContext.getCurrentInstance().update("dialogV");
                RequestContext context = RequestContext.getCurrentInstance();
             context.execute("PF('dlgVentas').show();");
              
        }
        
        public void prueba()
	{
		System.out.println("HOLA : "+listaTerapiasByterapeutas.size());
	}
    
    
    
}
