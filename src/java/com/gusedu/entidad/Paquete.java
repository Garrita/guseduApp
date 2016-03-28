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
public class Paquete {
           private int cod_paquete;
           private String nombre;
           private int cantidad;

    public Paquete() {
    }

    public Paquete(int cod_paquete, String nombre, int cantidad) {
        this.cod_paquete = cod_paquete;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }            
           
    public int getCod_paquete() {
        return cod_paquete;
    }

    public void setCod_paquete(int cod_paquete) {
        this.cod_paquete = cod_paquete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
           
           
           
           
}
