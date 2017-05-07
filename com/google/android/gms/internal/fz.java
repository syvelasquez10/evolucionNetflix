// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class fz implements Parcelable$Creator<fx.a>
{
    static void a(final fx.a a, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, a.versionCode);
        b.a(parcel, 2, a.DW, false);
        b.c(parcel, 3, a.DX);
        b.F(parcel, p3);
    }
    
    public fx.a[] U(final int n) {
        return new fx.a[n];
    }
    
    public fx.a s(final Parcel parcel) {
        int g = 0;
        final int o = a.o(parcel);
        String n = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new fx.a(g2, n, g);
    }
}
