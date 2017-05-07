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

public class iq implements Parcelable$Creator<ig.h>
{
    static void a(final ig.h h, final Parcel parcel, int o) {
        o = b.o(parcel);
        final Set<Integer> fa = h.fa();
        if (fa.contains(1)) {
            b.c(parcel, 1, h.getVersionCode());
        }
        if (fa.contains(3)) {
            b.c(parcel, 3, h.fN());
        }
        if (fa.contains(4)) {
            b.a(parcel, 4, h.getValue(), true);
        }
        if (fa.contains(5)) {
            b.a(parcel, 5, h.getLabel(), true);
        }
        if (fa.contains(6)) {
            b.c(parcel, 6, h.getType());
        }
        b.D(parcel, o);
    }
    
    public ig.h aD(final Parcel parcel) {
        String m = null;
        int g = 0;
        final int n = a.n(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g2 = 0;
        String i = null;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int j = a.m(parcel);
            switch (a.M(j)) {
                default: {
                    a.b(parcel, j);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, j);
                    set.add(1);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, j);
                    set.add(3);
                    continue;
                }
                case 4: {
                    m = a.m(parcel, j);
                    set.add(4);
                    continue;
                }
                case 5: {
                    i = a.m(parcel, j);
                    set.add(5);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, j);
                    set.add(6);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ig.h(set, g3, i, g2, m, g);
    }
    
    public ig.h[] bv(final int n) {
        return new ig.h[n];
    }
}
