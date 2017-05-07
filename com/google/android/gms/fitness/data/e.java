// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<DataPoint>
{
    static void a(final DataPoint dataPoint, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)dataPoint.getDataSource(), n, false);
        b.c(parcel, 1000, dataPoint.getVersionCode());
        b.a(parcel, 3, dataPoint.getTimestampNanos());
        b.a(parcel, 4, dataPoint.getStartTimeNanos());
        b.a(parcel, 5, dataPoint.iC(), n, false);
        b.a(parcel, 6, (Parcelable)dataPoint.getOriginalDataSource(), n, false);
        b.a(parcel, 7, dataPoint.iD());
        b.a(parcel, 8, dataPoint.iE());
        b.H(parcel, d);
    }
    
    public DataPoint bl(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        DataSource dataSource = null;
        long i = 0L;
        long j = 0L;
        Value[] array = null;
        DataSource dataSource2 = null;
        long k = 0L;
        long l = 0L;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    dataSource = a.a(parcel, b, DataSource.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
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
                    array = a.b(parcel, b, Value.CREATOR);
                    continue;
                }
                case 6: {
                    dataSource2 = a.a(parcel, b, DataSource.CREATOR);
                    continue;
                }
                case 7: {
                    k = a.i(parcel, b);
                    continue;
                }
                case 8: {
                    l = a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new DataPoint(g, dataSource, i, j, array, dataSource2, k, l);
    }
    
    public DataPoint[] cC(final int n) {
        return new DataPoint[n];
    }
}
