// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<b>
{
    static void a(final b b, final Parcel parcel, int p3) {
        p3 = com.google.android.gms.common.internal.safeparcel.b.p(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, b.Oh);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, b.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 2, b.Oi);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, b.Oj);
        com.google.android.gms.common.internal.safeparcel.b.F(parcel, p3);
    }
    
    public b aB(final Parcel parcel) {
        int g = 1;
        final int o = a.o(parcel);
        int g2 = 0;
        long i = 0L;
        int g3 = 1;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, n);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new b(g2, g3, g, i);
    }
    
    public b[] bA(final int n) {
        return new b[n];
    }
}
