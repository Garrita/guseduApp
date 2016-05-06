/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.entidad;

import java.util.Date;

/**
 *
 * @author NV55C
 */
public class cabecera_factura {
    
    private int cod_factura;
    private String cliente;
    private double monto;
    private Date fecha;
    private String factura_real;
    private int cod_cliente;
    private String ubicacion;
    private String ubicacion_contacto;
    private String contacto;
    private String vendedor;
    private boolean delivery;
    private double pasaje;
    
    public cabecera_factura() {
    }

    public cabecera_factura(int cod_factura, String cliente, double monto, Date fecha, String factura_real, int cod_cliente) {
        this.cod_factura = cod_factura;
        this.cliente = cliente;
        this.monto = monto;
        this.fecha = fecha;
        this.factura_real = factura_real;
        this.cod_cliente = cod_cliente;
    }

    public cabecera_factura(int cod_factura, String cliente, double monto, Date fecha, String factura_real, int cod_cliente, String ubicacion, String ubicacion_contacto, String contacto, String vendedor, boolean delivery, double pasaje) {
        this.cod_factura = cod_factura;
        this.cliente = cliente;
        this.monto = monto;
        this.fecha = fecha;
        this.factura_real = factura_real;
        this.cod_cliente = cod_cliente;
        this.ubicacion = ubicacion;
        this.ubicacion_contacto = ubicacion_contacto;
        this.contacto = contacto;
        this.vendedor = vendedor;
        this.delivery = delivery;
        this.pasaje = pasaje;
    }


    
    
    
    public int getCod_factura() {
        return cod_factura;
    }

    public void setCod_factura(int cod_factura) {
        this.cod_factura = cod_factura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFactura_real() {
        return factura_real;
    }

    public void setFactura_real(String factura_real) {
        this.factura_real = factura_real;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUbicacion_contacto() {
        return ubicacion_contacto;
    }

    public void setUbicacion_contacto(String ubicacion_contacto) {
        this.ubicacion_contacto = ubicacion_contacto;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public double getPasaje() {
        return pasaje;
    }

    public void setPasaje(double pasaje) {
        this.pasaje = pasaje;
    }
    
    
    
}
