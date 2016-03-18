// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   ProductoServiceImpl.java

package com.gusedu.dao.impl;

import com.gusedu.dao.ProductoService;
import com.gusedu.entidad.detalle_factura;
import com.gusedu.model.Producto;
import com.gusedu.model.ProductoVisita;
import com.gusedu.model.Visita;
import com.gusedu.util.HibernateUtil;
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductoServiceImpl
    implements ProductoService {

            EntityManager em;


            public List<Producto> getAllProductos() {
                System.out.println("Se ejecuto getAllProductos - ProductoServiceImpl");
        List<Producto> result = new ArrayList();
                 Session sesion = HibernateUtil.getSessionFactory().openSession();
        //Transaction tx = null;
        try {
             //tx = sesion.beginTransaction();
              String sql = "SELECT p FROM Producto p";
            Query q = sesion.createQuery(sql);
            result = q.list();
             // tx.commit();
         
        } catch (Exception e) {
            /*if (tx != null) {
                tx.rollback();
            }   */
            System.out.println(e.getMessage());
        } finally {
            //sesion.flush();
            //sesion.close();
        }
        /*try {
            String sql = "SELECT p FROM Producto p";
            Query q = sesion.createQuery(sql);
            result = q.list();
                }
        catch (NoResultException e) {
           System.out.println((new StringBuilder()).append("ERROR : ").append(e.getMessage()).toString());
                }*/
           return result;
            }

            public Producto getProductoById(Integer idProducto) {
                Producto producto;
                Exception exception;
/*  42*/        Session sesion = HibernateUtil.getSessionFactory().openSession();
/*  43*/        producto = null;
/*  44*/        Transaction tx = null;
/*  46*/        try {
/*  46*/            tx = sesion.beginTransaction();
/*  47*/            producto = (Producto)sesion.load(Producto.class, idProducto);
/*  48*/            tx.commit();
/*  49*/            System.out.println((new StringBuilder()).append("VALOR : ").append(producto.getProDescripcionM()).toString());
                }
/*  50*/        catch (Exception e) {
/*  51*/            if (tx != null) {
/*  52*/                tx.rollback();
/*  53*/                System.out.println((new StringBuilder()).append("ERROR . ").append(e.getMessage()).toString());
                    }
/*  55*/            throw new RuntimeException(e);
                }
/*  57*/        finally {
    sesion.flush();
/*  57*/            sesion.close();
                }
 
/*  59*/        return producto;
            }

            public boolean saveProductoVisita(ProductoVisita productoVisita) {
                
                boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(productoVisita);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de saveHistoriaClinica : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
 
/*  83*/        return resultado;
            }

            public List<ProductoVisita>  getAllProductosByVisita(Visita visita) {
                System.out.println("Se ejecuto getAllProductosByVisita - ProductoServiceImpl");
                List<ProductoVisita>  result= new ArrayList<>();
               

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
             String sql = "SELECT p FROM ProductoVisita p WHERE p.visita.visCodigo=:visita";
/*  97*/        Query q = sesion.createQuery(sql);
/*  98*/        q.setParameter("visita", visita.getVisCodigo());
/*  99*/        result = q.list();
/* 100*/        System.out.println((new StringBuilder()).append("LISTA : ").append(result.size()).toString());
/* 101*/        for (int i = 0; i < result.size(); i++) {
/* 103*/            System.out.println((new StringBuilder()).append("PRODUCTO : ").append(((ProductoVisita)result.get(i)).getProducto().getProDescripcionM()).toString());
                }
           // tx.commit();
         
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de saveHistoriaClinica : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
 
/* 121*/        return result;
            }

            public boolean updateProducto(Producto producto) {
                
                
                boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            System.out.println("Actualizo Producto....");
/* 133*/        sesion.merge(producto);
/* 134*/        System.out.println(producto.getProExistencias());
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de saveHistoriaClinica : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
 
/* 147*/        return resultado;
            }

            public boolean deleteProductoVisita(ProductoVisita productoVisita) {
                boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.delete(productoVisita);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de saveHistoriaClinica : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
             
/* 172*/        return resultado;
            }

            public boolean updateProductoVisita(ProductoVisita productoVisita) {
/* 178*/        boolean resultado = false;
/* 186*/        return resultado;
            }

    @Override
    public boolean saveProducto(Producto producto) {
       boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.save(producto);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de saveProducto : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return resultado;
    }

    @Override
    public boolean deleteProducto(Producto producto) {
        boolean resultado = false;

        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            sesion.delete(producto);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                System.out.println("ERROR de saveProducto : " + e.getMessage());
            }
            System.out.println(e.getMessage());
        } finally {
            sesion.flush();
            sesion.close();
        }
        return resultado;
    }

    @Override
    public boolean SP_SaveProductoVisita(ProductoVisita productoVisita) {
         boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL SP_InsertProductoVisita(:cantidad,:costo,:symbol,:prod_cod,:vis_cod) }");
             q.setParameter("cantidad", productoVisita.getPxvCantidad());
             q.setParameter("costo", productoVisita.getPxvCostoParcial());
             q.setParameter("symbol", productoVisita.getPxvCurrencySymbol());
             q.setParameter("prod_cod", productoVisita.getProducto().getProCodigo());
             q.setParameter("vis_cod", productoVisita.getVisita().getVisCodigo());
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SP_SaveProductoVisita : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }

    @Override
    public boolean SP_DeleteProductoVisita(ProductoVisita productoVisita) {
             boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL SP_EliminarProductoVisita(:cod_prod,:cod_vis,:cod_pxv,:cantidad,:costo) }");
             q.setParameter("cod_prod", productoVisita.getProducto().getProCodigo());
             q.setParameter("cod_vis", productoVisita.getVisita().getVisCodigo());
             q.setParameter("cod_pxv", productoVisita.getPxvCodigo());
             q.setParameter("cantidad", productoVisita.getPxvCantidad());
             q.setParameter("costo", productoVisita.getPxvCostoParcial());
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SP_DeleteProductoVisita : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }

    @Override
    public boolean SP_CrearCabeceraProducto(int cod_cli, int prod_cod,  String nom_item, int cantidad, double costo,int cod_vis) {
          boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         String empresa= StaticUtil.userLogged();
         try {
             Query q = session.createSQLQuery("{ CALL SP_CrearCabeceraProducto(:codigo_cliente,:prod_cod,:nom_item,:cantidad,:costo,:cod_vis,:empresa) }");
             q.setParameter("codigo_cliente", cod_cli);
             q.setParameter("prod_cod",prod_cod);
             q.setParameter("nom_item", nom_item);
             q.setParameter("cantidad", cantidad);
             q.setParameter("costo", costo);
             q.setParameter("cod_vis", cod_vis);
             q.setParameter("empresa", empresa);
             System.out.println("COD CLI : "+cod_cli+"\nPROD_COD :"+prod_cod+"\nNOM_ITEM : "+nom_item+
                                "\nCANTIDAD :"+cantidad+"\nCOSTO : "+costo+"\nCOD_VIS : "+cod_vis+"\nEMPRESA : "+empresa);
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SP_CrearCabeceraProducto : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }

    @Override
    public List<detalle_factura> SP_ListarProductosF(int cod_cli) {
        System.out.println("Se ejecuta SP_ListarProductosF - ClienteSwrviceImpl");
           List<detalle_factura> lista= new ArrayList<>();
                Session session = HibernateUtil.getSessionFactory().openSession();
                 try {
         
             Query q = session.createSQLQuery("{ CALL SP_ListarProductosF(:cli_cod) }");
               q.setParameter("cli_cod",cod_cli);
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
				    int cod_det_factura = (int) result[0];
                                    String item=(String) result[1];
                                    double precio_unitario=(double) result[2];
                                    int cantidad = (int) result[3];
                                    double monto=(double) result[4];
                                    int cod_factura=(int) result[5];
                                    lista.add(new detalle_factura(cod_det_factura, item, precio_unitario,cantidad, monto, cod_factura));
			}
        } catch (Exception e) {
            System.out.println("Error SP_ListarProductosF : "+e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
          return lista;  
    }

    @Override
    public boolean SP_EliminarProductoFactura(int cod_cli) {
boolean resultado = false;
         Session session = HibernateUtil.getSessionFactory().openSession();
         try {
             Query q = session.createSQLQuery("{ CALL SP_EliminarProductoFactura(:cod_detfact) }");
             q.setParameter("cod_detfact", cod_cli);
             q.executeUpdate();
             resultado = true;
         }
         catch(Exception e)
         {
             System.out.println("ERROR de SP_EliminarProductoFactura : "+e.getMessage());
             resultado=false;
         }
         return resultado;
    }
}
