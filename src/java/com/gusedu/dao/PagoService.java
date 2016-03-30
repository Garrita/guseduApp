/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.dao;

import com.gusedu.entidad.EPago;
import com.gusedu.model.Pago;
import com.gusedu.model.TipoPago;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NV55C
 */
public interface PagoService {
    
    public List<TipoPago> allTipoPagos();
    
    public boolean InsertPago(Pago pago);
    
    public boolean UpdatePago(Pago pago);
    
    public boolean DeletePago(Pago pago);
    
    public Pago obtenerPago(int visita);
    
    public List<Pago> allPagos();
    
    public List<Pago> allPagosByVisita(int visita);
    
    public List<EPago> allReport(Date fec_ini,Date fec_fin);
    
    public boolean SP_ValidarPagos(int pag,int fac,double monto);
}
