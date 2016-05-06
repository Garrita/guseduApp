package com.gusedu.dao;

import com.gusedu.entidad.EProductoLog;
import com.gusedu.entidad.EProductoLogAvanzado;
import com.gusedu.entidad.Obsequio;
import com.gusedu.entidad.detalleDelivery;
import com.gusedu.entidad.detalle_factura;
import java.util.List;

import com.gusedu.model.Producto;
import com.gusedu.model.ProductoVisita;
import com.gusedu.model.Visita;
import java.util.Date;

public interface ProductoService {

	public List<Producto> getAllProductos();
	
	public List<ProductoVisita> getAllProductosByVisita(Visita visita);
	
	public Producto getProductoById(Integer idProducto);
	
	public boolean updateProducto(Producto producto);
	
	public boolean saveProductoVisita(ProductoVisita productoVisita);
	
	public boolean updateProductoVisita(ProductoVisita productoVisita);
	
	public boolean deleteProductoVisita(ProductoVisita productoVisita);
        
        public boolean saveProducto(Producto producto);
        
        public boolean deleteProducto(int cod_producto);
        
        public boolean SP_SaveProductoVisita(ProductoVisita productoVisita,double val,String item);
        
        public boolean SP_DeleteProductoVisita(ProductoVisita productoVisita);
        
        public boolean SP_CrearCabeceraProducto(int cod_cli,int prod_cod,
                                                String nom_item,
                                                int cantidad,double costo,int cod_vis,double cost,
                                                String ubicacion,String ubicacio_contacto,String contacto,String vendedor,boolean delivery,double pasaje);
	public List<detalle_factura> SP_ListarProductosF(int cod_cli);
        
        public boolean SP_EliminarProductoFactura(int cod_cli);
        
        public boolean listarProductoLog();
        
        public boolean listarProductoLogAvanzado();
        
        public List<EProductoLog> MostrarProductoLog();
        
        public List<EProductoLogAvanzado> MostrarProductoLogAvanzado();
        
        public List<String> SP_ValidarStockMinimo();
        
        public boolean SP_CrearObsequio(String des ,Producto p,int cant,double total);
        
        public List<Obsequio> SP_ListarObsequio(Date fec_i,Date fec_f);
        
        public boolean SP_EliminarObsequio(int id_prodObsequio,int cant,int id_producto);
            
        public detalleDelivery SP_VerificarDelivery(int cli);
}
