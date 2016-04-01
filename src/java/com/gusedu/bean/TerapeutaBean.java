/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.ParService;
import com.gusedu.dao.VisitaService;
import com.gusedu.dao.impl.ParServiceImpl;
import com.gusedu.dao.impl.VisitaServiceImpl;
import com.gusedu.entidad.PacientePresencial;
import com.gusedu.entidad.ParX;
import com.gusedu.model.TerapiaSintoma;
import com.gusedu.util.StaticUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class TerapeutaBean {

   private PacientePresencial pacientePresencial;
   private List<PacientePresencial> lista;
   VisitaService visitaService;
   ParService parService;
   
   private List<ParX> listaParX;
   private ParX par;
   private boolean val;
    
    public TerapeutaBean() {
        visitaService= new VisitaServiceImpl();
        parService = new ParServiceImpl();
        pacientePresencial = new PacientePresencial();
        listarPacienteEnEspera();
        listarX();
        val=false;
        par = new ParX();
    }

    public PacientePresencial getPacientePresencial() {
        return pacientePresencial;
    }

    public void setPacientePresencial(PacientePresencial pacientePresencial) {
        this.pacientePresencial = pacientePresencial;
    }

    public boolean isVal() {
        return val;
    }

    public void setVal(boolean val) {
        this.val = val;
    }

    
    
    public ParX getPar() {
        return par;
    }

    public void setPar(ParX par) {
        this.par = par;
    }

    
    
    public List<PacientePresencial> getLista() {
        return lista;
    }

    public void setLista(List<PacientePresencial> lista) {
        this.lista = lista;
    }

    public List<ParX> getListaParX() {
        return listaParX;
    }

    public void setListaParX(List<ParX> listaParX) {
        this.listaParX = listaParX;
    }
    
    
    
    public void listarPacienteEnEspera()
    {
        lista = new ArrayList<>();
        
        lista = visitaService.SP_ListarPacienteEnEspera("");
       
     /*   if(lista.size()==0 || lista==null){
            
        }else
        {
            pacientePresencial=lista.get(0);
        }*/
 
    }
    
    public void listarX()
    {
        listaParX = new ArrayList<>();
        listaParX = parService.SP_LISTAR_PARES();
    }
   
    
    public String toShort(Date fecha)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(fecha);
    }
    public void PROBA()
    {
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getCod_vis() == pacientePresencial.getCod_vis())
            {
                pacientePresencial=lista.get(i);
                
                FacesContext fc = FacesContext.getCurrentInstance();
                TerapiaBean objetoTBean = (TerapiaBean)fc.getExternalContext().getSessionMap().get("terapiaBean");
                objetoTBean.LISTAR_PARES_POR_PACIENTE(pacientePresencial.getCod_ter());
            }
        }
    }
 
    public void increment() {
        listarPacienteEnEspera();
    }
    
    public List autoCompletar(String query) {
        List puntosFiltrados = new ArrayList();
        for (int i = 0; i < listaParX.size(); i++) {
            ParX par = (ParX)listaParX.get(i);
            if (StaticUtil.removeDiacriticalMarks(par.getNombre().toLowerCase()).startsWith(StaticUtil.removeDiacriticalMarks(query.toLowerCase()))) {
                puntosFiltrados.add(par);
                    }
                }
    return puntosFiltrados;
}
    public void INSERTAR_PAR()
    {
        if(pacientePresencial.getCod_ter()!=0 )
        {
                    System.out.println("Codigo de par : "+par.getCod_par());
        FacesContext fc = FacesContext.getCurrentInstance();
        TerapiaBean objetoTBean = (TerapiaBean)fc.getExternalContext().getSessionMap().get("terapiaBean");
        objetoTBean.addPar3SP_Terapeuta(pacientePresencial.getCod_ter(), par.getCod_par());
      
        }else
        {
             StaticUtil.errorMessage("Precaución", "Seleccione un paciente");
            StaticUtil.keepMessages();
        }
          par = new ParX();
        par.setNombre("");

    }
    
    public void ELIMINAR_PAR()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        TerapiaBean objetoTBean = (TerapiaBean)fc.getExternalContext().getSessionMap().get("terapiaBean");
        objetoTBean.LISTAR_PARES_POR_PACIENTE(pacientePresencial.getCod_ter());
        
    }
    
    public void PRUEBA(int val)
    {
        System.out.println("VAL : "+val);
    }
    
    public void TERMINAR_SESION()
    {

        visitaService.SP_CerrarSesion(pacientePresencial.getCod_vis());
        FacesContext fc = FacesContext.getCurrentInstance();
        TerapiaBean objetoTBean = (TerapiaBean)fc.getExternalContext().getSessionMap().get("terapiaBean");
        pacientePresencial = new PacientePresencial();
        listarPacienteEnEspera();
        objetoTBean.LIMPIAR_LISTA();
        val=false;
        
    }
}