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

public class of implements Parcelable$Creator<ny.d>
{
    static void a(final ny.d d, final Parcel parcel, int d2) {
        d2 = b.D(parcel);
        final Set<Integer> alR = d.alR;
        if (alR.contains(1)) {
            b.c(parcel, 1, d.BR);
        }
        if (alR.contains(2)) {
            b.a(parcel, 2, d.amp, true);
        }
        if (alR.contains(3)) {
            b.a(parcel, 3, d.anp, true);
        }
        if (alR.contains(4)) {
            b.a(parcel, 4, d.ams, true);
        }
        if (alR.contains(5)) {
            b.a(parcel, 5, d.anq, true);
        }
        if (alR.contains(6)) {
            b.a(parcel, 6, d.anr, true);
        }
        if (alR.contains(7)) {
            b.a(parcel, 7, d.ans, true);
        }
        b.H(parcel, d2);
    }
    
    public ny.d dj(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        String o2 = null;
        String o3 = null;
        String o4 = null;
        String o5 = null;
        String o6 = null;
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
                    o6 = a.o(parcel, b);
                    set.add(2);
                    continue;
                }
                case 3: {
                    o5 = a.o(parcel, b);
                    set.add(3);
                    continue;
                }
                case 4: {
                    o4 = a.o(parcel, b);
                    set.add(4);
                    continue;
                }
                case 5: {
                    o3 = a.o(parcel, b);
                    set.add(5);
                    continue;
                }
                case 6: {
                    o2 = a.o(parcel, b);
                    set.add(6);
                    continue;
                }
                case 7: {
                    o = a.o(parcel, b);
                    set.add(7);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ny.d(set, g, o6, o5, o4, o3, o2, o);
    }
    
    public ny.d[] fa(final int n) {
        return new ny.d[n];
    }
}
