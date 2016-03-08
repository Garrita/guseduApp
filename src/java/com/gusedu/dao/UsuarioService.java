package com.gusedu.dao;

import java.util.List;

import com.gusedu.model.TipoUsuario;
import com.gusedu.model.Usuario;

public interface UsuarioService {
	
	// Usuario
	public boolean saveUsuario(Usuario usuario);
	
	public boolean updateUsuario(Usuario usuario);
	
	public boolean deleteUsuario(Usuario usuario);
	
	public List<Usuario> getAll();	
	
	public Usuario getUsuarioeById(Integer idUsuario);
	
	// Tipo Usuario
	
	public List<TipoUsuario> getAllTipoUsuarios();
	
	public List<Usuario> getAllFinMembresia(int n);
	
	public void updateUsuarioFinMembresia();
	
        public int buscarporUsuario(String usuUsuario);

        public String buscarporCodigo(int codigo);
        
        public boolean SP_registro(String usuario,String empresa);
}
