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

public class nz implements Parcelable$Creator<ny>
{
    static void a(final ny ny, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        final Set<Integer> alR = ny.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, ny.BR);
        }
        if (alR.contains(2)) {
            b.a(parcel, 2, ny.amP, true);
        }
        if (alR.contains(3)) {
            b.a(parcel, 3, (Parcelable)ny.amQ, n, true);
        }
        if (alR.contains(4)) {
            b.a(parcel, 4, ny.amR, true);
        }
        if (alR.contains(5)) {
            b.a(parcel, 5, ny.amS, true);
        }
        if (alR.contains(6)) {
            b.c(parcel, 6, ny.amT);
        }
        if (alR.contains(7)) {
            b.a(parcel, 7, (Parcelable)ny.amU, n, true);
        }
        if (alR.contains(8)) {
            b.a(parcel, 8, ny.amV, true);
        }
        if (alR.contains(9)) {
            b.a(parcel, 9, ny.Nz, true);
        }
        if (alR.contains(12)) {
            b.c(parcel, 12, ny.om);
        }
        if (alR.contains(14)) {
            b.a(parcel, 14, ny.BL, true);
        }
        if (alR.contains(15)) {
            b.a(parcel, 15, (Parcelable)ny.amW, n, true);
        }
        if (alR.contains(16)) {
            b.a(parcel, 16, ny.amX);
        }
        if (alR.contains(19)) {
            b.a(parcel, 19, (Parcelable)ny.amY, n, true);
        }
        if (alR.contains(18)) {
            b.a(parcel, 18, ny.Fc, true);
        }
        if (alR.contains(21)) {
            b.c(parcel, 21, ny.ana);
        }
        if (alR.contains(20)) {
            b.a(parcel, 20, ny.amZ, true);
        }
        if (alR.contains(23)) {
            b.c(parcel, 23, ny.anc, true);
        }
        if (alR.contains(22)) {
            b.c(parcel, 22, ny.anb, true);
        }
        if (alR.contains(25)) {
            b.c(parcel, 25, ny.ane);
        }
        if (alR.contains(24)) {
            b.c(parcel, 24, ny.and);
        }
        if (alR.contains(27)) {
            b.a(parcel, 27, ny.uR, true);
        }
        if (alR.contains(26)) {
            b.a(parcel, 26, ny.anf, true);
        }
        if (alR.contains(29)) {
            b.a(parcel, 29, ny.anh);
        }
        if (alR.contains(28)) {
            b.c(parcel, 28, ny.ang, true);
        }
        b.H(parcel, d);
    }
    
    public ny dd(final Parcel parcel) {
        final int c = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        String o = null;
        ny$a ny$a = null;
        String o2 = null;
        String o3 = null;
        int g2 = 0;
        ny$b ny$b = null;
        String o4 = null;
        String o5 = null;
        int g3 = 0;
        String o6 = null;
        ny$c ny$c = null;
        boolean c2 = false;
        String o7 = null;
        ny$d ny$d = null;
        String o8 = null;
        int g4 = 0;
        List<ny$f> c3 = null;
        List<ny$g> c4 = null;
        int g5 = 0;
        int g6 = 0;
        String o9 = null;
        String o10 = null;
        List<ny$h> c5 = null;
        boolean c6 = false;
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
                    o = a.o(parcel, b);
                    set.add(2);
                    continue;
                }
                case 3: {
                    ny$a = a.a(parcel, b, (android.os.Parcelable$Creator<ny$a>)com.google.android.gms.internal.ny$a.CREATOR);
                    set.add(3);
                    continue;
                }
                case 4: {
                    o2 = a.o(parcel, b);
                    set.add(4);
                    continue;
                }
                case 5: {
                    o3 = a.o(parcel, b);
                    set.add(5);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, b);
                    set.add(6);
                    continue;
                }
                case 7: {
                    ny$b = a.a(parcel, b, (android.os.Parcelable$Creator<ny$b>)com.google.android.gms.internal.ny$b.CREATOR);
                    set.add(7);
                    continue;
                }
                case 8: {
                    o4 = a.o(parcel, b);
                    set.add(8);
                    continue;
                }
                case 9: {
                    o5 = a.o(parcel, b);
                    set.add(9);
                    continue;
                }
                case 12: {
                    g3 = a.g(parcel, b);
                    set.add(12);
                    continue;
                }
                case 14: {
                    o6 = a.o(parcel, b);
                    set.add(14);
                    continue;
                }
                case 15: {
                    ny$c = a.a(parcel, b, (android.os.Parcelable$Creator<ny$c>)com.google.android.gms.internal.ny$c.CREATOR);
                    set.add(15);
                    continue;
                }
                case 16: {
                    c2 = a.c(parcel, b);
                    set.add(16);
                    continue;
                }
                case 19: {
                    ny$d = a.a(parcel, b, (android.os.Parcelable$Creator<ny$d>)com.google.android.gms.internal.ny$d.CREATOR);
                    set.add(19);
                    continue;
                }
                case 18: {
                    o7 = a.o(parcel, b);
                    set.add(18);
                    continue;
                }
                case 21: {
                    g4 = a.g(parcel, b);
                    set.add(21);
                    continue;
                }
                case 20: {
                    o8 = a.o(parcel, b);
                    set.add(20);
                    continue;
                }
                case 23: {
                    c4 = a.c(parcel, b, (android.os.Parcelable$Creator<ny$g>)ny$g.CREATOR);
                    set.add(23);
                    continue;
                }
                case 22: {
                    c3 = a.c(parcel, b, (android.os.Parcelable$Creator<ny$f>)ny$f.CREATOR);
                    set.add(22);
                    continue;
                }
                case 25: {
                    g6 = a.g(parcel, b);
                    set.add(25);
                    continue;
                }
                case 24: {
                    g5 = a.g(parcel, b);
                    set.add(24);
                    continue;
                }
                case 27: {
                    o10 = a.o(parcel, b);
                    set.add(27);
                    continue;
                }
                case 26: {
                    o9 = a.o(parcel, b);
                    set.add(26);
                    continue;
                }
                case 29: {
                    c6 = a.c(parcel, b);
                    set.add(29);
                    continue;
                }
                case 28: {
                    c5 = a.c(parcel, b, (android.os.Parcelable$Creator<ny$h>)ny$h.CREATOR);
                    set.add(28);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ny(set, g, o, ny$a, o2, o3, g2, ny$b, o4, o5, g3, o6, ny$c, c2, o7, ny$d, o8, g4, c3, c4, g5, g6, o9, o10, c5, c6);
    }
    
    public ny[] eU(final int n) {
        return new ny[n];
    }
}
