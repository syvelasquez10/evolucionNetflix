// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.query;

import java.util.List;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<Query>
{
    static void a(final Query query, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1000, query.BR);
        b.a(parcel, 1, (Parcelable)query.Qt, n, false);
        b.a(parcel, 3, query.Qu, false);
        b.a(parcel, 4, (Parcelable)query.Qv, n, false);
        b.b(parcel, 5, query.Qw, false);
        b.H(parcel, d);
    }
    
    public Query aI(final Parcel parcel) {
        List<String> c = null;
        final int c2 = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        SortOrder sortOrder = null;
        String o = null;
        LogicalFilter logicalFilter = null;
        while (parcel.dataPosition() < c2) {
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
                    logicalFilter = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, LogicalFilter.CREATOR);
                    continue;
                }
                case 3: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 4: {
                    sortOrder = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, SortOrder.CREATOR);
                    continue;
                }
                case 5: {
                    c = com.google.android.gms.common.internal.safeparcel.a.C(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c2, parcel);
        }
        return new Query(g, logicalFilter, o, sortOrder, c);
    }
    
    public Query[] bU(final int n) {
        return new Query[n];
    }
}
