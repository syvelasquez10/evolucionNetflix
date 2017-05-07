// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ev implements SafeParcelable
{
    public static final ew CREATOR;
    private final int kg;
    private final HashMap<String, HashMap<String, es.a<?, ?>>> qs;
    private final ArrayList<a> qt;
    private final String qu;
    
    static {
        CREATOR = new ew();
    }
    
    ev(final int kg, final ArrayList<a> list, final String s) {
        this.kg = kg;
        this.qt = null;
        this.qs = b(list);
        this.qu = eg.f(s);
        this.cw();
    }
    
    public ev(final Class<? extends es> clazz) {
        this.kg = 1;
        this.qt = null;
        this.qs = new HashMap<String, HashMap<String, es.a<?, ?>>>();
        this.qu = clazz.getCanonicalName();
    }
    
    private static HashMap<String, HashMap<String, es.a<?, ?>>> b(final ArrayList<a> list) {
        final HashMap<String, HashMap<String, es.a<?, ?>>> hashMap = new HashMap<String, HashMap<String, es.a<?, ?>>>();
        for (int size = list.size(), i = 0; i < size; ++i) {
            final a a = list.get(i);
            hashMap.put(a.className, a.cA());
        }
        return hashMap;
    }
    
    public HashMap<String, es.a<?, ?>> Z(final String s) {
        return this.qs.get(s);
    }
    
    public void a(final Class<? extends es> clazz, final HashMap<String, es.a<?, ?>> hashMap) {
        this.qs.put(clazz.getCanonicalName(), hashMap);
    }
    
    public boolean b(final Class<? extends es> clazz) {
        return this.qs.containsKey(clazz.getCanonicalName());
    }
    
    public void cw() {
        final Iterator<String> iterator = this.qs.keySet().iterator();
        while (iterator.hasNext()) {
            final HashMap<String, es.a<?, ?>> hashMap = this.qs.get(iterator.next());
            final Iterator<String> iterator2 = hashMap.keySet().iterator();
            while (iterator2.hasNext()) {
                ((es.a)hashMap.get(iterator2.next())).a(this);
            }
        }
    }
    
    public void cx() {
        for (final String s : this.qs.keySet()) {
            final HashMap<String, es.a<?, ?>> hashMap = this.qs.get(s);
            final HashMap<String, es.a<?, ?>> hashMap2 = new HashMap<String, es.a<?, ?>>();
            for (final String s2 : hashMap.keySet()) {
                hashMap2.put(s2, hashMap.get(s2).cm());
            }
            this.qs.put(s, hashMap2);
        }
    }
    
    ArrayList<a> cy() {
        final ArrayList<a> list = new ArrayList<a>();
        for (final String s : this.qs.keySet()) {
            list.add(new a(s, this.qs.get(s)));
        }
        return list;
    }
    
    public String cz() {
        return this.qu;
    }
    
    public int describeContents() {
        final ew creator = ev.CREATOR;
        return 0;
    }
    
    int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final String s : this.qs.keySet()) {
            sb.append(s).append(":\n");
            final HashMap<String, es.a<?, ?>> hashMap = this.qs.get(s);
            for (final String s2 : hashMap.keySet()) {
                sb.append("  ").append(s2).append(": ");
                sb.append(hashMap.get(s2));
            }
        }
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ew creator = ev.CREATOR;
        ew.a(this, parcel, n);
    }
    
    public static class a implements SafeParcelable
    {
        public static final ex CREATOR;
        final String className;
        final ArrayList<b> qv;
        final int versionCode;
        
        static {
            CREATOR = new ex();
        }
        
        a(final int versionCode, final String className, final ArrayList<b> qv) {
            this.versionCode = versionCode;
            this.className = className;
            this.qv = qv;
        }
        
        a(final String className, final HashMap<String, es.a<?, ?>> hashMap) {
            this.versionCode = 1;
            this.className = className;
            this.qv = b(hashMap);
        }
        
        private static ArrayList<b> b(final HashMap<String, es.a<?, ?>> hashMap) {
            if (hashMap == null) {
                return null;
            }
            final ArrayList<b> list = new ArrayList<b>();
            for (final String s : hashMap.keySet()) {
                list.add(new b(s, hashMap.get(s)));
            }
            return list;
        }
        
        HashMap<String, es.a<?, ?>> cA() {
            final HashMap<String, es.a<?, ?>> hashMap = new HashMap<String, es.a<?, ?>>();
            for (int size = this.qv.size(), i = 0; i < size; ++i) {
                final b b = this.qv.get(i);
                hashMap.put(b.qw, b.qx);
            }
            return hashMap;
        }
        
        public int describeContents() {
            final ex creator = a.CREATOR;
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final ex creator = a.CREATOR;
            ex.a(this, parcel, n);
        }
    }
    
    public static class b implements SafeParcelable
    {
        public static final eu CREATOR;
        final String qw;
        final es.a<?, ?> qx;
        final int versionCode;
        
        static {
            CREATOR = new eu();
        }
        
        b(final int versionCode, final String qw, final es.a<?, ?> qx) {
            this.versionCode = versionCode;
            this.qw = qw;
            this.qx = qx;
        }
        
        b(final String qw, final es.a<?, ?> qx) {
            this.versionCode = 1;
            this.qw = qw;
            this.qx = qx;
        }
        
        public int describeContents() {
            final eu creator = b.CREATOR;
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final eu creator = b.CREATOR;
            eu.a(this, parcel, n);
        }
    }
}
