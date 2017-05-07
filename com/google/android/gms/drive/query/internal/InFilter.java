// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import java.util.Set;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Collections;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class InFilter<T> implements SafeParcelable, Filter
{
    public static final f CREATOR;
    final MetadataBundle GH;
    private final b<T> GR;
    final int xH;
    
    static {
        CREATOR = new f();
    }
    
    InFilter(final int xh, final MetadataBundle gh) {
        this.xH = xh;
        this.GH = gh;
        this.GR = (b<T>)(b)e.b(gh);
    }
    
    public InFilter(final SearchableCollectionMetadataField<T> searchableCollectionMetadataField, final T t) {
        this(1, MetadataBundle.a(searchableCollectionMetadataField, Collections.singleton(t)));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
