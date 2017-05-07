// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;

class aj implements j
{
    String wh;
    double wi;
    int wj;
    int wk;
    int wl;
    int wm;
    Map<String, String> wn;
    
    aj() {
        this.wi = -1.0;
        this.wj = -1;
        this.wk = -1;
        this.wl = -1;
        this.wm = -1;
        this.wn = new HashMap<String, String>();
    }
    
    public String M(final String s) {
        final String s2 = this.wn.get(s);
        if (s2 != null) {
            return s2;
        }
        return s;
    }
    
    public boolean dj() {
        return this.wh != null;
    }
    
    public String dk() {
        return this.wh;
    }
    
    public boolean dl() {
        return this.wi >= 0.0;
    }
    
    public double dm() {
        return this.wi;
    }
    
    public boolean dn() {
        return this.wj >= 0;
    }
    
    public boolean do() {
        return this.wk != -1;
    }
    
    public boolean dp() {
        return this.wk == 1;
    }
    
    public boolean dq() {
        return this.wl != -1;
    }
    
    public boolean dr() {
        return this.wl == 1;
    }
    
    public boolean ds() {
        return this.wm == 1;
    }
    
    public int getSessionTimeout() {
        return this.wj;
    }
    
    public String h(final Activity activity) {
        return this.M(activity.getClass().getCanonicalName());
    }
}
