/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.PagoService;
import com.gusedu.dao.impl.PagoServiceImpl;
import com.gusedu.entidad.EPago;
import com.gusedu.model.CabeceraFactura;
import com.gusedu.model.Pago;
import com.gusedu.model.TipoPago;
import com.gusedu.model.Visita;
import com.gusedu.util.StaticUtil;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class PagoBean {

    /**
     * Creates a new instance of PagoBean
     */
    private List<TipoPago> listapagos;
    private List<Pago> listaPagoByVisita;
    PagoService pagoservice;
    private Pago pago;
    private int id_visita;
    
    private List<EPago> listaepago;
    private Date fechaini;
    private Date fechafin;
    
    public PagoBean() {
        pagoservice = new PagoServiceImpl();
        pago = new Pago();
        pago.setTipoPago(new TipoPago());
        pago.setFactura(new CabeceraFactura());
        id_visita=0;
        Date currentDateTime=new Date();
        fechaini=new Date(currentDateTime.getYear(),currentDateTime.getMonth(),currentDateTime.getDate());
        fechafin=new Date(currentDateTime.getYear(),currentDateTime.getMonth(),currentDateTime.getDate());
    }

    public void inicializar()
    {
        pago = new Pago();
        pago.setTipoPago(new TipoPago());
        pago.setFactura(new CabeceraFactura());
    }
    
 /*   public void  insertarPago()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        Visita a =  (Visita) fc.getExternalContext().getSessionMap().get("visitaPago");
        int b=a.getVisCodigo();
        pago.setFactura(a);
        pagoservice.InsertPago(pago);
        inicializar();
        VisitaBean visBean = new VisitaBean();
        visBean.llenarlista(b);
    }*/
    
    public void updatePagos()
    {
        pagoservice.UpdatePago(pago) ;
    }
    
    public void llenarlista(int id)
    {
        System.out.println("Codigo: " + id); 
       id_visita=id;
       getListaPagoByVisita();
       //listaPagoByVisita= pagoservice.allPagosByVisita(id);
        System.out.println("Cantidad de Pagos : "+listaPagoByVisita.size());
        for (int i = 0; i < listaPagoByVisita.size(); i++) {
            System.out.println("MODALIDAD : "+ listaPagoByVisita.get(i).getTipoPago().getNombre() +
                               "\n MONTO :  "+ listaPagoByVisita.get(i).getMonto());
        }
    }
    
    
    public void onRowEdit(RowEditEvent event) {
        Pago p = new Pago();
        p = (Pago) event.getObject();
        pago = p;
          FacesContext fc = FacesContext.getCurrentInstance();
        //VisitaBean objetoBean = (VisitaBean)fc.getExternalContext().getSessionMap().get("visitaBean");
          int f=(int) fc.getExternalContext().getSessionMap().get("cab_fact");

        if(pagoservice.SP_ValidarPagos(pago.getPagoCodigo(),f, pago.getMonto()))
        {
                    updatePagos();
        }else
        {
            StaticUtil.errorMessage("Precaución", "El monto de los pagos no puede exceder al total");
            StaticUtil.keepMessages();
             VisitaBean objetoBean = (VisitaBean)fc.getExternalContext().getSessionMap().get("visitaBean");
  

        objetoBean.llenarlista(f);
        }
        inicializar();
    }
    
    public void cambioFecha()
    {
        if(fechafin.before(fechaini))
        {
           // System.out.println("FECHA FIN ES ANTES QUE LA FECHA INICIAL");
            fechaini=fechafin;
        }/*else
        {
            System.out.println("FECHA INICIO ES ANTES QUE LA FECHA FIN");
        }*/
        LLENARListaPago();
    }
    
    public void LLENARListaPago()
    {
        listaepago=pagoservice.allReport(fechaini, fechafin);
    }
    
    public List<TipoPago> getListapagos() {
        listapagos=pagoservice.allTipoPagos();
        return listapagos;
    }

    public List<Pago> getListaPagoByVisita() {
        listaPagoByVisita= pagoservice.allPagosByVisita(id_visita);
        return listaPagoByVisita;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public int getId_visita() {
        return id_visita;
    }

    public void setId_visita(int id_visita) {
        this.id_visita = id_visita;
    }

    public List<EPago> getListaepago() {
        listaepago=pagoservice.allReport(fechaini, fechafin);
        return listaepago;
    }

    public void setListaepago(List<EPago> listaepago) {
        this.listaepago = listaepago;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }
    
    
}
