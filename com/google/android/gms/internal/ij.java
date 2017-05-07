// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.HashSet;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.Set;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ij implements Parcelable$Creator<ig.b>
{
    static void a(final ig.b b, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        final Set<Integer> fa = b.fa();
        if (fa.contains(1)) {
            b.c(parcel, 1, b.getVersionCode());
        }
        if (fa.contains(2)) {
            b.a(parcel, 2, (Parcelable)b.fE(), n, true);
        }
        if (fa.contains(3)) {
            b.a(parcel, 3, (Parcelable)b.fF(), n, true);
        }
        if (fa.contains(4)) {
            b.c(parcel, 4, b.getLayout());
        }
        b.D(parcel, o);
    }
    
    public ig.b aw(final Parcel parcel) {
        SafeParcelable safeParcelable = null;
        int g = 0;
        final int n = a.n(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        SafeParcelable safeParcelable2 = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, m);
                    set.add(1);
                    continue;
                }
                case 2: {
                    safeParcelable2 = a.a(parcel, m, (android.os.Parcelable$Creator<ig.b.a>)ig.b.a.CREATOR);
                    set.add(2);
                    continue;
                }
                case 3: {
                    safeParcelable = a.a(parcel, m, (android.os.Parcelable$Creator<ig.b.b>)ig.b.b.CREATOR);
                    set.add(3);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, m);
                    set.add(4);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ig.b(set, g2, (ig.b.a)safeParcelable2, (ig.b.b)safeParcelable, g);
    }
    
    public ig.b[] bo(final int n) {
        return new ig.b[n];
    }
}
