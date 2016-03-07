package com.gusedu.model;
// Generated 01/03/2016 03:47:23 PM by Hibernate Tools 4.3.1



/**
 * DetalleFactura generated by hbm2java
 */
public class DetalleFactura  implements java.io.Serializable {


     private Integer codDetFactura;
     private CabeceraFactura cabeceraFactura;
     private String item;
     private Double precioUnitario;
     private Integer cantidad;
     private Double monto;

    public DetalleFactura() {
    }

    public DetalleFactura(CabeceraFactura cabeceraFactura, String item, Double precioUnitario, Integer cantidad, Double monto) {
       this.cabeceraFactura = cabeceraFactura;
       this.item = item;
       this.precioUnitario = precioUnitario;
       this.cantidad = cantidad;
       this.monto = monto;
    }
   
    public Integer getCodDetFactura() {
        return this.codDetFactura;
    }
    
    public void setCodDetFactura(Integer codDetFactura) {
        this.codDetFactura = codDetFactura;
    }
    public CabeceraFactura getCabeceraFactura() {
        return this.cabeceraFactura;
    }
    
    public void setCabeceraFactura(CabeceraFactura cabeceraFactura) {
        this.cabeceraFactura = cabeceraFactura;
    }
    public String getItem() {
        return this.item;
    }
    
    public void setItem(String item) {
        this.item = item;
    }
    public Double getPrecioUnitario() {
        return this.precioUnitario;
    }
    
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Double getMonto() {
        return this.monto;
    }
    
    public void setMonto(Double monto) {
        this.monto = monto;
    }




}


