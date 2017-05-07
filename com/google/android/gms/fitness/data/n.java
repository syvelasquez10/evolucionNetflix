// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class n implements Parcelable$Creator<RawDataPoint>
{
    static void a(final RawDataPoint rawDataPoint, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, rawDataPoint.Sz);
        b.c(parcel, 1000, rawDataPoint.BR);
        b.a(parcel, 2, rawDataPoint.SA);
        b.a(parcel, 3, rawDataPoint.SB, n, false);
        b.c(parcel, 4, rawDataPoint.Tb);
        b.c(parcel, 5, rawDataPoint.Tc);
        b.a(parcel, 6, rawDataPoint.SD);
        b.a(parcel, 7, rawDataPoint.SE);
        b.H(parcel, d);
    }
    
    public RawDataPoint bs(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        long i = 0L;
        long j = 0L;
        Value[] array = null;
        int g2 = 0;
        int g3 = 0;
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
                    i = a.i(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    array = a.b(parcel, b, Value.CREATOR);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 6: {
                    k = a.i(parcel, b);
                    continue;
                }
                case 7: {
                    l = a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new RawDataPoint(g, i, j, array, g2, g3, k, l);
    }
    
    public RawDataPoint[] cJ(final int n) {
        return new RawDataPoint[n];
    }
}
