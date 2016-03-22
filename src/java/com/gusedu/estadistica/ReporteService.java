package com.gusedu.estadistica;

import com.gusedu.entidad.ECajaResumen;
import java.util.Date;
import java.util.List;

public interface ReporteService {

	public List<Reporte> listarTerapiaByterapeutas();
        
        public List<ReporteClientes> listarClientesByterapeutas(String Terapeuta);
        
        public List<ReporteClientesXProd> listarProductosXCliente(String Product);
	
	public Reporte AcumuladoTerapias();
	
	public List<Reporte> listarProductos();
        
        public List<ECajaResumen> MostrarCajaResumen(Date fechitai, Date fechitaf);
	
}
