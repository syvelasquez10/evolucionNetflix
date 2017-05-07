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

public class il implements Parcelable$Creator<ig.b.b>
{
    static void a(final ig.b.b b, final Parcel parcel, int o) {
        o = b.o(parcel);
        final Set<Integer> fa = b.fa();
        if (fa.contains(1)) {
            b.c(parcel, 1, b.getVersionCode());
        }
        if (fa.contains(2)) {
            b.c(parcel, 2, b.getHeight());
        }
        if (fa.contains(3)) {
            b.a(parcel, 3, b.getUrl(), true);
        }
        if (fa.contains(4)) {
            b.c(parcel, 4, b.getWidth());
        }
        b.D(parcel, o);
    }
    
    public ig.b.b ay(final Parcel parcel) {
        int g = 0;
        final int n = a.n(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        String m = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, i);
                    set.add(1);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, i);
                    set.add(2);
                    continue;
                }
                case 3: {
                    m = a.m(parcel, i);
                    set.add(3);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, i);
                    set.add(4);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ig.b.b(set, g3, g2, m, g);
    }
    
    public ig.b.b[] bq(final int n) {
        return new ig.b.b[n];
    }
}
