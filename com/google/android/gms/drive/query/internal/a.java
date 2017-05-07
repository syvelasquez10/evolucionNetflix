// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<ComparisonFilter>
{
    static void a(final ComparisonFilter comparisonFilter, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1000, comparisonFilter.BR);
        b.a(parcel, 1, (Parcelable)comparisonFilter.QC, n, false);
        b.a(parcel, 2, (Parcelable)comparisonFilter.QD, n, false);
        b.H(parcel, d);
    }
    
    public ComparisonFilter aK(final Parcel parcel) {
        MetadataBundle metadataBundle = null;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        Operator operator = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 1: {
                    operator = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, Operator.CREATOR);
                    continue;
                }
                case 2: {
                    metadataBundle = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, MetadataBundle.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ComparisonFilter(g, operator, metadataBundle);
    }
    
    public ComparisonFilter[] bW(final int n) {
        return new ComparisonFilter[n];
    }
}
