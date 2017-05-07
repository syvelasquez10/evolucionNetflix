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

public class ip implements Parcelable$Creator<ig.g>
{
    static void a(final ig.g g, final Parcel parcel, int o) {
        o = b.o(parcel);
        final Set<Integer> fa = g.fa();
        if (fa.contains(1)) {
            b.c(parcel, 1, g.getVersionCode());
        }
        if (fa.contains(2)) {
            b.a(parcel, 2, g.isPrimary());
        }
        if (fa.contains(3)) {
            b.a(parcel, 3, g.getValue(), true);
        }
        b.D(parcel, o);
    }
    
    public ig.g aC(final Parcel parcel) {
        boolean c = false;
        final int n = a.n(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        String m = null;
        int g = 0;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, i);
                    set.add(1);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, i);
                    set.add(2);
                    continue;
                }
                case 3: {
                    m = a.m(parcel, i);
                    set.add(3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ig.g(set, g, c, m);
    }
    
    public ig.g[] bu(final int n) {
        return new ig.g[n];
    }
}
