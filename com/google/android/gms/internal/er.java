// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class er implements Parcelable$Creator<ep.a>
{
    static void a(final ep.a a, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, a.versionCode);
        b.a(parcel, 2, a.qg, false);
        b.c(parcel, 3, a.qh);
        b.D(parcel, o);
    }
    
    public ep.a[] Q(final int n) {
        return new ep.a[n];
    }
    
    public ep.a s(final Parcel parcel) {
        int g = 0;
        final int n = a.n(parcel);
        String m = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, i);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ep.a(g2, m, g);
    }
}
