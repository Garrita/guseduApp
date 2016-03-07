// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(5) braces fieldsfirst noctor nonlb space lnc 
// Source File Name:   classPar.java

package com.gusedu.bean;

import com.gusedu.model.Par;

public class classPar {

            private Par par1;
            private Par par2;
            private Par par3;
            private Par par4;


            public classPar(Par par1, Par par2, Par par3, Par par4) {
/*  19*/        setPar1(par1);
/*  20*/        setPar2(par2);
/*  21*/        setPar3(par3);
/*  22*/        setPar4(par4);
            }

            public Par getPar1() {
/*  26*/        return par1;
            }

            public void setPar1(Par par1) {
/*  30*/        this.par1 = par1;
            }

            public Par getPar2() {
/*  34*/        return par2;
            }

            public void setPar2(Par par2) {
/*  38*/        this.par2 = par2;
            }

            public Par getPar3() {
/*  42*/        return par3;
            }

            public void setPar3(Par par3) {
/*  46*/        this.par3 = par3;
            }

            public Par getPar4() {
/*  50*/        return par4;
            }

            public void setPar4(Par par4) {
/*  54*/        this.par4 = par4;
            }
}
