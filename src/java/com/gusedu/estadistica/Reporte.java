package com.gusedu.estadistica;


public class Reporte {
		private String Cantidad;
		private String nombre;
		private double costo;
		
		public Reporte()
		{
			
		}
		
		public Reporte(String Cantidad,String nombre,double costo)
		{
			this.Cantidad=Cantidad;
			this.nombre=nombre;
			this.costo=costo;
		}
		
		
		public String getCantidad() {
			return Cantidad;
		}
		public void setCantidad(String cantidad) {
			Cantidad = cantidad;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public double getCosto() {
			return costo;
		}
		public void setCosto(double costo) {
			this.costo = costo;
		}
		
		
}
