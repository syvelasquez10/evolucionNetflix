// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<d>
{
    static void a(final d d, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, d.xG);
        b.c(parcel, 1000, d.getVersionCode());
        b.c(parcel, 2, d.xH);
        b.a(parcel, 3, d.xI);
        b.D(parcel, o);
    }
    
    public d[] aS(final int n) {
        return new d[n];
    }
    
    public d af(final Parcel parcel) {
        int g = 1;
        final int n = a.n(parcel);
        int g2 = 0;
        long h = 0L;
        int g3 = 1;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, m);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 3: {
                    h = a.h(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new d(g2, g3, g, h);
    }
}
