// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   TerapiaBean.java

package com.gusedu.bean;

import com.gusedu.dao.EnfermedadService;
import com.gusedu.dao.SintomaService;
import com.gusedu.dao.TerapiaParService;
import com.gusedu.dao.TerapiaService;
import com.gusedu.dao.impl.EnfermedadServiceImpl;
import com.gusedu.dao.impl.SintomaServiceImpl;
import com.gusedu.dao.impl.TerapiaParServiceImpl;
import com.gusedu.dao.impl.TerapiaServiceImpl;
import com.gusedu.entidad.ParX;
import com.gusedu.model.*;
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

// Referenced classes of package com.gusedu.bean:
//            VisitaBean
@ManagedBean
@SessionScoped
public class TerapiaBean {

            private List<TipoTerapia> tipoTerapias;
            private List<Terapia> listar;
            private List<TerapiaSintoma> listarTerapiaSint;
            TerapiaService terapiaService;
            TerapiaParService terapiaparService;
            private Terapia terapia;
            private Integer idterapia;
            private String idterapeuta;
            private double precio;
            private List<TerapiaPar> listarTerapiaPar;
            private Enfermedad enfermedad;
            private Sintoma sintoma;
            private String queryS;
            private String queryE;
            EnfermedadService enfermedadService;
            SintomaService sintomaService;
            private List<SintomaVisita> listasintomaxvisita;
            private List<EnfermedadVisita> listaenfermedadvisita;
            
            private TerapiaPar datos;
            private ParX par;

            public TerapiaBean() {
        terapiaService = new TerapiaServiceImpl();
        terapiaparService = new TerapiaParServiceImpl();
        terapia = new Terapia();
        terapia.setTipoTerapia(new TipoTerapia());
        terapia.setVisita(new Visita());
        enfermedad = new Enfermedad();
        sintoma = new Sintoma();
        queryE = "";
        queryS = "";
        enfermedadService = new EnfermedadServiceImpl();
        sintomaService = new SintomaServiceImpl();
        LISTAR_TIPO_TERAPIAS();
            }

    public List<TipoTerapia> getTipoTerapias() {
        return tipoTerapias;
    }
    
    public void LISTAR_TIPO_TERAPIAS()
    {
        tipoTerapias = terapiaService.getTipoTerapias();
    }

    public TerapiaPar getDatos() {
        return datos;
    }

    public void setDatos(TerapiaPar datos) {
        this.datos = datos;
    }

    
    public void setTipoTerapias(List<TipoTerapia> tipoTerapias) {
        this.tipoTerapias = tipoTerapias;
    }

            
    public Terapia getTerapia() {
        return terapia;
    }

    public void setTerapia(Terapia terapia) {
        this.terapia = terapia;
    }

    public Integer getIdterapia() {
        return idterapia;
    }

    public void setIdterapia(Integer idterapia) {
        this.idterapia = idterapia;
    }

    public String getIdterapeuta() {
        return idterapeuta;
    }

    public void setIdterapeuta(String idterapeuta) {
        this.idterapeuta = idterapeuta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<TerapiaPar> getListarTerapiaPar() {
        return listarTerapiaPar;
    }

    public void setListarTerapiaPar(List<TerapiaPar> listarTerapiaPar) {
        this.listarTerapiaPar = listarTerapiaPar;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        this.sintoma = sintoma;
    }

    public String getQueryS() {
        return queryS;
    }

    public void setQueryS(String queryS) {
        this.queryS = queryS;
    }

    public String getQueryE() {
        return queryE;
    }

    public void setQueryE(String queryE) {
        this.queryE = queryE;
    }

    public List<SintomaVisita> getListasintomaxvisita() {
        return listasintomaxvisita;
    }

    public void setListasintomaxvisita(List<SintomaVisita> listasintomaxvisita) {
        this.listasintomaxvisita = listasintomaxvisita;
    }

    public List<EnfermedadVisita> getListaenfermedadvisita() {
        return listaenfermedadvisita;
    }

    public void setListaenfermedadvisita(List<EnfermedadVisita> listaenfermedadvisita) {
        this.listaenfermedadvisita = listaenfermedadvisita;
    }

    public List<Terapia> getListar() {
        return listar;
    }

    public void setListar(List<Terapia> listar) {
        this.listar = listar;
    }      
            
            

            public void addTerapiaSP() {
/* 192*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 193*/        Visita visita = (Visita)fc.getExternalContext().getSessionMap().get("ultimavisita");
/* 195*/        TipoTerapia tipoTerapia = new TipoTerapia();
/* 196*/        tipoTerapia = terapiaService.tteById(idterapia);
/* 197*/        terapia.setTerUsuCreacion(idterapeuta);
/* 198*/        terapia.setTipoTerapia(tipoTerapia);
/* 199*/        terapia.setTerFecRealizada(StaticUtil.getFechaActual());
/* 200*/        terapia.setVisita(visita);
/* 201*/        terapia.setTerCosto(Double.valueOf(precio));
/* 202*/        String usuarioCreacion = StaticUtil.userLogged();
/* 203*/        terapia.setTerEmpresa(usuarioCreacion);
/* 206*/        if (terapiaService.SPsaveTerapia(terapia)) {
/* 207*/            StaticUtil.correctMesage("Exito", "Se agregó correctamente la terapia");
/* 209*/            StaticUtil.keepMessages();
/* 210*/            System.out.println("ENTRO ACA c:");
                } else {
/* 213*/            StaticUtil.errorMessage("Error", "Hubo un error al agregar");
/* 214*/            StaticUtil.keepMessages();
/* 215*/            System.out.println("ENTRO A ERROR :c");
                }
            }

            public void usarTerapia() {
/* 220*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 221*/        Visita visita = (Visita)fc.getExternalContext().getSessionMap().get("ultimavisita");
/* 222*/        terapia = (Terapia)terapiaService.terapiasPorVisita(visita).get(0);
/* 223*/        listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
/* 224*/        fc.getExternalContext().getSessionMap().put("terapia", terapia);
/* 225*/        System.out.println(terapia.getTerCodigo());
            }

            public void listado() {
                System.out.println("PROBANDO LA LISTA DE PARES.....");
/* 229*/        clear();
/* 230*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 231*/        Visita visita = (Visita)fc.getExternalContext().getSessionMap().get("ultimavisita");
/* 232*/        System.out.println("VISITA : "+visita.getVisCodigo());
/* 233*/        List e = terapiaService.terapiasPorVisita(visita);
/* 234*/        if (e.size() > 0) {
/* 237*/            terapia = (Terapia)e.get(0);
/* 238*/            listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
                }
                System.out.println("PRUEBA FINALIZADA..... END");
            }
            
            public void listado_Terapeuta(int id_ter)
            {
                clear();
                terapia.setTerCodigo(id_ter);
                listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
            }

            public void clear() {
/* 244*/        terapia = new Terapia();
                terapia.setVisita(new Visita());
                terapia.setTipoTerapia(new TipoTerapia());
            }

            public void aceptar() {
/* 251*/        clear();
           
                if(listarTerapiaPar!=null)
                {
                    listarTerapiaPar.clear();
                }

      // FacesContext fc = FacesContext.getCurrentInstance();
       // VisitaBean objetoTBean = (VisitaBean)fc.getExternalContext().getSessionMap().get("visitaBean");
       //objetoTBean.probando();
            }

            public void Update(TerapiaPar tp) {
/* 262*/        terapiaparService.updateTerapia(tp);
            }
            
            public void Update_T(TerapiaPar tp){
                Update(tp);
                 FacesContext fc = FacesContext.getCurrentInstance();
                HistorialTerapiaBean objetoHTBean = (HistorialTerapiaBean)fc.getExternalContext().getSessionMap().get("historialTerapiaBean");
                objetoHTBean.llenamatriz();
            }

            public boolean ParExistente(int idpar) {
/* 267*/        boolean valor = false;   
/* 268*/        for (Iterator iterator = listarTerapiaPar.iterator(); iterator.hasNext();) {
/* 268*/            TerapiaPar s = (TerapiaPar)iterator.next();
/* 270*/            System.out.println((new StringBuilder()).append(s.getPar().getParCodigo()).append("-").append(idpar).toString());
/* 271*/            if (s.getPar().getParCodigo().intValue() == idpar) {
/* 273*/                StaticUtil.errorMessage("Error", "El par ya ha sido agregado");
/* 274*/                return true;
                    }
                }

/* 277*/        return valor;
            }

            public void addPar2(Integer idpar) {
/* 282*/        if (!ParExistente(idpar.intValue())) {
/* 284*/            FacesContext fc = FacesContext.getCurrentInstance();
/* 285*/            Terapia terapia = (Terapia)fc.getExternalContext().getSessionMap().get("terapia");
/* 286*/            if (terapia == null) {
/* 288*/                StaticUtil.errorMessage("Error", "Seleccione un tipo de Terapia");
/* 289*/                StaticUtil.keepMessages();
/* 290*/                return;
                    }
/* 292*/            Par par = new Par();
/* 293*/            par.setParCodigo(idpar);
/* 294*/            TerapiaPar tp = new TerapiaPar();
/* 295*/            tp.setPar(par);
/* 296*/            tp.setTerapia(terapia);
/* 297*/            tp.setTxpActivo(Boolean.valueOf(true));
/* 298*/            terapiaService.saveTerapiaPar(tp);
/* 299*/            StaticUtil.correctMesage("Exito", "Se agregó el par");
/* 301*/            StaticUtil.keepMessages();
/* 302*/            listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
                }
            }

            public List autoCompletarSintoma(String query) {
/* 315*/        List<Sintoma> allSintomas = sintomaService.getAll();
/* 316*/        List<Sintoma> sinFiltrados = new ArrayList();
/* 318*/        System.out.println((new StringBuilder()).append("Lista : ").append(allSintomas.size()).toString());
/* 319*/        System.out.println((new StringBuilder()).append("QUERY : ").append(query).toString());
/* 321*/        for (int i = 0; i < allSintomas.size(); i++) {
/* 322*/            Sintoma sintoma = (Sintoma)allSintomas.get(i);
/* 323*/            if (sintoma.getSinDescripcion().toLowerCase().startsWith(query)) {
/* 324*/                sinFiltrados.add(sintoma);
                    }
                }

/* 328*/        if (sinFiltrados.size() == 0) {
/* 330*/            queryS = query;
/* 331*/            System.out.println((new StringBuilder()).append("Sintoma : ").append(queryS).toString());
                }
/* 337*/        return sinFiltrados;
            }

            public List autoCompletarEnfermedad(String query) {
/* 341*/        List<Enfermedad> allEnfermedades = enfermedadService.getAll();
/* 342*/        List<Enfermedad> enfFiltrados = new ArrayList();
/* 344*/        for (int i = 0; i < allEnfermedades.size(); i++) {
/* 345*/            Enfermedad enfermedad = (Enfermedad)allEnfermedades.get(i);
/* 346*/            if (enfermedad.getEnfNombre().toLowerCase().startsWith(query)) {
/* 347*/                enfFiltrados.add(enfermedad);
                    }
                }

/* 350*/        if (enfFiltrados.size() == 0) {
/* 352*/            queryE = query;
/* 353*/            System.out.println((new StringBuilder()).append("Enfermedad : ").append(queryE).toString());
                }
/* 355*/        return enfFiltrados;
            }

            public void addSintoma3() {
/* 359*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 361*/        System.out.println((new StringBuilder()).append("QueryS : ").append(queryS).toString());
/* 362*/        for (int i = 0; i < queryS.length(); i++) {
/* 364*/            String c = (new StringBuilder()).append(queryS.charAt(i)).append("").toString();
/* 365*/            if (!c.equals(" "));
                }

/* 371*/        if (sintoma == null) {
/* 373*/            if (queryS != "") {
/* 375*/                sintoma = new Sintoma();
/* 376*/                sintoma.setSinDescripcion(queryS);
/* 377*/                sintomaService.saveSintoma(sintoma);
                    } else {
/* 381*/                StaticUtil.errorMessage("Error", "El campo sintoma esta vacio");
/* 382*/                return;
                    }
                }
/* 386*/        for (Iterator iterator = listasintomaxvisita.iterator(); iterator.hasNext();) {
/* 386*/            SintomaVisita s = (SintomaVisita)iterator.next();
/* 388*/            if (s.getSintoma().getSinCodigo() == sintoma.getSinCodigo()) {
/* 390*/                StaticUtil.errorMessage("Error", "El sintoma ya ha sido agregado");
/* 391*/                sintoma = new Sintoma();
/* 392*/                return;
                    }
                }

/* 397*/        Visita vis = (Visita)fc.getExternalContext().getSessionMap().get("ultimavisita");
/* 398*/        System.out.println((new StringBuilder()).append("Visita : ").append(vis.getVisCodigo()).append(" Sintoma : ").append(sintoma.getSinCodigo()).toString());
/* 399*/        SintomaVisita sinvis = new SintomaVisita();
/* 400*/        sinvis.setSintoma(sintoma);
/* 401*/        sinvis.setVisita(vis);
/* 402*/        terapiaService.saveSintomaVisita(sinvis);
/* 403*/        queryS = "";
/* 404*/        sintoma = new Sintoma();
/* 405*/        listasintomaxvisita = terapiaService.getAllSintomaxVisita(vis);
            }

            public void addEnfermedad3() {
/* 412*/        for (int i = 0; i < queryE.length(); i++) {
/* 414*/            String c = (new StringBuilder()).append(queryE.charAt(i)).append("").toString();
/* 415*/            if (!c.equals(" "));
                }

/* 421*/        if (enfermedad == null) {
/* 423*/            if (queryE != "") {
/* 425*/                enfermedad = new Enfermedad();
/* 426*/                enfermedad.setEnfNombre(queryE);
/* 427*/                enfermedadService.saveEnfermedad(enfermedad);
                    } else {
/* 431*/                StaticUtil.errorMessage("Error", "El campo enfermedad esta vacio");
/* 432*/                return;
                    }
                }
/* 436*/        for (Iterator iterator = listaenfermedadvisita.iterator(); iterator.hasNext();) {
/* 436*/            EnfermedadVisita s = (EnfermedadVisita)iterator.next();
/* 438*/            if (s.getEnfermedad().getEnfNombre().equals(enfermedad.getEnfNombre())) {
/* 441*/                StaticUtil.errorMessage("Error", "La Enfermedad ya ha sido agregada");
/* 442*/                enfermedad = new Enfermedad();
/* 443*/                return;
                    }
                }

/* 458*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 459*/        Visita vis = (Visita)fc.getExternalContext().getSessionMap().get("ultimavisita");
/* 460*/        EnfermedadVisita enfvis = new EnfermedadVisita();
/* 461*/        enfvis = new EnfermedadVisita();
/* 462*/        enfvis.setVisita(vis);
/* 463*/        enfvis.setEnfermedad(enfermedad);
/* 464*/        terapiaService.saveEnfermedadVisita(enfvis);
/* 465*/        enfermedad = new Enfermedad();
/* 466*/        listaenfermedadvisita = terapiaService.getAllEnfermedadxVisita(vis);
            }

            public void listarsintomas() {
/* 471*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 472*/        Visita vis = (Visita)fc.getExternalContext().getSessionMap().get("ultimavisita");
/* 477*/        listasintomaxvisita = terapiaService.getAllSintomaxVisita(vis);
            }

            public void listarenfermedades() {
/* 481*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 482*/        Visita vis = (Visita)fc.getExternalContext().getSessionMap().get("ultimavisita");
/* 483*/        listaenfermedadvisita = terapiaService.getAllEnfermedadxVisita(vis);
            }
            
             public void TerapiaHV(Terapia terHV) {
               FacesContext fc = FacesContext.getCurrentInstance();
        
              terapia = terHV;
              listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
              fc.getExternalContext().getSessionMap().put("terapia", terapia);
              System.out.println("|SIZE : "+listarTerapiaPar.size());
            }
             
             
      public void addPar2SP(Integer idpar) {
        if (!ParExistenteV2(idpar.intValue())) {
            FacesContext fc = FacesContext.getCurrentInstance();
           Terapia terapia = (Terapia)fc.getExternalContext().getSessionMap().get("terapia");
            if (terapia == null) {
                StaticUtil.errorMessage("Error", "Seleccione un tipo de Terapia");
                StaticUtil.keepMessages();
                return;
                    }
            Par par = new Par();
            par.setParCodigo(idpar);
            TerapiaPar tp = new TerapiaPar();
            tp.setPar(par);
            tp.setTerapia(terapia);
            tp.setTxpActivo(Boolean.valueOf(true));
            //terapiaService.saveTerapiaPar(tp);
            terapiaparService.SPsaveTerapiaPar(tp);
            StaticUtil.correctMesage("Exito", "Se agregó el par");
            StaticUtil.keepMessages();
            listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
                }
            } 
      
      public void addPar3SP(Integer idpar) {
        if (!ParExistenteV2(idpar)) {
            FacesContext fc = FacesContext.getCurrentInstance();
           Terapia terapia = (Terapia)fc.getExternalContext().getSessionMap().get("terActual");
            if (terapia == null) {
                StaticUtil.errorMessage("Error", "Seleccione un tipo de Terapia");
                StaticUtil.keepMessages();
                return;
                    }
            Par par = new Par();
            par.setParCodigo(idpar);
            TerapiaPar tp = new TerapiaPar();
            tp.setPar(par);
            tp.setTerapia(terapia);
            tp.setTxpActivo(Boolean.valueOf(true));
            //terapiaService.saveTerapiaPar(tp);
            terapiaparService.SPsaveTerapiaPar(tp);
            StaticUtil.correctMesage("Exito", "Se agregó el par");
            StaticUtil.keepMessages();
            listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
                }
            } 
      
     
      public void inserT_PART()
      {
           addPar3SP_Terapeuta(terapia.getTerCodigo(), par.getCod_par());
           FacesContext fc = FacesContext.getCurrentInstance();
           HistorialTerapiaBean objetoHTBean = (HistorialTerapiaBean)fc.getExternalContext().getSessionMap().get("historialTerapiaBean");
           objetoHTBean.llenamatriz();
           par= new ParX();
      }
      
     public void addPar3SP_Terapeuta(Integer idTerapia,Integer idpar) {
        if (!ParExistenteV2(idpar)) {
            Terapia terapia= new Terapia();
            terapia.setTerCodigo(idTerapia);

            Par par = new Par();
            par.setParCodigo(idpar);
            TerapiaPar tp = new TerapiaPar();
            tp.setPar(par);
            tp.setTerapia(terapia);
            tp.setTxpActivo(true);

            terapiaparService.SPsaveTerapiaPar(tp);
            StaticUtil.correctMesage("Exito", "Se agregó el par");
            StaticUtil.keepMessages();
            listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
        }
    }
      
         public boolean ParExistenteV2(int idpar) {
        boolean valor = false;   
        listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
        for (Iterator iterator = listarTerapiaPar.iterator(); iterator.hasNext();) {
            TerapiaPar s = (TerapiaPar)iterator.next();
            System.out.println((new StringBuilder()).append(s.getPar().getParCodigo()).append("-").append(idpar).toString());
            if (s.getPar().getParCodigo().intValue() == idpar) {
                StaticUtil.errorMessage("Error", "El par ya ha sido agregado");
               return true;
                    }
                }

        return valor;
            }    
         
    public void listadoX(Terapia t) {
        clear();

         FacesContext fc = FacesContext.getCurrentInstance();
         Terapia ter = (Terapia) fc.getExternalContext().getSessionMap().get("terActual");
         terapia = ter;
        listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
        System.out.println("Tamaño : " + listarTerapiaPar.size());
    }
    
    public void LISTAR_PARES_POR_PACIENTE(int t)
    {   
        terapia.setTerCodigo(t);
        listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
        System.out.println("Tamaño : " + listarTerapiaPar.size());
    }
    
    public void papu()
    {
        System.out.println("Soy papu :D");
        FacesContext fc = FacesContext.getCurrentInstance();
         Terapia ter = (Terapia) fc.getExternalContext().getSessionMap().get("terActual");
         terapia = ter;
        System.out.println("Prueba..."+terapia.getTerCodigo());
        listarTerapiaPar = terapiaService.getAllTerapiaParbyTerapia(terapia);
        System.out.println("Tamaño : " + listarTerapiaPar.size());
        /*for (int i = 0; i < listarTerapiaPar.size(); i++) {
            System.out.println(listarTerapiaPar.get(i).getPar().getPuntoByPunCodigoP1().getPunNombre()+"-"+listarTerapiaPar.get(i).getPar().getPuntoByPunCodigoP2().getPunNombre());
        }*/
    }
    public void BUSCARXID(int terpar)
    {
        datos = terapiaparService.getByParameters(terpar);
        System.out.println("Probando terapia par: JOjO: Terapia: " + datos.getTerapia().getTerDescripcion()+ "\n" +
                           "Par:" + datos.getPar().getPuntoByPunCodigoP1().getPunNombre()+"-"+datos.getPar().getPuntoByPunCodigoP2().getPunNombre());
    }
    
    public void BuscarEliminar(int codigo)
    {
        System.out.println("ENTRE AL METODO PS");
        BUSCARXID(codigo);
        ELIMINAR();
    }
    
    public void ELIMINAR()
    {
        System.out.println("Eliminando enfermedad par...");
        terapiaparService.deleteTerapiaPar(datos);
        datos = new TerapiaPar();
        datos.setTerapia(new Terapia());
        datos.setPar(new Par());
        listado();
    }
     public void ELIMINAR_T(int a)
    {
        System.out.println("Eliminando enfermedad par...");
        terapiaparService.deleteTerapiaPar(datos);
        datos = new TerapiaPar();
        datos.setTerapia(new Terapia());
        datos.setPar(new Par());
       
    }
     
   public void BuscarEliminar_T(int codigo,int ter)
    {
        System.out.println("ENTRE AL METODO PS" + codigo);
        BUSCARXID(codigo);
        ELIMINAR_T(codigo);
        LISTAR_PARES_POR_PACIENTE(ter);
        FacesContext fc = FacesContext.getCurrentInstance();
                HistorialTerapiaBean objetoHTBean = (HistorialTerapiaBean)fc.getExternalContext().getSessionMap().get("historialTerapiaBean");
                objetoHTBean.llenamatriz();
      
    }
   
   public void LIMPIAR_LISTA()
   {
       listarTerapiaPar = new ArrayList<>();
   }

    public ParX getPar() {
        return par;
    }

    public void setPar(ParX par) {
        this.par = par;
    }
   
}
