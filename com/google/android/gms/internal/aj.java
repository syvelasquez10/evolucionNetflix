// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class aj implements Parcelable$Creator<ai>
{
    static void a(final ai ai, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, ai.versionCode);
        b.c(parcel, 2, ai.eZ);
        b.c(parcel, 3, ai.backgroundColor);
        b.c(parcel, 4, ai.fa);
        b.c(parcel, 5, ai.fb);
        b.c(parcel, 6, ai.fc);
        b.c(parcel, 7, ai.fd);
        b.c(parcel, 8, ai.fe);
        b.c(parcel, 9, ai.ff);
        b.a(parcel, 10, ai.fg, false);
        b.c(parcel, 11, ai.fh);
        b.a(parcel, 12, ai.fi, false);
        b.c(parcel, 13, ai.fj);
        b.c(parcel, 14, ai.fk);
        b.a(parcel, 15, ai.fl, false);
        b.D(parcel, o);
    }
    
    public ai c(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        int g2 = 0;
        int g3 = 0;
        int g4 = 0;
        int g5 = 0;
        int g6 = 0;
        int g7 = 0;
        int g8 = 0;
        int g9 = 0;
        String m = null;
        int g10 = 0;
        String i = null;
        int g11 = 0;
        int g12 = 0;
        String j = null;
        while (parcel.dataPosition() < n) {
            final int k = a.m(parcel);
            switch (a.M(k)) {
                default: {
                    a.b(parcel, k);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, k);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, k);
                    continue;
                }
                case 3: {
                    g3 = a.g(parcel, k);
                    continue;
                }
                case 4: {
                    g4 = a.g(parcel, k);
                    continue;
                }
                case 5: {
                    g5 = a.g(parcel, k);
                    continue;
                }
                case 6: {
                    g6 = a.g(parcel, k);
                    continue;
                }
                case 7: {
                    g7 = a.g(parcel, k);
                    continue;
                }
                case 8: {
                    g8 = a.g(parcel, k);
                    continue;
                }
                case 9: {
                    g9 = a.g(parcel, k);
                    continue;
                }
                case 10: {
                    m = a.m(parcel, k);
                    continue;
                }
                case 11: {
                    g10 = a.g(parcel, k);
                    continue;
                }
                case 12: {
                    i = a.m(parcel, k);
                    continue;
                }
                case 13: {
                    g11 = a.g(parcel, k);
                    continue;
                }
                case 14: {
                    g12 = a.g(parcel, k);
                    continue;
                }
                case 15: {
                    j = a.m(parcel, k);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ai(g, g2, g3, g4, g5, g6, g7, g8, g9, m, g10, i, g11, g12, j);
    }
    
    public ai[] e(final int n) {
        return new ai[n];
    }
}
