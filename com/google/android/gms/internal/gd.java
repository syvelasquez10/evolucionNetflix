// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class gd implements SafeParcelable
{
    public static final ge CREATOR;
    private final HashMap<String, HashMap<String, ga.a<?, ?>>> Ei;
    private final ArrayList<a> Ej;
    private final String Ek;
    private final int xH;
    
    static {
        CREATOR = new ge();
    }
    
    gd(final int xh, final ArrayList<a> list, final String s) {
        this.xH = xh;
        this.Ej = null;
        this.Ei = b(list);
        this.Ek = fq.f(s);
        this.fl();
    }
    
    public gd(final Class<? extends ga> clazz) {
        this.xH = 1;
        this.Ej = null;
        this.Ei = new HashMap<String, HashMap<String, ga.a<?, ?>>>();
        this.Ek = clazz.getCanonicalName();
    }
    
    private static HashMap<String, HashMap<String, ga.a<?, ?>>> b(final ArrayList<a> list) {
        final HashMap<String, HashMap<String, ga.a<?, ?>>> hashMap = new HashMap<String, HashMap<String, ga.a<?, ?>>>();
        for (int size = list.size(), i = 0; i < size; ++i) {
            final a a = list.get(i);
            hashMap.put(a.className, a.fp());
        }
        return hashMap;
    }
    
    public void a(final Class<? extends ga> clazz, final HashMap<String, ga.a<?, ?>> hashMap) {
        this.Ei.put(clazz.getCanonicalName(), hashMap);
    }
    
    public HashMap<String, ga.a<?, ?>> au(final String s) {
        return this.Ei.get(s);
    }
    
    public boolean b(final Class<? extends ga> clazz) {
        return this.Ei.containsKey(clazz.getCanonicalName());
    }
    
    public int describeContents() {
        final ge creator = gd.CREATOR;
        return 0;
    }
    
    public void fl() {
        final Iterator<String> iterator = this.Ei.keySet().iterator();
        while (iterator.hasNext()) {
            final HashMap<String, ga.a<?, ?>> hashMap = this.Ei.get(iterator.next());
            final Iterator<String> iterator2 = hashMap.keySet().iterator();
            while (iterator2.hasNext()) {
                ((ga.a)hashMap.get(iterator2.next())).a(this);
            }
        }
    }
    
    public void fm() {
        for (final String s : this.Ei.keySet()) {
            final HashMap<String, ga.a<?, ?>> hashMap = this.Ei.get(s);
            final HashMap<String, ga.a<?, ?>> hashMap2 = new HashMap<String, ga.a<?, ?>>();
            for (final String s2 : hashMap.keySet()) {
                hashMap2.put(s2, hashMap.get(s2).fb());
            }
            this.Ei.put(s, hashMap2);
        }
    }
    
    ArrayList<a> fn() {
        final ArrayList<a> list = new ArrayList<a>();
        for (final String s : this.Ei.keySet()) {
            list.add(new a(s, this.Ei.get(s)));
        }
        return list;
    }
    
    public String fo() {
        return this.Ek;
    }
    
    int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final String s : this.Ei.keySet()) {
            sb.append(s).append(":\n");
            final HashMap<String, ga.a<?, ?>> hashMap = this.Ei.get(s);
            for (final String s2 : hashMap.keySet()) {
                sb.append("  ").append(s2).append(": ");
                sb.append(hashMap.get(s2));
            }
        }
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final ge creator = gd.CREATOR;
        ge.a(this, parcel, n);
    }
    
    public static class a implements SafeParcelable
    {
        public static final gf CREATOR;
        final ArrayList<b> El;
        final String className;
        final int versionCode;
        
        static {
            CREATOR = new gf();
        }
        
        a(final int versionCode, final String className, final ArrayList<b> el) {
            this.versionCode = versionCode;
            this.className = className;
            this.El = el;
        }
        
        a(final String className, final HashMap<String, ga.a<?, ?>> hashMap) {
            this.versionCode = 1;
            this.className = className;
            this.El = b(hashMap);
        }
        
        private static ArrayList<b> b(final HashMap<String, ga.a<?, ?>> hashMap) {
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
            final gf creator = a.CREATOR;
            return 0;
        }
        
        HashMap<String, ga.a<?, ?>> fp() {
            final HashMap<String, ga.a<?, ?>> hashMap = new HashMap<String, ga.a<?, ?>>();
            for (int size = this.El.size(), i = 0; i < size; ++i) {
                final b b = this.El.get(i);
                hashMap.put(b.eM, b.Em);
            }
            return hashMap;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final gf creator = a.CREATOR;
            gf.a(this, parcel, n);
        }
    }
    
    public static class b implements SafeParcelable
    {
        public static final gc CREATOR;
        final ga.a<?, ?> Em;
        final String eM;
        final int versionCode;
        
        static {
            CREATOR = new gc();
        }
        
        b(final int versionCode, final String em, final ga.a<?, ?> em2) {
            this.versionCode = versionCode;
            this.eM = em;
            this.Em = em2;
        }
        
        b(final String em, final ga.a<?, ?> em2) {
            this.versionCode = 1;
            this.eM = em;
            this.Em = em2;
        }
        
        public int describeContents() {
            final gc creator = b.CREATOR;
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final gc creator = b.CREATOR;
            gc.a(this, parcel, n);
        }
    }
}
