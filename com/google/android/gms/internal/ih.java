// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ih implements Parcelable$Creator<ig>
{
    static void a(final ig ig, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, ig.getVersionCode());
        b.a(parcel, 2, ig.fz(), false);
        b.H(parcel, d);
    }
    
    public ig[] ab(final int n) {
        return new ig[n];
    }
    
    public ig w(final Parcel parcel) {
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
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ig(g, o);
    }
}
