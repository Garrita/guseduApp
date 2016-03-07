/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao;

import com.gusedu.model.Ubdepartamento;
import com.gusedu.model.Ubdistrito;
import com.gusedu.model.Ubprovincia;
import java.util.List;

/**
 *
 * @author NV55C
 */
public interface UbigeoService {
    
    List<Ubdepartamento> getAllDepartamento();
    
    List<Ubprovincia> getAllProvincia(int idProvincia);
    
    List<Ubdistrito> getAllDistrito(int idDepartamento);
    
}
