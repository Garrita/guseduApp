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
import com.gusedu.model.Cliente;
import com.gusedu.model.Par;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaPar;
import com.gusedu.model.TerapiaSintoma;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    /**  Matriz de las terapias **/
    private List<String> rowNames = new ArrayList<String>();
    private List<String> colNames = new ArrayList<String>();
    private ArrayList<ArrayList<ArrayList<String>>> data3D = new ArrayList<ArrayList<ArrayList<String>>>();
	   
    
    private List<Terapia> listaterapia;
    private List<TerapiaPar> listaterapiapar;
    private List<Par> listapares;
    TerapiaService terapiaService;
    
    TerapiaSintomaService terapiasintomaService;
    private Terapia terapia;
    private TerapiaSintoma terapiasintoma;
    
    public HistorialTerapiaBean() 
    {
        terapiaService = new TerapiaServiceImpl();
        terapiasintomaService = new TerapiaSintomaServiceImpl();
        terapia = new Terapia();
        terapiasintoma = new TerapiaSintoma();  
    }

    public List<Terapia> getListaterapia() {
        return listaterapia;
    }

    public void setListaterapia(List<Terapia> listaterapia) {
        this.listaterapia = listaterapia;
    }

    public List<TerapiaPar> getListaterapiapar() {
        return listaterapiapar;
    }

    public void setListaterapiapar(List<TerapiaPar> listaterapiapar) {
        this.listaterapiapar = listaterapiapar;
    }

    public List<Par> getListapares() {
        return listapares;
    }

    public void setListapares(List<Par> listapares) {
        this.listapares = listapares;
    }

    
    public List<String> getRowNames() {
        return rowNames;
    }

    public void setRowNames(List<String> rowNames) {
        this.rowNames = rowNames;
    }

    public List<String> getColNames() {
        return colNames;
    }

    public void setColNames(List<String> colNames) {
        this.colNames = colNames;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getData3D() {
        return data3D;
    }

    public void setData3D(ArrayList<ArrayList<ArrayList<String>>> data3D) {
        this.data3D = data3D;
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
                      
		data3D = new ArrayList<ArrayList<ArrayList<String>>>();
		rowNames = new ArrayList<String>();
		colNames = new ArrayList<String>();
		FacesContext fc = FacesContext.getCurrentInstance();
		Cliente cli = (Cliente) fc.getExternalContext().getSessionMap()
				.get("cliente");
	
	 
		listaterapia = new ArrayList<>();
		listapares = new ArrayList<>();
		
		List<TerapiaPar> all = new ArrayList<>();
		all = terapiaService.getAllParbyCliente(cli); 
		
		 for(int i=0;i<all.size();i++)
	      {
	          if(!listaterapia.contains(all.get(i).getTerapia()))
	          {
	              listaterapia.add(all.get(i).getTerapia());
	          }
	      }
	     for(int j=0;j<all.size();j++)
	      {
	          if(!listapares.contains(all.get(j).getPar()))
	          {
	              listapares.add(all.get(j).getPar());
	          }
	      }
		
	     for (int j = 0; j < listapares.size(); j++) {
				rowNames.add(listapares.get(j).getPuntoByPunCodigoP1()
						.getPunNombre()
						+ "-"
						+ listapares.get(j).getPuntoByPunCodigoP2()
								.getPunNombre());
			}
	 
		System.out.println(listaterapia.size());
		for (int i = 0; i < listaterapia.size(); i++) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			colNames.add(format.format(listaterapia.get(i).getTerFecRealizada())
					+ "");
		}

		for (int i = 0; i < rowNames.size(); i++) {
			data3D.add(new ArrayList<ArrayList<String>>());
			for (int j = 0; j < colNames.size(); j++) {
				data3D.get(i).add(new ArrayList<String>());
			}
		}
	        
	      
            for(int i=0;i<listapares.size();i++)
            {
                for(int j=0;j<listaterapia.size();j++)
                {
                    for(int a=0;a<all.size();a++)
                    {
                        if(Objects.equals(listaterapia.get(j).getTerCodigo(), all.get(a).getTerapia().getTerCodigo()))
                        {                      
                            if(Objects.equals(listapares.get(i).getParCodigo(), all.get(a).getPar().getParCodigo()))
                            {
                                if(all.get(a).getTxpActivo())
                                {
                                    data3D.get(i).get(j).add("Si");
                                }else
                                {
                                    data3D.get(i).get(j).add("No");
                                }                   
                            }
                        }
                    }

                }
            }
	}
}
