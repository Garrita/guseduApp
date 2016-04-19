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
public class ETerapia {

    private String fecha;
    private String par;
    private String[] estado;

    public ETerapia() {
    }

    public ETerapia(String fecha, String par, String[] estado) {
        this.fecha = fecha;
        this.par = par;
        this.estado = estado;
    }

    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String[] getEstado() {
        return estado;
    }

    public void setEstado(String[] estado) {
        this.estado = estado;
    }


}
