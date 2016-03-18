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
public class EProductoLogAvanzado {
   
    private int codigo;
    private String usuario;
    private Date fecha;
    private String producto;
    private double stock_old;
    private double stock_new;
    private int stockmin_old;
    private int stockmin_new;
    private double precio_old;
    private double precio_new;

    public EProductoLogAvanzado() {
    }

    public EProductoLogAvanzado(int codigo, String usuario, Date fecha, String producto, double stock_old, double stock_new, int stockmin_old, int stockmin_new, double precio_old, double precio_new) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.fecha = fecha;
        this.producto = producto;
        this.stock_old = stock_old;
        this.stock_new = stock_new;
        this.stockmin_old = stockmin_old;
        this.stockmin_new = stockmin_new;
        this.precio_old = precio_old;
        this.precio_new = precio_new;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getStock_old() {
        return stock_old;
    }

    public void setStock_old(double stock_old) {
        this.stock_old = stock_old;
    }

    public double getStock_new() {
        return stock_new;
    }

    public void setStock_new(double stock_new) {
        this.stock_new = stock_new;
    }

    public int getStockmin_old() {
        return stockmin_old;
    }

    public void setStockmin_old(int stockmin_old) {
        this.stockmin_old = stockmin_old;
    }

    public int getStockmin_new() {
        return stockmin_new;
    }

    public void setStockmin_new(int stockmin_new) {
        this.stockmin_new = stockmin_new;
    }

    public double getPrecio_old() {
        return precio_old;
    }

    public void setPrecio_old(double precio_old) {
        this.precio_old = precio_old;
    }

    public double getPrecio_new() {
        return precio_new;
    }

    public void setPrecio_new(double precio_new) {
        this.precio_new = precio_new;
    }
}
