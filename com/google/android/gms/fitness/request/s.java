// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.List;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class s implements Parcelable$Creator<SessionReadRequest>
{
    static void a(final SessionReadRequest sessionReadRequest, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, sessionReadRequest.ju(), false);
        b.c(parcel, 1000, sessionReadRequest.getVersionCode());
        b.a(parcel, 2, sessionReadRequest.getSessionId(), false);
        b.a(parcel, 3, sessionReadRequest.getStartTimeMillis());
        b.a(parcel, 4, sessionReadRequest.getEndTimeMillis());
        b.c(parcel, 5, sessionReadRequest.getDataTypes(), false);
        b.c(parcel, 6, sessionReadRequest.getDataSources(), false);
        b.a(parcel, 7, sessionReadRequest.jv());
        b.a(parcel, 8, sessionReadRequest.jg());
        b.b(parcel, 9, sessionReadRequest.jw(), false);
        b.H(parcel, d);
    }
    
    public SessionReadRequest bL(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        String o2 = null;
        long i = 0L;
        long j = 0L;
        List<DataType> c2 = null;
        List<DataSource> c3 = null;
        boolean c4 = false;
        boolean c5 = false;
        List<String> c6 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 4: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 5: {
                    c2 = a.c(parcel, b, DataType.CREATOR);
                    continue;
                }
                case 6: {
                    c3 = a.c(parcel, b, DataSource.CREATOR);
                    continue;
                }
                case 7: {
                    c4 = a.c(parcel, b);
                    continue;
                }
                case 8: {
                    c5 = a.c(parcel, b);
                    continue;
                }
                case 9: {
                    c6 = a.C(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new SessionReadRequest(g, o, o2, i, j, c2, c3, c4, c5, c6);
    }
    
    public SessionReadRequest[] dd(final int n) {
        return new SessionReadRequest[n];
    }
}
