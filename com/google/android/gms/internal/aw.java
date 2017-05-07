// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class aw implements Parcelable$Creator<av>
{
    static void a(final av av, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, av.versionCode);
        b.c(parcel, 2, av.mq);
        b.c(parcel, 3, av.backgroundColor);
        b.c(parcel, 4, av.mr);
        b.c(parcel, 5, av.ms);
        b.c(parcel, 6, av.mt);
        b.c(parcel, 7, av.mu);
        b.c(parcel, 8, av.mv);
        b.c(parcel, 9, av.mw);
        b.a(parcel, 10, av.mx, false);
        b.c(parcel, 11, av.my);
        b.a(parcel, 12, av.mz, false);
        b.c(parcel, 13, av.mA);
        b.c(parcel, 14, av.mB);
        b.a(parcel, 15, av.mC, false);
        b.F(parcel, p3);
    }
    
    public av c(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        int g2 = 0;
        int g3 = 0;
        int g4 = 0;
        int g5 = 0;
        int g6 = 0;
        int g7 = 0;
        int g8 = 0;
        int g9 = 0;
        String n = null;
        int g10 = 0;
        String n2 = null;
        int g11 = 0;
        int g12 = 0;
        String n3 = null;
        while (parcel.dataPosition() < o) {
            final int n4 = a.n(parcel);
            switch (a.R(n4)) {
                default: {
                    a.b(parcel, n4);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n4);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, n4);
                    continue;
                }
                case 3: {
                    g3 = a.g(parcel, n4);
                    continue;
                }
                case 4: {
                    g4 = a.g(parcel, n4);
                    continue;
                }
                case 5: {
                    g5 = a.g(parcel, n4);
                    continue;
                }
                case 6: {
                    g6 = a.g(parcel, n4);
                    continue;
                }
                case 7: {
                    g7 = a.g(parcel, n4);
                    continue;
                }
                case 8: {
                    g8 = a.g(parcel, n4);
                    continue;
                }
                case 9: {
                    g9 = a.g(parcel, n4);
                    continue;
                }
                case 10: {
                    n = a.n(parcel, n4);
                    continue;
                }
                case 11: {
                    g10 = a.g(parcel, n4);
                    continue;
                }
                case 12: {
                    n2 = a.n(parcel, n4);
                    continue;
                }
                case 13: {
                    g11 = a.g(parcel, n4);
                    continue;
                }
                case 14: {
                    g12 = a.g(parcel, n4);
                    continue;
                }
                case 15: {
                    n3 = a.n(parcel, n4);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new av(g, g2, g3, g4, g5, g6, g7, g8, g9, n, g10, n2, g11, g12, n3);
    }
    
    public av[] e(final int n) {
        return new av[n];
    }
}
