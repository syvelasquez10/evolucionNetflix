// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashSet;
import android.view.View;
import java.util.List;
import android.content.Context;

@ez
class u$b
{
    public final String lA;
    public final Context lB;
    public final k lC;
    public final gt lD;
    public bc lE;
    public gg lF;
    public gg lG;
    public ay lH;
    public fz lI;
    public fz$a lJ;
    public ga lK;
    public bf lL;
    public el lM;
    public eh lN;
    public et lO;
    public eu lP;
    public bt lQ;
    public bu lR;
    public List<String> lS;
    public ee lT;
    public ge lU;
    public View lV;
    public int lW;
    public boolean lX;
    private HashSet<ga> lY;
    public final u$a lz;
    
    public u$b(final Context lb, final ay lh, final String la, final gt ld) {
        this.lU = null;
        this.lV = null;
        this.lW = 0;
        this.lX = false;
        this.lY = null;
        if (lh.og) {
            this.lz = null;
        }
        else {
            (this.lz = new u$a(lb)).setMinimumWidth(lh.widthPixels);
            this.lz.setMinimumHeight(lh.heightPixels);
            this.lz.setVisibility(4);
        }
        this.lH = lh;
        this.lA = la;
        this.lB = lb;
        this.lD = ld;
        this.lC = new k(new w(this));
    }
    
    public void a(final HashSet<ga> ly) {
        this.lY = ly;
    }
    
    public HashSet<ga> au() {
        return this.lY;
    }
}
