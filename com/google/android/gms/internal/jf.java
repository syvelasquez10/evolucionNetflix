// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class jf implements SafeParcelable, ji$b<String, Integer>
{
    public static final jg CREATOR;
    private final int BR;
    private final HashMap<String, Integer> Ml;
    private final HashMap<Integer, String> Mm;
    private final ArrayList<jf$a> Mn;
    
    static {
        CREATOR = new jg();
    }
    
    public jf() {
        this.BR = 1;
        this.Ml = new HashMap<String, Integer>();
        this.Mm = new HashMap<Integer, String>();
        this.Mn = null;
    }
    
    jf(final int br, final ArrayList<jf$a> list) {
        this.BR = br;
        this.Ml = new HashMap<String, Integer>();
        this.Mm = new HashMap<Integer, String>();
        this.Mn = null;
        this.b(list);
    }
    
    private void b(final ArrayList<jf$a> list) {
        for (final jf$a jf$a : list) {
            this.h(jf$a.Mo, jf$a.Mp);
        }
    }
    
    public String a(final Integer n) {
        String s2;
        final String s = s2 = this.Mm.get(n);
        if (s == null) {
            s2 = s;
            if (this.Ml.containsKey("gms_unknown")) {
                s2 = "gms_unknown";
            }
        }
        return s2;
    }
    
    public int describeContents() {
        final jg creator = jf.CREATOR;
        return 0;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public jf h(final String s, final int n) {
        this.Ml.put(s, n);
        this.Mm.put(n, s);
        return this;
    }
    
    ArrayList<jf$a> hc() {
        final ArrayList<jf$a> list = new ArrayList<jf$a>();
        for (final String s : this.Ml.keySet()) {
            list.add(new jf$a(s, this.Ml.get(s)));
        }
        return list;
    }
    
    @Override
    public int hd() {
        return 7;
    }
    
    @Override
    public int he() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final jg creator = jf.CREATOR;
        jg.a(this, parcel, n);
    }
}
