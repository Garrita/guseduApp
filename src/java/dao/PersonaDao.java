/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import com.gusedu.model.Persona;

/**
 *
 * @author NV55C
 */
public interface PersonaDao {
    void savePersona (Persona persona);
    void updatePersona (Persona persona);
    void deletePersona (Persona persona);
    Persona buscar(int id);
    List<Persona> getAllPersona();
}
