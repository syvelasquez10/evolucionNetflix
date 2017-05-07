// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class iw implements Parcelable$Creator<iv>
{
    static void a(final iv iv, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, iv.getVersionCode());
        b.a(parcel, 2, iv.acs, false);
        b.F(parcel, p3);
    }
    
    public iv bl(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        int[] t = null;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    t = a.t(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new iv(g, t);
    }
    
    public iv[] cx(final int n) {
        return new iv[n];
    }
}
