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
public class Calendario {
    private String nombre;
    private Date fec_creacion;
    private Date fec_fin;
    private int vis_codigo;
    private int cli_codigo;
    private String descripcion;
    private Boolean llegada;
    private String tte_codigo;

    public Calendario() {
    }

    public Calendario(String nombre, Date fec_creacion, Date fec_fin, int vis_codigo, int cli_codigo, String descripcion, Boolean llegada, String tte_codigo) {
        this.nombre = nombre;
        this.fec_creacion = fec_creacion;
        this.fec_fin = fec_fin;
        this.vis_codigo = vis_codigo;
        this.cli_codigo = cli_codigo;
        this.descripcion = descripcion;
        this.llegada = llegada;
        this.tte_codigo = tte_codigo;
    }



 

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFec_creacion() {
        return fec_creacion;
    }

    public void setFec_creacion(Date fec_creacion) {
        this.fec_creacion = fec_creacion;
    }

    public Date getFec_fin() {
        return fec_fin;
    }

    public void setFec_fin(Date fec_fin) {
        this.fec_fin = fec_fin;
    }

    public int getCli_codigo() {
        return cli_codigo;
    }

    public void setCli_codigo(int cli_codigo) {
        this.cli_codigo = cli_codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getLlegada() {
        return llegada;
    }

    public void setLlegada(Boolean llegada) {
        this.llegada = llegada;
    }

    public int getVis_codigo() {
        return vis_codigo;
    }

    public void setVis_codigo(int vis_codigo) {
        this.vis_codigo = vis_codigo;
    }

    public String getTte_codigo() {
        return tte_codigo;
    }

    public void setTte_codigo(String tte_codigo) {
        this.tte_codigo = tte_codigo;
    }
    
    
    
}
