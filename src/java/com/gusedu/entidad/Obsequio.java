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
public class Obsequio {
    private int obsequio_id;
    private String descripcion;
    private int id_producto;
    private String producto;
    private double costo_unitario;
    private int cantidad;
    private double costo_total;
    private Date fecha_l;
    private Date fecha;
    private String empresa;

    public Obsequio() {
    }

    public Obsequio(int obsequio_id, String descripcion, int id_producto, String producto, double costo_unitario, int cantidad, double costo_total, Date fecha_l, Date fecha, String empresa) {
        this.obsequio_id = obsequio_id;
        this.descripcion = descripcion;
        this.id_producto = id_producto;
        this.producto = producto;
        this.costo_unitario = costo_unitario;
        this.cantidad = cantidad;
        this.costo_total = costo_total;
        this.fecha_l = fecha_l;
        this.fecha = fecha;
        this.empresa = empresa;
    }
    
    public int getObsequio_id() {
        return obsequio_id;
    }

    public void setObsequio_id(int obsequio_id) {
        this.obsequio_id = obsequio_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getCosto_unitario() {
        return costo_unitario;
    }

    public void setCosto_unitario(double costo_unitario) {
        this.costo_unitario = costo_unitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }

    public Date getFecha_l() {
        return fecha_l;
    }

    public void setFecha_l(Date fecha_l) {
        this.fecha_l = fecha_l;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    
    
}
