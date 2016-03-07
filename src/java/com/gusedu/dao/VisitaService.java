package com.gusedu.dao;

import com.gusedu.entidad.Calendario;
import com.gusedu.entidad.EUltimaVisita;
import com.gusedu.entidad.EUltimaVisitaxCliente;
import java.util.Date;
import java.util.List;

import com.gusedu.model.Cliente;
import com.gusedu.model.Terapia;
import com.gusedu.model.Visita;

public interface VisitaService {

	public boolean saveVisita(Visita visita);

	public boolean updateVisita(Visita visita);

	public boolean deleteVisita(Visita visita);

	public List<EUltimaVisitaxCliente> getVisitasCliente(int clicodigo);
	
	public Visita getVisitaById(Integer idVisita);
	
	public Visita getLastVisitaCliente(Cliente cliente);
	
	public Visita buscarVisita(Cliente cliente);
	
	public Visita getLastVisitaCliente2(Cliente cliente);
	
	public double costodeVisita(Date fec_inicial,Date fec_final);
	
	public List<Visita> getVisitabyFechas(Date fec_inicial,Date fec_final);

	public List<Visita> getVisitas();
        
        public List<Visita> getVisitasByEmpresa();
        
        public boolean SPsaveVisitaxTerapia(Visita vis,Terapia ter); 
        
        public Visita visitaDelDia(Cliente cliente);
        
        public int visitaProgramada(Date fecha,Cliente cliente);
        
        public boolean SPdeleteVisita(int cod_visita);
        
        public boolean SP_CrearFactura(boolean llegada,int cli,int vis);
        
        public List<Calendario> SP_Calendario(String terapeuta);
        
        public boolean SP_ActualizarDiar(Visita visita);
        
        public List<EUltimaVisita> SP_LISTAR_VISITAS(Date fec_inicial,Date fec_final);
}
