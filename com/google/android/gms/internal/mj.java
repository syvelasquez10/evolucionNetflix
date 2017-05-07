// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class mj implements Parcelable$Creator<mi>
{
    static void a(final mi mi, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, mi.afg, false);
        b.c(parcel, 1000, mi.BR);
        b.a(parcel, 2, mi.mg(), false);
        b.a(parcel, 3, mi.mh());
        b.c(parcel, 4, mi.afj, false);
        b.b(parcel, 6, mi.afk, false);
        b.H(parcel, d);
    }
    
    public mi cz(final Parcel parcel) {
        boolean c = false;
        List<String> c2 = null;
        final int c3 = a.C(parcel);
        List<ms> c4 = null;
        String o = null;
        List<mo> c5 = null;
        int g = 0;
        while (parcel.dataPosition() < c3) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    c5 = a.c(parcel, b, (android.os.Parcelable$Creator<mo>)mo.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, b);
                    continue;
                }
                case 4: {
                    c4 = a.c(parcel, b, (android.os.Parcelable$Creator<ms>)ms.CREATOR);
                    continue;
                }
                case 6: {
                    c2 = a.C(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c3) {
            throw new a.a("Overread allowed size end=" + c3, parcel);
        }
        return new mi(g, c5, o, c, c4, c2);
    }
    
    public mi[] eo(final int n) {
        return new mi[n];
    }
}
