/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.entidad.ECajaResumen;
import com.gusedu.entidad.ECajaResumen1;
import com.gusedu.entidad.ECajaResumenMensual;
import com.gusedu.entidad.ECajaResumenMensual1;
import com.gusedu.estadistica.Reporte;
import com.gusedu.estadistica.ReporteClientes;
import com.gusedu.estadistica.ReporteClientesXProd;
import com.gusedu.estadistica.ReporteImpl;
import com.gusedu.estadistica.ReporteService;
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class ReporteEstBean {

    
    ReporteService reporteservice;
    
    public List<Reporte> listaTerapiasByterapeutas;
    public List<ReporteClientes> listaClientesByterapeutas;
    public List<ReporteClientesXProd> listaProductosXCliente;
    public List<Reporte> listarProductos;
    public List<ECajaResumen> listarcajaresumen;
    public List<ECajaResumen1> listacajaresumen1;
    public List<ECajaResumenMensual> listarcajaresumenmensual;
    public List<ECajaResumenMensual1> listarcajaresumenmensual1;
    
    public List<String> listatipostring;
	
	private double costoT;
	private double costoP;
	private double costoTotal;
        private String Terapeuta;
        private String Product;
        private Date fechita;
        private Date fechaGOD;
        private String mes;
        private String año;
    
    public ReporteEstBean() {
        reporteservice = new ReporteImpl();
        listatipostring = new ArrayList<>();
        setCostoP(0);
        setCostoT(0);
	setCostoTotal(0);
        setProduct(" ");
        setTerapeuta(" ");
        
        Date año = new Date();
        int a=2010;
        for (int i = a; i <= (año.getYear()+ 1900); i++) 
        {
            listatipostring.add((i)+"");
        }
    }

    public List<String> getListatipostring() {
        return listatipostring;
    }

    public void setListatipostring(List<String> listatipostring) {
        this.listatipostring = listatipostring;
    }
    
    public List<ECajaResumenMensual1> getListarcajaresumenmensual1() {
        return listarcajaresumenmensual1;
    }

    public void setListarcajaresumenmensual1(List<ECajaResumenMensual1> listarcajaresumenmensual1) {
        this.listarcajaresumenmensual1 = listarcajaresumenmensual1;
    }

    public List<ECajaResumen> getListarcajaresumen() {
        return listarcajaresumen;
    }

    public void setListarcajaresumen(List<ECajaResumen> listarcajaresumen) {
        this.listarcajaresumen = listarcajaresumen;
    }

    public List<ECajaResumen1> getListacajaresumen1() {
        return listacajaresumen1;
    }

    public void setListacajaresumen1(List<ECajaResumen1> listacajaresumen1) {
        this.listacajaresumen1 = listacajaresumen1;
    }

    public List<ECajaResumenMensual> getListarcajaresumenmensual() {
        return listarcajaresumenmensual;
    }

    public void setListarcajaresumenmensual(List<ECajaResumenMensual> listarcajaresumenmensual) {
        this.listarcajaresumenmensual = listarcajaresumenmensual;
    }
    
    public String getTerapeuta() {
        return Terapeuta;
    }

    public void setTerapeuta(String Terapeuta) {
        this.Terapeuta = Terapeuta;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String Product) {
        this.Product = Product;
    }  
    
    public List<Reporte> getListaTerapiasByterapeutas() {
        return listaTerapiasByterapeutas;
    }

    public void setListaTerapiasByterapeutas(List<Reporte> listaTerapiasByterapeutas) {
        this.listaTerapiasByterapeutas = listaTerapiasByterapeutas;
    }

    public List<ReporteClientes> getListaClientesByterapeutas() {
        return listaClientesByterapeutas;
    }

    public void setListaClientesByterapeutas(List<ReporteClientes> listaClientesByterapeutas) {
        this.listaClientesByterapeutas = listaClientesByterapeutas;
        Iterator iter = listaClientesByterapeutas.iterator();
        while(iter.hasNext())
        {
            
        }      
    }

    public List<ReporteClientesXProd> getListaProductosXCliente() {
        return listaProductosXCliente;
    }

    public void setListaProductosXCliente(List<ReporteClientesXProd> listaProductosXCliente) {
        this.listaProductosXCliente = listaProductosXCliente;
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
    
    public void onRowToggle(ToggleEvent event)
    {
        System.out.println("Row State : "+event.getVisibility());
        System.out.println("Model : "+((Reporte)event.getData()).getNombre());
        listaClientesByterapeutas = reporteservice.listarClientesByterapeutas(((Reporte)event.getData()).getNombre());
        for (int i = 0; i < listaClientesByterapeutas.size(); i++) {
            System.out.println("Cliente : "+listaClientesByterapeutas.get(i).getCliente()+"\nTipo Terapia : "+
                    listaClientesByterapeutas.get(i).getTipoTer());
        }
    }
    
    public void onRowToggle1(ToggleEvent event)
    {
        System.out.println("Row State : "+event.getVisibility());
        System.out.println("Model : "+((Reporte)event.getData()).getNombre());
        listaProductosXCliente = reporteservice.listarProductosXCliente(((Reporte)event.getData()).getNombre());
        for (int i = 0; i < listaProductosXCliente.size(); i++) {
            System.out.println("Cliente : "+listaProductosXCliente.get(i).getCliente()+"\nItem : "+
                    listaProductosXCliente.get(i).getItem());
        }
    }
        public void listarEstadoDiario()
        {
            Terapeuta = "";
            Product = "";
            costoP=0;
            costoT=0;
            costoTotal=0;
            listaTerapiasByterapeutas=reporteservice.listarTerapiaByterapeutas();
            listaClientesByterapeutas = reporteservice.listarClientesByterapeutas(Terapeuta);
            listaProductosXCliente = reporteservice.listarProductosXCliente(Product);
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

    @SuppressWarnings("deprecation")
    public void actualizar()
    {
        fechaGOD = fechita;
        
        fechita.setHours(0);
        fechita.setMinutes(0);
        fechita.setSeconds(0);
        System.out.println("Fecha inicial : "+fechita);
        Date fecha8 = new Date();
        fecha8= engañaFecha(fechita);
        System.out.println("Fecha inicial : "+fecha8);
        fechaGOD.setHours(23);
        fechaGOD.setMinutes(59);
        fechaGOD.setSeconds(59);
        System.out.println("Probando Caja Resumen");
        listarcajaresumen = reporteservice.MostrarCajaResumen(fecha8,fechaGOD);
        listacajaresumen1 = reporteservice.MostrarCajaDetalle(fecha8,fechaGOD);
        System.out.println("Fecha final: " + fechaGOD);
    }
    
    public Date engañaFecha(Date fecha)
    {
        return fecha;
    }
    
    @SuppressWarnings("deprecation")
    public void today()
    {
            Date fecha2 = new Date();
            fecha2.setHours(23);
            fecha2.setMinutes(59);
            fecha2.setSeconds(59);
            
            fechita = fecha2;
            actualizar();
    }
	
    public Date getFechita() {
        return fechita;
    }

    public void setFechita(Date fechita) {
        this.fechita = fechita;
    }

    public Date getFechaGOD() {
        return fechaGOD;
    }

    public void setFechaGOD(Date fechaGOD) {
        this.fechaGOD = fechaGOD;
    }
    
    public void CAJARESUMEN()
    {
        today();
        listarcajaresumen = reporteservice.MostrarCajaResumen(fechita,fechaGOD);
        listacajaresumen1 = reporteservice.MostrarCajaDetalle(fechita,fechaGOD);
        listarcajaresumenmensual = reporteservice.MostrarCajaResumenMensual(mes,año);
        listarcajaresumenmensual1 = reporteservice.MostrarCajaMensualDetalle(mes,año);       
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }
    
    public void ESCOGERMES()
    {
        System.out.println("Probando la elección de mes");
        listarcajaresumenmensual = reporteservice.MostrarCajaResumenMensual(mes,año);
        listarcajaresumenmensual1 = reporteservice.MostrarCajaMensualDetalle(mes,año);
        System.out.println(mes);
    }
}
