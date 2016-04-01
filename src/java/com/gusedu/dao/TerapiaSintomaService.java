/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao;

import com.gusedu.entidad.TerapiaSintomaX;
import com.gusedu.model.Cliente;
import com.gusedu.model.Sintoma;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaSintoma;
import java.util.List;

/**
 *
 * @author user
 */
public interface TerapiaSintomaService {
        
    public List<TerapiaSintoma> getAllSintoma();

    public TerapiaSintoma getByParameters(Integer terapiasintoma) ;

    public boolean saveTerapiaSintoma(TerapiaSintoma terapiaSintoma);

    public boolean updateTerapiaSintoma(TerapiaSintoma terapiaSintoma);

    public boolean deleteTerapiaSintoma(TerapiaSintoma terapiaSintoma);
    
    public List<TerapiaSintoma> getAllTerapiaSintoma(Terapia ter);
    
    public TerapiaSintoma buscarTerapiaSintoma(Cliente cliente);
    
    public List<TerapiaSintomaX> SP_LISTAR_SINTOMAS_CLIENTE(int cod_cli);
}
