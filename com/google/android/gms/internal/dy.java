// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class dy implements Parcelable$Creator<dx>
{
    static void a(final dx dx, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, dx.versionCode);
        b.a(parcel, 2, dx.rq, false);
        b.c(parcel, 3, dx.rr);
        b.c(parcel, 4, dx.rs);
        b.a(parcel, 5, dx.rt);
        b.F(parcel, p3);
    }
    
    public dx h(final Parcel parcel) {
        boolean c = false;
        final int o = a.o(parcel);
        String n = null;
        int g = 0;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 3: {
                    g2 = a.g(parcel, n2);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new dx(g3, n, g2, g, c);
    }
    
    public dx[] o(final int n) {
        return new dx[n];
    }
}
