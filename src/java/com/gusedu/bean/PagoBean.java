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
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author NV55C
 */
@ManagedBean(name = "pagoBean")
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
    
    private double monto_pago;
    private List<EPago> listaResumen;
    private List<EPago> listaResumenD;
    
    public PagoBean() {
        pagoservice = new PagoServiceImpl();
        pago = new Pago();
        pago.setTipoPago(new TipoPago());
        pago.setFactura(new CabeceraFactura());
        id_visita=0;
        Date currentDateTime=new Date();
        fechaini=new Date(currentDateTime.getYear(),currentDateTime.getMonth(),currentDateTime.getDate());
        fechafin=new Date(currentDateTime.getYear(),currentDateTime.getMonth(),currentDateTime.getDate());    
        LLENARListaPago();
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
        
       listaPagoByVisita= pagoservice.allPagosByVisita(id);
        System.out.println("Cantidad de Pagos : "+listaPagoByVisita.size());
        for (int i = 0; i < listaPagoByVisita.size(); i++) {
            System.out.println("MODALIDAD : "+ listaPagoByVisita.get(i).getTipoPago().getNombre() +
                               "\n MONTO :  "+ listaPagoByVisita.get(i).getMonto());
        }
    }
    
    public void Limpiar ()
    {
       listaPagoByVisita = new ArrayList<>();
    }
    
        public void onRowToggle(ToggleEvent event)
    {
        System.out.println("Row State : "+event.getVisibility());
        System.out.println("Model : "+((EPago)event.getData()).getVisita());
        listaResumenD=pagoservice.SP_Caja_Resumen_D(((EPago)event.getData()).getVisita());
        for (int i = 0; i < listaResumenD.size(); i++) {
            System.out.println("MONTO : "+listaResumenD.get(i).getMonto());
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
    
    public void VALIDAR()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        System.out.println("METODO VALIDAR");
        double total=0;
        double monto=(double) fc.getExternalContext().getSessionMap().get("cod_fact_monto");
         for (int i = 0; i < listaPagoByVisita.size(); i++) {
             total+=listaPagoByVisita.get(i).getMonto();
        }
         if(total<monto)
         {
             StaticUtil.errorMessage("Error", "Los montos ingresados no es el establecido");
             StaticUtil.keepMessages();
         }else if(total>monto)
         {
            StaticUtil.errorMessage("Error", "Los montos ingresados en el tipo de pago exceden al monto establecido");
            StaticUtil.keepMessages();
         }else
         {
                            for (int i = 0; i < listaPagoByVisita.size(); i++) {
             pago=listaPagoByVisita.get(i);
              updatePagos();
             }
             StaticUtil.correctMesage("Exito", "Se registraron los pagos correspondientes según el tipo de pago");
            StaticUtil.keepMessages();
         }

    }
    
    public void onCellEdit(CellEditEvent event) {

        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        if(newValue != null && !newValue.equals(oldValue)) {
          FacesContext context = FacesContext.getCurrentInstance();
          Pago p = context.getApplication().evaluateExpressionGet(context, "#{pagT}", Pago.class);
          pago = p;
           
int a=listaPagoByVisita.indexOf(p);
 listaPagoByVisita.set(a, p);
 double t=0;
 for (int i = 0; i < listaPagoByVisita.size(); i++) {
            System.out.println("MODALIDAD : "+ listaPagoByVisita.get(i).getTipoPago().getNombre() +
                               "\n MONTO :  "+ listaPagoByVisita.get(i).getMonto());
            t+=listaPagoByVisita.get(i).getMonto();
        }
 
        /*if(t==150)
         {
             for (int i = 0; i < listaPagoByVisita.size(); i++) {
             pago=listaPagoByVisita.get(i);
              updatePagos();       
             }
             StaticUtil.correctMesage("Exito", "Se registraron los pagos correspondientes según el tipo");
            StaticUtil.keepMessages();
         }*/
        /*List<Pago> filtrados = new ArrayList<>();
		for (Pago u : listaPagoByVisita) {
	 
			if (u.getPagoCodigo().equals(p.getPagoCodigo())  ) {
						filtrados.add(u);
			}
		 
		}*/
		//listaPagoByVisita = filtrados;
       
        
         /* int f=(int) context.getExternalContext().getSessionMap().get("cab_fact");

        if(pagoservice.SP_ValidarPagos(pago.getPagoCodigo(),f, pago.getMonto()))
        {
                    updatePagos();
        }else
        {
            StaticUtil.errorMessage("Precaución", "El monto de los pagos no puede exceder al total");
            StaticUtil.keepMessages();
             VisitaBean objetoBean = (VisitaBean)context.getExternalContext().getSessionMap().get("visitaBean");
  

        objetoBean.llenarlista(f);
        }
        inicializar(); 
           
           */
        }
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
        monto_pago=0;
        listaepago=pagoservice.allReport(fechaini, fechafin);
        listaResumen=pagoservice.SP_Caja_Resumen(fechaini, fechafin);
         for (int i = 0; i < listaepago.size(); i++) {
            monto_pago+=listaepago.get(i).getMonto();
        }
    }
    
    public List<TipoPago> getListapagos() {
        listapagos=pagoservice.allTipoPagos();
        return listapagos;
    }

    public List<Pago> getListaPagoByVisita() {
        //listaPagoByVisita= pagoservice.allPagosByVisita(id_visita);
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

    public double getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(double monto_pago) {
        this.monto_pago = monto_pago;
    }

    public List<EPago> getListaResumen() {
        return listaResumen;
    }

    public void setListaResumen(List<EPago> listaResumen) {
        this.listaResumen = listaResumen;
    }

    public List<EPago> getListaResumenD() {
        return listaResumenD;
    }

    public void setListaResumenD(List<EPago> listaResumenD) {
        this.listaResumenD = listaResumenD;
    }
    
    
    
}
