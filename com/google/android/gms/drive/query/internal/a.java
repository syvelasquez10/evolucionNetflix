// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<ComparisonFilter>
{
    static void a(final ComparisonFilter comparisonFilter, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1000, comparisonFilter.xH);
        b.a(parcel, 1, (Parcelable)comparisonFilter.GG, n, false);
        b.a(parcel, 2, (Parcelable)comparisonFilter.GH, n, false);
        b.F(parcel, p3);
    }
    
    public ComparisonFilter[] aI(final int n) {
        return new ComparisonFilter[n];
    }
    
    public ComparisonFilter ae(final Parcel parcel) {
        MetadataBundle metadataBundle = null;
        final int o = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int g = 0;
        Operator operator = null;
        while (parcel.dataPosition() < o) {
            final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n);
                    continue;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n);
                    continue;
                }
                case 1: {
                    operator = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n, Operator.CREATOR);
                    continue;
                }
                case 2: {
                    metadataBundle = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n, MetadataBundle.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + o, parcel);
        }
        return new ComparisonFilter(g, operator, metadataBundle);
    }
}
