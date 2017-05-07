// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FieldOnlyFilter implements SafeParcelable, Filter
{
    public static final Parcelable$Creator<FieldOnlyFilter> CREATOR;
    final MetadataBundle GH;
    private final MetadataField<?> GI;
    final int xH;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    FieldOnlyFilter(final int xh, final MetadataBundle gh) {
        this.xH = xh;
        this.GH = gh;
        this.GI = e.b(gh);
    }
    
    public FieldOnlyFilter(final SearchableMetadataField<?> searchableMetadataField) {
        this(1, MetadataBundle.a(searchableMetadataField, null));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
