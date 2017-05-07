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

public class aj implements Parcelable$Creator<QueryRequest>
{
    static void a(final QueryRequest queryRequest, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, queryRequest.xH);
        b.a(parcel, 2, (Parcelable)queryRequest.FL, n, false);
        b.F(parcel, p3);
    }
    
    public QueryRequest X(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        Query query = null;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    query = a.a(parcel, n, Query.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new QueryRequest(g, query);
    }
    
    public QueryRequest[] aB(final int n) {
        return new QueryRequest[n];
    }
}
