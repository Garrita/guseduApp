// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   TerapiaParService.java

package com.gusedu.dao;

import com.gusedu.model.TerapiaPar;

public interface TerapiaParService {

    public boolean saveTerapia(TerapiaPar terapiapar);

    public boolean updateTerapia(TerapiaPar terapiapar);

    public  boolean deleteTerapia(TerapiaPar terapiapar);
    
    public boolean SPsaveTerapiaPar(TerapiaPar terapiapar);
    
    //Terapia Par
    
    public TerapiaPar getByParameters(Integer terapiapar) ;
    
    public boolean deleteTerapiaPar(TerapiaPar terapiapar); 
}
