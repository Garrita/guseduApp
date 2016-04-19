/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.TerapiaService;
import com.gusedu.dao.TerapiaSintomaService;
import com.gusedu.dao.impl.TerapiaServiceImpl;
import com.gusedu.dao.impl.TerapiaSintomaServiceImpl;
import com.gusedu.entidad.ETerapia;
import com.gusedu.model.Cliente;
import com.gusedu.model.Par;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaPar;
import com.gusedu.model.TerapiaSintoma;
import com.gusedu.util.StaticUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class HistorialTerapiaBean 
{

	   
    
    private List<Terapia> listaterapia;

    TerapiaService terapiaService;
    

  
    
    private List<String> VALID_COLUMN_KEYS = Arrays.asList("par");
     
    private String columnTemplate = "par";
    private List<ColumnModel> columns;
     private List<Cabecera> headers;
    private List<ETerapia> lista;
    private int frozen;
    
    
 
    
    public HistorialTerapiaBean() 
    {
        frozen=0;
        terapiaService = new TerapiaServiceImpl();
    }

    public List<Terapia> getListaterapia() {
        return listaterapia;
    }

    public void setListaterapia(List<Terapia> listaterapia) {
        this.listaterapia = listaterapia;
    }



    

    
    public void llenadomatriz()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
		Cliente cli = (Cliente) fc.getExternalContext().getSessionMap()
				.get("cliente");
                System.out.println("Cod : "+cli.getCliCodigo());
        llenamatriz();
    }
    public void llenamatriz()
	{
            FacesContext fc = FacesContext.getCurrentInstance();
            Cliente cli = (Cliente) fc.getExternalContext().getSessionMap().get("cliente");
              cabecera();
            lista = terapiaService.SP_MatrizPares(cli.getCliCodigo(),listaterapia.size());
          
            createDynamicColumns();
	}
    public void cabecera()
    {
        frozen=0;
        FacesContext fc = FacesContext.getCurrentInstance();
            Cliente cli = (Cliente) fc.getExternalContext().getSessionMap().get("cliente");
        listaterapia= terapiaService.terapiasPorCliente(cli);
        String salida="par ";
        VALID_COLUMN_KEYS = new ArrayList<>();
        headers = new ArrayList<>();
        VALID_COLUMN_KEYS.add("par");
        headers.add(new Cabecera("par","PARES"));
        
        for(int i=1;i<=listaterapia.size();i++)
        {
            VALID_COLUMN_KEYS.add("estado"+i);
            headers.add(new Cabecera("estado"+i, StaticUtil.convertirDateToString(listaterapia.get(i-1).getTerFecRealizada())));
            
            if(i+1<=listaterapia.size())
            {
                salida+="estado"+i+" ";
            }else
            {
                salida+="estado"+i;frozen=1;
            }
        }
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
        private void createDynamicColumns() {
        String[] columnKeys = columnTemplate.split(" ");
        columns = new ArrayList<ColumnModel>();   
         
        for(String columnKey : columnKeys) {
            String key = columnKey.trim();
             
            if(VALID_COLUMN_KEYS.contains(key)) {
                columns.add(new ColumnModel( buscarHeader(columnKey), columnKey));
            }
        }
    }

 
    

    public List<String> getVALID_COLUMN_KEYS() {
        return VALID_COLUMN_KEYS;
    }

    public void setVALID_COLUMN_KEYS(List<String> VALID_COLUMN_KEYS) {
        this.VALID_COLUMN_KEYS = VALID_COLUMN_KEYS;
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

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public List<Cabecera> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Cabecera> headers) {
        this.headers = headers;
    }

    
    public List<ETerapia> getLista() {
        return lista;
    }

    public void setLista(List<ETerapia> lista) {
        this.lista = lista;
    }

    public int getFrozen() {
        return frozen;
    }

    public void setFrozen(int frozen) {
        this.frozen = frozen;
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
