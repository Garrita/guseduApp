/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao;

import com.gusedu.model.TipoTerapia;
import java.util.List;

/**
 *
 * @author user
 */
public interface TipoTerapiaService {
    
     public List<TipoTerapia> getAll();
     
     public boolean saveTipoTerapia(TipoTerapia tipoterapia);
     
     public boolean updateTipoTerapia(TipoTerapia tipoterapia);
     
     public boolean deleteTipoTerapia(TipoTerapia tipoterapia);
     
     public TipoTerapia getByNombre(String nombre);
     
     public TipoTerapia getById(Integer idTipoTerapia);
     
     public boolean SP_InsertarTipoterapia(TipoTerapia tipoterapia);
}
