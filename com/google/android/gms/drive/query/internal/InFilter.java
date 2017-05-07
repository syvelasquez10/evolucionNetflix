// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import java.util.Set;
import com.google.android.gms.drive.metadata.MetadataField;
import java.util.Collections;
import com.google.android.gms.drive.metadata.CollectionMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class InFilter<T> implements SafeParcelable, Filter
{
    public static final e CREATOR;
    final int kg;
    final MetadataBundle rS;
    private final CollectionMetadataField<T> sa;
    
    static {
        CREATOR = new e();
    }
    
    InFilter(final int kg, final MetadataBundle rs) {
        this.kg = kg;
        this.rS = rs;
        this.sa = (CollectionMetadataField<T>)(CollectionMetadataField)d.b(rs);
    }
    
    public InFilter(final CollectionMetadataField<T> collectionMetadataField, final T t) {
        this(1, MetadataBundle.a(collectionMetadataField, Collections.singleton(t)));
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
