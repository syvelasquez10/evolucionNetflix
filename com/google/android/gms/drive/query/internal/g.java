// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<HasFilter>
{
    static void a(final HasFilter hasFilter, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1000, hasFilter.BR);
        b.a(parcel, 1, (Parcelable)hasFilter.QD, n, false);
        b.H(parcel, d);
    }
    
    public HasFilter aO(final Parcel parcel) {
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
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new HasFilter(g, metadataBundle);
    }
    
    public HasFilter[] ca(final int n) {
        return new HasFilter[n];
    }
}
