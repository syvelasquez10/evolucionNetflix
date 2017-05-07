// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ha implements Parcelable$Creator<hd.a>
{
    static void a(final hd.a a, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.a(parcel, 1, a.eh(), false);
        b.c(parcel, 1000, a.kg);
        b.a(parcel, 2, a.getTag(), false);
        b.a(parcel, 3, a.ei(), false);
        b.c(parcel, 4, a.ej());
        b.D(parcel, o);
    }
    
    public hd.a am(final Parcel parcel) {
        int g = 0;
        String m = null;
        final int n = a.n(parcel);
        String i = null;
        String j = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int k = a.m(parcel);
            switch (a.M(k)) {
                default: {
                    a.b(parcel, k);
                    continue;
                }
                case 1: {
                    j = a.m(parcel, k);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, k);
                    continue;
                }
                case 2: {
                    i = a.m(parcel, k);
                    continue;
                }
                case 3: {
                    m = a.m(parcel, k);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, k);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new hd.a(g2, j, i, m, g);
    }
    
    public hd.a[] bb(final int n) {
        return new hd.a[n];
    }
}
