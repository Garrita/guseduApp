package com.gusedu.estadistica;

import java.util.List;

public interface ReporteService {

	public List<Reporte> listarTerapiaByterapeutas();
	
	public Reporte AcumuladoTerapias();
	
	public List<Reporte> listarProductos();
	
}
