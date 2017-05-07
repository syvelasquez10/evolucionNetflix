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
        final int o = b.o(parcel);
        b.c(parcel, 1000, query.kg);
        b.a(parcel, 1, (Parcelable)query.rO, n, false);
        b.a(parcel, 3, query.rP, false);
        b.D(parcel, o);
    }
    
    public Query Q(final Parcel parcel) {
        String m = null;
        final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
        int g = 0;
        LogicalFilter logicalFilter = null;
        while (parcel.dataPosition() < n) {
            final int i = com.google.android.gms.common.internal.safeparcel.a.m(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.M(i)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, i);
                    continue;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i);
                    continue;
                }
                case 1: {
                    logicalFilter = com.google.android.gms.common.internal.safeparcel.a.a(parcel, i, LogicalFilter.CREATOR);
                    continue;
                }
                case 3: {
                    m = com.google.android.gms.common.internal.safeparcel.a.m(parcel, i);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + n, parcel);
        }
        return new Query(g, logicalFilter, m);
    }
    
    public Query[] aq(final int n) {
        return new Query[n];
    }
}
