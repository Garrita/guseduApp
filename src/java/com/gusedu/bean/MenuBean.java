/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.util.StaticUtil;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author NV55C
 */
@ManagedBean
@SessionScoped
public class MenuBean {

    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
    }
    
    
    public String MENU_HISTORIAL_VISITAS()
    {
       Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		HistorialVisitaBean managedBean = (HistorialVisitaBean) sessionMap.get("historialVisitaBean");
		if (managedBean != null) {

			managedBean.LISTAR_HISTORIAL();
		}

        return "/web/HistorialVisita.jsf?faces-redirect=true";
    }
    
    public String MENU_CALENDARIO()
    {
        String salida="";
       Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		ScheduleView managedBean = (ScheduleView) sessionMap.get("scheduleView");
		if (managedBean != null) {

			managedBean.validarCalendario();
                
		}
        if(!"Terapeuta".equals(StaticUtil.usuario_tipo()))
        {
            salida="/web/Citas.jsf?faces-redirect=true";
        }else
        {
            salida="/web/terapeuta/Citas.jsf?faces-redirect=true\"";
        }

        return salida;
    }
}
