// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class fl implements Parcelable$Creator<fk>
{
    static void a(final fk fk, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, fk.versionCode);
        b.a(parcel, 2, fk.rP, false);
        b.a(parcel, 3, fk.tG, false);
        b.b(parcel, 4, fk.qf, false);
        b.c(parcel, 5, fk.errorCode);
        b.b(parcel, 6, fk.qg, false);
        b.a(parcel, 7, fk.tH);
        b.a(parcel, 8, fk.tI);
        b.a(parcel, 9, fk.tJ);
        b.b(parcel, 10, fk.tK, false);
        b.a(parcel, 11, fk.qj);
        b.c(parcel, 12, fk.orientation);
        b.a(parcel, 13, fk.tL, false);
        b.a(parcel, 14, fk.tM);
        b.a(parcel, 15, fk.tN, false);
        b.a(parcel, 19, fk.tP, false);
        b.a(parcel, 18, fk.tO);
        b.a(parcel, 21, fk.tQ, false);
        b.a(parcel, 23, fk.tS);
        b.a(parcel, 22, fk.tR);
        b.a(parcel, 25, fk.tT);
        b.a(parcel, 24, fk.tF);
        b.H(parcel, d);
    }
    
    public fk i(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        String o2 = null;
        List<String> c2 = null;
        int g2 = 0;
        List<String> c3 = null;
        long i = 0L;
        boolean c4 = false;
        long j = 0L;
        List<String> c5 = null;
        long k = 0L;
        int g3 = 0;
        String o3 = null;
        long l = 0L;
        String o4 = null;
        boolean c6 = false;
        String o5 = null;
        String o6 = null;
        boolean c7 = false;
        boolean c8 = false;
        boolean c9 = false;
        boolean c10 = false;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    c2 = a.C(parcel, b);
                    continue;
                }
                case 5: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 6: {
                    c3 = a.C(parcel, b);
                    continue;
                }
                case 7: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 8: {
                    c4 = a.c(parcel, b);
                    continue;
                }
                case 9: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 10: {
                    c5 = a.C(parcel, b);
                    continue;
                }
                case 11: {
                    k = a.i(parcel, b);
                    continue;
                }
                case 12: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 13: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 14: {
                    l = a.i(parcel, b);
                    continue;
                }
                case 15: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 19: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 18: {
                    c6 = a.c(parcel, b);
                    continue;
                }
                case 21: {
                    o6 = a.o(parcel, b);
                    continue;
                }
                case 23: {
                    c8 = a.c(parcel, b);
                    continue;
                }
                case 22: {
                    c7 = a.c(parcel, b);
                    continue;
                }
                case 25: {
                    c10 = a.c(parcel, b);
                    continue;
                }
                case 24: {
                    c9 = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new fk(g, o, o2, c2, g2, c3, i, c4, j, c5, k, g3, o3, l, o4, c6, o5, o6, c7, c8, c9, c10);
    }
    
    public fk[] q(final int n) {
        return new fk[n];
    }
}
