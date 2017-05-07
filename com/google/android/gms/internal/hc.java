// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hc implements Parcelable$Creator<hb>
{
    static void a(final hb hb, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.a(parcel, 1, hb.dU(), n, false);
        b.c(parcel, 1000, hb.kg);
        b.a(parcel, 2, hb.dV(), false);
        b.a(parcel, 3, hb.getTimestampMillis());
        b.D(parcel, o);
    }
    
    public hb an(final Parcel parcel) {
        float[] u = null;
        final int n = a.n(parcel);
        int g = 0;
        long h = 0L;
        hd[] array = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    array = a.b(parcel, m, (android.os.Parcelable$Creator<hd>)hd.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    u = a.u(parcel, m);
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
        return new hb(g, array, u, h);
    }
    
    public hb[] bc(final int n) {
        return new hb[n];
    }
}
