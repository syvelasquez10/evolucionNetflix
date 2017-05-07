// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public class HasFilter<T> extends AbstractFilter
{
    public static final g CREATOR;
    final int BR;
    final MetadataBundle QD;
    final MetadataField<T> QE;
    
    static {
        CREATOR = new g();
    }
    
    HasFilter(final int br, final MetadataBundle qd) {
        this.BR = br;
        this.QD = qd;
        this.QE = (MetadataField<T>)e.b(qd);
    }
    
    @Override
    public <F> F a(final f<F> f) {
        return f.d(this.QE, this.getValue());
    }
    
    public int describeContents() {
        return 0;
    }
    
    public T getValue() {
        return this.QD.a(this.QE);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(this, parcel, n);
    }
}
