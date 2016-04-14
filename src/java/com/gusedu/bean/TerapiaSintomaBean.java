/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.SintomaService;
import com.gusedu.dao.TerapiaService;
import com.gusedu.dao.TerapiaSintomaService;
import com.gusedu.dao.impl.SintomaServiceImpl;
import com.gusedu.dao.impl.TerapiaServiceImpl;
import com.gusedu.dao.impl.TerapiaSintomaServiceImpl;
import com.gusedu.entidad.TerapiaSintomaX;
import com.gusedu.model.Cliente;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaVisita;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaPar;
import com.gusedu.model.TerapiaSintoma;
import com.gusedu.model.TipoTerapia;
import com.gusedu.model.Visita;
import com.gusedu.util.StaticUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class TerapiaSintomaBean {

    private Sintoma sintoma;
    private TerapiaSintoma datos;
    public List<TerapiaSintoma> listar;
    public TerapiaService terapiaservice;
    TerapiaSintomaService terapiasintomaService;
    private TerapiaSintoma terapiasintoma;
    SintomaService sintomaService;
    private String queryS;
    private Terapia terapia;
    private boolean disable;
    private String comentario;
    private Visita visita;
    private Cliente cliente;
    
    private List<TerapiaSintoma> listaterapiasintoma;
    private List<TerapiaPar> listaterapiapar;
    private List<TerapiaSintomaX> listaterapiasintx;
    
    public String toShort(Date fecha)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha);
    }
    
    public TerapiaSintomaBean() 
    {
        visita = new Visita();
        cliente = new Cliente();
        terapiaservice = new TerapiaServiceImpl();
        sintomaService = new SintomaServiceImpl();
        terapiasintomaService = new TerapiaSintomaServiceImpl();
        terapiasintoma = new TerapiaSintoma();
        sintoma = new Sintoma();
        queryS = "";
        terapia = new Terapia();
        terapia.setTipoTerapia(new TipoTerapia());
        terapia.setVisita(new Visita());
        inicializar();
//        disable = true;
        disable = true;
    }

    
    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
    
    public void inicializar()
    {
        datos = new TerapiaSintoma();
        datos.setTerapia(new Terapia());
        datos.setSintoma(new Sintoma());
    }

    public TerapiaSintoma getDatos() {
        return datos;
    }

    public void setDatos(TerapiaSintoma datos) {
        this.datos = datos;
    }

    public List<TerapiaSintoma> getListar() {
        return listar;
    }

    public void setListar(List<TerapiaSintoma> listar) {
        this.listar = listar;
    }

    public void listarSintoma()
    {
         listar = terapiasintomaService.getAllSintoma();
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }

    public List<TerapiaPar> getListaterapiapar() {
        return listaterapiapar;
    }

    public void setListaterapiapar(List<TerapiaPar> listaterapiapar) {
        this.listaterapiapar = listaterapiapar;
    }

    public List<TerapiaSintomaX> getListaterapiasintx() {
        return listaterapiasintx;
    }

    public void setListaterapiasintx(List<TerapiaSintomaX> listaterapiasintx) {
        this.listaterapiasintx = listaterapiasintx;
    }

    
    public void AGREGAR()
    {
        System.out.println("Ingresando terapia sintoma...");
        System.out.println("Terapia: "+ datos.getTerapia().getTerCodigo());
        System.out.println("Sintoma: "+ datos.getSintoma().getSinCodigo());
        terapiasintomaService.saveTerapiaSintoma(datos);      
        datos = new TerapiaSintoma();
        datos.setTerapia(new Terapia());
        datos.setSintoma(new Sintoma());
        listarSintoma();
    }
    
    public void ACTUALIZAR()
    {
        System.out.println("Actualizando terapia sintoma...");
        terapiasintomaService.updateTerapiaSintoma(datos);
        datos = new TerapiaSintoma();
        datos.setTerapia(new Terapia());
        datos.setSintoma(new Sintoma());
        listarSintoma();
        llenarLISTITA(terapia.getTerCodigo());
         FacesContext fc = FacesContext.getCurrentInstance();
        TerapeutaBean objetoTBean = (TerapeutaBean)fc.getExternalContext().getSessionMap().get("terapeutaBean");
        objetoTBean.PASARBEAN();   
        SintomaTerapiaBean objetoSTBean = (SintomaTerapiaBean)fc.getExternalContext().getSessionMap().get("sintomaTerapiaBean");
        objetoSTBean.llenamatriz();
    }
    
    public void ACTUALIZAR_HT()
    {
        System.out.println("Actualizando terapia sintoma...");
        terapiasintomaService.updateTerapiaSintoma(datos);
        datos = new TerapiaSintoma();
        datos.setTerapia(new Terapia());
        datos.setSintoma(new Sintoma());
        listarSintoma();
        llenarLISTITA(terapia.getTerCodigo());
  
    }
    
    public void UpdateTS(TerapiaSintoma ts) 
    {
        terapiasintomaService.updateTerapiaSintoma(ts);
    }
            
    
    public void ACTUALIZAR_HT_T(TerapiaSintoma ts)
    {
        UpdateTS(ts);
        FacesContext fc = FacesContext.getCurrentInstance();
        SintomaTerapiaBean objetoSTBean = (SintomaTerapiaBean)fc.getExternalContext().getSessionMap().get("sintomaTerapiaBean");
        objetoSTBean.llenamatriz();
    }
    
    public void ELIMINAR()
    {
        System.out.println("Eliminando terapia sintoma...");
        terapiasintomaService.deleteTerapiaSintoma(datos);
        datos = new TerapiaSintoma();
        datos.setTerapia(new Terapia());
        datos.setSintoma(new Sintoma());
        listarSintoma();
        llenarLISTITA(terapia.getTerCodigo());
    }
    
    public void BUSCARXID(int tersintoma)
    {
        llenarLISTITA(tersintoma);
        datos = terapiasintomaService.getByParameters(tersintoma);
        terapia=datos.getTerapia();
        System.out.println("Terapia: " + datos.getTerapia().getTerDescripcion() + "\n" +
                           "Sintoma:" + datos.getSintoma().getSinDescripcion());
    }
    
    public void BuscarEliminar(int codigo)
    {
        BUSCARXID(codigo);
        ELIMINAR();
                FacesContext fc = FacesContext.getCurrentInstance();
        TerapeutaBean objetoTBean = (TerapeutaBean)fc.getExternalContext().getSessionMap().get("terapeutaBean");
        objetoTBean.PASARBEAN();   
        
        SintomaTerapiaBean objetoSTBean = (SintomaTerapiaBean)fc.getExternalContext().getSessionMap().get("sintomaTerapiaBean");
        objetoSTBean.llenamatriz();
    }
    
    public void GUARDARDATOS() 
    {
        if (terapiasintomaService.saveTerapiaSintoma(terapiasintoma)) 
        {
            registrarSintoma();
            StaticUtil.correctMesage("\311xito", "Se han guardado los datos m\351dicos");
            StaticUtil.keepMessages();
        } 
        else {
        StaticUtil.errorMessage("Error", "Hubo un error al guardar los datos");
        }
     }
    
    public void registrarSintoma() 
    {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        List s = (List)fc.getExternalContext().getSessionMap().get("listaterapiasintoma");
//        if (s != null) 
//        {
//            for (int i = 0; i < s.size(); i++) 
//            {
//                TerapiaSintoma tersin = new TerapiaSintoma();
//                tersin.setTerapia(terapia);
//                tersin.setSintoma((Sintoma)s.get(i));;
//                terapiasintomaService.saveTerapiaSintoma(tersin);
//            }
//        }
        FacesContext fc = FacesContext.getCurrentInstance();
        Terapia ter = (Terapia)fc.getExternalContext().getSessionMap().get("ultimaterapia");
        //System.out.println((new StringBuilder()).append("Terapia : ").append(ter.getTerCodigo()).append(" Síntoma : ").append(sintoma.getSinCodigo()).toString());
        System.out.println("TERAPIA : " + ter.getTerCodigo());
      
        System.out.println("SINTOMA : " + sintoma.getSinCodigo());
        TerapiaSintoma tersin = new TerapiaSintoma();
        tersin.setSintoma(sintoma);
        tersin.setTerapia(ter);
        terapiasintomaService.saveTerapiaSintoma(tersin);
    }

    public List<TerapiaSintoma> getListaterapiasintoma() {
        return listaterapiasintoma;
    }

    public void setListaterapiasintoma(List<TerapiaSintoma> listaterapiasintoma) {
        this.listaterapiasintoma = listaterapiasintoma;
    }
   
    public void addSintoma() 
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        System.out.println((new StringBuilder()).append("QueryS : ").append(queryS).toString());
        for (int i = 0; i < queryS.length(); i++) 
        {
            String c = (new StringBuilder()).append(queryS.charAt(i)).append("").toString();
            if (!c.equals(" "));
        }
        if (sintoma == null) 
        {
            if (queryS != "") 
            {
                sintoma = new Sintoma();
                sintoma.setSinDescripcion(queryS);
                sintomaService.saveSintoma(sintoma);
            } 
            else {
                StaticUtil.errorMessage("Error", "El campo síntoma está vacío");
                return;
            }
        }
        listarsintomas();
        for (Iterator iterator = listaterapiasintoma.iterator(); iterator.hasNext();) 
        {
            TerapiaSintoma s = (TerapiaSintoma)iterator.next();
            if (s.getSintoma().getSinCodigo() == sintoma.getSinCodigo()) 
            {
                StaticUtil.errorMessage("Error", "El síntoma ya ha sido agregado");
                sintoma = new Sintoma();
                return;
            }
        }
        Terapia ter = (Terapia)fc.getExternalContext().getSessionMap().get("ultimaterapia");
        //System.out.println((new StringBuilder()).append("Terapia : ").append(ter.getTerCodigo()).append(" Síntoma : ").append(sintoma.getSinCodigo()).toString());
        System.out.println("TERAPIA : " + ter.getTerCodigo());
      
        System.out.println("SINTOMA : " + sintoma.getSinCodigo());
        
        TerapiaSintoma tersin = new TerapiaSintoma();
        tersin.setSintoma(sintoma);
        tersin.setTerapia(ter);
        tersin.setTxsComentario(comentario);
        tersin.setTxsActivo(true);
        terapiasintomaService.saveTerapiaSintoma(tersin);
        System.out.println("Ingresando terapia sintoma...");
        System.out.println("Terapia: "+ datos.getTerapia().getTerCodigo());
        System.out.println("Sintoma: "+ datos.getSintoma().getSinCodigo());
        System.out.println("Comentario: " + datos.getTxsComentario());
//        datos = new TerapiaSintoma();
//        datos.setTerapia(new Terapia());
//        datos.setSintoma(new Sintoma());
        queryS = "";
        sintoma = new Sintoma();
        listaterapiasintoma = terapiasintomaService.getAllTerapiaSintoma(ter);
        listarSintoma();
    }
    
    public void llenarLISTITA(int ter)
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        System.out.println("TERAPIA : " + ter);       
        fc.getExternalContext().getSessionMap().put("ultimaterapia", terapiaservice.terapiaById(ter));
        listaterapiasintoma = terapiasintomaService.getAllTerapiaSintoma(terapiaservice.terapiaById(ter));
        if(ter==0)
        {
            disable = true;
        }else
        {
            disable = false;
        }
        System.out.println(ter+"disabvle : "+disable);
    }
    
    public List autoCompletarSintoma(String query)
    {
        List<Sintoma> allSintomas = sintomaService.getAll();
        List<Sintoma> sinFiltrados = new ArrayList();
        System.out.println((new StringBuilder()).append("Lista : ").append(allSintomas.size()).toString());
        System.out.println((new StringBuilder()).append("QUERY : ").append(query).toString());
        for (int i = 0; i < allSintomas.size(); i++) 
        {
            Sintoma sintoma = (Sintoma)allSintomas.get(i);
            if (sintoma.getSinDescripcion().toLowerCase().startsWith(query)) 
            {
                sinFiltrados.add(sintoma);
            }
        }
        if (sinFiltrados.size() == 0) 
        {
            queryS = query;
            System.out.println((new StringBuilder()).append("Síntoma : ").append(queryS).toString());
        }
        return sinFiltrados;
    }

    public void listarsintomas() 
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        Terapia ter = (Terapia)fc.getExternalContext().getSessionMap().get("ultimaterapia");
        listaterapiasintoma = terapiasintomaService.getAllTerapiaSintoma(ter);
    }   

    public String getQueryS() {
        return queryS;
    }

    public void setQueryS(String queryS) {
        this.queryS = queryS;
    }

    public Terapia getTerapia() {
        return terapia;
    }

    public void setTerapia(Terapia terapia) {
        this.terapia = terapia;
    }  

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public void verifyRequiredFields() 
    {
        if (terapiasintoma!= null) {
            disable = false;
        } else {
            disable = true;
        }
    }
        public void Update(TerapiaSintoma ts) 
    {
        terapiasintomaService.updateTerapiaSintoma(ts);
        SintomaTerapiaBean stBean = new SintomaTerapiaBean();
        stBean.llenamatriz();
    }
               
     public void REFRESH_SELECTONEMENU()
     {
        terapia = new Terapia();
        terapia.setTipoTerapia(new TipoTerapia());
        terapia.setVisita(new Visita());       
     }
        
     public void LISTARTERAPIASXCLIENTE(int c)
     {
        terapia = new Terapia();
        terapia.setTipoTerapia(new TipoTerapia());
        terapia.setVisita(new Visita()); 
        FacesContext fc = FacesContext.getCurrentInstance();
        Cliente cli = (Cliente) fc.getExternalContext().getSessionMap().get("cliente");
        listaterapiapar = terapiaservice.getAllParbyCliente(cli);
     }
        
     public void addSintoma_Terapeuta(int ter) 
     {
        if (!SintomaExistente(sintoma.getSinCodigo())) {
          
             
            terapia.setTerCodigo(ter);
       
            
            
            TerapiaSintoma txs = new TerapiaSintoma();
            txs.setTerapia(terapia);
            txs.setSintoma(sintoma);
            txs.setTxsComentario(comentario);
            txs.setTxsActivo(true);
            
            terapiasintomaService.saveTerapiaSintoma(txs);
            sintoma = new Sintoma();
             
            comentario="";
            StaticUtil.correctMesage("Exito", "Se agregó el síntoma");
            StaticUtil.keepMessages();
        }
    }
     
    public boolean SintomaExistente(int idsintoma) 
    {
        boolean valor = false;   
        listaterapiasintoma = terapiasintomaService.getAllTerapiaSintoma(terapia);
        for (Iterator iterator = listaterapiasintoma.iterator(); iterator.hasNext();) 
        {
            TerapiaSintoma s = (TerapiaSintoma)iterator.next();
            if (s.getSintoma().getSinCodigo().intValue() == idsintoma) 
            {
                StaticUtil.errorMessage("Error", "El síntoma ya ha sido agregado");
                return true;
            }
        }
        return valor;
    }   
    
    public void LISTAR_SINTOMAS_POR_PACIENTE(int cli)
    {   
        listaterapiasintx = terapiasintomaService.SP_LISTAR_SINTOMAS_CLIENTE(cli);
        System.out.println("Tamaño : " + listaterapiasintx.size());
    }
    
    public void LIMPIAR()
    {
        listaterapiasintx = new ArrayList<>();
    }
}
