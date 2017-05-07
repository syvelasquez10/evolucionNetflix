// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import java.util.Collection;
import java.util.Arrays;
import android.content.Context;
import java.util.ArrayList;

public class i
{
    private String[] Um;
    private String Un;
    private String Uo;
    private String Up;
    private PlusCommonExtras Ur;
    private final ArrayList<String> Us;
    private String[] Ut;
    private String wG;
    
    public i(final Context context) {
        this.Us = new ArrayList<String>();
        this.Uo = context.getPackageName();
        this.Un = context.getPackageName();
        this.Ur = new PlusCommonExtras();
        this.Us.add("https://www.googleapis.com/auth/plus.login");
    }
    
    public i bh(final String wg) {
        this.wG = wg;
        return this;
    }
    
    public i e(final String... array) {
        this.Us.clear();
        this.Us.addAll(Arrays.asList(array));
        return this;
    }
    
    public i f(final String... ut) {
        this.Ut = ut;
        return this;
    }
    
    public i iY() {
        this.Us.clear();
        return this;
    }
    
    public h iZ() {
        if (this.wG == null) {
            this.wG = "<<default account>>";
        }
        return new h(this.wG, this.Us.toArray(new String[this.Us.size()]), this.Ut, this.Um, this.Un, this.Uo, this.Up, this.Ur);
    }
}
