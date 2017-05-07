// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Set;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class og implements Parcelable$Creator<ny.f>
{
    static void a(final ny.f f, final Parcel parcel, int d) {
        d = b.D(parcel);
        final Set<Integer> alR = f.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, f.BR);
        }
        if (alR.contains(2)) {
            b.a(parcel, 2, f.ant, true);
        }
        if (alR.contains(3)) {
            b.a(parcel, 3, f.Tg, true);
        }
        if (alR.contains(4)) {
            b.a(parcel, 4, f.amo, true);
        }
        if (alR.contains(5)) {
            b.a(parcel, 5, f.anu, true);
        }
        if (alR.contains(6)) {
            b.a(parcel, 6, f.mName, true);
        }
        if (alR.contains(7)) {
            b.a(parcel, 7, f.anv);
        }
        if (alR.contains(8)) {
            b.a(parcel, 8, f.amE, true);
        }
        if (alR.contains(9)) {
            b.a(parcel, 9, f.No, true);
        }
        if (alR.contains(10)) {
            b.c(parcel, 10, f.FD);
        }
        b.H(parcel, d);
    }
    
    public ny.f dk(final Parcel parcel) {
        int g = 0;
        String o = null;
        final int c = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        String o2 = null;
        boolean c2 = false;
        String o3 = null;
        String o4 = null;
        String o5 = null;
        String o6 = null;
        String o7 = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    set.add(1);
                    continue;
                }
                case 2: {
                    o7 = a.o(parcel, b);
                    set.add(2);
                    continue;
                }
                case 3: {
                    o6 = a.o(parcel, b);
                    set.add(3);
                    continue;
                }
                case 4: {
                    o5 = a.o(parcel, b);
                    set.add(4);
                    continue;
                }
                case 5: {
                    o4 = a.o(parcel, b);
                    set.add(5);
                    continue;
                }
                case 6: {
                    o3 = a.o(parcel, b);
                    set.add(6);
                    continue;
                }
                case 7: {
                    c2 = a.c(parcel, b);
                    set.add(7);
                    continue;
                }
                case 8: {
                    o2 = a.o(parcel, b);
                    set.add(8);
                    continue;
                }
                case 9: {
                    o = a.o(parcel, b);
                    set.add(9);
                    continue;
                }
                case 10: {
                    g = a.g(parcel, b);
                    set.add(10);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ny.f(set, g2, o7, o6, o5, o4, o3, c2, o2, o, g);
    }
    
    public ny.f[] fb(final int n) {
        return new ny.f[n];
    }
}
