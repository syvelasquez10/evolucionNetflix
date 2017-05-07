// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import java.util.Collection;
import java.util.Set;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Collections;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class InFilter<T> extends AbstractFilter
{
    public static final h CREATOR;
    final int BR;
    final MetadataBundle QD;
    private final b<T> QO;
    
    static {
        CREATOR = new h();
    }
    
    InFilter(final int br, final MetadataBundle qd) {
        this.BR = br;
        this.QD = qd;
        this.QO = (b<T>)(b)e.b(qd);
    }
    
    public InFilter(final SearchableCollectionMetadataField<T> searchableCollectionMetadataField, final T t) {
        this(1, MetadataBundle.a(searchableCollectionMetadataField, Collections.singleton(t)));
    }
    
    @Override
    public <F> F a(final f<F> f) {
        return f.b(this.QO, this.getValue());
    }
    
    public int describeContents() {
        return 0;
    }
    
    public T getValue() {
        return this.QD.a(this.QO).iterator().next();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        h.a(this, parcel, n);
    }
}
