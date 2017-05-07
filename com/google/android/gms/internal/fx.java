// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class fx implements SafeParcelable, b<String, Integer>
{
    public static final fy CREATOR;
    private final HashMap<String, Integer> DT;
    private final HashMap<Integer, String> DU;
    private final ArrayList<a> DV;
    private final int xH;
    
    static {
        CREATOR = new fy();
    }
    
    public fx() {
        this.xH = 1;
        this.DT = new HashMap<String, Integer>();
        this.DU = new HashMap<Integer, String>();
        this.DV = null;
    }
    
    fx(final int xh, final ArrayList<a> list) {
        this.xH = xh;
        this.DT = new HashMap<String, Integer>();
        this.DU = new HashMap<Integer, String>();
        this.DV = null;
        this.a(list);
    }
    
    private void a(final ArrayList<a> list) {
        for (final a a : list) {
            this.f(a.DW, a.DX);
        }
    }
    
    public String a(final Integer n) {
        String s2;
        final String s = s2 = this.DU.get(n);
        if (s == null) {
            s2 = s;
            if (this.DT.containsKey("gms_unknown")) {
                s2 = "gms_unknown";
            }
        }
        return s2;
    }
    
    public int describeContents() {
        final fy creator = fx.CREATOR;
        return 0;
    }
    
    ArrayList<a> eV() {
        final ArrayList<a> list = new ArrayList<a>();
        for (final String s : this.DT.keySet()) {
            list.add(new a(s, this.DT.get(s)));
        }
        return list;
    }
    
    @Override
    public int eW() {
        return 7;
    }
    
    @Override
    public int eX() {
        return 0;
    }
    
    public fx f(final String s, final int n) {
        this.DT.put(s, n);
        this.DU.put(n, s);
        return this;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final fy creator = fx.CREATOR;
        fy.a(this, parcel, n);
    }
    
    public static final class a implements SafeParcelable
    {
        public static final fz CREATOR;
        final String DW;
        final int DX;
        final int versionCode;
        
        static {
            CREATOR = new fz();
        }
        
        a(final int versionCode, final String dw, final int dx) {
            this.versionCode = versionCode;
            this.DW = dw;
            this.DX = dx;
        }
        
        a(final String dw, final int dx) {
            this.versionCode = 1;
            this.DW = dw;
            this.DX = dx;
        }
        
        public int describeContents() {
            final fz creator = a.CREATOR;
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final fz creator = a.CREATOR;
            fz.a(this, parcel, n);
        }
    }
}
