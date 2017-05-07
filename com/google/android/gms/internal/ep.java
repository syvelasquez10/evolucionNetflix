// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ep implements SafeParcelable, b<String, Integer>
{
    public static final eq CREATOR;
    private final int kg;
    private final HashMap<String, Integer> qd;
    private final HashMap<Integer, String> qe;
    private final ArrayList<a> qf;
    
    static {
        CREATOR = new eq();
    }
    
    public ep() {
        this.kg = 1;
        this.qd = new HashMap<String, Integer>();
        this.qe = new HashMap<Integer, String>();
        this.qf = null;
    }
    
    ep(final int kg, final ArrayList<a> list) {
        this.kg = kg;
        this.qd = new HashMap<String, Integer>();
        this.qe = new HashMap<Integer, String>();
        this.qf = null;
        this.a(list);
    }
    
    private void a(final ArrayList<a> list) {
        for (final a a : list) {
            this.c(a.qg, a.qh);
        }
    }
    
    public String a(final Integer n) {
        String s2;
        final String s = s2 = this.qe.get(n);
        if (s == null) {
            s2 = s;
            if (this.qd.containsKey("gms_unknown")) {
                s2 = "gms_unknown";
            }
        }
        return s2;
    }
    
    public ep c(final String s, final int n) {
        this.qd.put(s, n);
        this.qe.put(n, s);
        return this;
    }
    
    ArrayList<a> cg() {
        final ArrayList<a> list = new ArrayList<a>();
        for (final String s : this.qd.keySet()) {
            list.add(new a(s, this.qd.get(s)));
        }
        return list;
    }
    
    @Override
    public int ch() {
        return 7;
    }
    
    @Override
    public int ci() {
        return 0;
    }
    
    public int describeContents() {
        final eq creator = ep.CREATOR;
        return 0;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final eq creator = ep.CREATOR;
        eq.a(this, parcel, n);
    }
    
    public static final class a implements SafeParcelable
    {
        public static final er CREATOR;
        final String qg;
        final int qh;
        final int versionCode;
        
        static {
            CREATOR = new er();
        }
        
        a(final int versionCode, final String qg, final int qh) {
            this.versionCode = versionCode;
            this.qg = qg;
            this.qh = qh;
        }
        
        a(final String qg, final int qh) {
            this.versionCode = 1;
            this.qg = qg;
            this.qh = qh;
        }
        
        public int describeContents() {
            final er creator = a.CREATOR;
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final er creator = a.CREATOR;
            er.a(this, parcel, n);
        }
    }
}
