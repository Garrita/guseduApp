package com.gusedu.estadistica;

import com.gusedu.entidad.ECajaResumen;
import com.gusedu.entidad.ECajaResumen1;
import com.gusedu.entidad.ECajaResumenMensual;
import com.gusedu.entidad.ECajaResumenMensual1;
import com.gusedu.util.HibernateUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 

import com.gusedu.util.StaticUtil;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

 
public class ReporteImpl implements ReporteService{

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
 
        @Override
	public List<Reporte> listarTerapiaByterapeutas(Date fechitai, Date fechitaf) 
        {
            System.out.println("Se ejecuta listarTerapiaByterapeutas - ReporteImpl");
            List<Reporte> lista= new ArrayList<>();
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
             //tx = sesion.beginTransaction();
            String empresa = StaticUtil.userLogged();
            Query q = session.createSQLQuery("{ CALL Reporte_TerapiasbyTerapeutas(:empresa, :fechitai, :fechitaf) }");
            q.setParameter("empresa",empresa);
            q.setParameter("fechitai", fechitai);
            q.setParameter("fechitaf", fechitaf);

            List<Object[]> d=q.list();
            for (Object[] result : d) {

                    String CANTIDAD = ((BigInteger) result[0]).toString();
                    String NOMBRE = (String) result[1];
                    double COSTO = (double) result[2];
                    lista.add(new Reporte(CANTIDAD, NOMBRE, COSTO));
            }
            } catch (Exception e) {
                /*if (tx != null) {
                    tx.rollback();
                }   */
                System.out.println(e.getMessage());
            } finally {
                session.flush();
                session.close();
            }
                
                /* try {
                     String empresa = StaticUtil.userLogged();
             Query q = session.createSQLQuery("{ CALL Reporte_TerapiasbyTerapeutas(:empresa) }");
                    q.setParameter("empresa",empresa);
			
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
				String CANTIDAD = ((BigInteger) result[0]).toString();
				String NOMBRE = (String) result[1];
				double COSTO = (double) result[2];
				lista.add(new Reporte(CANTIDAD, NOMBRE, COSTO));
			}

                }
                
                
		 
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}*/
		
		return lista;
	}

        @Override
        public List<ReporteClientes> listarClientesByterapeutas(String Terapeuta,Date fechitai, Date fechitaf)
        {
            System.out.println("Se ejecuta listarClientesByterapeutas - ReporteImpl");
            List<ReporteClientes> lista= new ArrayList<>();
            Session session = HibernateUtil.getSessionFactory().openSession();
             try {
            String empresa = StaticUtil.userLogged();
            Query q = session.createSQLQuery("{ CALL Reporte_ClientesbyTerapeuta(:empresa, :Terapeuta, :fechitai, :fechitaf) }");
                q.setParameter("Terapeuta",Terapeuta);
                q.setParameter("empresa",empresa);
                q.setParameter("fechitai", fechitai);
                q.setParameter("fechitaf", fechitaf);
                
                List<Object[]> d=q.list();
                for (Object[] result : d) 
                {
                    String Cliente = (String) result[0];
                    String TipoTer = (String) result[1];
                    double Monto = (double) result[2];
                    System.out.println("Cliente: "+result[0] + "\nMonto: " + result[1] + "\nTipo Terapia: " + result[2]);
                    lista.add(new ReporteClientes(Cliente,TipoTer,Monto));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                session.flush();
                session.close();
            }
		return lista;
        }
        
        @Override
        public List<ReporteClientesXProd> listarProductosXCliente(String Product,Date fechitai, Date fechitaf)
        {
            System.out.println("Se ejecuta listarProductosXCliente - ReporteImpl");
            List<ReporteClientesXProd> lista= new ArrayList<>();
            Session session = HibernateUtil.getSessionFactory().openSession();
             try {
            String empresa = StaticUtil.userLogged();
            Query q = session.createSQLQuery("{ CALL Reporte_ProductosbyCliente(:empresa, :producto, :fechitai, :fechitaf) }");
                q.setParameter("empresa",empresa);
                q.setParameter("producto",Product);
                q.setParameter("fechitai", fechitai);
                q.setParameter("fechitaf", fechitaf);
                
                List<Object[]> d=q.list();
                for (Object[] result : d) 
                {
                    String Cliente = (String) result[0];
                    String TipoTer = (String) result[1];
                    double precioU = (double) result[2];
                    int cant = (int) result[3];
                    double Monto = (double) result[4];
                    lista.add(new ReporteClientesXProd(Cliente,TipoTer,precioU,cant,Monto));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                session.flush();
                session.close();
            }
		return lista;
        }
        
	@Override
	public Reporte AcumuladoTerapias() {
		Reporte rep =  new Reporte();
                
                
                 Session session = HibernateUtil.getSessionFactory().openSession();
                 
                  try {
             //tx = sesion.beginTransaction();
           String empresa = StaticUtil.userLogged();
             Query q = session.createSQLQuery("{ CALL Reporte_Acumulado_Terapias(:empresa) }");
                    q.setParameter("empresa",empresa);
			
			Object[] result=  (Object[]) q.uniqueResult()   ;
			rep.setCantidad(((BigInteger) result[0]).toString());
			rep.setNombre(result[1].toString());
			rep.setCosto((double) result[2]);
        } catch (Exception e) {
            /*if (tx != null) {
                tx.rollback();
            }   */
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
                 
               /*  try {
                     String empresa = StaticUtil.userLogged();
             Query q = session.createSQLQuery("{ CALL Reporte_Acumulado_Terapias(:empresa) }");
                    q.setParameter("empresa",empresa);
			
			Object[] result=  (Object[]) q.uniqueResult()   ;
			rep.setCantidad(((BigInteger) result[0]).toString());
			rep.setNombre(result[1].toString());
			rep.setCosto((double) result[2]);

                }
                
	 
		catch(Exception e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}*/
		
		return rep;
	}

	@Override
	public List<Reporte> listarProductos(Date fechitai, Date fechitaf) {
            System.out.println("Se ejecuta listarProductos - ReporteImpl");
		List<Reporte> lista= new ArrayList<>();
                
                Session session = HibernateUtil.getSessionFactory().openSession();
                
                 try {
             //tx = sesion.beginTransaction();
          String empresa = StaticUtil.userLogged();
             Query q = session.createSQLQuery("{ CALL Reporte_Cant_Prod(:empresa, :fechitai, :fechitaf) }");
                    q.setParameter("empresa",empresa);
                    q.setParameter("fechitai", fechitai);
                    q.setParameter("fechitaf", fechitaf);
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
				double cant = (double) result[0] ;
				int a=(int)cant;
				String CANTIDAD = a+"";
				String NOMBRE = (String) result[1];
				String val=((double) result[2])+"";
				double COSTO = Double.parseDouble(val);
				lista.add(new Reporte(CANTIDAD, NOMBRE, COSTO));
			}
        } catch (Exception e) {
            /*if (tx != null) {
                tx.rollback();
            }   */
            System.out.println(e.getMessage());
        } finally {
            session.flush();
            session.close();
        }
                
            /*     try {
                     String empresa = StaticUtil.userLogged();
             Query q = session.createSQLQuery("{ CALL Reporte_Cant_Prod(:empresa) }");
                    q.setParameter("empresa",empresa);
			
			List<Object[]> d=q.list();
			for (Object[] result : d) {
				
				double cant = (double) result[0] ;
				int a=(int)cant;
				String CANTIDAD = a+"";
				String NOMBRE = (String) result[1];
				String val=((double) result[2])+"";
				double COSTO = Double.parseDouble(val);
				lista.add(new Reporte(CANTIDAD, NOMBRE, COSTO));
			}

                }
                 catch(Exception e)
                 {
                     
                 }
 */
		
		return lista;
	}

        @Override
    public List<ECajaResumen> MostrarCajaResumen(Date fechitai, Date fechitaf)
    {
        System.out.println("Se ejecuta MostrarCajaResumen - ReporteServiceImpl");
        System.out.println("Fecha inicial: " + fechitai);
        System.out.println("Fecha final: " + fechitaf);
        List<ECajaResumen> resultado = new ArrayList<>();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String empr = StaticUtil.userLogged();
            Query q = sesion.createSQLQuery("{ CALL Reporte_CajaDiarioResumen(:empr, :fechitai, :fechitaf)}");
            q.setParameter("empr", empr);
            q.setParameter("fechitai", fechitai);
            q.setParameter("fechitaf", fechitaf);
            List<Object[]> d=q.list();
            for (Object[] result : d) 
            {
                Date fecha = (Date) result[0];
                double monto = (double) result[1];
                resultado.add(new ECajaResumen(fecha,monto));		 
            }
        } 
        catch(Exception e)
        {
            System.out.println("ERROR de Mostrar Caja Resumen : "+e.getMessage());
        }
        return resultado;
        }
    
        @Override
    public List<ECajaResumen1> MostrarCajaDetalle(Date fechitai, Date fechitaf)
    {
        System.out.println("Se ejecuta MostrarCajaResumen1 - ReporteServiceImpl");
        System.out.println("Fecha inicial: " + fechitai);
        System.out.println("Fecha final: " + fechitaf);
        List<ECajaResumen1> resultado = new ArrayList<>();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String empr = StaticUtil.userLogged();
            Query q = sesion.createSQLQuery("{ CALL Reporte_CajaDiarioDetalle(:empr, :fechitai, :fechitaf)}");
            q.setParameter("empr", empr);
            q.setParameter("fechitai", fechitai);
            q.setParameter("fechitaf", fechitaf);
            List<Object[]> d=q.list();
            for (Object[] result : d) 
            {
                int cod_factura = (int) result[0];
                String cliente = (String) result[1];
                double monto = (double) result[2];
                Date fecha = (Date) result[3];
                String factura_real = (String) result[4];
                int cod_cliente = (int) result[5];
                int estado = (int) result[6];
                int cod_visita = (int) result[7];
                String empresa = (String) result[8];
                resultado.add(new ECajaResumen1(cod_factura,cliente,monto,fecha,factura_real,cod_cliente,estado,cod_visita,empresa));		 
            }
        } 
        catch(Exception e)
        {
            System.out.println("ERROR de Mostrar Caja Resumen Detalle : "+e.getMessage());
        }
        return resultado;
        }
    
        @Override
     public List<ECajaResumenMensual> MostrarCajaResumenMensual(String mes,String año)
    {
        System.out.println("Se ejecuta MostrarCajaResumenMensual - ReporteServiceImpl");
        List<ECajaResumenMensual> resultado = new ArrayList<>();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            String empr = StaticUtil.userLogged();
            Query q = sesion.createSQLQuery("{ CALL Reporte_CajaMensualResumen(:empr, :mes, :año)}");
            q.setParameter("empr", empr);
            q.setParameter("mes", mes);
            q.setParameter("año", año);
            List<Object[]> d=q.list();
            for (Object[] result : d) 
            {
                Date fecha = (Date) result[0];
                double monto = (double) result[1];
                resultado.add(new ECajaResumenMensual(fecha,monto));		 
            }
        } 
        catch(Exception e)
        {
            System.out.println("ERROR de Mostrar Caja Resumen : "+e.getMessage());
        }
        return resultado;
    }
     
        @Override
     public List<ECajaResumenMensual1> MostrarCajaMensualDetalle(String mes,String año)
    {
        System.out.println("Se ejecuta MostrarCajaMensualDetalle - ReporteServiceImpl");
        List<ECajaResumenMensual1> resultado = new ArrayList<>();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = sesion.beginTransaction();
            String empr = StaticUtil.userLogged();
            System.out.println("EMPRESA : "+empr+"\nMES : "+mes+"\nAÑO : "+año);
            Query q = sesion.createSQLQuery("{ CALL Reporte_CajaMensualDetalle(:empr, :mes, :año)}");
            q.setParameter("empr", empr);
            q.setParameter("mes", mes);
            q.setParameter("año", año);
            List<Object[]> d=q.list();
            for (Object[] result : d) 
            {
                int cod_factura = (int) result[0];
                String cliente = (String) result[1];
                double monto = (double) result[2];
                Date fecha = (Date) result[3];
                String factura_real = (String) result[4];
                int cod_cliente = (int) result[5];
                int estado = (int) result[6];
                int cod_visita = (int) result[7];
                String empresa = (String) result[8];
                resultado.add(new ECajaResumenMensual1(cod_factura,cliente,monto,fecha,factura_real,cod_cliente,estado,cod_visita,empresa));		 
            }
        } 
        catch(Exception e)
        {
            System.out.println("ERROR de Mostrar Caja Mensual Detalle : "+e.getMessage());
        }
        return resultado;
        }
}
