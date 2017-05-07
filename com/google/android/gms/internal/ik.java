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

public class ik implements Parcelable$Creator<ig.b.a>
{
    static void a(final ig.b.a a, final Parcel parcel, int o) {
        o = b.o(parcel);
        final Set<Integer> fa = a.fa();
        if (fa.contains(1)) {
            b.c(parcel, 1, a.getVersionCode());
        }
        if (fa.contains(2)) {
            b.c(parcel, 2, a.getLeftImageOffset());
        }
        if (fa.contains(3)) {
            b.c(parcel, 3, a.getTopImageOffset());
        }
        b.D(parcel, o);
    }
    
    public ig.b.a ax(final Parcel parcel) {
        int g = 0;
        final int n = a.n(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, m);
                    set.add(1);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, m);
                    set.add(2);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, m);
                    set.add(3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ig.b.a(set, g3, g2, g);
    }
    
    public ig.b.a[] bp(final int n) {
        return new ig.b.a[n];
    }
}
