// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   PuntoBean.java

package com.gusedu.bean;

import com.gusedu.dao.ParService;
import com.gusedu.dao.PuntoService;
import com.gusedu.dao.impl.ParServiceImpl;
import com.gusedu.dao.impl.PuntoServiceImpl;
import com.gusedu.model.Par;
import com.gusedu.model.Punto;
import com.gusedu.util.StaticUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

// Referenced classes of package com.gusedu.bean:
//            classPar, classPunto
@ManagedBean
@SessionScoped
public class PuntoBean {

            ParService parService;
            PuntoService puntoService;
            private Punto punto;
            private List<Par>  parcito;
            private List<classPunto> lista;
            private List<classPar> listapar;
            private int filaPunto;
            private int filaPar;

            private String query;
            private Punto puntoU;
            private List<Punto> puntos;
            
            	int asc;
	int desc;
	int goiz;
        
        private String puntonombre;
        
            public PuntoBean() {
                parService = new ParServiceImpl();
                puntoService = new PuntoServiceImpl();
                punto = new Punto();
                puntoU = new Punto();
                filaPar = 0;
                listapar = new ArrayList();
               
        post();
        LISTAR_PUNTOS();
            }

            
    public void post() {
 
        if(listapar.size()==0)
        {
            List<Par> pares = new ArrayList<>();
 
        pares = parService.SP_ORDENAMIENTO_PAR(0);

        double p = pares.size();
        int leng = pares.size() / 4;
        double t = p / 4D;
        if ((double) leng < t) {
            leng++;
        }
        filaPar = leng;
        int f = leng * 4 - (int) p;
        for (int j = 0; j < leng; j++) {
            if (j >= leng - f) {
                pares.add(new Par());
            }
            listapar.add(new classPar((Par) pares.get(j), (Par) pares.get(j + leng * 1), (Par) pares.get(leng * 2 + j), (Par) pares.get(j + leng * 3)));
        }

        listarpuntos();
        }
    }

            public void buscar(int p1) {
        punto.setPunCodigo(p1);
        parcito = parService.paresByPunto(punto);
        System.out.println((new StringBuilder()).append("Lista : ").append(parcito.size()).toString());
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("punto", punto);
            }

            public void listarpuntos() {
        lista = new ArrayList();
        List<Punto>  ptos = new ArrayList();
       // ptos = puntoService.getAllOrdenAlfabeticoAsc();
        ptos = puntoService.SP_ORDENAMIENTO_PUNTO(0);
        double p = ptos.size();
        int len = ptos.size() / 4;
        double t = p / 4D;
        if ((double)len < t) {
            len++;
                }
        filaPunto = len;
        int f = len * 4 - (int)p;
        for (int i = 0; i < len; i++) {
            if (i >= len - f) {
                ptos.add(new Punto("", 0, " ", " ", null, null, null, null));
                    }
            lista.add(new classPunto((Punto)ptos.get(i), (Punto)ptos.get(i + len * 1), (Punto)ptos.get(i + len * 2), (Punto)ptos.get(i + len * 3)));
                }

            }

            
            public void cancelar() {
		punto = new Punto();
	}
            
            public void orderAsc() {
		asc = 1;
		desc = 0;
		goiz = 0;
		//puntos = puntoService.getAllOrdenAlfabeticoAsc();
                puntos = puntoService.SP_ORDENAMIENTO_PUNTO(0);
	}

	public void orderDesc() {
		desc = 1;
		asc = 0;
		goiz = 0;
		//puntos = puntoService.getAllOrdenAlfabeticoDesc();
                puntos = puntoService.SP_ORDENAMIENTO_PUNTO(1);
	}

	public void orderGoiz() {
		desc = 0;
		asc = 0;
		goiz = 1;
		//puntos = puntoService.getAllOrdenGoiz();
                puntos = puntoService.SP_ORDENAMIENTO_PUNTO(2);
	}
        
        public void filtrarBusqueda() {
		puntos = puntoService.getAllPuntos();
		List<Punto> filtrados = new ArrayList<>();
		for (Punto p : puntos) {
			if (p.getPunNombre().toLowerCase().contains(query.toLowerCase())) {
				filtrados.add(p);
			}
		}
		puntos = filtrados;
	}
        
        public void filtrarNuevo() {
            listarpuntos();
            List<classPunto> filtrados = new ArrayList<>();
            for(classPunto cp : lista) {
                        
                        if(cp.getPunto1().getPunNombre()!=null && 
                           cp.getPunto2().getPunNombre()!=null && 
                           cp.getPunto3().getPunNombre()!=null &&
                           cp.getPunto1().getPunNombre()!=null){
                            
                        }
                        if (cp.getPunto1().getPunNombre().toLowerCase().contains(query.toLowerCase())||
                            cp.getPunto2().getPunNombre().toLowerCase().contains(query.toLowerCase()) ||
                                cp.getPunto3().getPunNombre().toLowerCase().contains(query.toLowerCase()) ||
                                cp.getPunto4().getPunNombre().toLowerCase().contains(query.toLowerCase())){
                            filtrados.add(cp);
                        }
            }
            lista = filtrados;
            System.out.println("CANTIDAD DE FILTRADOS :D" +lista.size());
        }
           
        
        	public void añadirPuntoWeb()
	{
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "El nombre del punto ya existe");
			//return null;
		}
		if (puntoService.savePunto(punto)) {
			punto = new Punto();
			StaticUtil.correctMesage("Éxito", "Se ha añadido correctamente el punto");
			listarpuntos();
			//StaticUtil.keepMessages();			
			//return "pm:consultarPuntos?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al añadir el punto");
			//return null;
		}
	}
                
                public void detallePuntoWeb(Integer id)
	{
		punto = puntoService.puntoById(id);
	 
	}
              	public void detallePuntoUpdateWeb(Punto p) {
		//punto = p;
		puntoU=p;
		puntonombre=p.getPunNombre();
		//System.out.println("Punto : "+punto.getNombre());
		
	}
                public void cargarPunto(Integer id) {
		punto = puntoService.puntoById(id);
	}
                
                	public void eliminarPunto() {
		puntoService.deletePunto(punto);
		punto = new Punto();
	}
                        	public void actualizarPuntoWeb(){
		System.out.println("Descripcion : "+puntoU);
		//puntoU.setNombre(puntonombre);
		
		if(puntoU.getPunNombre().isEmpty())
		{
			StaticUtil.errorMessage("Error", "El campo descripcion no puede ser vacío");
			return;
		}
		
		if (puntoService.updatePunto(puntoU)) {
			puntoU = new Punto();
			StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente el punto");
			RequestContext.getCurrentInstance().execute("PF('dlgEditarPunto').hide();");
			 //StaticUtil.keepMessages();
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al actualizar los datos del punto");
			// StaticUtil.keepMessages();
		}
	}
                                
        public void nuevoPuntoWeb()
	{
		punto = new Punto();
	}
        public boolean esRepetido() {
		List<Punto> allPuntos = puntoService.getAllPuntos();
		for (Punto p : allPuntos) {
			if (p.getPunNombre().toLowerCase().equals(punto.getPunNombre())) {
				return true;
			}
		}
		return false;
	}	
    public Punto getPunto() {
        return punto;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    public List<Par> getParcito() {
        return parcito;
    }

    public void setParcito(List<Par> parcito) {
        this.parcito = parcito;
    }

    public List<classPunto> getLista() {
        return lista;
    }

    public void setLista(List<classPunto> lista) {
        this.lista = lista;
    }

    public List<classPar> getListapar() {
        return listapar;
    }

    public void setListapar(List<classPar> listapar) {
        this.listapar = listapar;
    }

    public int getFilaPunto() {
        return filaPunto;
    }

    public void setFilaPunto(int filaPunto) {
        this.filaPunto = filaPunto;
    }

    public int getFilaPar() {
        return filaPar;
    }

    public void setFilaPar(int filaPar) {
        this.filaPar = filaPar;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Punto getPuntoU() {
        return puntoU;
    }

    public void setPuntoU(Punto puntoU) {
        this.puntoU = puntoU;
    }

    public List<Punto> getPuntos() {
	return puntos;
    }
    
    public void LISTAR_PUNTOS()
    {
            if (query != null) {
			if (!query.isEmpty()) {
				puntos = new ArrayList<>() ;
			}
		}
		if (asc != 0 || desc != 0 || goiz != 0) {
			puntos = new ArrayList<>() ;
		}
                puntos = puntoService.getAllPuntosRastreables();
    }

    public void setPuntos(List<Punto> puntos) {
        this.puntos = puntos;
    }

    public String getPuntonombre() {
        return puntonombre;
    }

    public void setPuntonombre(String puntonombre) {
        this.puntonombre = puntonombre;
    }
    
      
}
