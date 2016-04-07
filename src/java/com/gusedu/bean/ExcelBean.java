/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.PagoService;
import com.gusedu.dao.impl.PagoServiceImpl;
import com.gusedu.entidad.EPago;
import com.gusedu.entidad.Excel;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class ExcelBean {

    /**
     * Creates a new instance of ExcelBean
     */
    PagoService pagoService;
    
    private List<Excel> lista;
    private Date fec_ini;
    private Date fec_fin;
    private List<String> cabecera;
    private String nom;
   
     private     List<String> VALID_COLUMN_KEYS = Arrays.asList("fecha", "paciente", "total");
     
    private String columnTemplate = "fecha paciente total";
     
    private List<ColumnModel> columns;
    private List<Cabecera> headers;
    
    
    public ExcelBean() {
        pagoService= new PagoServiceImpl();
        fec_ini= new Date();
        fec_fin= new Date();
        cabecera = new ArrayList<>();
        
         SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
         nom="CAJA_"+sdf.format(new Date());
    }

    
    public void cabecera()
    {
        cabecera=pagoService.SP_CABECERA(fec_ini, fec_fin);
        String salida="fecha paciente ";
        VALID_COLUMN_KEYS = new ArrayList<>();
        headers = new ArrayList<>();
        VALID_COLUMN_KEYS.add("fecha");
        VALID_COLUMN_KEYS.add("paciente");
        headers.add(new Cabecera("fecha","fecha"));
        headers.add(new Cabecera("paciente","paciente"));
        DecimalFormat df =new DecimalFormat("0.00") ;
        for(int i=1;i<=cabecera.size();i++)
        {
            VALID_COLUMN_KEYS.add("monto"+i);
            headers.add(new Cabecera("monto"+i, cabecera.get(i-1)));
            
            if(i+1<cabecera.size())
            {
                salida+="monto"+i+" ";
            }else
            {
                salida+="monto"+i+ " total";
            }
        }
        VALID_COLUMN_KEYS.add("total");
        headers.add(new Cabecera("total","total"));
        columnTemplate=salida;
    }
    
    public String buscarHeader(String col)
    {
        String salida="";
         for (int i = 0; i < headers.size(); i++) {
                if(headers.get(i).getHeader().equals(col))
                {
                    salida=headers.get(i).getProperty();
                }
            }
        return salida;
    }
    
        public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
         
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
        HSSFCellStyle dollarStyle = wb.createCellStyle();
dollarStyle.setDataFormat((short)5);

HSSFCellStyle decStyle = wb.createCellStyle();
decStyle.setDataFormat((short)2);
        int ind=0;
        for(int j=0;j<lista.size()+1;j++) {
            header = sheet.getRow(j);
            for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
                 if(j==0){
                    HSSFCell cell = header.getCell(i);
                    cell.setCellStyle(cellStyle);
                }
                else{
                    if(i>=2){
                        HSSFCell cell = header.getCell(i);
                     //This is sortof a hack to counter PF exporting all data as text
        //We capture the existing value as string, convert to int, 
        //then format the cell to be numeric and reset the value to be int
        String strVal = cell.getStringCellValue();

        //this has to be done to temporarily blank out the cell value
        //because setting the type to numeric directly will cause 
        //an IllegalStateException because POI stupidly thinks
        //the cell is text because it was exported as such by PF...
        cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

        strVal = strVal.replace(",", StringUtils.EMPTY);

        if(strVal.indexOf('.') == -1) {
            //integer
            //numStyle.setDataFormat((short)1);
            int intVal = Integer.valueOf(strVal);

            cell.setCellStyle(cellStyle);
            cell.setCellValue(intVal);
        } else {
            //double
            if(strVal.startsWith("$")) {
                strVal = strVal.replace("$", StringUtils.EMPTY);
                //numStyle.setDataFormat((short)5);
                cell.setCellStyle(dollarStyle);
            } else {
                //numStyle.setDataFormat((short)2);
                cell.setCellStyle(decStyle);
            }

            double dblVal = Double.valueOf(strVal);
            cell.setCellValue(dblVal);
        }
            }
                    } 
                    
                    
                }
                

       
        }
    }
    
    public void today()
    {
        fec_ini= new Date();
        fec_fin= new Date();
        llenarLista();
    }
        
    public void llenarLista()         
    {
        lista=pagoService.SP_REPORTE(fec_ini, fec_fin);
        System.out.println("TAMAÑO : "+lista.size());
        cabecera();
        createDynamicColumns();
    /*    listaEpago = pagoService.SP_Excel_Caja();
        lista = new ArrayList<>();
        listaEpago= new ArrayList<>();
        for (int i = 0; i < listaEpago.size(); i++) {
           lista.add(new Excel(listaEpago.get(i).getVisita(), listaEpago.get(i).getPaciente(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 , 0, 0, 0, 0, 0, 0, 0));
 
        }*/
    }

    public List<String> getCabecera() {
        return cabecera;
    }

    public void setCabecera(List<String> cabecera) {
        this.cabecera = cabecera;
    }

 
    
    public List<Excel> getLista() {
        return lista;
    }

    public void setLista(List<Excel> lista) {
        this.lista = lista;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    
    public Date getFec_ini() {
        return fec_ini;
    }

    public void setFec_ini(Date fec_ini) {
        this.fec_ini = fec_ini;
    }

    public Date getFec_fin() {
        return fec_fin;
    }

    public void setFec_fin(Date fec_fin) {
        this.fec_fin = fec_fin;
    }
    
    public String getColumnTemplate() {
        return columnTemplate;
    }
 
    public void setColumnTemplate(String columnTemplate) {
        this.columnTemplate = columnTemplate;
    }
 
    public List<ColumnModel> getColumns() {
        return columns;
    }

    public List<Cabecera> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Cabecera> headers) {
        this.headers = headers;
    }
 
    
    
    private void createDynamicColumns() {
        String[] columnKeys = columnTemplate.split(" ");
        columns = new ArrayList<ColumnModel>();   
         
        for(String columnKey : columnKeys) {
            String key = columnKey.trim();
             
            if(VALID_COLUMN_KEYS.contains(key)) {
                columns.add(new ColumnModel(buscarHeader(columnKey), columnKey));
            }
        }
    }
    
    
        static public class ColumnModel implements Serializable {
 
        private String header;
        private String property;
 
        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }
 
        public String getHeader() {
            return header;
        }
 
        public String getProperty() {
            return property;
        }
    }
     static public class Cabecera implements Serializable {
 
        private String header;
        private String property;
 
        public Cabecera(String header, String property) {
            this.header = header;
            this.property = property;
        }
 
        public String getHeader() {
            return header;
        }
 
        public String getProperty() {
            return property;
        }
    }
    
}
