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
public class Excel {
    
    private Date fecha;
    private String paciente;
    private double[] monto;
    private double total;

    public Excel() {
    }

    public Excel(Date fecha, String paciente,double[] valores,double total)
    {
        this.fecha = fecha;
        this.paciente = paciente;
        this.monto=valores;
        this.total=total;
         
    }
    


    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    
    
    
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double[] getMonto() {
        return monto;
    }

    public void setMonto(double[] monto) {
        this.monto = monto;
    }

  
    
}
