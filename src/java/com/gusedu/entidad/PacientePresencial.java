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
public class PacientePresencial {
    private int posicion;
    private String paciente;
    private Date fecha;
    private int cod_cli;
    private int cod_vis;
    private int cod_ter;

    public PacientePresencial() {
    }

    public PacientePresencial(int posicion, String paciente,Date fecha, int cod_cli, int cod_vis, int cod_ter) {
        this.posicion = posicion;
        this.paciente = paciente;
        this.fecha = fecha;
        this.cod_cli = cod_cli;
        this.cod_vis = cod_vis;
        this.cod_ter = cod_ter;
    }
 
    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    public int getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(int cod_cli) {
        this.cod_cli = cod_cli;
    }

    public int getCod_vis() {
        return cod_vis;
    }

    public void setCod_vis(int cod_vis) {
        this.cod_vis = cod_vis;
    }

    public int getCod_ter() {
        return cod_ter;
    }

    public void setCod_ter(int cod_ter) {
        this.cod_ter = cod_ter;
    }
    
    
    
    
}
