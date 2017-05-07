// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import com.google.android.gms.internal.kd;
import com.google.android.gms.common.data.a;
import android.content.Context;
import java.util.HashSet;
import java.util.Set;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Iterator;
import com.google.android.gms.drive.internal.v;
import java.util.ArrayList;
import com.google.android.gms.common.internal.n;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MetadataBundle implements SafeParcelable
{
    public static final Parcelable$Creator<MetadataBundle> CREATOR;
    final int BR;
    final Bundle PD;
    
    static {
        CREATOR = (Parcelable$Creator)new h();
    }
    
    MetadataBundle(final int br, final Bundle bundle) {
        this.BR = br;
        (this.PD = n.i(bundle)).setClassLoader(this.getClass().getClassLoader());
        final ArrayList<String> list = new ArrayList<String>();
        for (final String s : this.PD.keySet()) {
            if (e.bj(s) == null) {
                list.add(s);
                v.p("MetadataBundle", "Ignored unknown metadata field in bundle: " + s);
            }
        }
        final Iterator<Object> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            this.PD.remove((String)iterator2.next());
        }
    }
    
    private MetadataBundle(final Bundle bundle) {
        this(1, bundle);
    }
    
    public static <T> MetadataBundle a(final MetadataField<T> metadataField, final T t) {
        final MetadataBundle io = io();
        io.b(metadataField, t);
        return io;
    }
    
    public static MetadataBundle a(final MetadataBundle metadataBundle) {
        return new MetadataBundle(new Bundle(metadataBundle.PD));
    }
    
    public static MetadataBundle io() {
        return new MetadataBundle(new Bundle());
    }
    
    public <T> T a(final MetadataField<T> metadataField) {
        return metadataField.f(this.PD);
    }
    
    public <T> void b(final MetadataField<T> metadataField, final T t) {
        if (e.bj(metadataField.getName()) == null) {
            throw new IllegalArgumentException("Unregistered field: " + metadataField.getName());
        }
        metadataField.a(t, this.PD);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MetadataBundle)) {
            return false;
        }
        final MetadataBundle metadataBundle = (MetadataBundle)o;
        final Set keySet = this.PD.keySet();
        if (!keySet.equals(metadataBundle.PD.keySet())) {
            return false;
        }
        for (final String s : keySet) {
            if (!m.equal(this.PD.get(s), metadataBundle.PD.get(s))) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final Iterator<String> iterator = this.PD.keySet().iterator();
        int n = 1;
        while (iterator.hasNext()) {
            n = this.PD.get((String)iterator.next()).hashCode() + n * 31;
        }
        return n;
    }
    
    public Set<MetadataField<?>> ip() {
        final HashSet<MetadataField<?>> set = new HashSet<MetadataField<?>>();
        final Iterator<String> iterator = this.PD.keySet().iterator();
        while (iterator.hasNext()) {
            set.add(e.bj(iterator.next()));
        }
        return set;
    }
    
    public void setContext(final Context context) {
        final a a = this.a(kd.Qd);
        if (a != null) {
            a.a(context.getCacheDir());
        }
    }
    
    @Override
    public String toString() {
        return "MetadataBundle [values=" + this.PD + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
