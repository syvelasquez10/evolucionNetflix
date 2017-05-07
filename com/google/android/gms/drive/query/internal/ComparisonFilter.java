// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ComparisonFilter<T> implements SafeParcelable, Filter
{
    public static final a CREATOR;
    final Operator GG;
    final MetadataBundle GH;
    final MetadataField<T> GI;
    final int xH;
    
    static {
        CREATOR = new a();
    }
    
    ComparisonFilter(final int xh, final Operator gg, final MetadataBundle gh) {
        this.xH = xh;
        this.GG = gg;
        this.GH = gh;
        this.GI = (MetadataField<T>)e.b(gh);
    }
    
    public ComparisonFilter(final Operator operator, final SearchableMetadataField<T> searchableMetadataField, final T t) {
        this(1, operator, MetadataBundle.a(searchableMetadataField, t));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        a.a(this, parcel, n);
    }
}
