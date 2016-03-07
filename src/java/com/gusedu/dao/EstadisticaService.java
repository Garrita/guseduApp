/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao;

import com.gusedu.entidad.Estadistica;
import java.util.List;

/**
 *
 * @author NV55C
 */
public interface EstadisticaService {
      public List<Estadistica> pacienteMesActual();
	
	public List<Estadistica> visitaMesActual();
	
	public List<Estadistica> pacienteMeses();
	
	public List<Estadistica> visitaMeses();
	
	public List<Estadistica> pacienteAnual();
	
	public List<Estadistica> visitaAnual();
}
