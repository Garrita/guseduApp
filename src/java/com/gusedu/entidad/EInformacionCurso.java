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
public class EInformacionCurso {
    
    private int inf_codigo;
    private String inf_formacad;
    private String inf_tallercurs;
    private String inf_otrotall;
    private String inf_nombredepo;
    private String inf_fechahoradepo;
    private String inf_enviocorreo;
    private String inf_numerooperac;
    private int per_codigo;

    public EInformacionCurso() {
    }

    public EInformacionCurso(int inf_codigo, String inf_formacad, String inf_tallercurs, String inf_otrotall, String inf_nombredepo, String inf_fechahoradepo, String inf_enviocorreo, String inf_numerooperac, int per_codigo) {
        this.inf_codigo = inf_codigo;
        this.inf_formacad = inf_formacad;
        this.inf_tallercurs = inf_tallercurs;
        this.inf_otrotall = inf_otrotall;
        this.inf_nombredepo = inf_nombredepo;
        this.inf_fechahoradepo = inf_fechahoradepo;
        this.inf_enviocorreo = inf_enviocorreo;
        this.inf_numerooperac = inf_numerooperac;
        this.per_codigo = per_codigo;
    }

    public int getInf_codigo() {
        return inf_codigo;
    }

    public void setInf_codigo(int inf_codigo) {
        this.inf_codigo = inf_codigo;
    }

    public String getInf_formacad() {
        return inf_formacad;
    }

    public void setInf_formacad(String inf_formacad) {
        this.inf_formacad = inf_formacad;
    }

    public String getInf_tallercurs() {
        return inf_tallercurs;
    }

    public void setInf_tallercurs(String inf_tallercurs) {
        this.inf_tallercurs = inf_tallercurs;
    }

    public String getInf_otrotall() {
        return inf_otrotall;
    }

    public void setInf_otrotall(String inf_otrotall) {
        this.inf_otrotall = inf_otrotall;
    }

    public String getInf_nombredepo() {
        return inf_nombredepo;
    }

    public void setInf_nombredepo(String inf_nombredepo) {
        this.inf_nombredepo = inf_nombredepo;
    }

    public String getInf_fechahoradepo() {
        return inf_fechahoradepo;
    }

    public void setInf_fechahoradepo(String inf_fechahoradepo) {
        this.inf_fechahoradepo = inf_fechahoradepo;
    }

    public String getInf_enviocorreo() {
        return inf_enviocorreo;
    }

    public void setInf_enviocorreo(String inf_enviocorreo) {
        this.inf_enviocorreo = inf_enviocorreo;
    }

    public String getInf_numerooperac() {
        return inf_numerooperac;
    }

    public void setInf_numerooperac(String inf_numerooperac) {
        this.inf_numerooperac = inf_numerooperac;
    }

    public int getPer_codigo() {
        return per_codigo;
    }

    public void setPer_codigo(int per_codigo) {
        this.per_codigo = per_codigo;
    }
}
