// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class iy implements Parcelable$Creator<ix>
{
    static void a(final ix ix, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, ix.getVersionCode());
        b.a(parcel, 2, ix.act, false);
        b.a(parcel, 3, ix.acu, false);
        b.F(parcel, p3);
    }
    
    public ix bm(final Parcel parcel) {
        String[] z = null;
        final int o = a.o(parcel);
        int g = 0;
        byte[][] r = null;
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
                    z = a.z(parcel, n);
                    continue;
                }
                case 3: {
                    r = a.r(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new ix(g, z, r);
    }
    
    public ix[] cy(final int n) {
        return new ix[n];
    }
}
