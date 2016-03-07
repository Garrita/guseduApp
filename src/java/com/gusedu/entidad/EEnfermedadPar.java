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
public class EEnfermedadPar {
     private int exp_codigo;
     private String nombre;
     private String par;
     private String desc;

    public EEnfermedadPar() {
    }

    public EEnfermedadPar(int exp_codigo, String nombre, String par, String desc) {
        this.exp_codigo = exp_codigo;
        this.nombre = nombre;
        this.par = par;
        this.desc = desc;
    }

     
     
    public int getExp_codigo() {
        return exp_codigo;
    }

    public void setExp_codigo(int exp_codigo) {
        this.exp_codigo = exp_codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
     
     
}
