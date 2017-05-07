// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;

class ai implements i
{
    String BC;
    double BD;
    int BE;
    int BF;
    int BG;
    int BH;
    Map<String, String> BI;
    
    ai() {
        this.BD = -1.0;
        this.BE = -1;
        this.BF = -1;
        this.BG = -1;
        this.BH = -1;
        this.BI = new HashMap<String, String>();
    }
    
    public String am(final String s) {
        final String s2 = this.BI.get(s);
        if (s2 != null) {
            return s2;
        }
        return s;
    }
    
    public boolean fa() {
        return this.BC != null;
    }
    
    public String fb() {
        return this.BC;
    }
    
    public boolean fc() {
        return this.BD >= 0.0;
    }
    
    public double fd() {
        return this.BD;
    }
    
    public boolean fe() {
        return this.BE >= 0;
    }
    
    public boolean ff() {
        return this.BF != -1;
    }
    
    public boolean fg() {
        return this.BF == 1;
    }
    
    public boolean fh() {
        return this.BG != -1;
    }
    
    public boolean fi() {
        return this.BG == 1;
    }
    
    public boolean fj() {
        return this.BH == 1;
    }
    
    public int getSessionTimeout() {
        return this.BE;
    }
    
    public String k(final Activity activity) {
        return this.am(activity.getClass().getCanonicalName());
    }
}
