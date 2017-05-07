// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hh implements Parcelable$Creator<hg>
{
    static void a(final hg hg, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, hg.BZ, false);
        b.c(parcel, 1000, hg.BR);
        b.a(parcel, 2, hg.Ca, false);
        b.a(parcel, 3, hg.Cb, false);
        b.H(parcel, d);
    }
    
    public hg[] J(final int n) {
        return new hg[n];
    }
    
    public hg m(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        int g = 0;
        String o2 = null;
        String o3 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new hg(g, o3, o2, o);
    }
}
