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
public class EPago {
    
    private String paciente;
    private Date visita;
    private String tipopago;
    private double monto;
    
    
    
    public EPago()
    {
        
    }

    public EPago(String paciente, Date visita, String tipopago, double monto) {
        this.paciente = paciente;
        this.visita = visita;
        this.tipopago = tipopago;
        this.monto = monto;
    }

    
    
    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Date getVisita() {
        return visita;
    }

    public void setVisita(Date visita) {
        this.visita = visita;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
}
