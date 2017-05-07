// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FieldOnlyFilter implements SafeParcelable, Filter
{
    public static final Parcelable$Creator<FieldOnlyFilter> CREATOR;
    final int kg;
    final MetadataBundle rS;
    private final MetadataField<?> rT;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    FieldOnlyFilter(final int kg, final MetadataBundle rs) {
        this.kg = kg;
        this.rS = rs;
        this.rT = d.b(rs);
    }
    
    public FieldOnlyFilter(final MetadataField<?> metadataField) {
        this(1, MetadataBundle.a(metadataField, null));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
