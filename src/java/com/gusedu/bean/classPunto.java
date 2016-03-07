// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   classPunto.java

package com.gusedu.bean;

import com.gusedu.model.Punto;

public class classPunto {

            private Punto punto1;
            private Punto punto2;
            private Punto punto3;
            private Punto punto4;


            public classPunto(Punto punto1, Punto punto2, Punto punto3, Punto punto4) {
/*  19*/        this.punto1 = punto1;
/*  20*/        this.punto2 = punto2;
/*  21*/        this.punto3 = punto3;
/*  22*/        this.punto4 = punto4;
            }

            public Punto getPunto1() {
/*  27*/        return punto1;
            }

            public void setPunto1(Punto punto1) {
/*  31*/        this.punto1 = punto1;
            }

            public Punto getPunto2() {
/*  35*/        return punto2;
            }

            public void setPunto2(Punto punto2) {
/*  39*/        this.punto2 = punto2;
            }

            public Punto getPunto3() {
/*  43*/        return punto3;
            }

            public void setPunto3(Punto punto3) {
/*  47*/        this.punto3 = punto3;
            }

            public Punto getPunto4() {
/*  51*/        return punto4;
            }

            public void setPunto4(Punto punto4) {
/*  55*/        this.punto4 = punto4;
            }
}
