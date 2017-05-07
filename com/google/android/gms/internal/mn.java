// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class mn implements Parcelable$Creator<mm>
{
    static void a(final mm mm, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1000, mm.BR);
        b.a(parcel, 2, (Parcelable)mm.mf(), n, false);
        b.a(parcel, 3, mm.getInterval());
        b.c(parcel, 4, mm.getPriority());
        b.H(parcel, d);
    }
    
    public mm cB(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        mi mi = null;
        long n = mm.afp;
        int g2 = 102;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    mi = a.a(parcel, b, (android.os.Parcelable$Creator<mi>)com.google.android.gms.internal.mi.CREATOR);
                    continue;
                }
                case 3: {
                    n = a.i(parcel, b);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new mm(g, mi, n, g2);
    }
    
    public mm[] eq(final int n) {
        return new mm[n];
    }
}
