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
public class EParametro {
    
    private int dom_codigo;
    private int param_codigo;
    private String param_valor;
    private boolean param_estado;
    private String param_descripcion;
    private String param_valor1;
    private String param_valor2;

    public EParametro() {
    }

    public EParametro(int dom_codigo, int param_codigo, String param_valor, boolean param_estado, String param_descripcion, String param_valor1, String param_valor2) {
        this.dom_codigo = dom_codigo;
        this.param_codigo = param_codigo;
        this.param_valor = param_valor;
        this.param_estado = param_estado;
        this.param_descripcion = param_descripcion;
        this.param_valor1 = param_valor1;
        this.param_valor2 = param_valor2;
    }

    public int getDom_codigo() {
        return dom_codigo;
    }

    public void setDom_codigo(int dom_codigo) {
        this.dom_codigo = dom_codigo;
    }

    public int getParam_codigo() {
        return param_codigo;
    }

    public void setParam_codigo(int param_codigo) {
        this.param_codigo = param_codigo;
    }

    public String getParam_valor() {
        return param_valor;
    }

    public void setParam_valor(String param_valor) {
        this.param_valor = param_valor;
    }

    public boolean isParam_estado() {
        return param_estado;
    }

    public void setParam_estado(boolean param_estado) {
        this.param_estado = param_estado;
    }

    public String getParam_descripcion() {
        return param_descripcion;
    }

    public void setParam_descripcion(String param_descripcion) {
        this.param_descripcion = param_descripcion;
    }

    public String getParam_valor1() {
        return param_valor1;
    }

    public void setParam_valor1(String param_valor1) {
        this.param_valor1 = param_valor1;
    }

    public String getParam_valor2() {
        return param_valor2;
    }

    public void setParam_valor2(String param_valor2) {
        this.param_valor2 = param_valor2;
    }
}
