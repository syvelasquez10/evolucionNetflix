// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable$Creator;

public class FieldOnlyFilter extends AbstractFilter
{
    public static final Parcelable$Creator<FieldOnlyFilter> CREATOR;
    final int BR;
    final MetadataBundle QD;
    private final MetadataField<?> QE;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    FieldOnlyFilter(final int br, final MetadataBundle qd) {
        this.BR = br;
        this.QD = qd;
        this.QE = e.b(qd);
    }
    
    public FieldOnlyFilter(final SearchableMetadataField<?> searchableMetadataField) {
        this(1, MetadataBundle.a(searchableMetadataField, null));
    }
    
    @Override
    public <T> T a(final f<T> f) {
        return f.d(this.QE);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
