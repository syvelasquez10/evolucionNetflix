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

public class ax implements Parcelable$Creator<QueryRequest>
{
    static void a(final QueryRequest queryRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, queryRequest.BR);
        b.a(parcel, 2, (Parcelable)queryRequest.Pq, n, false);
        b.H(parcel, d);
    }
    
    public QueryRequest ay(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        Query query = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    query = a.a(parcel, b, Query.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new QueryRequest(g, query);
    }
    
    public QueryRequest[] bK(final int n) {
        return new QueryRequest[n];
    }
}
