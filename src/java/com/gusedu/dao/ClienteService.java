package com.gusedu.dao;

import com.gusedu.entidad.ClientePersona;
import com.gusedu.entidad.EUltimaVisita;
import java.util.List;

import com.gusedu.model.Cliente;

public interface ClienteService {

	public boolean saveCliente(Cliente cliente);
	
	public boolean updateCliente(Cliente cliente);
	
	public boolean deleteCliente(Cliente cliente);
	
	public List<Cliente> getClientes();	
	
	public Cliente getClienteById(Integer idCliente);
	
	public List<Cliente> getClientesPacientes();
	
	public List<Cliente> getClientesPacientesByUsuario(String usuario);
	
	public Cliente getClienteByIdPersona(Integer idPersona);
		
	public List<Cliente> ordenar();	
	
	public Cliente lastClient();
        
        public List<EUltimaVisita>  getListaUltimaVisita(String cliUsuCreacion) ;  
        
        public List<ClientePersona> listaClientePersona(String nom, String ap_p,String ap_m,String dni);
        
        public List<ClientePersona> getALLlistaClientePersona();
        
}
