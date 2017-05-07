// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import com.google.android.gms.internal.ee;
import java.util.HashSet;
import java.util.Set;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Iterator;
import android.util.Log;
import java.util.ArrayList;
import com.google.android.gms.internal.eg;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class MetadataBundle implements SafeParcelable
{
    public static final Parcelable$Creator<MetadataBundle> CREATOR;
    final int kg;
    final Bundle rF;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    MetadataBundle(final int kg, final Bundle bundle) {
        this.kg = kg;
        (this.rF = eg.f(bundle)).setClassLoader(this.getClass().getClassLoader());
        final ArrayList<String> list = new ArrayList<String>();
        for (final String s : this.rF.keySet()) {
            if (c.ac(s) == null) {
                list.add(s);
                Log.w("MetadataBundle", "Ignored unknown metadata field in bundle: " + s);
            }
        }
        final Iterator<Object> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            this.rF.remove((String)iterator2.next());
        }
    }
    
    private MetadataBundle(final Bundle bundle) {
        this(1, bundle);
    }
    
    public static <T> MetadataBundle a(final MetadataField<T> metadataField, final T t) {
        final MetadataBundle cx = cX();
        cx.b(metadataField, t);
        return cx;
    }
    
    public static MetadataBundle a(final MetadataBundle metadataBundle) {
        return new MetadataBundle(new Bundle(metadataBundle.rF));
    }
    
    public static MetadataBundle cX() {
        return new MetadataBundle(new Bundle());
    }
    
    public <T> T a(final MetadataField<T> metadataField) {
        return metadataField.d(this.rF);
    }
    
    public <T> void b(final MetadataField<T> metadataField, final T t) {
        if (c.ac(metadataField.getName()) == null) {
            throw new IllegalArgumentException("Unregistered field: " + metadataField.getName());
        }
        metadataField.a(t, this.rF);
    }
    
    public Set<MetadataField<?>> cY() {
        final HashSet<MetadataField<?>> set = new HashSet<MetadataField<?>>();
        final Iterator<String> iterator = this.rF.keySet().iterator();
        while (iterator.hasNext()) {
            set.add(c.ac(iterator.next()));
        }
        return set;
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
        final Set keySet = this.rF.keySet();
        if (!keySet.equals(metadataBundle.rF.keySet())) {
            return false;
        }
        for (final String s : keySet) {
            if (!ee.equal(this.rF.get(s), metadataBundle.rF.get(s))) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final Iterator<String> iterator = this.rF.keySet().iterator();
        int n = 1;
        while (iterator.hasNext()) {
            n = this.rF.get((String)iterator.next()).hashCode() + n * 31;
        }
        return n;
    }
    
    @Override
    public String toString() {
        return "MetadataBundle [values=" + this.rF + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
