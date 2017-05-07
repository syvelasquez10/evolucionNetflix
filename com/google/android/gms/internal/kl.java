// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class kl implements Parcelable$Creator<kk>
{
    static void a(final kk kk, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, kk.xH);
        b.a(parcel, 2, kk.getId(), false);
        b.a(parcel, 3, kk.getDisplayName(), false);
        b.F(parcel, p3);
    }
    
    public kk bz(final Parcel parcel) {
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
        return new kk(g, n2, n);
    }
    
    public kk[] cO(final int n) {
        return new kk[n];
    }
}
