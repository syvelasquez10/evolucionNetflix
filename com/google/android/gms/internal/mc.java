// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class mc implements Parcelable$Creator<mb>
{
    static void a(final mb mb, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, mb.getRequestId(), false);
        b.c(parcel, 1000, mb.getVersionCode());
        b.a(parcel, 2, mb.getExpirationTime());
        b.a(parcel, 3, mb.lY());
        b.a(parcel, 4, mb.getLatitude());
        b.a(parcel, 5, mb.getLongitude());
        b.a(parcel, 6, mb.lZ());
        b.c(parcel, 7, mb.ma());
        b.c(parcel, 8, mb.getNotificationResponsiveness());
        b.c(parcel, 9, mb.mb());
        b.H(parcel, d);
    }
    
    public mb cw(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        int g2 = 0;
        short f = 0;
        double m = 0.0;
        double i = 0.0;
        float l = 0.0f;
        long j = 0L;
        int g3 = 0;
        int g4 = -1;
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
                    j = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    f = a.f(parcel, b);
                    continue;
                }
                case 4: {
                    m = a.m(parcel, b);
                    continue;
                }
                case 5: {
                    i = a.m(parcel, b);
                    continue;
                }
                case 6: {
                    l = a.l(parcel, b);
                    continue;
                }
                case 7: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 8: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 9: {
                    g4 = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new mb(g, o, g2, f, m, i, l, j, g3, g4);
    }
    
    public mb[] el(final int n) {
        return new mb[n];
    }
}
