// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.List;

public class cr$f
{
    private final List<cr$a> aqA;
    private final List<cr$a> aqB;
    private final List<cr$a> aqC;
    private final List<String> aqD;
    private final List<String> aqE;
    private final List<String> aqF;
    private final List<String> aqG;
    private final List<cr$a> aqx;
    private final List<cr$a> aqy;
    private final List<cr$a> aqz;
    
    private cr$f() {
        this.aqx = new ArrayList<cr$a>();
        this.aqy = new ArrayList<cr$a>();
        this.aqz = new ArrayList<cr$a>();
        this.aqA = new ArrayList<cr$a>();
        this.aqB = new ArrayList<cr$a>();
        this.aqC = new ArrayList<cr$a>();
        this.aqD = new ArrayList<String>();
        this.aqE = new ArrayList<String>();
        this.aqF = new ArrayList<String>();
        this.aqG = new ArrayList<String>();
    }
    
    public cr$f b(final cr$a cr$a) {
        this.aqx.add(cr$a);
        return this;
    }
    
    public cr$f c(final cr$a cr$a) {
        this.aqy.add(cr$a);
        return this;
    }
    
    public cr$f cK(final String s) {
        this.aqF.add(s);
        return this;
    }
    
    public cr$f cL(final String s) {
        this.aqG.add(s);
        return this;
    }
    
    public cr$f cM(final String s) {
        this.aqD.add(s);
        return this;
    }
    
    public cr$f cN(final String s) {
        this.aqE.add(s);
        return this;
    }
    
    public cr$f d(final cr$a cr$a) {
        this.aqz.add(cr$a);
        return this;
    }
    
    public cr$f e(final cr$a cr$a) {
        this.aqA.add(cr$a);
        return this;
    }
    
    public cr$f f(final cr$a cr$a) {
        this.aqB.add(cr$a);
        return this;
    }
    
    public cr$f g(final cr$a cr$a) {
        this.aqC.add(cr$a);
        return this;
    }
    
    public cr$e pk() {
        return new cr$e(this.aqx, this.aqy, this.aqz, this.aqA, this.aqB, this.aqC, this.aqD, this.aqE, this.aqF, this.aqG, null);
    }
}
