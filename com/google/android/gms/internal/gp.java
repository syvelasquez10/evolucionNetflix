// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class gp implements Parcelable$Creator<go>
{
    static void a(final go go, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.a(parcel, 1, go.getRequestId(), false);
        b.c(parcel, 1000, go.getVersionCode());
        b.a(parcel, 2, go.getExpirationTime());
        b.a(parcel, 3, go.dK());
        b.a(parcel, 4, go.getLatitude());
        b.a(parcel, 5, go.getLongitude());
        b.a(parcel, 6, go.dL());
        b.c(parcel, 7, go.dM());
        b.c(parcel, 8, go.getNotificationResponsiveness());
        b.c(parcel, 9, go.dN());
        b.D(parcel, o);
    }
    
    public go[] aX(final int n) {
        return new go[n];
    }
    
    public go ai(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
        int g2 = 0;
        short f = 0;
        double k = 0.0;
        double i = 0.0;
        float j = 0.0f;
        long h = 0L;
        int g3 = 0;
        int g4 = -1;
        while (parcel.dataPosition() < n) {
            final int l = a.m(parcel);
            switch (a.M(l)) {
                default: {
                    a.b(parcel, l);
                    continue;
                }
                case 1: {
                    m = a.m(parcel, l);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, l);
                    continue;
                }
                case 2: {
                    h = a.h(parcel, l);
                    continue;
                }
                case 3: {
                    f = a.f(parcel, l);
                    continue;
                }
                case 4: {
                    k = a.k(parcel, l);
                    continue;
                }
                case 5: {
                    i = a.k(parcel, l);
                    continue;
                }
                case 6: {
                    j = a.j(parcel, l);
                    continue;
                }
                case 7: {
                    g2 = a.g(parcel, l);
                    continue;
                }
                case 8: {
                    g3 = a.g(parcel, l);
                    continue;
                }
                case 9: {
                    g4 = a.g(parcel, l);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new go(g, m, g2, f, k, i, j, h, g3, g4);
    }
}
