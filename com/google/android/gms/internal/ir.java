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

public class ir implements Parcelable$Creator<ih.h>
{
    static void a(final ih.h h, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        final Set<Integer> ja = h.ja();
        if (ja.contains(1)) {
            b.c(parcel, 1, h.getVersionCode());
        }
        if (ja.contains(3)) {
            b.c(parcel, 3, h.jN());
        }
        if (ja.contains(4)) {
            b.a(parcel, 4, h.getValue(), true);
        }
        if (ja.contains(5)) {
            b.a(parcel, 5, h.getLabel(), true);
        }
        if (ja.contains(6)) {
            b.c(parcel, 6, h.getType());
        }
        b.F(parcel, p3);
    }
    
    public ih.h aW(final Parcel parcel) {
        String n = null;
        int g = 0;
        final int o = a.o(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g2 = 0;
        String n2 = null;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, n3);
                    set.add(1);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, n3);
                    set.add(3);
                    continue;
                }
                case 4: {
                    n = a.n(parcel, n3);
                    set.add(4);
                    continue;
                }
                case 5: {
                    n2 = a.n(parcel, n3);
                    set.add(5);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, n3);
                    set.add(6);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ih.h(set, g3, n2, g2, n, g);
    }
    
    public ih.h[] bZ(final int n) {
        return new ih.h[n];
    }
}
