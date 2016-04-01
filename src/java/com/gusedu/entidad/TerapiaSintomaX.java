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
public class TerapiaSintomaX {
    
    private int txsCodigo;
    private String sinDescripcion;
    private String txsComentario;
    private boolean txsActivo;

    public TerapiaSintomaX() {
    }

    public TerapiaSintomaX(int txsCodigo, String sinDescripcion, String txsComentario, boolean txsActivo) {
        this.txsCodigo = txsCodigo;
        this.sinDescripcion = sinDescripcion;
        this.txsComentario = txsComentario;
        this.txsActivo = txsActivo;
    }

    public int getTxsCodigo() {
        return txsCodigo;
    }

    public void setTxsCodigo(int txsCodigo) {
        this.txsCodigo = txsCodigo;
    }

    public String getSinDescripcion() {
        return sinDescripcion;
    }

    public void setSinDescripcion(String sinDescripcion) {
        this.sinDescripcion = sinDescripcion;
    }

    public String getTxsComentario() {
        return txsComentario;
    }

    public void setTxsComentario(String txsComentario) {
        this.txsComentario = txsComentario;
    }

    public boolean isTxsActivo() {
        return txsActivo;
    }

    public void setTxsActivo(boolean txsActivo) {
        this.txsActivo = txsActivo;
    }
    
    
}
