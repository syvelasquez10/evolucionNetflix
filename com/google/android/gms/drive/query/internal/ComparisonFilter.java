// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ComparisonFilter<T> implements SafeParcelable, Filter
{
    public static final a CREATOR;
    final int kg;
    final Operator rR;
    final MetadataBundle rS;
    final MetadataField<T> rT;
    
    static {
        CREATOR = new a();
    }
    
    ComparisonFilter(final int kg, final Operator rr, final MetadataBundle rs) {
        this.kg = kg;
        this.rR = rr;
        this.rS = rs;
        this.rT = (MetadataField<T>)d.b(rs);
    }
    
    public ComparisonFilter(final Operator operator, final MetadataField<T> metadataField, final T t) {
        this(1, operator, MetadataBundle.a(metadataField, t));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
