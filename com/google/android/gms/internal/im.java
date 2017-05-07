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

public class im implements Parcelable$Creator<ig.c>
{
    static void a(final ig.c c, final Parcel parcel, int o) {
        o = b.o(parcel);
        final Set<Integer> fa = c.fa();
        if (fa.contains(1)) {
            b.c(parcel, 1, c.getVersionCode());
        }
        if (fa.contains(2)) {
            b.a(parcel, 2, c.getUrl(), true);
        }
        b.D(parcel, o);
    }
    
    public ig.c az(final Parcel parcel) {
        final int n = a.n(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        int g = 0;
        String m = null;
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
                    m = a.m(parcel, i);
                    set.add(2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ig.c(set, g, m);
    }
    
    public ig.c[] br(final int n) {
        return new ig.c[n];
    }
}
