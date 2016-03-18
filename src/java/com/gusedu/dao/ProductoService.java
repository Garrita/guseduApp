package com.gusedu.dao;

import com.gusedu.entidad.EProductoLog;
import com.gusedu.entidad.EProductoLogAvanzado;
import com.gusedu.entidad.detalle_factura;
import java.util.List;

import com.gusedu.model.Producto;
import com.gusedu.model.ProductoVisita;
import com.gusedu.model.Visita;

public interface ProductoService {

	public List<Producto> getAllProductos();
	
	public List<ProductoVisita> getAllProductosByVisita(Visita visita);
	
	public Producto getProductoById(Integer idProducto);
	
	public boolean updateProducto(Producto producto);
	
	public boolean saveProductoVisita(ProductoVisita productoVisita);
	
	public boolean updateProductoVisita(ProductoVisita productoVisita);
	
	public boolean deleteProductoVisita(ProductoVisita productoVisita);
        
        public boolean saveProducto(Producto producto);
        
        public boolean deleteProducto(Producto producto);
        
        public boolean SP_SaveProductoVisita(ProductoVisita productoVisita);
        
        public boolean SP_DeleteProductoVisita(ProductoVisita productoVisita);
        
        public boolean SP_CrearCabeceraProducto(int cod_cli,int prod_cod,
                                                String nom_item,
                                                int cantidad,double costo,int cod_vis);
	public List<detalle_factura> SP_ListarProductosF(int cod_cli);
        
        public boolean SP_EliminarProductoFactura(int cod_cli);
        
        public boolean listarProductoLog();
        
        public boolean listarProductoLogAvanzado();
        
        public List<EProductoLog> MostrarProductoLog();
        
        public List<EProductoLogAvanzado> MostrarProductoLogAvanzado();
}
