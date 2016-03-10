/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao;

import com.gusedu.entidad.cabecera_factura;
import com.gusedu.entidad.detalle_factura;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NV55C
 */
public interface FacturaService {
    
    public cabecera_factura SP_ObtenerCabecera(int cli_codigo,Date fec,int vis_cod);
    
    public boolean SP_UpdateCabecera(int cli_codigo,String fac_real);
    
    public List<detalle_factura> SP_ListaDetalle(int pk_cabecera);
}
