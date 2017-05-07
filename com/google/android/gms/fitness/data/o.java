// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class o implements Parcelable$Creator<RawDataSet>
{
    static void a(final RawDataSet set, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, set.Tb);
        b.c(parcel, 1000, set.BR);
        b.c(parcel, 2, set.Td);
        b.c(parcel, 3, set.Te, false);
        b.a(parcel, 4, set.Sy);
        b.H(parcel, d);
    }
    
    public RawDataSet bt(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        List<RawDataPoint> c3 = null;
        int g = 0;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 1000: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    c3 = a.c(parcel, b, RawDataPoint.CREATOR);
                    continue;
                }
                case 4: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new RawDataSet(g3, g2, g, c3, c);
    }
    
    public RawDataSet[] cK(final int n) {
        return new RawDataSet[n];
    }
}
