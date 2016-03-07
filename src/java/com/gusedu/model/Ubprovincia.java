package com.gusedu.model;
// Generated 01/03/2016 03:47:23 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Ubprovincia generated by hbm2java
 */
public class Ubprovincia  implements java.io.Serializable {


     private int idProv;
     private Ubdepartamento ubdepartamento;
     private String provincia;
     private Set ubdistritos = new HashSet(0);

    public Ubprovincia() {
    }

	
    public Ubprovincia(int idProv) {
        this.idProv = idProv;
    }
    public Ubprovincia(int idProv, Ubdepartamento ubdepartamento, String provincia, Set ubdistritos) {
       this.idProv = idProv;
       this.ubdepartamento = ubdepartamento;
       this.provincia = provincia;
       this.ubdistritos = ubdistritos;
    }
   
    public int getIdProv() {
        return this.idProv;
    }
    
    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }
    public Ubdepartamento getUbdepartamento() {
        return this.ubdepartamento;
    }
    
    public void setUbdepartamento(Ubdepartamento ubdepartamento) {
        this.ubdepartamento = ubdepartamento;
    }
    public String getProvincia() {
        return this.provincia;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public Set getUbdistritos() {
        return this.ubdistritos;
    }
    
    public void setUbdistritos(Set ubdistritos) {
        this.ubdistritos = ubdistritos;
    }




}


