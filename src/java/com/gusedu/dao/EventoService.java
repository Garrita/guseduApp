/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao;

import java.util.List;
import com.gusedu.model.Evento;

/**
 *
 * @author user
 */
public interface EventoService {
    
     public List<Evento> getAll();
     
     public boolean saveEvento(Evento evento);
     
     public boolean updateEvento(Evento evento);
     
     public boolean deleteEvento(Evento evento);
     
     public Evento getByNombre(String nombre);
     
     public Evento getById(Integer idEvento);
     
     public boolean SP_InsertarEvento(Evento evento);
}
