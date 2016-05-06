/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.entidad;

/**
 *
 * @author NV55C
 */
public class detalleDelivery {
    private boolean delivery;
    private String vendedor;
    private String ubicacion;
    private String contacto;
    private double pasaje;

    public detalleDelivery() {
    }

    public detalleDelivery(boolean delivery, String vendedor, String ubicacion, String contacto, double pasaje) {
        this.delivery = delivery;
        this.vendedor = vendedor;
        this.ubicacion = ubicacion;
        this.contacto = contacto;
        this.pasaje = pasaje;
    }



    
    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public double getPasaje() {
        return pasaje;
    }

    public void setPasaje(double pasaje) {
        this.pasaje = pasaje;
    }
    
    
    
}
