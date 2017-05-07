// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<FieldOnlyFilter>
{
    static void a(final FieldOnlyFilter fieldOnlyFilter, final Parcel parcel, final int n) {
        final int o = com.google.android.gms.common.internal.safeparcel.b.o(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, fieldOnlyFilter.kg);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable)fieldOnlyFilter.rS, n, false);
        com.google.android.gms.common.internal.safeparcel.b.D(parcel, o);
    }
    
    public FieldOnlyFilter S(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 1: {
                    metadataBundle = a.a(parcel, m, MetadataBundle.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new FieldOnlyFilter(g, metadataBundle);
    }
    
    public FieldOnlyFilter[] as(final int n) {
        return new FieldOnlyFilter[n];
    }
}
