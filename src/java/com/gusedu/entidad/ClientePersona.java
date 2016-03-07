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
public class ClientePersona {
        private int cli_codigo ;
        private String per_dni;
        private String paciente;

    public ClientePersona() {
    }

    public ClientePersona(int cli_codigo, String per_dni, String paciente) {
        this.cli_codigo = cli_codigo;
        this.per_dni = per_dni;
        this.paciente = paciente;
    }

        
        
        
    public int getCli_codigo() {
        return cli_codigo;
    }

    public void setCli_codigo(int cli_codigo) {
        this.cli_codigo = cli_codigo;
    }

    public String getPer_dni() {
        return per_dni;
    }

    public void setPer_dni(String per_dni) {
        this.per_dni = per_dni;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
        
        
        
}
