// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class he implements Parcelable$Creator<hd>
{
    static void a(final hd hd, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.a(parcel, 1, hd.getRequestId(), false);
        b.c(parcel, 1000, hd.getVersionCode());
        b.a(parcel, 2, hd.getExpirationTime());
        b.a(parcel, 3, hd.hS());
        b.a(parcel, 4, hd.getLatitude());
        b.a(parcel, 5, hd.getLongitude());
        b.a(parcel, 6, hd.hT());
        b.c(parcel, 7, hd.hU());
        b.c(parcel, 8, hd.getNotificationResponsiveness());
        b.c(parcel, 9, hd.hV());
        b.F(parcel, p3);
    }
    
    public hd aC(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        int g2 = 0;
        short f = 0;
        double l = 0.0;
        double i = 0.0;
        float k = 0.0f;
        long j = 0L;
        int g3 = 0;
        int g4 = -1;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    j = a.i(parcel, n2);
                    continue;
                }
                case 3: {
                    f = a.f(parcel, n2);
                    continue;
                }
                case 4: {
                    l = a.l(parcel, n2);
                    continue;
                }
                case 5: {
                    i = a.l(parcel, n2);
                    continue;
                }
                case 6: {
                    k = a.k(parcel, n2);
                    continue;
                }
                case 7: {
                    g2 = a.g(parcel, n2);
                    continue;
                }
                case 8: {
                    g3 = a.g(parcel, n2);
                    continue;
                }
                case 9: {
                    g4 = a.g(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new hd(g, n, g2, f, l, i, k, j, g3, g4);
    }
    
    public hd[] bD(final int n) {
        return new hd[n];
    }
}
