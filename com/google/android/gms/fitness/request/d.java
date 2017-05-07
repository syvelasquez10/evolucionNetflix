// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<DataDeleteRequest>
{
    static void a(final DataDeleteRequest dataDeleteRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, dataDeleteRequest.getStartTimeMillis());
        b.c(parcel, 1000, dataDeleteRequest.getVersionCode());
        b.a(parcel, 2, dataDeleteRequest.getEndTimeMillis());
        b.c(parcel, 3, dataDeleteRequest.getDataSources(), false);
        b.c(parcel, 4, dataDeleteRequest.getDataTypes(), false);
        b.c(parcel, 5, dataDeleteRequest.getSessions(), false);
        b.a(parcel, 6, dataDeleteRequest.iX());
        b.a(parcel, 7, dataDeleteRequest.iY());
        b.H(parcel, d);
    }
    
    public DataDeleteRequest bB(final Parcel parcel) {
        long i = 0L;
        List<Session> c = null;
        boolean c2 = false;
        final int c3 = a.C(parcel);
        boolean c4 = false;
        List<DataType> c5 = null;
        List<DataSource> c6 = null;
        long j = 0L;
        int g = 0;
        while (parcel.dataPosition() < c3) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    c6 = a.c(parcel, b, DataSource.CREATOR);
                    continue;
                }
                case 4: {
                    c5 = a.c(parcel, b, DataType.CREATOR);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, b, Session.CREATOR);
                    continue;
                }
                case 6: {
                    c4 = a.c(parcel, b);
                    continue;
                }
                case 7: {
                    c2 = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c3) {
            throw new a$a("Overread allowed size end=" + c3, parcel);
        }
        return new DataDeleteRequest(g, j, i, c6, c5, c, c4, c2);
    }
    
    public DataDeleteRequest[] cS(final int n) {
        return new DataDeleteRequest[n];
    }
}
