// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class h implements Parcelable$Creator<g>
{
    static void a(final g g, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, g.getVersionCode());
        b.c(parcel, 2, g.aus);
        b.a(parcel, 3, g.aut, false);
        b.a(parcel, 4, g.auu);
        b.a(parcel, 5, g.auv, false);
        b.a(parcel, 6, g.auw);
        b.c(parcel, 7, g.aux);
        b.H(parcel, d);
    }
    
    public g dJ(final Parcel parcel) {
        String o = null;
        int g = 0;
        final int c = a.C(parcel);
        double m = 0.0;
        long i = 0L;
        int g2 = -1;
        String o2 = null;
        int g3 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    m = a.m(parcel, b);
                    continue;
                }
                case 5: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 7: {
                    g2 = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new g(g3, g, o2, m, o, i, g2);
    }
    
    public g[] fL(final int n) {
        return new g[n];
    }
}
