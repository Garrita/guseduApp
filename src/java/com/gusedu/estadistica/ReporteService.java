package com.gusedu.estadistica;

import com.gusedu.entidad.ECajaResumen;
import com.gusedu.entidad.ECajaResumen1;
import com.gusedu.entidad.ECajaResumenMensual;
import com.gusedu.entidad.ECajaResumenMensual1;
import com.gusedu.model.DetalleFactura;
import java.util.Date;
import java.util.List;

public interface ReporteService {

	public List<Reporte> listarTerapiaByterapeutas(Date fechitai, Date fechitaf);
        
        public List<ReporteClientes> listarClientesByterapeutas(String Terapeuta,Date fechitai, Date fechitaf);
        
        public List<ReporteClientesXProd> listarProductosXCliente(String Product,Date fechitai, Date fechitaf);
	
	public Reporte AcumuladoTerapias();
	
	public List<Reporte> listarProductos(Date fechitai, Date fechitaf);
        
        public List<ECajaResumen> MostrarCajaResumen(Date fechitai, Date fechitaf);
	
        public List<ECajaResumen1> MostrarCajaDetalle(Date fechitai, Date fechitaf);
        
        public List<ECajaResumenMensual> MostrarCajaResumenMensual(String mes, String año);
        
        public List<ECajaResumenMensual1> MostrarCajaMensualDetalle(String mes, String año);
        
//        public List<DetalleFactura> getDetalleFactura(Cliente cliente);
}
