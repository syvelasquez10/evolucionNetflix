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

public class il implements Parcelable$Creator<ih.b.a>
{
    static void a(final ih.b.a a, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        final Set<Integer> ja = a.ja();
        if (ja.contains(1)) {
            b.c(parcel, 1, a.getVersionCode());
        }
        if (ja.contains(2)) {
            b.c(parcel, 2, a.getLeftImageOffset());
        }
        if (ja.contains(3)) {
            b.c(parcel, 3, a.getTopImageOffset());
        }
        b.F(parcel, p3);
    }
    
    public ih.b.a aQ(final Parcel parcel) {
        int g = 0;
        final int o = a.o(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, n);
                    set.add(1);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, n);
                    set.add(2);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, n);
                    set.add(3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ih.b.a(set, g3, g2, g);
    }
    
    public ih.b.a[] bT(final int n) {
        return new ih.b.a[n];
    }
}
