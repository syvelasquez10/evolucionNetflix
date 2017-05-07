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

public class io implements Parcelable$Creator<ig.f>
{
    static void a(final ig.f f, final Parcel parcel, int o) {
        o = b.o(parcel);
        final Set<Integer> fa = f.fa();
        if (fa.contains(1)) {
            b.c(parcel, 1, f.getVersionCode());
        }
        if (fa.contains(2)) {
            b.a(parcel, 2, f.getDepartment(), true);
        }
        if (fa.contains(3)) {
            b.a(parcel, 3, f.getDescription(), true);
        }
        if (fa.contains(4)) {
            b.a(parcel, 4, f.getEndDate(), true);
        }
        if (fa.contains(5)) {
            b.a(parcel, 5, f.getLocation(), true);
        }
        if (fa.contains(6)) {
            b.a(parcel, 6, f.getName(), true);
        }
        if (fa.contains(7)) {
            b.a(parcel, 7, f.isPrimary());
        }
        if (fa.contains(8)) {
            b.a(parcel, 8, f.getStartDate(), true);
        }
        if (fa.contains(9)) {
            b.a(parcel, 9, f.getTitle(), true);
        }
        if (fa.contains(10)) {
            b.c(parcel, 10, f.getType());
        }
        b.D(parcel, o);
    }
    
    public ig.f aB(final Parcel parcel) {
        int g = 0;
        String m = null;
        final int n = a.n(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        String i = null;
        boolean c = false;
        String j = null;
        String k = null;
        String l = null;
        String m2 = null;
        String m3 = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int m4 = a.m(parcel);
            switch (a.M(m4)) {
                default: {
                    a.b(parcel, m4);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, m4);
                    set.add(1);
                    continue;
                }
                case 2: {
                    m3 = a.m(parcel, m4);
                    set.add(2);
                    continue;
                }
                case 3: {
                    m2 = a.m(parcel, m4);
                    set.add(3);
                    continue;
                }
                case 4: {
                    l = a.m(parcel, m4);
                    set.add(4);
                    continue;
                }
                case 5: {
                    k = a.m(parcel, m4);
                    set.add(5);
                    continue;
                }
                case 6: {
                    j = a.m(parcel, m4);
                    set.add(6);
                    continue;
                }
                case 7: {
                    c = a.c(parcel, m4);
                    set.add(7);
                    continue;
                }
                case 8: {
                    i = a.m(parcel, m4);
                    set.add(8);
                    continue;
                }
                case 9: {
                    m = a.m(parcel, m4);
                    set.add(9);
                    continue;
                }
                case 10: {
                    g = a.g(parcel, m4);
                    set.add(10);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ig.f(set, g2, m3, m2, l, k, j, c, i, m, g);
    }
    
    public ig.f[] bt(final int n) {
        return new ig.f[n];
    }
}
