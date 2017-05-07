// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class bk implements Parcelable$Creator<bj>
{
    static void a(final bj bj, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, bj.versionCode);
        b.c(parcel, 2, bj.oH);
        b.c(parcel, 3, bj.backgroundColor);
        b.c(parcel, 4, bj.oI);
        b.c(parcel, 5, bj.oJ);
        b.c(parcel, 6, bj.oK);
        b.c(parcel, 7, bj.oL);
        b.c(parcel, 8, bj.oM);
        b.c(parcel, 9, bj.oN);
        b.a(parcel, 10, bj.oO, false);
        b.c(parcel, 11, bj.oP);
        b.a(parcel, 12, bj.oQ, false);
        b.c(parcel, 13, bj.oR);
        b.c(parcel, 14, bj.oS);
        b.a(parcel, 15, bj.oT, false);
        b.H(parcel, d);
    }
    
    public bj d(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        int g2 = 0;
        int g3 = 0;
        int g4 = 0;
        int g5 = 0;
        int g6 = 0;
        int g7 = 0;
        int g8 = 0;
        int g9 = 0;
        String o = null;
        int g10 = 0;
        String o2 = null;
        int g11 = 0;
        int g12 = 0;
        String o3 = null;
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
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    g5 = a.g(parcel, b);
                    continue;
                }
                case 6: {
                    g6 = a.g(parcel, b);
                    continue;
                }
                case 7: {
                    g7 = a.g(parcel, b);
                    continue;
                }
                case 8: {
                    g8 = a.g(parcel, b);
                    continue;
                }
                case 9: {
                    g9 = a.g(parcel, b);
                    continue;
                }
                case 10: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 11: {
                    g10 = a.g(parcel, b);
                    continue;
                }
                case 12: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 13: {
                    g11 = a.g(parcel, b);
                    continue;
                }
                case 14: {
                    g12 = a.g(parcel, b);
                    continue;
                }
                case 15: {
                    o3 = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new bj(g, g2, g3, g4, g5, g6, g7, g8, g9, o, g10, o2, g11, g12, o3);
    }
    
    public bj[] h(final int n) {
        return new bj[n];
    }
}
