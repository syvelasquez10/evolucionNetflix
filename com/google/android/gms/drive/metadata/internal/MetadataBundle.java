// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import java.util.HashSet;
import java.util.Set;
import com.google.android.gms.internal.fo;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Iterator;
import android.util.Log;
import java.util.ArrayList;
import com.google.android.gms.internal.fq;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MetadataBundle implements SafeParcelable
{
    public static final Parcelable$Creator<MetadataBundle> CREATOR;
    final Bundle FQ;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new f();
    }
    
    MetadataBundle(final int xh, final Bundle bundle) {
        this.xH = xh;
        (this.FQ = fq.f(bundle)).setClassLoader(this.getClass().getClassLoader());
        final ArrayList<String> list = new ArrayList<String>();
        for (final String s : this.FQ.keySet()) {
            if (c.ax(s) == null) {
                list.add(s);
                Log.w("MetadataBundle", "Ignored unknown metadata field in bundle: " + s);
            }
        }
        final Iterator<Object> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            this.FQ.remove((String)iterator2.next());
        }
    }
    
    private MetadataBundle(final Bundle bundle) {
        this(1, bundle);
    }
    
    public static <T> MetadataBundle a(final MetadataField<T> metadataField, final T t) {
        final MetadataBundle ft = fT();
        ft.b(metadataField, t);
        return ft;
    }
    
    public static MetadataBundle a(final MetadataBundle metadataBundle) {
        return new MetadataBundle(new Bundle(metadataBundle.FQ));
    }
    
    public static MetadataBundle fT() {
        return new MetadataBundle(new Bundle());
    }
    
    public <T> T a(final MetadataField<T> metadataField) {
        return metadataField.d(this.FQ);
    }
    
    public <T> void b(final MetadataField<T> metadataField, final T t) {
        if (c.ax(metadataField.getName()) == null) {
            throw new IllegalArgumentException("Unregistered field: " + metadataField.getName());
        }
        metadataField.a(t, this.FQ);
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
        final Set keySet = this.FQ.keySet();
        if (!keySet.equals(metadataBundle.FQ.keySet())) {
            return false;
        }
        for (final String s : keySet) {
            if (!fo.equal(this.FQ.get(s), metadataBundle.FQ.get(s))) {
                return false;
            }
        }
        return true;
    }
    
    public Set<MetadataField<?>> fU() {
        final HashSet<MetadataField<?>> set = new HashSet<MetadataField<?>>();
        final Iterator<String> iterator = this.FQ.keySet().iterator();
        while (iterator.hasNext()) {
            set.add(c.ax(iterator.next()));
        }
        return set;
    }
    
    @Override
    public int hashCode() {
        final Iterator<String> iterator = this.FQ.keySet().iterator();
        int n = 1;
        while (iterator.hasNext()) {
            n = this.FQ.get((String)iterator.next()).hashCode() + n * 31;
        }
        return n;
    }
    
    @Override
    public String toString() {
        return "MetadataBundle [values=" + this.FQ + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
