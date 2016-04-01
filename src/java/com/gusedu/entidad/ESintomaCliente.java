/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.entidad;

/**
 *
 * @author user
 */
public class ESintomaCliente {
    
    private int sinCodigo;
    private String sinDescripcion;

    public ESintomaCliente() {
    }

    public ESintomaCliente(int sinCodigo, String sinDescripcion) {
        this.sinCodigo = sinCodigo;
        this.sinDescripcion = sinDescripcion;
    }
    
    public int getSinCodigo() {
        return sinCodigo;
    }

    public void setSinCodigo(int sinCodigo) {
        this.sinCodigo = sinCodigo;
    }
  
    public String getSinDescripcion() {
        return sinDescripcion;
    }

    public void setSinDescripcion(String sinDescripcion) {
        this.sinDescripcion = sinDescripcion;
    }
}
