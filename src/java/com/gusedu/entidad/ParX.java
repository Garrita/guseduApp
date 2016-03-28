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
public class ParX {
     private int cod_par;
     private String nombre;

    public ParX() {
    }

    public ParX(int cod_par, String nombre) {
        this.cod_par = cod_par;
        this.nombre = nombre;
    }
     
    

    public int getCod_par() {
        return cod_par;
    }

    public void setCod_par(int cod_par) {
        this.cod_par = cod_par;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     
     
}
