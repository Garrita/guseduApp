// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   HistoriaClinicaService.java

package com.gusedu.dao;

import com.gusedu.model.HistoriaClinica;
import com.gusedu.model.Visita;

public interface HistoriaClinicaService {

    public  boolean saveHistoriaClinica(HistoriaClinica historiaclinica);

    public  HistoriaClinica getHistoriaByVisita(Visita visita);
}
