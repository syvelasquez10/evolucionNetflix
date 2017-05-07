// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class d implements Parcelable$Creator<c>
{
    static void a(final c c, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, c.aem);
        b.c(parcel, 1000, c.getVersionCode());
        b.c(parcel, 2, c.aen);
        b.a(parcel, 3, c.aeo);
        b.H(parcel, d);
    }
    
    public c ct(final Parcel parcel) {
        int g = 1;
        final int c = a.C(parcel);
        int g2 = 0;
        long i = 0L;
        int g3 = 1;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new c(g2, g3, g, i);
    }
    
    public c[] eg(final int n) {
        return new c[n];
    }
}
