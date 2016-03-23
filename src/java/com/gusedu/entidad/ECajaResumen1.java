/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.entidad;

import java.util.Date;

/**
 *
 * @author user
 */
public class ECajaResumen1 {
    
    private int cod_factura;
    private String cliente;
    private double monto;
    private Date fecha;
    private String factura_real;
    private int cod_cliente;
    private int estado;
    private int cod_visita;
    private String empresa;

    public ECajaResumen1() {
    }

    public ECajaResumen1(int cod_factura, String cliente, double monto, Date fecha, String factura_real, int cod_cliente, int estado, int cod_visita, String empresa) {
        this.cod_factura = cod_factura;
        this.cliente = cliente;
        this.monto = monto;
        this.fecha = fecha;
        this.factura_real = factura_real;
        this.cod_cliente = cod_cliente;
        this.estado = estado;
        this.cod_visita = cod_visita;
        this.empresa = empresa;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCod_visita() {
        return cod_visita;
    }

    public void setCod_visita(int cod_visita) {
        this.cod_visita = cod_visita;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
