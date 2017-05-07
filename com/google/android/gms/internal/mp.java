// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class mp implements Parcelable$Creator<mo>
{
    static void a(final mo mo, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, mo.uO, false);
        b.c(parcel, 1000, mo.BR);
        b.H(parcel, d);
    }
    
    public mo cC(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
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
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new mo(g, o);
    }
    
    public mo[] er(final int n) {
        return new mo[n];
    }
}
