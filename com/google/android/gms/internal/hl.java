// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hl implements Parcelable$Creator<hk>
{
    static void a(final hk hk, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1000, hk.xH);
        b.a(parcel, 2, (Parcelable)hk.hZ(), n, false);
        b.a(parcel, 3, hk.getInterval());
        b.c(parcel, 4, hk.getPriority());
        b.F(parcel, p3);
    }
    
    public hk aF(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        hg hg = null;
        long n = hk.OF;
        int g2 = 102;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    hg = a.a(parcel, n2, (android.os.Parcelable$Creator<hg>)com.google.android.gms.internal.hg.CREATOR);
                    continue;
                }
                case 3: {
                    n = a.i(parcel, n2);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new hk(g, hg, n, g2);
    }
    
    public hk[] bG(final int n) {
        return new hk[n];
    }
}
