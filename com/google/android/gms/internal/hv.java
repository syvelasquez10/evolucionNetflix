// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collection;
import java.util.Arrays;
import android.content.Context;
import java.util.ArrayList;

public class hv
{
    private String[] DT;
    private String DU;
    private String DV;
    private String DW;
    private ArrayList<String> DY;
    private String[] DZ;
    private String jG;
    
    public hv(final Context context) {
        this.DY = new ArrayList<String>();
        this.DV = context.getPackageName();
        this.DU = context.getPackageName();
        this.DY.add("https://www.googleapis.com/auth/plus.login");
    }
    
    public hv aA(final String jg) {
        this.jG = jg;
        return this;
    }
    
    public hv d(final String... array) {
        this.DY.clear();
        this.DY.addAll(Arrays.asList(array));
        return this;
    }
    
    public hv e(final String... dz) {
        this.DZ = dz;
        return this;
    }
    
    public hv eY() {
        this.DY.clear();
        return this;
    }
    
    public hu eZ() {
        if (this.jG == null) {
            this.jG = "<<default account>>";
        }
        return new hu(this.jG, this.DY.toArray(new String[this.DY.size()]), this.DZ, this.DT, this.DU, this.DV, this.DW);
    }
}
