/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.CursoService;
import com.gusedu.dao.impl.CursoServiceImpl;
import com.gusedu.entidad.EInformacionCurso;
import com.gusedu.entidad.EParametro;
import com.gusedu.model.Persona;
import com.gusedu.util.StaticUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jboss.logging.Logger;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class CursoBean {

    private Persona persona;
    private String telfijocodpais;
    private String telfijo;
    private String telmovilcodpais;
    private String telmovil;
    private EParametro datos;
    private List<String> listar;
    private CursoService metodo;
    private String [] variable;
    private EInformacionCurso infcurso;
    
    public CursoBean() 
    {
        persona = new Persona();
        infcurso = new EInformacionCurso();
        datos = new EParametro();
        metodo = new CursoServiceImpl();
        variable = new String[100];
        inicializar();
    }

    public void inicializar()
    {
        datos = new EParametro();
        listarParametros();
    }
    
    public void listarParametros()
    {
        int count = 0;
        listar = metodo.listarParametros();
        for(int i=0;i<listar.size();i++)
        {
            variable[count] = listar.get(i);
            count++;
        }
    }

    public EInformacionCurso getInfcurso() {
        return infcurso;
    }

    public void setInfcurso(EInformacionCurso infcurso) {
        this.infcurso = infcurso;
    }
    
    public EParametro getDatos() {
        return datos;
    }

    public void setDatos(EParametro datos) {
        this.datos = datos;
    }
    
    public List<String> getListar() {
        return listar;
    }

    public void setListar(List<String> listar) {
        this.listar = listar;
    }
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getTelfijocodpais() {
        return telfijocodpais;
    }

    public void setTelfijocodpais(String telfijocodpais) {
        this.telfijocodpais = telfijocodpais;
    }

    public String getTelfijo() {
        return telfijo;
    }

    public void setTelfijo(String telfijo) {
        this.telfijo = telfijo;
    }

    public String getTelmovilcodpais() {
        return telmovilcodpais;
    }

    public void setTelmovilcodpais(String telmovilcodpais) {
        this.telmovilcodpais = telmovilcodpais;
    }

    public String getTelmovil() {
        return telmovil;
    }

    public void setTelmovil(String telmovil) {
        this.telmovil = telmovil;
    }

    public String[] getVariable() {
        return variable;
    }

    public void setVariable(String[] variable) {
        this.variable = variable;
    }
    
    public void AGREGAR()
    {
        System.out.println("Ingresando registro de curso...");    
        persona.setPerTelFijo("("+telfijocodpais+")"+telfijo);
        persona.setPerTelMovil("("+telmovilcodpais+")"+telmovil);
        metodo.registroAlumno(persona);
        datos = new EParametro();
        
        infcurso.setPer_codigo(persona.getPerCodigo());
        metodo.registroInformacionCurso(infcurso);
        listarParametros();
    }
    
    public void Reporte_HojaRegistro()
    {
        try{
            Object [] opciones = {"Aceptar","Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(null,
                    "Ver Hoja de Registro de Curso, desea continuar?","Mensaje de Confirmación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
            
            if(eleccion == JOptionPane.YES_OPTION)
            {
                String master = System.getProperty("user.dir") + "/Reporte/PruebaRegistroCurso.jasper";
                HashMap parametros = new HashMap();
                System.out.println("Iniciando Parametros");
                
                parametros.put("inicioCurso",metodo.listarParametros().get(0));
                System.out.println("va : "+metodo.listarParametros().get(0));
                
                parametros.put("apelidoPaterno",persona.getPerApellidoP());
                System.out.println("va : " + persona.getPerApellidoP());
                
                parametros.put("apellidoMaterno",persona.getPerApellidoM());
                System.out.println("va : " + persona.getPerApellidoM());
                
                parametros.put("nombres",persona.getPerNombres());
                parametros.put("genero",persona.getPerSexo());
                parametros.put("fechanac", persona.getPerFecNacimiento());
                parametros.put("dni",persona.getPerDni());
                parametros.put("direccion",persona.getPerDireccion());
                parametros.put("correo",persona.getPerCorreo());
                parametros.put("telfijo",persona.getPerTelFijo());
                parametros.put("telmovil",persona.getPerTelMovil());
                parametros.put("formacad",infcurso.getInf_formacad());
                parametros.put("tallercurs",infcurso.getInf_tallercurs());
                parametros.put("otrotaller",infcurso.getInf_otrotall());
                parametros.put("enterogusedu",persona.getPerEnteroGusedu());
                parametros.put("cuentainter",metodo.listarParametros().get(1));
                parametros.put("cuenta",metodo.listarParametros().get(2));
                parametros.put("anombre",metodo.listarParametros().get(3));
                parametros.put("ruc",metodo.listarParametros().get(4));            
                parametros.put("nombredepos",infcurso.getInf_nombredepo());
                parametros.put("fechahora",infcurso.getInf_fechahoradepo());
                parametros.put("enviocorreo",infcurso.getInf_enviocorreo());
                parametros.put("nrooperac",infcurso.getInf_numerooperac());
                System.out.println("Finalizando Parametros");
                JasperPrint informe = JasperFillManager.fillReport(master,parametros, new JREmptyDataSource());
                JasperViewer.viewReport(informe, false);
            }
            else{
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error General Imprimiendo Reporte " + e.getMessage());
        }
    }
    
    InputStream inputstream = null;
    JasperPrint jasperprint = null;
    
    public void Reporte_HojaRegistro1()
    {
        try{
            inputstream = new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\appGusedu\\web\\Reporte\\PruebaRegistroCurso.jrxml");
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Error al leer el fichero de cargar jasper report: " + ex.getMessage());            
        }
        
        try{
                //JasperDesign jasperDesign = JRXmlLoader.load(inputstream);
                JasperReport jasperReport = JasperCompileManager.compileReport(inputstream);
                HashMap parametros = new HashMap();
                System.out.println("Iniciando Parametros");

                parametros.put("inicioCurso",metodo.listarParametros().get(0));
                System.out.println("Inicio Curso : "+metodo.listarParametros().get(0));

                parametros.put("apellidoPaterno",persona.getPerApellidoP());
                System.out.println("Apellido Paterno : " + persona.getPerApellidoP());

                parametros.put("apellidoMaterno",persona.getPerApellidoM());
                System.out.println("Apellido Materno : " + persona.getPerApellidoM());

                parametros.put("nombres",persona.getPerNombres());
                System.out.println("Nombres : " + persona.getPerNombres());

                parametros.put("genero",persona.getPerSexo());
                System.out.println("Género : " + persona.getPerSexo());

                parametros.put("dni",persona.getPerDni());
                parametros.put("direccion",persona.getPerDireccion());
                parametros.put("correo",persona.getPerCorreo());
                parametros.put("telfijo",persona.getPerTelFijo());
                parametros.put("telmovil",persona.getPerTelMovil());
                parametros.put("formacad",infcurso.getInf_formacad());
                parametros.put("tallercurs",infcurso.getInf_tallercurs());
                parametros.put("otrotaller",infcurso.getInf_otrotall());
                parametros.put("enterogusedu",persona.getPerEnteroGusedu());
                parametros.put("cuentainter",metodo.listarParametros().get(1));
                parametros.put("cuenta",metodo.listarParametros().get(2));
                parametros.put("anombre",metodo.listarParametros().get(3));
                parametros.put("ruc",metodo.listarParametros().get(4));            
                parametros.put("nombredepos",infcurso.getInf_nombredepo());
                parametros.put("fechahora",infcurso.getInf_fechahoradepo());
                parametros.put("enviocorreo",infcurso.getInf_enviocorreo());
                parametros.put("nrooperac",infcurso.getInf_numerooperac());
                parametros.put("fechanac", StaticUtil.convertirDateToString(persona.getPerFecNacimiento()));
                System.out.println("Finalizando Parametros");

            jasperprint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
            
            JasperExportManager.exportReportToPdfFile(jasperprint, "C:\\Users\\user\\Desktop\\HojaRegistroCurso.pdf"); 
//            JasperViewer.viewReport(jasperprint, false);
//            JasperViewer view = new JasperViewer(jasperprint);
//            view.setTitle("Hoja Registro Curso");
//            view.setVisible(true);
//
//            JRExporter exporter = new JRPdfExporter();
//
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperprint);
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
//            exporter.exportReport();
        }
        catch (JRException e)
        {
            JOptionPane.showMessageDialog(null,"Error al cargar fichero jrml jasper report " + e.getMessage());
        }
    }
    
    public void Reporte_HojaRegistro2()
    {
        try{
            Object [] opciones = {"Aceptar","Cancelar"};
            int eleccion = JOptionPane.showOptionDialog(null,
                    "Ver Hoja de Registro de Curso, desea continuar?","Mensaje de Confirmación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
            
            if(eleccion == JOptionPane.YES_OPTION)
            {
                JasperReport reporte = null;
                try
                {
                    reporte = (JasperReport)JRLoader.loadObject("PruebaRegistroCurso.jrxml");
                }
                catch(JRException e){
                    System.out.println("Error cargando el reporte maestro: " + e.getMessage());
                }

                HashMap parametros = new HashMap();
                    System.out.println("Iniciando Parametros");

                    parametros.put("inicioCurso",metodo.listarParametros().get(0));
                    System.out.println("Inicio Curso : "+metodo.listarParametros().get(0));

                    parametros.put("apelidoPaterno",persona.getPerApellidoP());
                    System.out.println("Apellido Paterno : " + persona.getPerApellidoP());

                    parametros.put("apellidoMaterno",persona.getPerApellidoM());
                    System.out.println("Apellido Materno : " + persona.getPerApellidoM());

                    parametros.put("nombres",persona.getPerNombres());
                    System.out.println("Nombres : " + persona.getPerNombres());

                    parametros.put("genero",persona.getPerSexo());
                    System.out.println("Género : " + persona.getPerSexo());

                    parametros.put("fechanac", persona.getPerFecNacimiento());
                    parametros.put("dni",persona.getPerDni());
                    parametros.put("direccion",persona.getPerDireccion());
                    parametros.put("correo",persona.getPerCorreo());
                    parametros.put("telfijo",persona.getPerTelFijo());
                    parametros.put("telmovil",persona.getPerTelMovil());
                    parametros.put("formacad",infcurso.getInf_formacad());
                    parametros.put("tallercurs",infcurso.getInf_tallercurs());
                    parametros.put("otrotaller",infcurso.getInf_otrotall());
                    parametros.put("enterogusedu",persona.getPerEnteroGusedu());
                    parametros.put("cuentainter",metodo.listarParametros().get(1));
                    parametros.put("cuenta",metodo.listarParametros().get(2));
                    parametros.put("anombre",metodo.listarParametros().get(3));
                    parametros.put("ruc",metodo.listarParametros().get(4));            
                    parametros.put("nombredepos",infcurso.getInf_nombredepo());
                    parametros.put("fechahora",infcurso.getInf_fechahoradepo());
                    parametros.put("enviocorreo",infcurso.getInf_enviocorreo());
                    parametros.put("nrooperac",infcurso.getInf_numerooperac());
                    System.out.println("Finalizando Parametros");

                 JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,parametros, new JREmptyDataSource());   
                 JasperViewer view = new JasperViewer(jasperPrint);
                 view.setTitle("Hoja Registro Curso");
                 view.setVisible(true);
                 
                JRExporter exporter = new JRPdfExporter();
        
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
                exporter.exportReport();
            }
            else{
            }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error al cargar fichero jrml jasper report " + e.getMessage());
        }
    }
    
    public void Reporte_HojaRegistro3()
    {
        try{
            String master = System.getProperty("user.dir") + "/Reporte/PruebaRegistroCurso.jasper";
            if(master == null)
            {
                JOptionPane.showMessageDialog(null,"No se encuentra el archivo maestro","ERROR REPORTE",JOptionPane.ERROR_MESSAGE);
                return;
            }
            JasperReport masterReport = null;
            try{
                masterReport = JasperCompileManager.compileReport(master);
            }
            catch(Exception jre)
            {
                JOptionPane.showMessageDialog(null,"Problema al cargar el archivo maestro","EROR REPORTE",JOptionPane.ERROR_MESSAGE);
                System.out.println("Error: " + jre);
                return;
            }
                HashMap parametros = new HashMap();
                    System.out.println("Iniciando Parametros");

                    parametros.put("inicioCurso",metodo.listarParametros().get(0));
                    System.out.println("Inicio Curso : "+metodo.listarParametros().get(0));

                    parametros.put("apelidoPaterno",persona.getPerApellidoP());
                    System.out.println("Apellido Paterno : " + persona.getPerApellidoP());

                    parametros.put("apellidoMaterno",persona.getPerApellidoM());
                    System.out.println("Apellido Materno : " + persona.getPerApellidoM());

                    parametros.put("nombres",persona.getPerNombres());
                    System.out.println("Nombres : " + persona.getPerNombres());

                    parametros.put("genero",persona.getPerSexo());
                    System.out.println("Género : " + persona.getPerSexo());

                    parametros.put("fechanac", persona.getPerFecNacimiento());
                    parametros.put("dni",persona.getPerDni());
                    parametros.put("direccion",persona.getPerDireccion());
                    parametros.put("correo",persona.getPerCorreo());
                    parametros.put("telfijo",persona.getPerTelFijo());
                    parametros.put("telmovil",persona.getPerTelMovil());
                    parametros.put("formacad",infcurso.getInf_formacad());
                    parametros.put("tallercurs",infcurso.getInf_tallercurs());
                    parametros.put("otrotaller",infcurso.getInf_otrotall());
                    parametros.put("enterogusedu",persona.getPerEnteroGusedu());
                    parametros.put("cuentainter",metodo.listarParametros().get(1));
                    parametros.put("cuenta",metodo.listarParametros().get(2));
                    parametros.put("anombre",metodo.listarParametros().get(3));
                    parametros.put("ruc",metodo.listarParametros().get(4));            
                    parametros.put("nombredepos",infcurso.getInf_nombredepo());
                    parametros.put("fechahora",infcurso.getInf_fechahoradepo());
                    parametros.put("enviocorreo",infcurso.getInf_enviocorreo());
                    parametros.put("nrooperac",infcurso.getInf_numerooperac());
                    System.out.println("Finalizando Parametros");

                 JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport,parametros, new JREmptyDataSource());   
//                 JasperViewer view = new JasperViewer(jasperPrint,true);
//                 
//                JRExporter exporter = new JRPdfExporter();
//        
//                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportePDF.pdf"));
//                exporter.exportReport();
                
                JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\user\\Desktop\\HojaRegistroCurso.pdf"); 
                
        }
        catch(Exception j)
        {
            JOptionPane.showMessageDialog(null,"Problemas al generar el reporte. \n Detalles: " + j);
        }
    }
    
    public void IMPRIMIRREPORTE()
    {
        Reporte_HojaRegistro1();
        datos = new EParametro();
        infcurso = new EInformacionCurso();
        persona = new Persona();
        listarParametros();
    }
    
    public void validaEdad() 
    {
        int e = persona.getPerFecNacimiento().getYear() + 1900;
        Date fec = new Date();
        int anio = fec.getYear() + 1900;
        int ed = anio - e;
        persona.setPerEdad(ed);
        System.out.println("Edad : " + ed);
    }
}
