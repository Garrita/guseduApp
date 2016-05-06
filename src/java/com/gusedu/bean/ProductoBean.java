/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.ClienteService;
import com.gusedu.dao.ProductoService;
import com.gusedu.dao.TipoProductoService;
import com.gusedu.dao.impl.ClienteServiceImpl;
import com.gusedu.dao.impl.ProductoServiceImpl;
import com.gusedu.dao.impl.TipoProductoServiceImpl;
import com.gusedu.entidad.ClientePersona;
import com.gusedu.entidad.EProductoLog;
import com.gusedu.entidad.EProductoLogAvanzado;
import com.gusedu.entidad.detalleDelivery;
import com.gusedu.entidad.detalle_factura;
import com.gusedu.model.Modelo;
import com.gusedu.model.Producto;
import com.gusedu.model.TipoProducto;
import com.gusedu.model.UnidadMedida;
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class ProductoBean {

    
    ProductoService productoservice;
    ClienteService clienteService ;
    TipoProductoService tipoProductoservice;
    
    private Producto producto;
    private List<Producto> listaproducto;
    private String query;
    
    private String dni;
    private String nom;
    private String ap_p;
    private String ap_m;
    private List<ClientePersona> lista_cliper; 
    
    private double costoParcial;
    private int cantidadProducto;
    private int cod_cli;
    public List<detalle_factura> lista_detfact;
    private List<EProductoLog> listarMovimientoLog;
    private List<EProductoLogAvanzado> listarMovimientoLogAvanzado;
    private List<String> existencias;
    private String descripcion;
    private String queryProducto;
    
   private String tipo;
   private double valor;
   
   private List<TipoProducto> listatipoProducto;
   private String nombre;
   private String ubicacion;
   private String direccion;
   
   private detalleDelivery detDel;
   private int idDepa;
   private int idProv;
   private int idDist;
   
   private String depa;
   private String prov;
   private String dist;
   private double pasaje;
    
    public ProductoBean() {
        
        producto = new Producto();
        producto.setTipoProducto(new TipoProducto());
        producto.setUnidadMedida(new UnidadMedida());
        producto.setModelo(new Modelo());
        
        productoservice = new ProductoServiceImpl();
        clienteService = new ClienteServiceImpl();
        tipoProductoservice = new TipoProductoServiceImpl();
        detDel= new detalleDelivery();
        LISTAR_PRODUCTOS();
        ListarTipos();
        LISTA_CLIPER();
        cantidadProducto=1;
//        MOSTRARLOGProducto();
        
        
        validadorLogin();
        queryProducto = "";
    }

    public String getQueryProducto() {
        return queryProducto;
    }

    public void setQueryProducto(String queryProducto) {
        this.queryProducto = queryProducto;
    }

    public void validadorLogin()
    {
        validador();
        if(existencias.size()>0)
        {
            List<String> ss  = new ArrayList<>();
                    for (int i = 0; i < existencias.size(); i++) {
                        if(ss.indexOf(existencias.get(i))==-1)
                        {
                            ss.add(existencias.get(i));
                        }
                    }
                    existencias=ss;
        RequestContext.getCurrentInstance().execute("PF('dlgAvisoProducto').show();");
        }
    }
    
    public void validador()
    {
        existencias= new ArrayList<>();
        existencias=productoservice.SP_ValidarStockMinimo();
        List<String> ss = new ArrayList<>();
        String val="";String op="";
        int tam=existencias.size();
       if(tam>0)
        {
            for (int i = 0; i < tam; i++) {
                ss.add(StaticUtil.cortar(existencias.get(i), 1));
                if(producto.getProCodigo()!=null)
                {
                    if(Integer.parseInt(StaticUtil.cortar(existencias.get(i), 0))==producto.getProCodigo())
                    {
                    op=StaticUtil.cortar(existencias.get(i), 1);
                }
          
                }
                 }
            
           
           // StaticUtil.errorMessage("Precaución", "Los siguientes productos están por agotarse : "+val);
  
         existencias=ss;
         if(!"".equals(op))
         {
             StaticUtil.errorMessage("Precaución", "El siguiente productos está por agotarse : "+op);
         }
                     
        }
    }

    public void ListarTipos()
    {
        listatipoProducto= tipoProductoservice.getAllTipoProducto();
    }
    
    public List<TipoProducto> getListatipoProducto() {
        return listatipoProducto;
    }

    public void setListatipoProducto(List<TipoProducto> listatipoProducto) {
        this.listatipoProducto = listatipoProducto;
    }
    
    
    public List<String> getExistencias() {
        return existencias;
    }

    public void setExistencias(List<String> existencias) {
        this.existencias = existencias;
    }

    
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    
    
    public List<Producto> getListaproducto() {
        return listaproducto;
    }
    
    public void LISTAR_PRODUCTOS()
    {
        listaproducto = productoservice.getAllProductos();                               
    }

    public void filtrarProductos() 
    {
        listaproducto = productoservice.getAllProductos();
        List filtrados = new ArrayList();
        System.out.println((new StringBuilder()).append("Listado : ").append(listaproducto.size()).toString());
        System.out.println((new StringBuilder()).append("QueryP : ").append(queryProducto).toString());
        Iterator iterator = listaproducto.iterator();
        do {
            if (!iterator.hasNext()) 
            {
                 break;
            }
            Producto p = (Producto)iterator.next();
            if (p.getProDescripcionM().toLowerCase().contains(queryProducto.toLowerCase())) 
            {
                 filtrados.add(p);
            }
           } while (true);
        listaproducto = filtrados;
     }
                
    public void cancelar()
    {
        producto = new Producto();
        producto.setTipoProducto(new TipoProducto());
        producto.setUnidadMedida(new UnidadMedida());
        producto.setModelo(new Modelo());
    }
    public void ADDPRODUCTO()
    {
        TipoProducto tp = new TipoProducto();
        tp.setTprCodigo(1);
        Modelo m = new Modelo();
        m.setModCodigo(1);
        UnidadMedida um = new UnidadMedida();
        um.setUmeCodigo(1);
        producto.setTipoProducto(tp);
        producto.setModelo(m);
        producto.setUnidadMedida(um);
        if(productoservice.saveProducto(producto))
        {
            StaticUtil.correctMesage("Exito", "Se ha registrado correctamente los datos del producto");
            LISTAR_PRODUCTOS();
        }else
        {
            StaticUtil.errorMessage("Error", "No se pudo registrar los datos del producto");
        }
        cantidadProducto=1;
                
    }
    
    public void get_Producto(Producto p)
    {
        producto=p;
    }
    
    public void deleteProducto()
    {
         if(productoservice.deleteProducto(producto.getProCodigo()))
        {
            StaticUtil.correctMesage("Exito", "Se ha eliminado correctamente el producto");
            LISTAR_PRODUCTOS();
        }else
        {
            StaticUtil.errorMessage("Error", "No se pudo eliminar el producto");
        }
    }
    
    public void updateProducto()
    {
         if(productoservice.updateProducto(producto))
        {
            //productoservice.listarProductoLog();
            productoservice.listarProductoLogAvanzado();
            StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente el producto");
        }else
        {
            StaticUtil.errorMessage("Error", "No se pudo actualizar los datos del producto");
        }
           LISTAR_PRODUCTOS();
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
    public void filtrarBusqueda() {
		LISTAR_PRODUCTOS();
		List<Producto> filtrados = new ArrayList<>();
		for (Producto u : listaproducto) {
	 
				if (u.getProDescripcionC().toLowerCase().contains(query.toLowerCase())  ) {
						filtrados.add(u);
					}
		 
		}
		listaproducto = filtrados;
	}

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAp_p() {
        return ap_p;
    }

    public void setAp_p(String ap_p) {
        this.ap_p = ap_p;
    }

    public String getAp_m() {
        return ap_m;
    }

    public void setAp_m(String ap_m) {
        this.ap_m = ap_m;
    }

    public int getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(int cod_cli) {
        this.cod_cli = cod_cli;
    }

    public void calculo()
    {
        if(tipo.equals("0"))
        {
            valor=producto.getProCostoUnitario();
            calculaCostoParcial(producto.getProCostoUnitario());
            
        }else
        {
            valor=producto.getProCostoUnitarioC();
            calculaCostoParcial(producto.getProCostoUnitarioC());
        }
    }
    
             public void calculaCostoParcial(double valor) {
                 double cant=cantidadProducto;
/* 460*/        try {
/* 460*/            if (cant > 0.0D) {
/* 461*/                costoParcial = Double.valueOf(cant * valor);
                    } else {
/* 463*/                costoParcial = Double.valueOf(0.0D);
                    }
                }
/* 465*/        catch (NumberFormatException ex) {
/* 466*/            System.out.print("Error, no se ha insertado un n\372mero");
                }
            }

    public double getCostoParcial() {
        return costoParcial;
    }

    public void setCostoParcial(double costoParcial) {
        this.costoParcial = costoParcial;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    
    
    public List<ClientePersona> getLista_cliper() {
        return lista_cliper;
    }
    
    public void LISTA_CLIPER()
    {
        lista_cliper=clienteService.getALLlistaClientePersona();
    }

    public void setLista_cliper(List<ClientePersona> lista_cliper) {
        this.lista_cliper = lista_cliper;
    }

    public List<detalle_factura> getLista_detfact() {
        return lista_detfact;
    }

    public void setLista_detfact(List<detalle_factura> lista_detfact) {
        this.lista_detfact = lista_detfact;
    }
    
    
    
    public void BUSQUEDAD()
    {
 
        lista_cliper = clienteService.listaClientePersona(nom, ap_p, ap_m, dni);
        System.out.println("Lista : "+lista_cliper.size());
        System.out.println("NOMBRE : "+nom);
        System.out.println("AP_P : "+ap_p);
        System.out.println("AP_M : "+ap_m);
        System.out.println("DNI : "+dni);
    }
    
    public void preProducto(int idProducto)
    {
        tipo="0";
        producto = productoservice.getProductoById(idProducto);
      calculo();
    }
    
    public void CLIENTE_sELECCIONADO(String cli)
    {
        cod_cli=Integer.parseInt(StaticUtil.cortar(cli, 0));
        nombre=StaticUtil.cortar(cli, 1);
        ubicacion=StaticUtil.cortar(cli, 2);
        direccion=StaticUtil.cortar(cli, 3);
        detDel=new detalleDelivery();
        MOSTRAR();
        idDepa=0;
        idProv=0;
        idDist=0;
        pasaje=0.0;
        if(lista_detfact.size()>0)
        {
            detDel= productoservice.SP_VerificarDelivery(cod_cli);
            System.out.println("LL : "+StaticUtil.cortar(detDel.getUbicacion(), 1)+"|"+StaticUtil.cortar(detDel.getUbicacion(), 2));
            depa=StaticUtil.cortar(detDel.getUbicacion(), 1)+"|"+StaticUtil.cortar(detDel.getUbicacion(), 2);
            prov=StaticUtil.cortar(detDel.getUbicacion(), 3)+"|"+StaticUtil.cortar(detDel.getUbicacion(), 4);
            dist=StaticUtil.cortar(detDel.getUbicacion(), 5)+"|"+StaticUtil.cortar(detDel.getUbicacion(), 6);
            pasaje=detDel.getPasaje();
            /*idDepa=Integer.parseInt(StaticUtil.cortar(detDel.getUbicacion(), 1));
            idProv=Integer.parseInt(StaticUtil.cortar(detDel.getUbicacion(), 3));
            idDist=Integer.parseInt(StaticUtil.cortar(detDel.getUbicacion(), 5));*/
            detDel.setUbicacion(StaticUtil.cortar(detDel.getUbicacion(), 0));
            
        }
    }
    
    public int devolucion(String cadena)
    {
        return Integer.parseInt(StaticUtil.cortar(cadena, 0));
    }
    
    public void ADD_PRODUCTO()
    {
       
         if( productoservice.SP_CrearCabeceraProducto(cod_cli, producto.getProCodigo(), producto.getProDescripcionM(), cantidadProducto, costoParcial,0,valor,direccion+","+ubicacion,detDel.getUbicacion()+"|"+depa+"|"+prov+"|"+dist,detDel.getContacto(),detDel.getVendedor(),detDel.isDelivery(),pasaje))
        {
             productoservice.listarProductoLogAvanzado();
            StaticUtil.correctMesage("Exito", "Se ha registrado correctamente los datos del producto");
            LISTAR_PRODUCTOS();
            MOSTRAR();
            validador();
        }else
        {
            StaticUtil.errorMessage("Error", "No se pudo registrar los datos del producto");
        }
    
    }
    
    public void ADD_PRODUCTO_OBSEQUIO()
    {
       
         if(productoservice.SP_CrearObsequio(descripcion, producto, cantidadProducto, costoParcial))
        { 
             productoservice.listarProductoLogAvanzado();
            StaticUtil.correctMesage("Exito", "Se ha registrado correctamente los datos del producto");
            LISTAR_PRODUCTOS();
            validador();
        }else
        {
            StaticUtil.errorMessage("Error", "No se pudo registrar los datos del producto");
        }
    
    }
    
    public void MOSTRAR()
    {
        
        lista_detfact= productoservice.SP_ListarProductosF(cod_cli);
        
    }
    
    public void ELIMINAR(int fact)
    {
        System.out.println("Entro a Eliminar"+fact);
        productoservice.SP_EliminarProductoFactura(fact);
        lista_detfact= productoservice.SP_ListarProductosF(cod_cli);
        LISTAR_PRODUCTOS();
    }
       
    public void MOSTRARLOGProducto() 
    {
        System.out.println("Probando LOG de Producto");
        //listarMovimientoLog = productoservice.MostrarProductoLog();
        listarMovimientoLogAvanzado = productoservice.MostrarProductoLogAvanzado();
        System.out.println("Éxito!!!");
    }

    public List<EProductoLog> getListarMovimientoLog() {
        return listarMovimientoLog;
    }

    public void setListarMovimientoLog(List<EProductoLog> listarMovimientoLog) {
        this.listarMovimientoLog = listarMovimientoLog;
    }

    public List<EProductoLogAvanzado> getListarMovimientoLogAvanzado() {
        return listarMovimientoLogAvanzado;
    }

    public void setListarMovimientoLogAvanzado(List<EProductoLogAvanzado> listarMovimientoLogAvanzado) {
        this.listarMovimientoLogAvanzado = listarMovimientoLogAvanzado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }





    public detalleDelivery getDetDel() {
        return detDel;
    }

    public void setDetDel(detalleDelivery detDel) {
        this.detDel = detDel;
    }

    public int getIdDepa() {
        return idDepa;
    }

    public void setIdDepa(int idDepa) {
        this.idDepa = idDepa;
    }

    public int getIdProv() {
        return idProv;
    }

    public void setIdProv(int idProv) {
        this.idProv = idProv;
    }

    public int getIdDist() {
        return idDist;
    }

    public void setIdDist(int idDist) {
        this.idDist = idDist;
    }

    public String getDepa() {
        return depa;
    }

    public void setDepa(String depa) {
        this.depa = depa;
    }

    public String getProv() {
        return prov;
    }

    public void setProv(String prov) {
        this.prov = prov;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public double getPasaje() {
        return pasaje;
    }

    public void setPasaje(double pasaje) {
        this.pasaje = pasaje;
    }
    
    
    
}
