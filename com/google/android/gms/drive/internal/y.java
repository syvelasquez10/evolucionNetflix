// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.query.Query;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class y implements Parcelable$Creator<QueryRequest>
{
    static void a(final QueryRequest queryRequest, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, queryRequest.kg);
        b.a(parcel, 2, (Parcelable)queryRequest.rA, n, false);
        b.D(parcel, o);
    }
    
    public QueryRequest N(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        Query query = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    query = a.a(parcel, m, Query.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new QueryRequest(g, query);
    }
    
    public QueryRequest[] an(final int n) {
        return new QueryRequest[n];
    }
}
