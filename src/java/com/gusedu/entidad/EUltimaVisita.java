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
public class EUltimaVisita {
    
    private int codCliente;
    private String dni;
    private String apellidoPPersona;
    private String apellidoMPersona;
    private String nombresPersona;
    private String telmovilPersona;
    private Date fechafinVisita;
    private double costoTotal;
    private String tipoCliente; 

    public EUltimaVisita(int codCliente, String dni, String apellidoPPersona, String apellidoMPersona, String nombresPersona, String telmovilPersona, Date fechafinVisita, double costoTotal, String tipoCliente) {
        this.codCliente = codCliente;
        this.dni = dni;
        this.apellidoPPersona = apellidoPPersona;
        this.apellidoMPersona = apellidoMPersona;
        this.nombresPersona = nombresPersona;
        this.telmovilPersona = telmovilPersona;
        this.fechafinVisita = fechafinVisita;
        this.costoTotal = costoTotal;
        this.tipoCliente = tipoCliente;
    }
    
    public EUltimaVisita()
    {
        
    }

    
    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getApellidoPPersona() {
        return apellidoPPersona;
    }

    public void setApellidoPPersona(String apellidoPPersona) {
        this.apellidoPPersona = apellidoPPersona;
    }

    public String getApellidoMPersona() {
        return apellidoMPersona;
    }

    public void setApellidoMPersona(String apellidoMPersona) {
        this.apellidoMPersona = apellidoMPersona;
    }

    public String getNombresPersona() {
        return nombresPersona;
    }

    public void setNombresPersona(String nombresPersona) {
        this.nombresPersona = nombresPersona;
    }

    public String getTelmovilPersona() {
        return telmovilPersona;
    }

    public void setTelmovilPersona(String telmovilPersona) {
        this.telmovilPersona = telmovilPersona;
    }

    public Date getFechafinVisita() {
        return fechafinVisita;
    }

    public void setFechafinVisita(Date fechafinVisita) {
        this.fechafinVisita = fechafinVisita;
    }        

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }
}