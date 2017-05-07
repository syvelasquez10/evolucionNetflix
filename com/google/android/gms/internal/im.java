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

public class im implements Parcelable$Creator<ih.b.b>
{
    static void a(final ih.b.b b, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        final Set<Integer> ja = b.ja();
        if (ja.contains(1)) {
            b.c(parcel, 1, b.getVersionCode());
        }
        if (ja.contains(2)) {
            b.c(parcel, 2, b.getHeight());
        }
        if (ja.contains(3)) {
            b.a(parcel, 3, b.getUrl(), true);
        }
        if (ja.contains(4)) {
            b.c(parcel, 4, b.getWidth());
        }
        b.F(parcel, p3);
    }
    
    public ih.b.b aR(final Parcel parcel) {
        int g = 0;
        final int o = a.o(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        String n = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, n2);
                    set.add(1);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, n2);
                    set.add(2);
                    continue;
                }
                case 3: {
                    n = a.n(parcel, n2);
                    set.add(3);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, n2);
                    set.add(4);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ih.b.b(set, g3, g2, n, g);
    }
    
    public ih.b.b[] bU(final int n) {
        return new ih.b.b[n];
    }
}
