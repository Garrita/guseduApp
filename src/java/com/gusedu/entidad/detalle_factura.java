/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.entidad;

/**
 *
 * @author NV55C
 */
public class detalle_factura {
    
    private int cod_det_factura;
    private String item;
    private double precio_unitario;
    private int cantidad;
    private double monto;
    private int cod_factura;

    public detalle_factura() {
    }

    public detalle_factura(int cod_det_factura, String item, double precio_unitario, int cantidad, double monto, int cod_factura) {
        this.cod_det_factura = cod_det_factura;
        this.item = item;
        this.precio_unitario = precio_unitario;
        this.cantidad = cantidad;
        this.monto = monto;
        this.cod_factura = cod_factura;
    }

 

    public int getCod_det_factura() {
        return cod_det_factura;
    }

    public void setCod_det_factura(int cod_det_factura) {
        this.cod_det_factura = cod_det_factura;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getCod_factura() {
        return cod_factura;
    }

    public void setCod_factura(int cod_factura) {
        this.cod_factura = cod_factura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
