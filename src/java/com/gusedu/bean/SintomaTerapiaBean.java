/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gusedu.bean;

import com.gusedu.dao.SintomaService;
import com.gusedu.dao.impl.SintomaServiceImpl;
import com.gusedu.entidad.ESintomaTerapia;
import com.gusedu.model.Cliente;
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
public class SintomaTerapiaBean {

              private List rowNames;
            private List colNames;
            private ArrayList data3D;
            private List listaFechas;
            private List listaSintomas;
            private List listaESintomaTerapia;
            SintomaService sintomaService;

            public SintomaTerapiaBean() {
/*  29*/        rowNames = new ArrayList();
/*  30*/        colNames = new ArrayList();
/*  31*/        data3D = new ArrayList();
/*  41*/        sintomaService = new SintomaServiceImpl();
            }

            public List getRowNames() {
/*  45*/        return rowNames;
            }

            public void setRowNames(List rowNames) {
/*  49*/        this.rowNames = rowNames;
            }

            public List getColNames() {
/*  53*/        return colNames;
            }

            public void setColNames(List colNames) {
/*  57*/        this.colNames = colNames;
            }

            public ArrayList getData3D() {
/*  61*/        return data3D;
            }

            public void setData3D(ArrayList data3D) {
/*  65*/        this.data3D = data3D;
            }

            public List getListaFechas() {
/*  69*/        return listaFechas;
            }

            public void setListaFechas(List listaFechas) {
/*  73*/        this.listaFechas = listaFechas;
            }

            public List getListaSintomas() {
/*  77*/        return listaSintomas;
            }

            public void setListaSintomas(List listaSintomas) {
/*  81*/        this.listaSintomas = listaSintomas;
            }

            public List getListaESintomaTerapia() {
/*  85*/        return listaESintomaTerapia;
            }

            public void setListaESintomaTerapia(List listaESintomaTerapia) {
/*  89*/        this.listaESintomaTerapia = listaESintomaTerapia;
            }

            public void llenamatriz() {
/*  96*/        data3D = new ArrayList();
/*  97*/        rowNames = new ArrayList();
/*  98*/        colNames = new ArrayList();
/*  99*/        FacesContext fc = FacesContext.getCurrentInstance();
/* 100*/        Cliente cli = (Cliente)fc.getExternalContext().getSessionMap().get("cliente");
/* 103*/        listaFechas = new ArrayList();
/* 104*/        listaSintomas = new ArrayList();
/* 106*/        List all = new ArrayList();
/* 107*/        all = sintomaService.SP_MatrizSintomaxTerapia(cli.getCliCodigo());
/* 108*/        System.out.println((new StringBuilder()).append("Codigo de Cliente : ").append(cli.getCliCodigo()).append("\nAll : ").append(all.size()).toString());
/* 109*/        for (int i = 0; i < all.size(); i++) {
/* 111*/            if (!listaFechas.contains(((ESintomaTerapia)all.get(i)).getFecha())) {
/* 113*/                listaFechas.add(((ESintomaTerapia)all.get(i)).getFecha());
                    }
                }

/* 116*/        for (int j = 0; j < all.size(); j++) {
/* 118*/            if (!listaSintomas.contains(((ESintomaTerapia)all.get(j)).getSin_descripcion())) {
/* 120*/                listaSintomas.add(((ESintomaTerapia)all.get(j)).getSin_descripcion());
                    }
                }

/* 124*/        for (int j = 0; j < listaSintomas.size(); j++) {
/* 125*/            rowNames.add(listaSintomas.get(j));
                }

/* 128*/        System.out.println(listaFechas.size());
/* 129*/        for (int i = 0; i < listaFechas.size(); i++) {
/* 131*/            colNames.add(listaFechas.get(i));
                }

/* 134*/        for (int i = 0; i < rowNames.size(); i++) {
/* 135*/            data3D.add(new ArrayList());
/* 136*/            for (int j = 0; j < colNames.size(); j++) {
/* 137*/                ((ArrayList)data3D.get(i)).add(new ArrayList());
                    }

                }

/* 142*/        for (int i = 0; i < listaSintomas.size(); i++) {
/* 144*/            for (int j = 0; j < listaFechas.size(); j++) {
/* 146*/                for (int a = 0; a < all.size(); a++) {
/* 148*/                    if (!Objects.equals(listaFechas.get(j), ((ESintomaTerapia)all.get(a)).getFecha()) || !Objects.equals(listaSintomas.get(i), ((ESintomaTerapia)all.get(a)).getSin_descripcion())) {
/* 153*/                        continue;
                            }
/* 153*/                    if (((ESintomaTerapia)all.get(a)).isEstado()) {
/* 155*/                        ((ArrayList)((ArrayList)data3D.get(i)).get(j)).add("Si");
                            } else {
/* 158*/                        ((ArrayList)((ArrayList)data3D.get(i)).get(j)).add("No");
                            }
                        }

                    }

                }

            }
}


