// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class jm implements SafeParcelable
{
    public static final jn CREATOR;
    private final int BR;
    private final HashMap<String, HashMap<String, ji.a<?, ?>>> MA;
    private final ArrayList<a> MB;
    private final String MC;
    
    static {
        CREATOR = new jn();
    }
    
    jm(final int br, final ArrayList<a> list, final String s) {
        this.BR = br;
        this.MB = null;
        this.MA = c(list);
        this.MC = n.i(s);
        this.hs();
    }
    
    public jm(final Class<? extends ji> clazz) {
        this.BR = 1;
        this.MB = null;
        this.MA = new HashMap<String, HashMap<String, ji.a<?, ?>>>();
        this.MC = clazz.getCanonicalName();
    }
    
    private static HashMap<String, HashMap<String, ji.a<?, ?>>> c(final ArrayList<a> list) {
        final HashMap<String, HashMap<String, ji.a<?, ?>>> hashMap = new HashMap<String, HashMap<String, ji.a<?, ?>>>();
        for (int size = list.size(), i = 0; i < size; ++i) {
            final a a = list.get(i);
            hashMap.put(a.className, a.hw());
        }
        return hashMap;
    }
    
    public void a(final Class<? extends ji> clazz, final HashMap<String, ji.a<?, ?>> hashMap) {
        this.MA.put(clazz.getCanonicalName(), hashMap);
    }
    
    public boolean b(final Class<? extends ji> clazz) {
        return this.MA.containsKey(clazz.getCanonicalName());
    }
    
    public HashMap<String, ji.a<?, ?>> be(final String s) {
        return this.MA.get(s);
    }
    
    public int describeContents() {
        final jn creator = jm.CREATOR;
        return 0;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    public void hs() {
        final Iterator<String> iterator = this.MA.keySet().iterator();
        while (iterator.hasNext()) {
            final HashMap<String, ji.a<?, ?>> hashMap = this.MA.get(iterator.next());
            final Iterator<String> iterator2 = hashMap.keySet().iterator();
            while (iterator2.hasNext()) {
                ((ji.a)hashMap.get(iterator2.next())).a(this);
            }
        }
    }
    
    public void ht() {
        for (final String s : this.MA.keySet()) {
            final HashMap<String, ji.a<?, ?>> hashMap = this.MA.get(s);
            final HashMap<String, ji.a<?, ?>> hashMap2 = new HashMap<String, ji.a<?, ?>>();
            for (final String s2 : hashMap.keySet()) {
                hashMap2.put(s2, hashMap.get(s2).hi());
            }
            this.MA.put(s, hashMap2);
        }
    }
    
    ArrayList<a> hu() {
        final ArrayList<a> list = new ArrayList<a>();
        for (final String s : this.MA.keySet()) {
            list.add(new a(s, this.MA.get(s)));
        }
        return list;
    }
    
    public String hv() {
        return this.MC;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final String s : this.MA.keySet()) {
            sb.append(s).append(":\n");
            final HashMap<String, ji.a<?, ?>> hashMap = this.MA.get(s);
            for (final String s2 : hashMap.keySet()) {
                sb.append("  ").append(s2).append(": ");
                sb.append(hashMap.get(s2));
            }
        }
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final jn creator = jm.CREATOR;
        jn.a(this, parcel, n);
    }
    
    public static class a implements SafeParcelable
    {
        public static final jo CREATOR;
        final ArrayList<b> MD;
        final String className;
        final int versionCode;
        
        static {
            CREATOR = new jo();
        }
        
        a(final int versionCode, final String className, final ArrayList<b> md) {
            this.versionCode = versionCode;
            this.className = className;
            this.MD = md;
        }
        
        a(final String className, final HashMap<String, ji.a<?, ?>> hashMap) {
            this.versionCode = 1;
            this.className = className;
            this.MD = a(hashMap);
        }
        
        private static ArrayList<b> a(final HashMap<String, ji.a<?, ?>> hashMap) {
            if (hashMap == null) {
                return null;
            }
            final ArrayList<b> list = new ArrayList<b>();
            for (final String s : hashMap.keySet()) {
                list.add(new b(s, hashMap.get(s)));
            }
            return list;
        }
        
        public int describeContents() {
            final jo creator = a.CREATOR;
            return 0;
        }
        
        HashMap<String, ji.a<?, ?>> hw() {
            final HashMap<String, ji.a<?, ?>> hashMap = new HashMap<String, ji.a<?, ?>>();
            for (int size = this.MD.size(), i = 0; i < size; ++i) {
                final b b = this.MD.get(i);
                hashMap.put(b.fv, b.ME);
            }
            return hashMap;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final jo creator = a.CREATOR;
            jo.a(this, parcel, n);
        }
    }
    
    public static class b implements SafeParcelable
    {
        public static final jl CREATOR;
        final ji.a<?, ?> ME;
        final String fv;
        final int versionCode;
        
        static {
            CREATOR = new jl();
        }
        
        b(final int versionCode, final String fv, final ji.a<?, ?> me) {
            this.versionCode = versionCode;
            this.fv = fv;
            this.ME = me;
        }
        
        b(final String fv, final ji.a<?, ?> me) {
            this.versionCode = 1;
            this.fv = fv;
            this.ME = me;
        }
        
        public int describeContents() {
            final jl creator = b.CREATOR;
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final jl creator = b.CREATOR;
            jl.a(this, parcel, n);
        }
    }
}
