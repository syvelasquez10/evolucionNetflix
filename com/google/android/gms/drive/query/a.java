// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import com.google.android.gms.drive.query.internal.LogicalFilter;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<Query>
{
    static void a(final Query query, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1000, query.xH);
        b.a(parcel, 1, (Parcelable)query.GA, n, false);
        b.a(parcel, 3, query.GB, false);
        b.a(parcel, 4, (Parcelable)query.GC, n, false);
        b.F(parcel, p3);
    }
    
    public Query[] aG(final int n) {
        return new Query[n];
    }
    
    public Query ac(final Parcel parcel) {
        SortOrder sortOrder = null;
        final int o = com.google.android.gms.common.internal.safeparcel.a.o(parcel);
        int g = 0;
        String s = null;
        LogicalFilter logicalFilter = null;
        while (parcel.dataPosition() < o) {
            final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            LogicalFilter logicalFilter2 = null;
            String s3 = null;
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n);
                    final String s2 = s;
                    logicalFilter2 = logicalFilter;
                    s3 = s2;
                    break;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n);
                    final LogicalFilter logicalFilter3 = logicalFilter;
                    s3 = s;
                    logicalFilter2 = logicalFilter3;
                    break;
                }
                case 1: {
                    final LogicalFilter logicalFilter4 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n, LogicalFilter.CREATOR);
                    s3 = s;
                    logicalFilter2 = logicalFilter4;
                    break;
                }
                case 3: {
                    final String n2 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n);
                    logicalFilter2 = logicalFilter;
                    s3 = n2;
                    break;
                }
                case 4: {
                    sortOrder = com.google.android.gms.common.internal.safeparcel.a.a(parcel, n, SortOrder.CREATOR);
                    final LogicalFilter logicalFilter5 = logicalFilter;
                    s3 = s;
                    logicalFilter2 = logicalFilter5;
                    break;
                }
            }
            final LogicalFilter logicalFilter6 = logicalFilter2;
            s = s3;
            logicalFilter = logicalFilter6;
        }
        if (parcel.dataPosition() != o) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + o, parcel);
        }
        return new Query(g, logicalFilter, s, sortOrder);
    }
}
