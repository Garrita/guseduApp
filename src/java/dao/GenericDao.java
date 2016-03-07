/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author NV55C
 */
public interface GenericDao <Entity, PK extends Serializable>{
    
    void Guardar (Entity t);
    void Actualizar (Entity t);
    void Eliminar (Entity t);  
    Entity Buscar(Class<Entity> type,PK t);
    
}
