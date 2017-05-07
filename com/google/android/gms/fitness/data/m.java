// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class m implements Parcelable$Creator<RawBucket>
{
    static void a(final RawBucket rawBucket, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, rawBucket.KL);
        b.c(parcel, 1000, rawBucket.BR);
        b.a(parcel, 2, rawBucket.Si);
        b.a(parcel, 3, (Parcelable)rawBucket.Sk, n, false);
        b.c(parcel, 4, rawBucket.Sv);
        b.c(parcel, 5, rawBucket.Sw, false);
        b.c(parcel, 6, rawBucket.Sx);
        b.a(parcel, 7, rawBucket.Sy);
        b.H(parcel, d);
    }
    
    public RawBucket br(final Parcel parcel) {
        long i = 0L;
        List<RawDataSet> c = null;
        boolean c2 = false;
        final int c3 = a.C(parcel);
        int g = 0;
        int g2 = 0;
        Session session = null;
        long j = 0L;
        int g3 = 0;
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
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    session = a.a(parcel, b, Session.CREATOR);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, b, RawDataSet.CREATOR);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, b);
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
        return new RawBucket(g3, j, i, session, g2, c, g, c2);
    }
    
    public RawBucket[] cI(final int n) {
        return new RawBucket[n];
    }
}
