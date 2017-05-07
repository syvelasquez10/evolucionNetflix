// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ef implements Parcelable$Creator<dt.a>
{
    static void a(final dt.a a, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.a(parcel, 1, a.getAccountName(), false);
        b.c(parcel, 1000, a.getVersionCode());
        b.a(parcel, 2, a.bH(), false);
        b.c(parcel, 3, a.bG());
        b.a(parcel, 4, a.bJ(), false);
        b.D(parcel, o);
    }
    
    public dt.a[] L(final int n) {
        return new dt.a[n];
    }
    
    public dt.a l(final Parcel parcel) {
        int g = 0;
        String m = null;
        final int n = a.n(parcel);
        List<String> y = null;
        String i = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int j = a.m(parcel);
            switch (a.M(j)) {
                default: {
                    a.b(parcel, j);
                    continue;
                }
                case 1: {
                    i = a.m(parcel, j);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, j);
                    continue;
                }
                case 2: {
                    y = a.y(parcel, j);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, j);
                    continue;
                }
                case 4: {
                    m = a.m(parcel, j);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new dt.a(g2, i, y, g, m);
    }
}
