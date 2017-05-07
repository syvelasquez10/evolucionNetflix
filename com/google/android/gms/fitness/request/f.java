// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.List;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class f implements Parcelable$Creator<DataReadRequest>
{
    static void a(final DataReadRequest dataReadRequest, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, dataReadRequest.getDataTypes(), false);
        b.c(parcel, 1000, dataReadRequest.getVersionCode());
        b.c(parcel, 2, dataReadRequest.getDataSources(), false);
        b.a(parcel, 3, dataReadRequest.getStartTimeMillis());
        b.a(parcel, 4, dataReadRequest.getEndTimeMillis());
        b.c(parcel, 5, dataReadRequest.ja(), false);
        b.c(parcel, 6, dataReadRequest.jb(), false);
        b.c(parcel, 7, dataReadRequest.getBucketType());
        b.a(parcel, 8, dataReadRequest.jc());
        b.a(parcel, 9, (Parcelable)dataReadRequest.jd(), n, false);
        b.c(parcel, 10, dataReadRequest.je());
        b.a(parcel, 11, dataReadRequest.jf());
        b.a(parcel, 12, dataReadRequest.jh());
        b.a(parcel, 13, dataReadRequest.jg());
        b.H(parcel, d);
    }
    
    public DataReadRequest bD(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        List<DataType> c2 = null;
        List<DataSource> c3 = null;
        long i = 0L;
        long j = 0L;
        List<DataType> c4 = null;
        List<DataSource> c5 = null;
        int g2 = 0;
        long k = 0L;
        DataSource dataSource = null;
        int g3 = 0;
        boolean c6 = false;
        boolean c7 = false;
        boolean c8 = false;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    c2 = a.c(parcel, b, DataType.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    c3 = a.c(parcel, b, DataSource.CREATOR);
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
                    c4 = a.c(parcel, b, DataType.CREATOR);
                    continue;
                }
                case 6: {
                    c5 = a.c(parcel, b, DataSource.CREATOR);
                    continue;
                }
                case 7: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 8: {
                    k = a.i(parcel, b);
                    continue;
                }
                case 9: {
                    dataSource = a.a(parcel, b, DataSource.CREATOR);
                    continue;
                }
                case 10: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 11: {
                    c6 = a.c(parcel, b);
                    continue;
                }
                case 12: {
                    c7 = a.c(parcel, b);
                    continue;
                }
                case 13: {
                    c8 = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new DataReadRequest(g, c2, c3, i, j, c4, c5, g2, k, dataSource, g3, c6, c7, c8);
    }
    
    public DataReadRequest[] cU(final int n) {
        return new DataReadRequest[n];
    }
}
