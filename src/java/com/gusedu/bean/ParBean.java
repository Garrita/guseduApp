// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   ParBean.java

package com.gusedu.bean;

import com.gusedu.dao.GrupoService;
import com.gusedu.dao.ParService;
import com.gusedu.dao.PuntoService;
import com.gusedu.dao.impl.GrupoServiceImpl;
import com.gusedu.dao.impl.ParServiceImpl;
import com.gusedu.dao.impl.PuntoServiceImpl;
import com.gusedu.model.*;
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

// Referenced classes of package com.gusedu.bean:
//            TerapiaBean

@ManagedBean
@SessionScoped
public class ParBean {

            private Par par;
            private List<Par> pares;
            private List parcito;
            private List npar;
            private List<Grupo> grupos;
            List result;
            private Punto punto1;
            private Punto punto2;
            private UploadedFile uploadedFile;
            private Grupo grupoSeleccionado;
            private Par parSeleccionado;
            private List enfermedadesPar;
            private List sintomasPar;
            private Enfermedad enfermedadAdd;
            private Sintoma sintomaAdd;
            private List enfermedadesAll;
            private List sintomaAll;
            private EnfermedadPar expToDelete;
            private SintomaPar sxpToDelete;
            private String query;
            int ascP1;
            int ascP2;
            int goiz;
            private List allPuntos;
            PuntoService puntoService;
            ParService parService;
            GrupoService grupoService;

            public ParBean() {
/*  40*/        result = new ArrayList();
/*  72*/        punto1 = new Punto();
/*  73*/        punto2 = new Punto();
/*  74*/        puntoService = new PuntoServiceImpl();
/*  75*/        parService = new ParServiceImpl();
                    grupoService = new GrupoServiceImpl();
/*  76*/        clean();
post();
            }

        
            public void post() {
         listarptos();
         LISTAR_GRUPOS();
         LISTAR_PARES();
            }

            public void clean() {
/*  85*/        par = new Par();
/*  86*/        par.setGrupo(new Grupo());
                par.setPuntoByPunCodigoP1(new Punto());
                par.setPuntoByPunCodigoP2(new Punto());
/*  87*/        punto1 = new Punto();
/*  88*/        punto2 = new Punto();
/*  89*/        parSeleccionado = new Par();
/*  90*/        enfermedadAdd = new Enfermedad();
/*  91*/        sintomaAdd = new Sintoma();
/*  92*/        grupoSeleccionado = new Grupo();
            }

            public void listarptos() {
                allPuntos = puntoService.getAllPuntos();
            }

            public List autoCompletar(String query) {
/* 102*/        List puntosFiltrados = new ArrayList();
/* 104*/        for (int i = 0; i < allPuntos.size(); i++) {
/* 105*/            Punto punto = (Punto)allPuntos.get(i);
/* 106*/            if (punto.getPunNombre().toLowerCase().startsWith(query)) {
/* 107*/                puntosFiltrados.add(punto);
                    }
                }

/* 110*/        return puntosFiltrados;
            }

            public void agregarPar2() {
/* 116*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 117*/        Punto p = (Punto)fc.getExternalContext().getSessionMap().get("punto");
/* 118*/        Grupo gr = new Grupo();
/* 119*/        gr.setGruCodigo(Integer.valueOf(5));
/* 121*/        if (punto2 == null) {
/* 123*/            StaticUtil.errorMessage("Error", "El punto que elegio no existe");
/* 124*/            punto2 = new Punto();
/* 125*/            return;
                }
/* 128*/        System.out.println("No es nulo :-)");
/* 131*/        System.out.println((new StringBuilder()).append("Grupo : ").append(gr.getGruCodigo()).toString());
/* 132*/        System.out.println((new StringBuilder()).append("Punto 1 : ").append(p.getPunCodigo()).toString());
/* 133*/        System.out.println((new StringBuilder()).append("Punto 2 : ").append(punto2.getPunCodigo()).toString());
/* 135*/        par.setGrupo(gr);
/* 136*/        par.setPuntoByPunCodigoP1(p);
/* 137*/        par.setPuntoByPunCodigoP2(punto2);
/* 139*/        Par newPar = parService.parByPuntos(p, punto2, gr);
/* 140*/        if (newPar != null) {
/* 142*/            TerapiaBean objetoTBean = (TerapiaBean)fc.getExternalContext().getSessionMap().get("terapiaBean");
/* 143*/            objetoTBean.addPar2(newPar.getParCodigo());
                   par = new Par();
                   par.setGrupo(new Grupo());
                   par.setPuntoByPunCodigoP1(new Punto());
                   par.setPuntoByPunCodigoP2(new Punto());
/* 145*/            punto2 = new Punto();
/* 146*/            return;
                }
/* 149*/        if (parService.savePar(par).booleanValue()) {
/* 153*/            TerapiaBean objetoTBean = (TerapiaBean)fc.getExternalContext().getSessionMap().get("terapiaBean");
/* 154*/            objetoTBean.addPar2(par.getParCodigo());
/* 155*/             par = new Par();
                   par.setGrupo(new Grupo());
                   par.setPuntoByPunCodigoP1(new Punto());
                   par.setPuntoByPunCodigoP2(new Punto());
/* 156*/            punto2 = new Punto();
                } else {
/* 159*/            StaticUtil.errorMessage("Error", "Hubo un error al a\361adir el par");
                }
            }

        public void filtrarBusqueda() {
		pares = parService.SP_ORDENAMIENTO_PAR(0);
		List<Par> filtrados = new ArrayList<>();
		for (Par p : pares) {
			if (p.getPuntoByPunCodigoP1().getPunNombre().toLowerCase().contains(query)
					|| p.getPuntoByPunCodigoP2().getPunNombre().toLowerCase()
							.contains(query)) {
				filtrados.add(p);
			}
		}
		pares = filtrados;
	}
            
        public void orderAscP1() {
		ascP1 = 1;
		ascP2 = 0;
		goiz = 0;
		//pares = parService.getAllParesOrderByP1();
                pares = parService.SP_ORDENAMIENTO_PAR(0);
	}

	public void orderAscP2() {
		ascP1 = 0;
		ascP2 = 1;
		goiz = 0;
		//pares = parService.getAllParesOrderByP2();
                pares = parService.SP_ORDENAMIENTO_PAR(1);
	}

	public void orderGoiz() {
		ascP1 = 0;
		ascP2 = 0;
		goiz = 1;
		//pares = parService.getAllParesOrderGoiz();
                pares = parService.SP_ORDENAMIENTO_PAR(2);
	}    
        
        public void toRegistrarWeb()
	{
		
		punto1 = new Punto();
		punto2 = new Punto();
		par = new Par();
		par.setPuntoByPunCodigoP1(punto1);
		par.setPuntoByPunCodigoP2(punto2);
		par.setGrupo(grupoSeleccionado);
	}
        public void cancelarPar() {
		parSeleccionado = new Par();
	}
        public void mergeParWeb()
	{
		//par.setParPunto1(punto1);
		//par.setParPunto2(punto2);
		//par.setParGrupo(grupoSeleccionado);
		parService.updatePar(par);
		StaticUtil.correctMesage("Exito", "Se modifico los datos del par");
		clean();
		
	}
        
        public void cargarRemovePar(int id) {
		parSeleccionado = parService.parById(id);
	}
        	public void cargarUpdateParWeb(int id) {
		par = parService.parById(id);
		/*punto1 = par.getParPunto1();
		punto2 = par.getParPunto2();
		grupoSeleccionado = par.getParGrupo();*/
	}
                
        public String toFileUpload(int idPar) {
		par = parService.parById(idPar);
		return "pm:uploadImage?transition=flip";
	}
        
        public void agregarParWeb() {
		System.out.println(grupoSeleccionado);
		/*if(grupoSeleccionado.getIdGrupo()==0 )
		{
			StaticUtil.errorMessage("Error", "Seleccione un grupo para los pares");
			return;
		}*/
		
		par.setGrupo(grupoSeleccionado);
		par.setPuntoByPunCodigoP1(punto1);
		par.setPuntoByPunCodigoP2(punto2);
		Par newPar = parService.parByPuntos(punto1, punto2, grupoSeleccionado);
		if (newPar != null) {
			StaticUtil.errorMessage("Error", "El par ya existe");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.getFlash().setKeepMessages(true);
		
		clean();
		return;
		}
		if (parService.savePar(par)) {
			grupoSeleccionado = new Grupo();
			StaticUtil.correctMesage("Éxito",
					"Se ha añadido correctamente el par");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.getFlash().setKeepMessages(true);
			//RequestContext.getCurrentInstance().execute("PF('dlgAddPar').hide();");
			clean();
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al añadir el par");
	 
		}
	}
        
        	public void backToConsultar() {
		
		punto1 = new Punto();
		punto2 = new Punto();
		grupoSeleccionado = new Grupo();
		par = new Par();
		par.setPuntoByPunCodigoP1(punto1);
		par.setPuntoByPunCodigoP2(punto2);
		par.setGrupo(grupoSeleccionado);
		//return "pm:consultarPares?transition=flip";
	}
                
        public void cargarParWeb(int id)
	{
		parSeleccionado = parService.parById(id); 
	           System.out.println("P1 : "+parSeleccionado.getPuntoByPunCodigoP1().getPunNombre()+
                                       "\nP2 : "+parSeleccionado.getPuntoByPunCodigoP2().getPunNombre()+
                           "\nPatogeno : "+parSeleccionado.getParPatogeno()+
                           "\nDescripcion : "+parSeleccionado.getParDescripcion()+
                           "\nGrupo : "+parSeleccionado.getGrupo().getGruNombre());
        }
                
       	public void removePar() {
		parService.deletePar(parSeleccionado);
		parSeleccionado = new Par();
	}
            public Par getPar() {
/* 167*/        return par;
            }

            public void setPar(Par par) {
/* 171*/        this.par = par;
            }

    public List<Par> getPares() {
        return pares;
    }
    
    public void LISTAR_PARES()
    {
          if (query != null) {
            if (!query.isEmpty()) {
                pares= new ArrayList<>();
            }
        }
        if (ascP1 != 0 || ascP2 != 0 || goiz != 0) {
           pares= new ArrayList<>();
        }
        pares=parService.SP_ORDENAMIENTO_PAR(0);
    }

            public void setPares(List<Par>  pares) {
/* 179*/        this.pares = pares;
            }

            public List getParcito() {
/* 183*/        return parcito;
            }

            public void setParcito(List parcito) {
/* 187*/        this.parcito = parcito;
            }

            public List getNpar() {
/* 191*/        return npar;
            }

            public void setNpar(List npar) {
/* 195*/        this.npar = npar;
            }

            public List<Grupo> getGrupos() {
		
/* 199*/        return grupos;
            }
            
            public void  LISTAR_GRUPOS()
            {
                grupos = grupoService.getAllGrupos();
            }

            public void setGrupos(List grupos) {
/* 203*/        this.grupos = grupos;
            }

            public List getResult() {
/* 207*/        return result;
            }

            public void setResult(List result) {
/* 211*/        this.result = result;
            }

            public Punto getPunto1() {
/* 215*/        return punto1;
            }

            public void setPunto1(Punto punto1) {
/* 219*/        this.punto1 = punto1;
            }

            public Punto getPunto2() {
/* 223*/        return punto2;
            }

            public void setPunto2(Punto punto2) {
/* 227*/        this.punto2 = punto2;
            }

            public UploadedFile getUploadedFile() {
/* 231*/        return uploadedFile;
            }

            public void setUploadedFile(UploadedFile uploadedFile) {
/* 235*/        this.uploadedFile = uploadedFile;
            }

            public Grupo getGrupoSeleccionado() {
/* 239*/        return grupoSeleccionado;
            }

            public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
/* 243*/        this.grupoSeleccionado = grupoSeleccionado;
            }

            public Par getParSeleccionado() {
/* 247*/        return parSeleccionado;
            }

            public void setParSeleccionado(Par parSeleccionado) {
/* 251*/        this.parSeleccionado = parSeleccionado;
            }

            public List getEnfermedadesPar() {
/* 255*/        return enfermedadesPar;
            }

            public void setEnfermedadesPar(List enfermedadesPar) {
/* 259*/        this.enfermedadesPar = enfermedadesPar;
            }

            public List getSintomasPar() {
/* 263*/        return sintomasPar;
            }

            public void setSintomasPar(List sintomasPar) {
/* 267*/        this.sintomasPar = sintomasPar;
            }

            public Enfermedad getEnfermedadAdd() {
/* 271*/        return enfermedadAdd;
            }

            public void setEnfermedadAdd(Enfermedad enfermedadAdd) {
/* 275*/        this.enfermedadAdd = enfermedadAdd;
            }

            public Sintoma getSintomaAdd() {
/* 279*/        return sintomaAdd;
            }

            public void setSintomaAdd(Sintoma sintomaAdd) {
/* 283*/        this.sintomaAdd = sintomaAdd;
            }

            public List getEnfermedadesAll() {
/* 287*/        return enfermedadesAll;
            }

            public void setEnfermedadesAll(List enfermedadesAll) {
/* 291*/        this.enfermedadesAll = enfermedadesAll;
            }

            public List getSintomaAll() {
/* 295*/        return sintomaAll;
            }

            public void setSintomaAll(List sintomaAll) {
/* 299*/        this.sintomaAll = sintomaAll;
            }

            public EnfermedadPar getExpToDelete() {
/* 303*/        return expToDelete;
            }

            public void setExpToDelete(EnfermedadPar expToDelete) {
/* 307*/        this.expToDelete = expToDelete;
            }

            public SintomaPar getSxpToDelete() {
/* 311*/        return sxpToDelete;
            }

            public void setSxpToDelete(SintomaPar sxpToDelete) {
/* 315*/        this.sxpToDelete = sxpToDelete;
            }

            public String getQuery() {
/* 319*/        return query;
            }

            public void setQuery(String query) {
/* 323*/        this.query = query;
            }

            public int getAscP1() {
/* 327*/        return ascP1;
            }

            public void setAscP1(int ascP1) {
/* 331*/        this.ascP1 = ascP1;
            }

            public int getAscP2() {
/* 335*/        return ascP2;
            }

            public void setAscP2(int ascP2) {
/* 339*/        this.ascP2 = ascP2;
            }

            public int getGoiz() {
/* 343*/        return goiz;
            }

            public void setGoiz(int goiz) {
/* 347*/        this.goiz = goiz;
            }

            public List getAllPuntos() {
/* 351*/        return allPuntos;
            }

            public void setAllPuntos(List allPuntos) {
/* 355*/        this.allPuntos = allPuntos;
            }
            
            public void cargarPar2(int id)
	{
             
		parSeleccionado = parService.parById(id);
              
	}
            
         public void agregarPar2SP() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Punto p = (Punto)fc.getExternalContext().getSessionMap().get("punto");
        Grupo gr = new Grupo();
        gr.setGruCodigo(Integer.valueOf(5));
        if (punto2 == null) {
            StaticUtil.errorMessage("Error", "El punto que elegio no existe");
            punto2 = new Punto();
            return;
                }
        System.out.println("No es nulo :-)");
        System.out.println((new StringBuilder()).append("Grupo : ").append(gr.getGruCodigo()).toString());
        System.out.println((new StringBuilder()).append("Punto 1 : ").append(p.getPunCodigo()).toString());
        System.out.println((new StringBuilder()).append("Punto 2 : ").append(punto2.getPunCodigo()).toString());
        par.setGrupo(gr);
        par.setPuntoByPunCodigoP1(p);
        par.setPuntoByPunCodigoP2(punto2);
        Par newPar = parService.parByPuntos(p, punto2, gr);
        if (newPar != null) {
            TerapiaBean objetoTBean = (TerapiaBean)fc.getExternalContext().getSessionMap().get("terapiaBean");
            objetoTBean.addPar2(newPar.getParCodigo());
             par = new Par();
                   par.setGrupo(new Grupo());
                   par.setPuntoByPunCodigoP1(new Punto());
                   par.setPuntoByPunCodigoP2(new Punto());
            punto2 = new Punto();
            return;
                }
        if (parService.savePar(par).booleanValue()) {
            TerapiaBean objetoTBean = (TerapiaBean)fc.getExternalContext().getSessionMap().get("terapiaBean");
            objetoTBean.addPar2SP(par.getParCodigo());
             par = new Par();
                   par.setGrupo(new Grupo());
                   par.setPuntoByPunCodigoP1(new Punto());
                   par.setPuntoByPunCodigoP2(new Punto());
            punto2 = new Punto();
                } else {
            StaticUtil.errorMessage("Error", "Hubo un error al a\361adir el par");
                }
            }
            
}
