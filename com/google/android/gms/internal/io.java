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

public class io implements Parcelable$Creator<ih.d>
{
    static void a(final ih.d d, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        final Set<Integer> ja = d.ja();
        if (ja.contains(1)) {
            b.c(parcel, 1, d.getVersionCode());
        }
        if (ja.contains(2)) {
            b.a(parcel, 2, d.getFamilyName(), true);
        }
        if (ja.contains(3)) {
            b.a(parcel, 3, d.getFormatted(), true);
        }
        if (ja.contains(4)) {
            b.a(parcel, 4, d.getGivenName(), true);
        }
        if (ja.contains(5)) {
            b.a(parcel, 5, d.getHonorificPrefix(), true);
        }
        if (ja.contains(6)) {
            b.a(parcel, 6, d.getHonorificSuffix(), true);
        }
        if (ja.contains(7)) {
            b.a(parcel, 7, d.getMiddleName(), true);
        }
        b.F(parcel, p3);
    }
    
    public ih.d aT(final Parcel parcel) {
        String n = null;
        final int o = a.o(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        String n2 = null;
        String n3 = null;
        String n4 = null;
        String n5 = null;
        String n6 = null;
        while (parcel.dataPosition() < o) {
            final int n7 = a.n(parcel);
            switch (a.R(n7)) {
                default: {
                    a.b(parcel, n7);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n7);
                    set.add(1);
                    continue;
                }
                case 2: {
                    n6 = a.n(parcel, n7);
                    set.add(2);
                    continue;
                }
                case 3: {
                    n5 = a.n(parcel, n7);
                    set.add(3);
                    continue;
                }
                case 4: {
                    n4 = a.n(parcel, n7);
                    set.add(4);
                    continue;
                }
                case 5: {
                    n3 = a.n(parcel, n7);
                    set.add(5);
                    continue;
                }
                case 6: {
                    n2 = a.n(parcel, n7);
                    set.add(6);
                    continue;
                }
                case 7: {
                    n = a.n(parcel, n7);
                    set.add(7);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ih.d(set, g, n6, n5, n4, n3, n2, n);
    }
    
    public ih.d[] bW(final int n) {
        return new ih.d[n];
    }
}
