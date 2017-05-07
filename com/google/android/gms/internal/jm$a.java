// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class jm$a implements SafeParcelable
{
    public static final jo CREATOR;
    final ArrayList<jm$b> MD;
    final String className;
    final int versionCode;
    
    static {
        CREATOR = new jo();
    }
    
    jm$a(final int versionCode, final String className, final ArrayList<jm$b> md) {
        this.versionCode = versionCode;
        this.className = className;
        this.MD = md;
    }
    
    jm$a(final String className, final HashMap<String, ji$a<?, ?>> hashMap) {
        this.versionCode = 1;
        this.className = className;
        this.MD = a(hashMap);
    }
    
    private static ArrayList<jm$b> a(final HashMap<String, ji$a<?, ?>> hashMap) {
        if (hashMap == null) {
            return null;
        }
        final ArrayList<jm$b> list = new ArrayList<jm$b>();
        for (final String s : hashMap.keySet()) {
            list.add(new jm$b(s, hashMap.get(s)));
        }
        return list;
    }
    
    public int describeContents() {
        final jo creator = jm$a.CREATOR;
        return 0;
    }
    
    HashMap<String, ji$a<?, ?>> hw() {
        final HashMap<String, ji$a<?, ?>> hashMap = new HashMap<String, ji$a<?, ?>>();
        for (int size = this.MD.size(), i = 0; i < size; ++i) {
            final jm$b jm$b = this.MD.get(i);
            hashMap.put(jm$b.fv, jm$b.ME);
        }
        return hashMap;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final jo creator = jm$a.CREATOR;
        jo.a(this, parcel, n);
    }
}
