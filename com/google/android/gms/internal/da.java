// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class da implements Parcelable$Creator<cz>
{
    static void a(final cz cz, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, cz.versionCode);
        b.a(parcel, 2, cz.ol, false);
        b.a(parcel, 3, cz.pm, false);
        b.a(parcel, 4, cz.ne, false);
        b.c(parcel, 5, cz.errorCode);
        b.a(parcel, 6, cz.nf, false);
        b.a(parcel, 7, cz.pn);
        b.a(parcel, 8, cz.po);
        b.a(parcel, 9, cz.pp);
        b.a(parcel, 10, cz.pq, false);
        b.a(parcel, 11, cz.ni);
        b.c(parcel, 12, cz.orientation);
        b.a(parcel, 13, cz.pr, false);
        b.a(parcel, 14, cz.ps);
        b.a(parcel, 15, cz.pt, false);
        b.a(parcel, 19, cz.pv, false);
        b.a(parcel, 18, cz.pu);
        b.a(parcel, 21, cz.pw, false);
        b.F(parcel, p3);
    }
    
    public cz g(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        String n2 = null;
        List<String> a = null;
        int g2 = 0;
        List<String> a2 = null;
        long i = 0L;
        boolean c = false;
        long j = 0L;
        List<String> a3 = null;
        long k = 0L;
        int g3 = 0;
        String n3 = null;
        long l = 0L;
        String n4 = null;
        boolean c2 = false;
        String n5 = null;
        String n6 = null;
        while (parcel.dataPosition() < o) {
            final int n7 = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n7)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n7);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n7);
                    continue;
                }
                case 2: {
                    n = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n7);
                    continue;
                }
                case 3: {
                    n2 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n7);
                    continue;
                }
                case 4: {
                    a = com.google.android.gms.common.internal.safeparcel.a.A(parcel, n7);
                    continue;
                }
                case 5: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n7);
                    continue;
                }
                case 6: {
                    a2 = com.google.android.gms.common.internal.safeparcel.a.A(parcel, n7);
                    continue;
                }
                case 7: {
                    i = com.google.android.gms.common.internal.safeparcel.a.i(parcel, n7);
                    continue;
                }
                case 8: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n7);
                    continue;
                }
                case 9: {
                    j = com.google.android.gms.common.internal.safeparcel.a.i(parcel, n7);
                    continue;
                }
                case 10: {
                    a3 = com.google.android.gms.common.internal.safeparcel.a.A(parcel, n7);
                    continue;
                }
                case 11: {
                    k = com.google.android.gms.common.internal.safeparcel.a.i(parcel, n7);
                    continue;
                }
                case 12: {
                    g3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n7);
                    continue;
                }
                case 13: {
                    n3 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n7);
                    continue;
                }
                case 14: {
                    l = com.google.android.gms.common.internal.safeparcel.a.i(parcel, n7);
                    continue;
                }
                case 15: {
                    n4 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n7);
                    continue;
                }
                case 19: {
                    n5 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n7);
                    continue;
                }
                case 18: {
                    c2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, n7);
                    continue;
                }
                case 21: {
                    n6 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n7);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new cz(g, n, n2, a, g2, a2, i, c, j, a3, k, g3, n3, l, n4, c2, n5, n6);
    }
    
    public cz[] l(final int n) {
        return new cz[n];
    }
}
