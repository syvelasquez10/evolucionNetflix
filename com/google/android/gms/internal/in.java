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

public class in implements Parcelable$Creator<ih.c>
{
    static void a(final ih.c c, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        final Set<Integer> ja = c.ja();
        if (ja.contains(1)) {
            b.c(parcel, 1, c.getVersionCode());
        }
        if (ja.contains(2)) {
            b.a(parcel, 2, c.getUrl(), true);
        }
        b.F(parcel, p3);
    }
    
    public ih.c aS(final Parcel parcel) {
        final int o = a.o(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        String n = null;
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
                    n = a.n(parcel, n2);
                    set.add(2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ih.c(set, g, n);
    }
    
    public ih.c[] bV(final int n) {
        return new ih.c[n];
    }
}
