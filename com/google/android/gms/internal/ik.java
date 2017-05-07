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

public class ik implements Parcelable$Creator<ih.b>
{
    static void a(final ih.b b, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        final Set<Integer> ja = b.ja();
        if (ja.contains(1)) {
            b.c(parcel, 1, b.getVersionCode());
        }
        if (ja.contains(2)) {
            b.a(parcel, 2, (Parcelable)b.jE(), n, true);
        }
        if (ja.contains(3)) {
            b.a(parcel, 3, (Parcelable)b.jF(), n, true);
        }
        if (ja.contains(4)) {
            b.c(parcel, 4, b.getLayout());
        }
        b.F(parcel, p3);
    }
    
    public ih.b aP(final Parcel parcel) {
        SafeParcelable safeParcelable = null;
        int g = 0;
        final int o = a.o(parcel);
        final HashSet<Integer> set = new HashSet<Integer>();
        SafeParcelable safeParcelable2 = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n);
                    set.add(1);
                    continue;
                }
                case 2: {
                    safeParcelable2 = a.a(parcel, n, (android.os.Parcelable$Creator<ih.b.a>)ih.b.a.CREATOR);
                    set.add(2);
                    continue;
                }
                case 3: {
                    safeParcelable = a.a(parcel, n, (android.os.Parcelable$Creator<ih.b.b>)ih.b.b.CREATOR);
                    set.add(3);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, n);
                    set.add(4);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ih.b(set, g2, (ih.b.a)safeParcelable2, (ih.b.b)safeParcelable, g);
    }
    
    public ih.b[] bS(final int n) {
        return new ih.b[n];
    }
}
