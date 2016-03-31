/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.estadistica;

import java.util.Date;

/**
 *
 * @author user
 */
public class ReporteClientesXProd {
    
    private Date fecha;
    private String cliente;
    private String item;
    private double precioU;
    private int cantidad;
    private double monto;

    public ReporteClientesXProd()
    {
        
    }

    public ReporteClientesXProd(Date fecha, String cliente, String item, double precioU, int cantidad, double monto) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.item = item;
        this.precioU = precioU;
        this.cantidad = cantidad;
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrecioU() {
        return precioU;
    }

    public void setPrecioU(double precioU) {
        this.precioU = precioU;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
