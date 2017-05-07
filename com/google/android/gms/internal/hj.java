// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hj implements Parcelable$Creator<hi>
{
    static void a(final hi hi, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, hi.xH);
        b.a(parcel, 2, hi.hY(), false);
        b.a(parcel, 3, hi.getTag(), false);
        b.F(parcel, p3);
    }
    
    public hi aE(final Parcel parcel) {
        String n = null;
        final int o = a.o(parcel);
        int g = 0;
        String n2 = null;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 3: {
                    n = a.n(parcel, n3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new hi(g, n2, n);
    }
    
    public hi[] bF(final int n) {
        return new hi[n];
    }
}
