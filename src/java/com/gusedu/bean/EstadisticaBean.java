/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.entidad.Estadistica;
import com.gusedu.dao.impl.EstadisticaImpl;
import com.gusedu.dao.EstadisticaService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class EstadisticaBean implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * Creates a new instance of EstadisticaBean
     */
    
    private List<Estadistica> lista;
    private List<Estadistica> listaM;
    private List<Estadistica> listaD;
    
    private BarChartModel barra;
    private BarChartModel barraM;
    private LineChartModel lineModel2;
    
    EstadisticaService estadisticaservice; 
    
    public EstadisticaBean() {
        estadisticaservice= new EstadisticaImpl();
        createAnimatedModels();    
             createAnimatedModelsM();
             createLineModels();
    }
    
    
 
    	private void createAnimatedModels() {
        
         
        setBarra(initBarModel());
        getBarra().setTitle("Cantidad de Citas por Año");
        getBarra().setAnimate(true);
        getBarra().setLegendPosition("ne");
        getBarra().setBarMargin(80);
        //getBarra().setBarPadding(100);
    }
        
    private void createAnimatedModelsM() {
        setBarraM(initBarModelM());
        getBarraM().setTitle("Cantida de Citas por Meses");
        getBarraM().setAnimate(true);
        getBarraM().setLegendPosition("ne");
        //getBarraM().setBarMargin(50); 
       // getBarraM().setBarPadding(80);
    }
   private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries est = new ChartSeries();
        est.setLabel("Citas");
        lista=new ArrayList<>();
        lista.addAll(estadisticaservice.visitaAnual());
        System.out.println("Salida : "+lista.size());
        for(int i=0;i<lista.size();i++)
        {
        	est.set(lista.get(i).getFECHA(), lista.get(i).getCANTIDAD());
        	//est.set(x, y);
        }
        model.addSeries(est);
         
        return model;
    }
   
      private BarChartModel initBarModelM() {
        BarChartModel modelx = new BarChartModel();
 
        ChartSeries estx = new ChartSeries();
        estx.setLabel("Citas");
        listaM=new ArrayList<>();
        listaM.addAll(estadisticaservice.visitaMeses());
        System.out.println("SalidaM : "+listaM.size()+"|"+estadisticaservice.visitaMeses().size());
        for(int i=0;i<listaM.size();i++)
        {
        	estx.set(listaM.get(i).getFECHA(), listaM.get(i).getCANTIDAD());
        	//est.set(x, y);
        }
        modelx.addSeries(estx);
         
        return modelx;
    }
   
    public List<Estadistica> getLista() {
 
		return lista;
	}

	public void setLista(List<Estadistica> lista) {
		this.lista = lista;
	}

    public List<Estadistica> getListaM() {
        return listaM;
    }

    public void setListaM(List<Estadistica> listaM) {
        this.listaM = listaM;
    }
        
        

    public BarChartModel getBarra() {
        return barra;
    }

    public void setBarra(BarChartModel barra) {
        this.barra = barra;
    }

    public BarChartModel getBarraM() {
        return barraM;
    }

    public void setBarraM(BarChartModel barraM) {
        this.barraM = barraM;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }
    
     private void createLineModels() {
          Axis yAxis;
        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Cantidad de Citas por Día");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(true);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Días"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(60);
    }
     
      private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Citas");
        listaD=new ArrayList<>();
        listaD.addAll(estadisticaservice.visitaMesActual());
          for (int i = 0; i < listaD.size(); i++) {
              boys.set(listaD.get(i).getFECHA(), 
                       listaD.get(i).getCANTIDAD());
          }
        model.addSeries(boys);

         
        return model;
    }

    public List<Estadistica> getListaD() {
        return listaD;
    }

    public void setListaD(List<Estadistica> listaD) {
        this.listaD = listaD;
    }
      
      
}
