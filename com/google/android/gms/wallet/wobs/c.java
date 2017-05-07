// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<b>
{
    static void a(final b b, final Parcel parcel, int d) {
        d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, b.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, b.label, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, b.value, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public b dH(final Parcel parcel) {
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
        return new b(g, o2, o);
    }
    
    public b[] fJ(final int n) {
        return new b[n];
    }
}
