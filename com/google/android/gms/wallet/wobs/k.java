// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class k implements Parcelable$Creator<j>
{
    static void a(final j j, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, j.getVersionCode());
        b.a(parcel, 2, j.auy, false);
        b.a(parcel, 3, j.tG, false);
        b.H(parcel, d);
    }
    
    public j dL(final Parcel parcel) {
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
        return new j(g, o2, o);
    }
    
    public j[] fN(final int n) {
        return new j[n];
    }
}
