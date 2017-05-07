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
        final int d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, fieldOnlyFilter.BR);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, (Parcelable)fieldOnlyFilter.QD, n, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public FieldOnlyFilter aL(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        MetadataBundle metadataBundle = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 1: {
                    metadataBundle = a.a(parcel, b, MetadataBundle.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new FieldOnlyFilter(g, metadataBundle);
    }
    
    public FieldOnlyFilter[] bX(final int n) {
        return new FieldOnlyFilter[n];
    }
}
