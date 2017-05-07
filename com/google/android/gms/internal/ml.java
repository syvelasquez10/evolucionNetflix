// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ml implements Parcelable$Creator<mk>
{
    static void a(final mk mk, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, mk.BR);
        b.a(parcel, 2, mk.mi(), false);
        b.a(parcel, 3, mk.getTag(), false);
        b.H(parcel, d);
    }
    
    public mk cA(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        int g = 0;
        String o2 = null;
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
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new mk(g, o2, o);
    }
    
    public mk[] ep(final int n) {
        return new mk[n];
    }
}
