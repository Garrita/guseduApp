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
public class EUltimaVisitaxCliente 
{
    private int visitaCodigo;
    private String visita;
    private Date fechaCreacion;
    private double costoTotal;  
    
    public EUltimaVisitaxCliente()
    {
        
    }

    public EUltimaVisitaxCliente(int visitaCodigo, String visita, Date fechaCreacion, double costoTotal) {
        this.visitaCodigo = visitaCodigo;
        this.visita = visita;
        this.fechaCreacion = fechaCreacion;
        this.costoTotal = costoTotal;
    }

    public int getVisitaCodigo() {
        return visitaCodigo;
    }

    public void setVisitaCodigo(int visitaCodigo) {
        this.visitaCodigo = visitaCodigo;
    }


    public String getVisita() {
        return visita;
    }

    public void setVisita(String visita) {
        this.visita = visita;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
}
