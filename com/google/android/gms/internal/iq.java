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

public class iq implements Parcelable$Creator<ih.g>
{
    static void a(final ih.g g, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        final Set<Integer> ja = g.ja();
        if (ja.contains(1)) {
            b.c(parcel, 1, g.getVersionCode());
        }
        if (ja.contains(2)) {
            b.a(parcel, 2, g.isPrimary());
        }
        if (ja.contains(3)) {
            b.a(parcel, 3, g.getValue(), true);
        }
        b.F(parcel, p3);
    }
    
    public ih.g aV(final Parcel parcel) {
        boolean c = false;
        final int o = a.o(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        String n = null;
        int g = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n2);
                    set.add(1);
                    continue;
                }
                case 2: {
                    c = a.c(parcel, n2);
                    set.add(2);
                    continue;
                }
                case 3: {
                    n = a.n(parcel, n2);
                    set.add(3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ih.g(set, g, c, n);
    }
    
    public ih.g[] bY(final int n) {
        return new ih.g[n];
    }
}
