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
    private String Dd;
    private final ArrayList<String> alA;
    private String[] alB;
    private String[] alu;
    private String alv;
    private String alw;
    private String alx;
    private PlusCommonExtras alz;
    
    public i(final Context context) {
        this.alA = new ArrayList<String>();
        this.alw = context.getPackageName();
        this.alv = context.getPackageName();
        this.alz = new PlusCommonExtras();
        this.alA.add("https://www.googleapis.com/auth/plus.login");
    }
    
    public i ce(final String dd) {
        this.Dd = dd;
        return this;
    }
    
    public i g(final String... array) {
        this.alA.clear();
        this.alA.addAll(Arrays.asList(array));
        return this;
    }
    
    public i h(final String... alB) {
        this.alB = alB;
        return this;
    }
    
    public i nn() {
        this.alA.clear();
        return this;
    }
    
    public h no() {
        if (this.Dd == null) {
            this.Dd = "<<default account>>";
        }
        return new h(this.Dd, this.alA.toArray(new String[this.alA.size()]), this.alB, this.alu, this.alv, this.alw, this.alx, this.alz);
    }
}
