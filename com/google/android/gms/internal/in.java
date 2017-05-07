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

public class in implements Parcelable$Creator<ig.d>
{
    static void a(final ig.d d, final Parcel parcel, int o) {
        o = b.o(parcel);
        final Set<Integer> fa = d.fa();
        if (fa.contains(1)) {
            b.c(parcel, 1, d.getVersionCode());
        }
        if (fa.contains(2)) {
            b.a(parcel, 2, d.getFamilyName(), true);
        }
        if (fa.contains(3)) {
            b.a(parcel, 3, d.getFormatted(), true);
        }
        if (fa.contains(4)) {
            b.a(parcel, 4, d.getGivenName(), true);
        }
        if (fa.contains(5)) {
            b.a(parcel, 5, d.getHonorificPrefix(), true);
        }
        if (fa.contains(6)) {
            b.a(parcel, 6, d.getHonorificSuffix(), true);
        }
        if (fa.contains(7)) {
            b.a(parcel, 7, d.getMiddleName(), true);
        }
        b.D(parcel, o);
    }
    
    public ig.d aA(final Parcel parcel) {
        String m = null;
        final int n = a.n(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        String i = null;
        String j = null;
        String k = null;
        String l = null;
        String m2 = null;
        while (parcel.dataPosition() < n) {
            final int m3 = a.m(parcel);
            switch (a.M(m3)) {
                default: {
                    a.b(parcel, m3);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m3);
                    set.add(1);
                    continue;
                }
                case 2: {
                    m2 = a.m(parcel, m3);
                    set.add(2);
                    continue;
                }
                case 3: {
                    l = a.m(parcel, m3);
                    set.add(3);
                    continue;
                }
                case 4: {
                    k = a.m(parcel, m3);
                    set.add(4);
                    continue;
                }
                case 5: {
                    j = a.m(parcel, m3);
                    set.add(5);
                    continue;
                }
                case 6: {
                    i = a.m(parcel, m3);
                    set.add(6);
                    continue;
                }
                case 7: {
                    m = a.m(parcel, m3);
                    set.add(7);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ig.d(set, g, m2, l, k, j, i, m);
    }
    
    public ig.d[] bs(final int n) {
        return new ig.d[n];
    }
}
