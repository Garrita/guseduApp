/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.estadistica;

/**
 *
 * @author user
 */
public class ReporteClientes {
    
    private String Cliente;
    private String tipoTer;
    private double Monto;
    
    public ReporteClientes()
    {
        
    }

    public ReporteClientes(String Cliente, String tipoTer, double Monto) {
        this.Cliente = Cliente;
        this.tipoTer = tipoTer;
        this.Monto = Monto;
    }
    
    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getTipoTer() {
        return tipoTer;
    }

    public void setTipoTer(String tipoTer) {
        this.tipoTer = tipoTer;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double Monto) {
        this.Monto = Monto;
    }
}
