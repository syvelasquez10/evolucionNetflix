// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class ComparisonFilter<T> extends AbstractFilter
{
    public static final a CREATOR;
    final int BR;
    final Operator QC;
    final MetadataBundle QD;
    final MetadataField<T> QE;
    
    static {
        CREATOR = new a();
    }
    
    ComparisonFilter(final int br, final Operator qc, final MetadataBundle qd) {
        this.BR = br;
        this.QC = qc;
        this.QD = qd;
        this.QE = (MetadataField<T>)e.b(qd);
    }
    
    public ComparisonFilter(final Operator operator, final SearchableMetadataField<T> searchableMetadataField, final T t) {
        this(1, operator, MetadataBundle.a(searchableMetadataField, t));
    }
    
    @Override
    public <F> F a(final f<F> f) {
        return f.b(this.QC, this.QE, this.getValue());
    }
    
    public int describeContents() {
        return 0;
    }
    
    public T getValue() {
        return this.QD.a(this.QE);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
