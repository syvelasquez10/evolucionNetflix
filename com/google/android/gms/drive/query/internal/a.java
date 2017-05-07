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
        final int o = b.o(parcel);
        b.c(parcel, 1000, comparisonFilter.kg);
        b.a(parcel, 1, (Parcelable)comparisonFilter.rR, n, false);
        b.a(parcel, 2, (Parcelable)comparisonFilter.rS, n, false);
        b.D(parcel, o);
    }
    
    public ComparisonFilter R(final Parcel parcel) {
        MetadataBundle metadataBundle = null;
        final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
        int g = 0;
        Operator operator = null;
        while (parcel.dataPosition() < n) {
            final int m = com.google.android.gms.common.internal.safeparcel.a.m(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.M(m)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, m);
                    continue;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, m);
                    continue;
                }
                case 1: {
                    operator = com.google.android.gms.common.internal.safeparcel.a.a(parcel, m, Operator.CREATOR);
                    continue;
                }
                case 2: {
                    metadataBundle = com.google.android.gms.common.internal.safeparcel.a.a(parcel, m, MetadataBundle.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + n, parcel);
        }
        return new ComparisonFilter(g, operator, metadataBundle);
    }
    
    public ComparisonFilter[] ar(final int n) {
        return new ComparisonFilter[n];
    }
}
