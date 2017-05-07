// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Set;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class nu implements Parcelable$Creator<nt>
{
    static void a(final nt nt, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        final Set<Integer> alR = nt.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, nt.BR);
        }
        if (alR.contains(2)) {
            b.a(parcel, 2, (Parcelable)nt.alS, n, true);
        }
        if (alR.contains(3)) {
            b.b(parcel, 3, nt.alT, true);
        }
        if (alR.contains(4)) {
            b.a(parcel, 4, (Parcelable)nt.alU, n, true);
        }
        if (alR.contains(5)) {
            b.a(parcel, 5, nt.alV, true);
        }
        if (alR.contains(6)) {
            b.a(parcel, 6, nt.alW, true);
        }
        if (alR.contains(7)) {
            b.a(parcel, 7, nt.alX, true);
        }
        if (alR.contains(8)) {
            b.c(parcel, 8, nt.alY, true);
        }
        if (alR.contains(9)) {
            b.c(parcel, 9, nt.alZ);
        }
        if (alR.contains(10)) {
            b.c(parcel, 10, nt.ama, true);
        }
        if (alR.contains(11)) {
            b.a(parcel, 11, (Parcelable)nt.amb, n, true);
        }
        if (alR.contains(12)) {
            b.c(parcel, 12, nt.amc, true);
        }
        if (alR.contains(13)) {
            b.a(parcel, 13, nt.amd, true);
        }
        if (alR.contains(14)) {
            b.a(parcel, 14, nt.ame, true);
        }
        if (alR.contains(15)) {
            b.a(parcel, 15, (Parcelable)nt.amf, n, true);
        }
        if (alR.contains(17)) {
            b.a(parcel, 17, nt.amh, true);
        }
        if (alR.contains(16)) {
            b.a(parcel, 16, nt.amg, true);
        }
        if (alR.contains(19)) {
            b.c(parcel, 19, nt.ami, true);
        }
        if (alR.contains(18)) {
            b.a(parcel, 18, nt.ol, true);
        }
        if (alR.contains(21)) {
            b.a(parcel, 21, nt.amk, true);
        }
        if (alR.contains(20)) {
            b.a(parcel, 20, nt.amj, true);
        }
        if (alR.contains(23)) {
            b.a(parcel, 23, nt.Tg, true);
        }
        if (alR.contains(22)) {
            b.a(parcel, 22, nt.aml, true);
        }
        if (alR.contains(25)) {
            b.a(parcel, 25, nt.amn, true);
        }
        if (alR.contains(24)) {
            b.a(parcel, 24, nt.amm, true);
        }
        if (alR.contains(27)) {
            b.a(parcel, 27, nt.amp, true);
        }
        if (alR.contains(26)) {
            b.a(parcel, 26, nt.amo, true);
        }
        if (alR.contains(29)) {
            b.a(parcel, 29, (Parcelable)nt.amr, n, true);
        }
        if (alR.contains(28)) {
            b.a(parcel, 28, nt.amq, true);
        }
        if (alR.contains(31)) {
            b.a(parcel, 31, nt.amt, true);
        }
        if (alR.contains(30)) {
            b.a(parcel, 30, nt.ams, true);
        }
        if (alR.contains(34)) {
            b.a(parcel, 34, (Parcelable)nt.amv, n, true);
        }
        if (alR.contains(32)) {
            b.a(parcel, 32, nt.BL, true);
        }
        if (alR.contains(33)) {
            b.a(parcel, 33, nt.amu, true);
        }
        if (alR.contains(38)) {
            b.a(parcel, 38, nt.aea);
        }
        if (alR.contains(39)) {
            b.a(parcel, 39, nt.mName, true);
        }
        if (alR.contains(36)) {
            b.a(parcel, 36, nt.adZ);
        }
        if (alR.contains(37)) {
            b.a(parcel, 37, (Parcelable)nt.amw, n, true);
        }
        if (alR.contains(42)) {
            b.a(parcel, 42, nt.amz, true);
        }
        if (alR.contains(43)) {
            b.a(parcel, 43, nt.amA, true);
        }
        if (alR.contains(40)) {
            b.a(parcel, 40, (Parcelable)nt.amx, n, true);
        }
        if (alR.contains(41)) {
            b.c(parcel, 41, nt.amy, true);
        }
        if (alR.contains(46)) {
            b.a(parcel, 46, (Parcelable)nt.amD, n, true);
        }
        if (alR.contains(47)) {
            b.a(parcel, 47, nt.amE, true);
        }
        if (alR.contains(44)) {
            b.a(parcel, 44, nt.amB, true);
        }
        if (alR.contains(45)) {
            b.a(parcel, 45, nt.amC, true);
        }
        if (alR.contains(51)) {
            b.a(parcel, 51, nt.amI, true);
        }
        if (alR.contains(50)) {
            b.a(parcel, 50, (Parcelable)nt.amH, n, true);
        }
        if (alR.contains(49)) {
            b.a(parcel, 49, nt.amG, true);
        }
        if (alR.contains(48)) {
            b.a(parcel, 48, nt.amF, true);
        }
        if (alR.contains(55)) {
            b.a(parcel, 55, nt.amK, true);
        }
        if (alR.contains(54)) {
            b.a(parcel, 54, nt.uR, true);
        }
        if (alR.contains(53)) {
            b.a(parcel, 53, nt.uO, true);
        }
        if (alR.contains(52)) {
            b.a(parcel, 52, nt.amJ, true);
        }
        if (alR.contains(56)) {
            b.a(parcel, 56, nt.amL, true);
        }
        b.H(parcel, d);
    }
    
    public nt db(final Parcel parcel) {
        final int c = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        nt nt = null;
        List<String> c2 = null;
        nt nt2 = null;
        String o = null;
        String o2 = null;
        String o3 = null;
        List<nt> c3 = null;
        int g2 = 0;
        List<nt> c4 = null;
        nt nt3 = null;
        List<nt> c5 = null;
        String o4 = null;
        String o5 = null;
        nt nt4 = null;
        String o6 = null;
        String o7 = null;
        String o8 = null;
        List<nt> c6 = null;
        String o9 = null;
        String o10 = null;
        String o11 = null;
        String o12 = null;
        String o13 = null;
        String o14 = null;
        String o15 = null;
        String o16 = null;
        String o17 = null;
        nt nt5 = null;
        String o18 = null;
        String o19 = null;
        String o20 = null;
        String o21 = null;
        nt nt6 = null;
        double m = 0.0;
        nt nt7 = null;
        double i = 0.0;
        String o22 = null;
        nt nt8 = null;
        List<nt> c7 = null;
        String o23 = null;
        String o24 = null;
        String o25 = null;
        String o26 = null;
        nt nt9 = null;
        String o27 = null;
        String o28 = null;
        String o29 = null;
        nt nt10 = null;
        String o30 = null;
        String o31 = null;
        String o32 = null;
        String o33 = null;
        String o34 = null;
        String o35 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    set.add(1);
                    continue;
                }
                case 2: {
                    nt = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(2);
                    continue;
                }
                case 3: {
                    c2 = a.C(parcel, b);
                    set.add(3);
                    continue;
                }
                case 4: {
                    nt2 = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(4);
                    continue;
                }
                case 5: {
                    o = a.o(parcel, b);
                    set.add(5);
                    continue;
                }
                case 6: {
                    o2 = a.o(parcel, b);
                    set.add(6);
                    continue;
                }
                case 7: {
                    o3 = a.o(parcel, b);
                    set.add(7);
                    continue;
                }
                case 8: {
                    c3 = a.c(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(8);
                    continue;
                }
                case 9: {
                    g2 = a.g(parcel, b);
                    set.add(9);
                    continue;
                }
                case 10: {
                    c4 = a.c(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(10);
                    continue;
                }
                case 11: {
                    nt3 = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(11);
                    continue;
                }
                case 12: {
                    c5 = a.c(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(12);
                    continue;
                }
                case 13: {
                    o4 = a.o(parcel, b);
                    set.add(13);
                    continue;
                }
                case 14: {
                    o5 = a.o(parcel, b);
                    set.add(14);
                    continue;
                }
                case 15: {
                    nt4 = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(15);
                    continue;
                }
                case 17: {
                    o7 = a.o(parcel, b);
                    set.add(17);
                    continue;
                }
                case 16: {
                    o6 = a.o(parcel, b);
                    set.add(16);
                    continue;
                }
                case 19: {
                    c6 = a.c(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(19);
                    continue;
                }
                case 18: {
                    o8 = a.o(parcel, b);
                    set.add(18);
                    continue;
                }
                case 21: {
                    o10 = a.o(parcel, b);
                    set.add(21);
                    continue;
                }
                case 20: {
                    o9 = a.o(parcel, b);
                    set.add(20);
                    continue;
                }
                case 23: {
                    o12 = a.o(parcel, b);
                    set.add(23);
                    continue;
                }
                case 22: {
                    o11 = a.o(parcel, b);
                    set.add(22);
                    continue;
                }
                case 25: {
                    o14 = a.o(parcel, b);
                    set.add(25);
                    continue;
                }
                case 24: {
                    o13 = a.o(parcel, b);
                    set.add(24);
                    continue;
                }
                case 27: {
                    o16 = a.o(parcel, b);
                    set.add(27);
                    continue;
                }
                case 26: {
                    o15 = a.o(parcel, b);
                    set.add(26);
                    continue;
                }
                case 29: {
                    nt5 = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(29);
                    continue;
                }
                case 28: {
                    o17 = a.o(parcel, b);
                    set.add(28);
                    continue;
                }
                case 31: {
                    o19 = a.o(parcel, b);
                    set.add(31);
                    continue;
                }
                case 30: {
                    o18 = a.o(parcel, b);
                    set.add(30);
                    continue;
                }
                case 34: {
                    nt6 = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(34);
                    continue;
                }
                case 32: {
                    o20 = a.o(parcel, b);
                    set.add(32);
                    continue;
                }
                case 33: {
                    o21 = a.o(parcel, b);
                    set.add(33);
                    continue;
                }
                case 38: {
                    i = a.m(parcel, b);
                    set.add(38);
                    continue;
                }
                case 39: {
                    o22 = a.o(parcel, b);
                    set.add(39);
                    continue;
                }
                case 36: {
                    m = a.m(parcel, b);
                    set.add(36);
                    continue;
                }
                case 37: {
                    nt7 = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(37);
                    continue;
                }
                case 42: {
                    o23 = a.o(parcel, b);
                    set.add(42);
                    continue;
                }
                case 43: {
                    o24 = a.o(parcel, b);
                    set.add(43);
                    continue;
                }
                case 40: {
                    nt8 = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(40);
                    continue;
                }
                case 41: {
                    c7 = a.c(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(41);
                    continue;
                }
                case 46: {
                    nt9 = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(46);
                    continue;
                }
                case 47: {
                    o27 = a.o(parcel, b);
                    set.add(47);
                    continue;
                }
                case 44: {
                    o25 = a.o(parcel, b);
                    set.add(44);
                    continue;
                }
                case 45: {
                    o26 = a.o(parcel, b);
                    set.add(45);
                    continue;
                }
                case 51: {
                    o30 = a.o(parcel, b);
                    set.add(51);
                    continue;
                }
                case 50: {
                    nt10 = a.a(parcel, b, (android.os.Parcelable$Creator<nt>)com.google.android.gms.internal.nt.CREATOR);
                    set.add(50);
                    continue;
                }
                case 49: {
                    o29 = a.o(parcel, b);
                    set.add(49);
                    continue;
                }
                case 48: {
                    o28 = a.o(parcel, b);
                    set.add(48);
                    continue;
                }
                case 55: {
                    o34 = a.o(parcel, b);
                    set.add(55);
                    continue;
                }
                case 54: {
                    o33 = a.o(parcel, b);
                    set.add(54);
                    continue;
                }
                case 53: {
                    o32 = a.o(parcel, b);
                    set.add(53);
                    continue;
                }
                case 52: {
                    o31 = a.o(parcel, b);
                    set.add(52);
                    continue;
                }
                case 56: {
                    o35 = a.o(parcel, b);
                    set.add(56);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new nt(set, g, nt, c2, nt2, o, o2, o3, c3, g2, c4, nt3, c5, o4, o5, nt4, o6, o7, o8, c6, o9, o10, o11, o12, o13, o14, o15, o16, o17, nt5, o18, o19, o20, o21, nt6, m, nt7, i, o22, nt8, c7, o23, o24, o25, o26, nt9, o27, o28, o29, nt10, o30, o31, o32, o33, o34, o35);
    }
    
    public nt[] eS(final int n) {
        return new nt[n];
    }
}
