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
        final int p3 = com.google.android.gms.common.internal.safeparcel.b.p(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, fieldOnlyFilter.xH);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable)fieldOnlyFilter.GH, n, false);
        com.google.android.gms.common.internal.safeparcel.b.F(parcel, p3);
    }
    
    public FieldOnlyFilter[] aJ(final int n) {
        return new FieldOnlyFilter[n];
    }
    
    public FieldOnlyFilter af(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 1: {
                    metadataBundle = a.a(parcel, n, MetadataBundle.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new FieldOnlyFilter(g, metadataBundle);
    }
}
