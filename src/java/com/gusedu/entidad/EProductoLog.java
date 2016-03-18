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
public class EProductoLog {
    
    private String usuario;
    private String movimiento;
    private Date fecha;
    private double costo;
    private String descripcion;
    private double stock;

    public EProductoLog() {
    }

    public EProductoLog(String usuario, String movimiento, Date fecha, double costo, String descripcion, double stock) {
        this.usuario = usuario;
        this.movimiento = movimiento;
        this.fecha = fecha;
        this.costo = costo;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }
}
