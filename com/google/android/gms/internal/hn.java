// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hn implements Parcelable$Creator<hm>
{
    static void a(final hm hm, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.a(parcel, 1, hm.Rd, false);
        b.c(parcel, 1000, hm.xH);
        b.F(parcel, p3);
    }
    
    public hm aG(final Parcel parcel) {
        final int o = a.o(parcel);
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
                    n = a.n(parcel, n2);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new hm(g, n);
    }
    
    public hm[] bH(final int n) {
        return new hm[n];
    }
}
