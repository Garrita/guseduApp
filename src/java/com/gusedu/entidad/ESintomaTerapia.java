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
public class ESintomaTerapia {
       
        private String fecha;
        private String sin_descripcion;
        private boolean estado;

    public ESintomaTerapia() {
    }

    public ESintomaTerapia(String fecha, String sin_descripcion, boolean estado) {
        this.fecha = fecha;
        this.sin_descripcion = sin_descripcion;
        this.estado = estado;
    }

        
        
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSin_descripcion() {
        return sin_descripcion;
    }

    public void setSin_descripcion(String sin_descripcion) {
        this.sin_descripcion = sin_descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
        
        
}
