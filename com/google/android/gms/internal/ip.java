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

public class ip implements Parcelable$Creator<ih.f>
{
    static void a(final ih.f f, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        final Set<Integer> ja = f.ja();
        if (ja.contains(1)) {
            b.c(parcel, 1, f.getVersionCode());
        }
        if (ja.contains(2)) {
            b.a(parcel, 2, f.getDepartment(), true);
        }
        if (ja.contains(3)) {
            b.a(parcel, 3, f.getDescription(), true);
        }
        if (ja.contains(4)) {
            b.a(parcel, 4, f.getEndDate(), true);
        }
        if (ja.contains(5)) {
            b.a(parcel, 5, f.getLocation(), true);
        }
        if (ja.contains(6)) {
            b.a(parcel, 6, f.getName(), true);
        }
        if (ja.contains(7)) {
            b.a(parcel, 7, f.isPrimary());
        }
        if (ja.contains(8)) {
            b.a(parcel, 8, f.getStartDate(), true);
        }
        if (ja.contains(9)) {
            b.a(parcel, 9, f.getTitle(), true);
        }
        if (ja.contains(10)) {
            b.c(parcel, 10, f.getType());
        }
        b.F(parcel, p3);
    }
    
    public ih.f aU(final Parcel parcel) {
        int g = 0;
        String n = null;
        final int o = a.o(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        String n2 = null;
        boolean c = false;
        String n3 = null;
        String n4 = null;
        String n5 = null;
        String n6 = null;
        String n7 = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n8 = a.n(parcel);
            switch (a.R(n8)) {
                default: {
                    a.b(parcel, n8);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n8);
                    set.add(1);
                    continue;
                }
                case 2: {
                    n7 = a.n(parcel, n8);
                    set.add(2);
                    continue;
                }
                case 3: {
                    n6 = a.n(parcel, n8);
                    set.add(3);
                    continue;
                }
                case 4: {
                    n5 = a.n(parcel, n8);
                    set.add(4);
                    continue;
                }
                case 5: {
                    n4 = a.n(parcel, n8);
                    set.add(5);
                    continue;
                }
                case 6: {
                    n3 = a.n(parcel, n8);
                    set.add(6);
                    continue;
                }
                case 7: {
                    c = a.c(parcel, n8);
                    set.add(7);
                    continue;
                }
                case 8: {
                    n2 = a.n(parcel, n8);
                    set.add(8);
                    continue;
                }
                case 9: {
                    n = a.n(parcel, n8);
                    set.add(9);
                    continue;
                }
                case 10: {
                    g = a.g(parcel, n8);
                    set.add(10);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ih.f(set, g2, n7, n6, n5, n4, n3, c, n2, n, g);
    }
    
    public ih.f[] bX(final int n) {
        return new ih.f[n];
    }
}
