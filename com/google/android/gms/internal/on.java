// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class on implements Parcelable$Creator<om>
{
    static void a(final om om, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, om.getVersionCode());
        b.a(parcel, 2, om.atC, false);
        b.H(parcel, d);
    }
    
    public om dB(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        int[] u = null;
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
                case 2: {
                    u = a.u(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new om(g, u);
    }
    
    public om[] fB(final int n) {
        return new om[n];
    }
}
