// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   VisitaBean.java

package com.gusedu.bean;

import com.gusedu.dao.ClienteService;
import com.gusedu.dao.HistoriaClinicaService;
import com.gusedu.dao.PagoService;
import com.gusedu.dao.ProductoService;
import com.gusedu.dao.TerapiaService;
import com.gusedu.dao.TerapiaSintomaService;
import com.gusedu.dao.VisitaService;
import com.gusedu.dao.impl.ClienteServiceImpl;
import com.gusedu.dao.impl.HistoriaClinicaServiceImpl;
import com.gusedu.dao.impl.PagoServiceImpl;
import com.gusedu.dao.impl.ProductoServiceImpl;
import com.gusedu.dao.impl.TerapiaServiceImpl;
import com.gusedu.dao.impl.TerapiaSintomaServiceImpl;
import com.gusedu.dao.impl.VisitaServiceImpl;
import com.gusedu.entidad.EUltimaVisitaxCliente;
import com.gusedu.model.*;
import com.gusedu.util.StaticUtil;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

// Referenced classes of package com.gusedu.bean:
//            HistorialTerapiaBean, TerapiaBean
@ManagedBean
@SessionScoped
public class VisitaBean {

            VisitaService visitaService;
            ProductoService productoService;
            private String opciones;
            private Visita visita;
            private Cliente cliente;
            private boolean prod;
            private String queryProducto;
            private List allProductos;
            private List<Producto> listaproductosDD;
            private Producto producto;
            private Integer mostrarFormProducto;
            private int cantidadProducto;
            private Double costoParcial;
            private List<ProductoVisita> productosDeVisita;
            private List<Visita> visitasPaciente;
            private List<EUltimaVisitaxCliente> visitasxPaciente;
            private HistoriaClinica historiaClinica;
            private String descripcionIMC;
            TerapiaService terapiaService;
            TerapiaSintomaService terapiasintomaService;
            HistoriaClinicaService historiaClinicaService;
            private TerapiaSintoma terapiasintoma;
            
            private Terapia ter;
            private double precioTotal;
            private boolean edit;
            private double prePrecioTerapia;
            
            private List<Pago> listaPagoByVisita;
            PagoService pagoservice;
            
              private String tipo;
   private double valor;

            public VisitaBean() {
/*  82*/        productoService = new ProductoServiceImpl();
visitaService= new VisitaServiceImpl();
                pagoservice = new PagoServiceImpl();
/*  83*/        visita = new Visita();
                terapiasintoma = new TerapiaSintoma();
/*  84*/        cliente = new Cliente();
/*  86*/        producto = new Producto();
/*  87*/        producto.setTipoProducto(new TipoProducto());
/*  88*/        producto.setUnidadMedida(new UnidadMedida());

                ter = new Terapia();
                ter.setTipoTerapia(new TipoTerapia());
                ter.setVisita(new Visita());
                
/*  90*/        historiaClinica = new HistoriaClinica();
/*  91*/        historiaClinica.setVisita(new Visita());
                
/*  93*/        cliente.setPersona(new Persona());
/*  95*/        queryProducto = "";
/*  96*/        mostrarFormProducto = -1;
/*  97*/        terapiaService = new TerapiaServiceImpl();
                terapiasintomaService = new TerapiaSintomaServiceImpl();
/*  98*/        historiaClinicaService = new HistoriaClinicaServiceImpl();
                precioTotal=0.0;
                edit=false;
                cantidadProducto=1;
                LISTAR_PRODUCTOS();
                LISTANDO_PRODUCTOS();
            }        

    public List<Producto> getListaproductosDD() {
        return listaproductosDD;
    }

    public void setListaproductosDD(List<Producto> listaproductosDD) {
        this.listaproductosDD = listaproductosDD;
    }
            
    public double getPrePrecioTerapia() {
        return prePrecioTerapia;
    }

    public void setPrePrecioTerapia(double prePrecioTerapia) {
        this.prePrecioTerapia = prePrecioTerapia;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    public double getPrecioTotal() {
        return precioTotal;
    }
    
    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Terapia getTer() {
        return ter;
    }

    public void setTer(Terapia ter) {
        this.ter = ter;
    }
            
            
            public String getDescripcionIMC() {
/* 102*/        return descripcionIMC;
            }

            public void setDescripcionIMC(String descripcionIMC) {
/* 106*/        this.descripcionIMC = descripcionIMC;
            }

            public HistoriaClinica getHistoriaClinica() {
/* 112*/        return historiaClinica;
            }

            public void setHistoriaClinica(HistoriaClinica historiaClinica) {
/* 116*/        this.historiaClinica = historiaClinica;
            }

            public List<Visita> getVisitasPaciente() {
/* 121*/        return visitasPaciente;
            }

            public List<ProductoVisita> getProductosDeVisita() {
/* 126*/        return productosDeVisita;
            }

            public int getCantidadProducto() {
/* 131*/        return cantidadProducto;
            }

            public void setCantidadProducto(int cantidadProducto) {
/* 135*/        this.cantidadProducto = cantidadProducto;
            }

            public Double getCostoParcial() {
/* 139*/        return costoParcial;
            }

            public void setCostoParcial(Double costoParcial) {
/* 143*/        this.costoParcial = costoParcial;
            }

            public List getAllProductos() {
            return allProductos;
                
            }
            public void LISTAR_PRODUCTOS()
            {
                 if (queryProducto != null && !queryProducto.isEmpty()) {
             allProductos= new ArrayList();
                } else {
             allProductos= productoService.getAllProductos();
                }
            }
            
            public void LIST_PROD()
            {
                allProductos= productoService.getAllProductos();
            }
            public void LISTANDO_PRODUCTOS()
            {
                listaproductosDD = productoService.getAllProductos();
            }

            public String getQueryProducto() {
/* 159*/        return queryProducto;
            }

            public void setQueryProducto(String queryProducto) {
/* 163*/        this.queryProducto = queryProducto;
            }

            public VisitaService getVisitaService() {
/* 168*/        return visitaService;
            }

            public void setVisitaService(VisitaService visitaService) {
/* 172*/        this.visitaService = visitaService;
            }

            public Cliente getCliente() {
/* 176*/        return cliente;
            }

            public void setCliente(Cliente cliente) {
/* 180*/        this.cliente = cliente;
            }

            public Visita getVisita() {
/* 184*/        return visita;
            }

            public void setVisita(Visita visita) {
/* 188*/        this.visita = visita;
            }

            public String getOpciones() {
/* 193*/        return opciones;
            }

            public void setOpciones(String opciones) {
/* 197*/        this.opciones = opciones;
            }

            public boolean isProd() {
/* 201*/        return prod;
            }

            public void setProd(boolean prod) {
/* 205*/        this.prod = prod;
            }

            public Producto getProducto() {
/* 209*/        return producto;
            }

            public void setProducto(Producto producto) {
/* 213*/        this.producto = producto;
            }

            public Integer getMostrarFormProducto() {
/* 217*/        return mostrarFormProducto;
            }

            public void setMostrarFormProducto(Integer mostrarFormProducto) {
/* 221*/        this.mostrarFormProducto = mostrarFormProducto;
            }

            public void cargaHistorial()
            {
            
                FacesContext fc = FacesContext.getCurrentInstance();
           
                 HistorialTerapiaBean objetoHTBean = (HistorialTerapiaBean)fc.getExternalContext().getSessionMap().get("historialTerapiaBean");
                 SintomaTerapiaBean stBean = (SintomaTerapiaBean)fc.getExternalContext().getSessionMap().get("sintomaTerapiaBean");
                 TerapiaBean objetoTBean = (TerapiaBean)fc.getExternalContext().getSessionMap().get("terapiaBean");
                    objetoHTBean.llenamatriz();
                    stBean.llenamatriz();
                    ListarVisitas();
                  /*  RequestContext context = RequestContext.getCurrentInstance();
                    RequestContext.getCurrentInstance().update("dialogHistorialVisitas");
                     context.execute("PF('dlgHV').show();");
                   // fc.getExternalContext().getSessionMap().put("ultimaterapia", ultimaterapia);
                    RequestContext.getCurrentInstance().update("frame4");*/
            }
            
            public void lastvisita(Cliente client) {
        visitaService = new VisitaServiceImpl();
        FacesContext fc = FacesContext.getCurrentInstance();
        VisitaBean objetoBean = (VisitaBean)fc.getExternalContext().getSessionMap().get("visitaBean");
        HistorialTerapiaBean objetoHTBean = (HistorialTerapiaBean)fc.getExternalContext().getSessionMap().get("historialTerapiaBean");
        SintomaTerapiaBean stBean = (SintomaTerapiaBean)fc.getExternalContext().getSessionMap().get("sintomaTerapiaBean");
        TerapiaSintomaBean tsBean = (TerapiaSintomaBean)fc.getExternalContext().getSessionMap().get("terapiaSintomaBean");
        TerapiaBean objetoTBean = (TerapiaBean)fc.getExternalContext().getSessionMap().get("terapiaBean");
        Visita vis = visitaService.buscarVisita(client);
              //  TerapiaSintoma tersin = terapiasintomaService.buscarTerapiaSintoma(client);
               // fc.getExternalContext().getSessionMap().put("tersin", tersin);
        fc.getExternalContext().getSessionMap().put("vis", vis);
        visita = new Visita();
        if (vis == null) {
            System.out.println("No hay visita, gg");
            if (opciones.equals("CV")) {
                registrarVisita();
                    } else
            if (!opciones.equals("HT") && !opciones.equals("HV") && !opciones.equals("DP")) {
                StaticUtil.errorMessage("Precaución", "Registre una visita antes de usar esta opción");
                StaticUtil.keepMessages();
                clearEntities();
                RequestContext.getCurrentInstance().update("formulario");
                return;
                    }
                } else {
/* 258*/            if (opciones.equals("CV")) {
/* 260*/                StaticUtil.errorMessage("Precaución", "Ya registro una visita el día de hoy");
/* 261*/                StaticUtil.keepMessages();
/* 262*/                clearEntities();
/* 263*/                RequestContext.getCurrentInstance().update("formulario");
/* 263*/                return;
                    }
/* 266*/            if (opciones.equals("DM")) {
/* 268*/                RequestContext context = RequestContext.getCurrentInstance();
/* 269*/                context.execute("PF('dlgHEA').show();");
/* 270*/                Visita ultimavisita = new Visita();
/* 272*/                ultimavisita = vis;
/* 273*/                visita = ultimavisita;
/* 274*/                fc.getExternalContext().getSessionMap().put("ultimavisita", ultimavisita);
/* 276*/                preNuevaHistoria2();
/* 278*/                objetoTBean.listarsintomas();
/* 279*/                objetoTBean.listarenfermedades();
/* 281*/                RequestContext.getCurrentInstance().update("frame2");
                    }
/* 285*/            if (opciones.equals("RGT")) {
                        RequestContext.getCurrentInstance().update("iddlgTerapeutas");
/* 287*/                RequestContext context = RequestContext.getCurrentInstance();
/* 288*/                context.execute("PF('dlgTerapeutas').show();");
/* 289*/                Visita ultimavisita = new Visita();
/* 291*/                ultimavisita = vis;
/* 292*/                visita = ultimavisita;
/* 293*/                fc.getExternalContext().getSessionMap().put("ultimavisita", ultimavisita);
                    }
/* 298*/          

         
                    if (opciones.equals("RGPT")) {
                        if(terapiaService.lastTerapiabyVisita(vis))
                             {
                                 RequestContext context = RequestContext.getCurrentInstance();
                                 context.execute("PF('dlgParesT').show();");

                                Visita ultimavisita = new Visita();
                                 ultimavisita = vis;
                                 visita = ultimavisita;
                                  fc.getExternalContext().getSessionMap().put("ultimavisita", ultimavisita);
                                 objetoTBean.usarTerapia();
                                   objetoBean.Prueba();
                             }else
                        {
                            StaticUtil.errorMessage("Precaución", "Registre una terapia para usar esta opción");
                  /* 261*/                StaticUtil.keepMessages();
                      clearEntities();
                  /* 253*/                RequestContext.getCurrentInstance().update("formulario");
                        }

                                      }
                                  }
                if (opciones.equals("HT")) {
                   objetoHTBean.llenamatriz();
                   stBean.llenamatriz();
                   tsBean.REFRESH_SELECTONEMENU();
                    RequestContext context = RequestContext.getCurrentInstance();
                    RequestContext.getCurrentInstance().update("dialoghistorialTerapia");
                    context.execute("PF('dlgHTe').show();");
                    TerapiaSintoma ultimaterapia = new TerapiaSintoma();
                   // ultimaterapia = tersin;
                    terapiasintoma  = ultimaterapia;
                   // fc.getExternalContext().getSessionMap().put("ultimaterapia", ultimaterapia);
                    RequestContext.getCurrentInstance().update("frame5");
                }
                if (opciones.equals("HV")) {
                    objetoHTBean.llenamatriz();
                    stBean.llenamatriz();
                    ListarVisitas();
                    RequestContext context = RequestContext.getCurrentInstance();
                    RequestContext.getCurrentInstance().update("dialogHistorialVisitas");
                    context.execute("PF('dlgHV').show();");
                    TerapiaSintoma ultimaterapia = new TerapiaSintoma();
                   // ultimaterapia = tersin;
                    terapiasintoma  = ultimaterapia;
                   // fc.getExternalContext().getSessionMap().put("ultimaterapia", ultimaterapia);
                    RequestContext.getCurrentInstance().update("frame4");
                }
/* 332*/        if (opciones.equals("DP")) {
/* 334*/            RequestContext context = RequestContext.getCurrentInstance();
/* 335*/            context.execute("PF('dlgDP').show();");
                }
/* 337*/        if (opciones.equals("P")) {
/* 339*/            RequestContext context = RequestContext.getCurrentInstance();
/* 340*/            context.execute("PF('dlgP').show();");
/* 342*/            prod = true;
/* 343*/            Visita ultimavisita = new Visita();
/* 345*/            ultimavisita = vis;
/* 346*/            visita = ultimavisita;
/* 347*/            fc.getExternalContext().getSessionMap().put("ultimavisita", ultimavisita);
/* 349*/            RequestContext.getCurrentInstance().update("formProduct");
                }
         if (opciones.equals("DCaja")) {
             Visita ultimavisita = new Visita();
             ultimavisita = vis;
             visita = ultimavisita;
             productosDeVisita = productoService.getAllProductosByVisita(visita);
             ter = terapiaService.terapiaByVisita(visita);
             
             precioTotal= visita.getVisCostoTotal();
             fc.getExternalContext().getSessionMap().put("visitaPago", visita);
             /*PagoBean pbean = new PagoBean();
             pbean.llenarlista(visita.getVisCodigo());*/
             llenarlista(visita.getVisCodigo());
             RequestContext.getCurrentInstance().update("pagosT");
            RequestContext.getCurrentInstance().update("formCaja");
            // visita=visitaService ter = terapiaService.terapiaByVisita(visita);.visitaDelDia(client);
             System.out.println("La visita de hoy es : "+ visita.getVisCodigo());
            
            
             RequestContext context = RequestContext.getCurrentInstance();
             context.execute("PF('dlgpago').show();");
                }
            }

            public void clearEntities() {
/* 369*/        visita = new Visita();
/* 376*/        opciones = "S";
            }

            public void probando() {
/* 380*/        opciones = "S";
            }
            
            public void Prueba() {
/* 385*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 386*/        Cliente cli = (Cliente)fc.getExternalContext().getSessionMap().get("cliente");
/* 387*/        Visita vis1 = visitaService.getLastVisitaCliente2(cli);
/* 388*/        if (vis1 == null) {
/* 390*/            Visita vs = new Visita();
/* 391*/            vs.setVisCodigo(Integer.valueOf(0));
/* 392*/            vis1 = vs;
                }
/* 394*/        fc.getExternalContext().getSessionMap().put("penultimavisita", vis1);
            }

            public void registrarVisita() {
/* 397*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 398*/        cliente = (Cliente)fc.getExternalContext().getSessionMap().get("cliente");
/* 400*/        visita.setVisPresencial(Boolean.valueOf(true));
/* 401*/        visita.setVisPrioridad(Integer.valueOf(2));
/* 402*/        visita.setVisEstado(Integer.valueOf(1));
/* 403*/        visita.setCliente(cliente);
/* 404*/        String usuarioCreacion = StaticUtil.userLogged();
/* 405*/        visita.setVisUsuCreacion(usuarioCreacion);
/* 406*/        visita.setVisCostoTotal(Double.valueOf(0.0D));
/* 407*/        Date fechaActual = StaticUtil.getFechaActual();
/* 408*/        visita.setVisFecCreacion(fechaActual);
/* 410*/        if (visitaService.saveVisita(visita)) {
/* 413*/            StaticUtil.correctMesage("\311xito", "Se ha registrado correctamente la visita");
/* 414*/            StaticUtil.keepMessages();
/* 416*/            Visita vis = visitaService.getLastVisitaCliente(cliente);
/* 418*/            fc.getExternalContext().getSessionMap().put("visita", vis);
/* 419*/            fc.getExternalContext().getSessionMap().put("ultimavisita", vis);
/* 421*/            clearEntities();
/* 422*/            RequestContext.getCurrentInstance().update("formulario");
                }
            }

            public void filtrarProductos() {
/* 433*/        allProductos = productoService.getAllProductos();
/* 435*/        List filtrados = new ArrayList();
/* 437*/        System.out.println((new StringBuilder()).append("Listado : ").append(allProductos.size()).toString());
/* 438*/        System.out.println((new StringBuilder()).append("QueryP : ").append(queryProducto).toString());
/* 439*/        Iterator iterator = allProductos.iterator();
/* 439*/        do {
/* 439*/            if (!iterator.hasNext()) {
/* 439*/                break;
                    }
/* 439*/            Producto p = (Producto)iterator.next();
/* 440*/            if (p.getProDescripcionM().toLowerCase().contains(queryProducto.toLowerCase())) {
/* 443*/                filtrados.add(p);
                    }
                } while (true);
/* 446*/        allProductos = filtrados;
            }

            public void preAddProductoWeb(Integer idProducto) {
/* 451*/        System.out.println((new StringBuilder()).append("ID PRODUCTO : ").append(idProducto).toString());
/* 452*/        producto = productoService.getProductoById(idProducto);
/* 453*/        prod = false;
                tipo="0";
                calculo();
/* 454*/        mostrarFormProducto = Integer.valueOf(1);
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
            public void calculaCostoParcial(double val) 
            {
                double cant=cantidadProducto;
                try {   
                        if (cantidadProducto > 0.0D) 
                        {
                            costoParcial = Double.valueOf(cant * val);
                        } 
                        else
                        {
                            costoParcial = Double.valueOf(0.0D);
                        } 

                    }
                catch (NumberFormatException ex) {
                    System.out.print("Error, no se ha insertado un número");
                }
            }

            public void listar() {
/* 473*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 474*/        Visita vis = (Visita)fc.getExternalContext().getSessionMap().get("ultimavisita");
/* 475*/        productosDeVisita = productoService.getAllProductosByVisita(vis);
/* 476*/        System.out.println((new StringBuilder()).append("Productos de Visita : ").append(productosDeVisita.size()).toString());
            }

        public void addProductoToVisitaWeb() 
        {
            if (cantidadProducto <= 0.0D)
            {
                return;
            }
            FacesContext fc = FacesContext.getCurrentInstance();
            Visita vis = (Visita)fc.getExternalContext().getSessionMap().get("ultimavisita");
            ProductoVisita toAdd = new ProductoVisita();
            toAdd.setPxvCantidad(Double.parseDouble(cantidadProducto+""));
            toAdd.setPxvCostoParcial(costoParcial);
            toAdd.setProducto(producto);
            toAdd.setVisita(vis);
            if (productoService.SP_SaveProductoVisita(toAdd,valor,producto.getProDescripcionM())) 
            {
                StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente el producto");
                StaticUtil.keepMessages();
                System.out.println("SI ACTUALIZO ?");
               /* producto.setProExistencias(Double.valueOf(producto.getProExistencias().doubleValue() - cantidadProducto.doubleValue()));
                productoService.updateProducto(producto);
                //vis.setVisCostoTotal(Double.valueOf(vis.getVisCostoTotal().doubleValue() + toAdd.getPxvCostoParcial().doubleValue()));


                        Visita v2= new Visita();
                        v2 = visitaService.getVisitaById(vis.getVisCodigo());
                        System.out.println("VISITA : "+v2.getVisCodigo()+"\n"
                                + "Cliente: "+v2.getCliente().getCliCodigo() +
                                "\nCosto : "+v2.getVisCostoTotal());
                //#           v2.setVisCostoTotal(vis.getVisCostoTotal()+ toAdd.getPxvCostoParcial());

                visitaService.updateVisita(v2);*/
                costoParcial = 0.0;
                cantidadProducto = 1;
                mostrarFormProducto = -1;
                productoService.listarProductoLogAvanzado();
               /* LISTAR_PRODUCTOS();
                LISTANDO_PRODUCTOS();*/
                LIST_PROD();
                listar();
                ProductoBean objetoBean = (ProductoBean)fc.getExternalContext().getSessionMap().get("productoBean");
                objetoBean.validador();
                objetoBean.LISTAR_PRODUCTOS();
            } else 
            {
                System.out.println("ERROR, DEBUGEAR.");
            }
        }

            public void eliminarProducto(ProductoVisita pxv) {
                FacesContext fc = FacesContext.getCurrentInstance();
        System.out.println("Elimina Producto");
       /* Producto pr = new Producto();
                Visita v1 = (Visita)fc.getExternalContext().getSessionMap().get("ultimavisita");
 
                System.out.println("VISITA : "+v1.getVisCodigo() + "|| COSTO : "+v1.getVisCostoTotal());
                System.out.println("Costo de Producto : "+pxv.getPxvCostoParcial());
                v1.setVisCostoTotal(v1.getVisCostoTotal()-pxv.getPxvCostoParcial());
                System.out.println("NEW : "+v1.getVisCostoTotal());
        pr = pxv.getProducto();
        pr.setProExistencias(Double.valueOf(pr.getProExistencias().doubleValue() + pxv.getPxvCantidad().doubleValue()));
        productoService.updateProducto(pr);
        productoService.deleteProductoVisita(pxv);
               
*/
        productoService.SP_DeleteProductoVisita(pxv);
//# visitaService.updateVisita(v1);
        listar();
         LIST_PROD();
            }

            public void ListarVisitas() {
/* 528*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 529*/        Cliente client = (Cliente)fc.getExternalContext().getSessionMap().get("cliente");
                System.out.println("LISTAR VISITAS : "+client.getCliCodigo());
/* 530*/        visitasxPaciente = visitaService.getVisitasCliente(client.getCliCodigo());
/* 531*/        System.out.println("VisitaBean - ListarVisitas : "+visitasxPaciente.size());
            }

            public List<EUltimaVisitaxCliente> getVisitasxPaciente() {
                return visitasxPaciente;
            }

            public void setVisitasxPaciente(List<EUltimaVisitaxCliente> visitasxPaciente) {
                this.visitasxPaciente = visitasxPaciente;
            }
                    public void cancel() {
/* 537*/        clearEntities();
/* 538*/        RequestContext.getCurrentInstance().update("formulario");
/* 539*/        RequestContext context = RequestContext.getCurrentInstance();
/* 540*/        context.execute("PF('dlgDP').hide();");
            }

            public void calcularIMC() {
/* 548*/        double peso = 0.0D;
/* 549*/        double talla = 0.0D;
/* 550*/        if (historiaClinica.getHclPeso() != null && historiaClinica.getHclTalla() != null) {
/* 552*/            if (historiaClinica.getHclTalla().doubleValue() < 2.5D) {
/* 554*/                talla = Math.pow(historiaClinica.getHclTalla().doubleValue(), 2D);
                    } else {
/* 557*/                talla = Math.pow(historiaClinica.getHclTalla().doubleValue() / 100D, 2D);
                    }
/* 560*/            peso = historiaClinica.getHclPeso().doubleValue();
/* 561*/            double imc = peso / talla;
/* 563*/            descripcionIMC = StaticUtil.calculoIMC(imc);
/* 564*/            historiaClinica.setHclImc(Double.valueOf(imc));
                }
            }

            public void preNuevaHistoria2() {
/* 570*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 571*/        Visita vis = (Visita)fc.getExternalContext().getSessionMap().get("ultimavisita");
/* 572*/        System.out.println("VisitaBean - preNuevaHistoria2 :"+vis);
/* 573*/        if (historiaClinicaService.getHistoriaByVisita(vis) == null) {
/* 575*/            historiaClinica = new HistoriaClinica();
/* 579*/            historiaClinica.setVisita(vis);
                } else {
/* 582*/            historiaClinica = historiaClinicaService.getHistoriaByVisita(visita);
/* 583*/            System.out.println("VisitaBean - preNuevaHistoria2 else :"+historiaClinica.getHclCodigo());
                }
            }

            public void nuevaHistoria2() {
/* 589*/        if (historiaClinicaService.saveHistoriaClinica(historiaClinica)) {
/* 590*/            registrarSintoma();
/* 591*/            registrarEnfermedad();
/* 592*/            RequestContext context = RequestContext.getCurrentInstance();
/* 593*/            context.execute("PF('dlgHEA').hide();");
/* 594*/            clearEntities();
/* 595*/            StaticUtil.correctMesage("\311xito", "Se han guardado los datos m\351dicos");
/* 596*/            StaticUtil.keepMessages();
                } else {
/* 601*/            StaticUtil.errorMessage("Error", "Hubo un error al guardar los datos");
                }
            }

            public void registrarSintoma() {
/* 608*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 609*/        List s = (List)fc.getExternalContext().getSessionMap().get("listaSintoma");
/* 611*/        if (s != null) {
/* 613*/            for (int i = 0; i < s.size(); i++) {
/* 614*/                SintomaVisita sinvis = new SintomaVisita();
/* 615*/                sinvis.setVisita(visita);
/* 616*/                sinvis.setSintoma((Sintoma)s.get(i));
/* 617*/                terapiaService.saveSintomaVisita(sinvis);
                    }

                }
            }

            public void registrarEnfermedad() {
/* 624*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 625*/        List e = (List)fc.getExternalContext().getSessionMap().get("listaEnfermedad");
/* 628*/        if (e != null) {
/* 630*/            for (int j = 0; j < e.size(); j++) {
/* 631*/                EnfermedadVisita enfvis = new EnfermedadVisita();
/* 632*/                enfvis.setVisita(visita);
/* 633*/                enfvis.setEnfermedad((Enfermedad)e.get(j));
/* 634*/                terapiaService.saveEnfermedadVisita(enfvis);
                    }

                }
            }
            
            public void change()
            {
                 System.out.println("PRE-EDIT : "+edit);
                if(edit==false)
                {
                    prePrecioTerapia=ter.getTerCosto();
                    edit=true;
                    System.out.println("POST-EDIT : "+edit);
                    System.out.println("Precio Terapia : "+ prePrecioTerapia);
                }else
                {
                    edit=false;
                     System.out.println("POST-EDIT : "+edit);
                     pagoTerapia();
                }
            }
            
            public void pagoTerapia()
            {
                
                terapiaService.updateTerapia(ter);
                visita.setVisCostoTotal(visita.getVisCostoTotal()-prePrecioTerapia+ter.getTerCosto());
                visitaService.updateVisita(visita);
                precioTotal= visita.getVisCostoTotal();
                System.out.println("Nuevo Precio de Visita :"+visita.getVisCostoTotal());
            }
            
            
            public void limpiar()
            {
                probando();
                precioTotal=0.0;
                visita= new Visita();
                visita.setCliente(new Cliente());
                
                ter = new Terapia();
                ter.setTipoTerapia(new TipoTerapia());
                ter.setVisita(new Visita());
                edit=false;
            }

    public List<Pago> getListaPagoByVisita() {
        return listaPagoByVisita;
    }
        public void llenarlista(int id)
    {
      
     
      listaPagoByVisita= pagoservice.allPagosByVisita(id);
        System.out.println("Cantidad de Pagos : "+listaPagoByVisita.size());
        for (int i = 0; i < listaPagoByVisita.size(); i++) {
            System.out.println("MODALIDAD : "+ listaPagoByVisita.get(i).getTipoPago().getNombre() +
                               "\n MONTO :  "+ listaPagoByVisita.get(i).getMonto());
        }
    }              
              
        public void changeVisita(Visita v)
        {
            visita=v;
        }
        
        public void limpiarLista()
        {
            listaPagoByVisita= new ArrayList<>();
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
        
}
