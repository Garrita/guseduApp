package com.gusedu.entidad;

import java.math.BigInteger;





public class Estadistica {

	private BigInteger CANTIDAD;

	private String FECHA;
	
	public Estadistica()
	{
		
	}
	
	public Estadistica(BigInteger CANTIDAD,String FECHA)
	{
		this.CANTIDAD=CANTIDAD;
		this.FECHA=FECHA;
	}
	
	public BigInteger getCANTIDAD() {
		return CANTIDAD;
	}
	public void setCantidad(BigInteger CANTIDAD) {
		this.CANTIDAD = CANTIDAD;
	}
	public String getFECHA() {
		return FECHA;
	}
	public void setFECHA(String FECHA) {
		this.FECHA = FECHA;
	}
	
}
