// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hl implements Parcelable$Creator<hk>
{
    static void a(final hk hk, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, hk.id);
        b.c(parcel, 1000, hk.BR);
        b.a(parcel, 2, hk.Ci, false);
        b.H(parcel, d);
    }
    
    public hk[] L(final int n) {
        return new hk[n];
    }
    
    public hk o(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        Bundle q = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    q = a.q(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new hk(g2, g, q);
    }
}
