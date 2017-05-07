// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class jq implements Parcelable$Creator<jp>
{
    static void a(final jp jp, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, jp.getVersionCode());
        b.c(parcel, 2, jp.adh);
        b.a(parcel, 3, jp.adi, false);
        b.a(parcel, 4, jp.adj);
        b.a(parcel, 5, jp.adk, false);
        b.a(parcel, 6, jp.adl);
        b.c(parcel, 7, jp.adm);
        b.F(parcel, p3);
    }
    
    public jp bs(final Parcel parcel) {
        String n = null;
        int g = 0;
        final int o = a.o(parcel);
        double l = 0.0;
        long i = 0L;
        int g2 = -1;
        String n2 = null;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 3: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 4: {
                    l = a.l(parcel, n3);
                    continue;
                }
                case 5: {
                    n = a.n(parcel, n3);
                    continue;
                }
                case 6: {
                    i = a.i(parcel, n3);
                    continue;
                }
                case 7: {
                    g2 = a.g(parcel, n3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new jp(g3, g, n2, l, n, i, g2);
    }
    
    public jp[] cG(final int n) {
        return new jp[n];
    }
}
