/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao;

import com.gusedu.entidad.EInformacionCurso;
import com.gusedu.entidad.EParametro;
import com.gusedu.model.Persona;
import java.util.List;

/**
 *
 * @author user
 */
public interface CursoService 
{

    public List<String> listarParametros();
    
    public boolean registroAlumno(Persona persona);
    
    public boolean registroInformacionCurso(EInformacionCurso infcur);
}
