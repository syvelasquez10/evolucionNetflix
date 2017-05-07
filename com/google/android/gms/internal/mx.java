// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class mx implements Parcelable$Creator<mw>
{
    static void a(final mw mw, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, mw.ahY, false);
        b.c(parcel, 1000, mw.versionCode);
        b.a(parcel, 2, mw.ahZ, false);
        b.a(parcel, 3, mw.Dv, false);
        b.H(parcel, d);
    }
    
    public mw cF(final Parcel parcel) {
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
        return new mw(g, o3, o2, o);
    }
    
    public mw[] eu(final int n) {
        return new mw[n];
    }
}
