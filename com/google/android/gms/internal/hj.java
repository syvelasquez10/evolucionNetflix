// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hj implements Parcelable$Creator<hi>
{
    static void a(final hi hi, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, hi.Ce, false);
        b.c(parcel, 1000, hi.BR);
        b.a(parcel, 3, (Parcelable)hi.Cf, n, false);
        b.c(parcel, 4, hi.Cg);
        b.a(parcel, 5, hi.Ch, false);
        b.H(parcel, d);
    }
    
    public hi[] K(final int n) {
        return new hi[n];
    }
    
    public hi n(final Parcel parcel) {
        byte[] r = null;
        final int c = a.C(parcel);
        int g = 0;
        int g2 = -1;
        hq hq = null;
        String o = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    hq = a.a(parcel, b, (android.os.Parcelable$Creator<hq>)com.google.android.gms.internal.hq.CREATOR);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    r = a.r(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new hi(g, o, hq, g2, r);
    }
}
