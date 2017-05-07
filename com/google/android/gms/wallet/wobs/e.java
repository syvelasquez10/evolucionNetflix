// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.wobs;

import java.util.ArrayList;
import com.google.android.gms.internal.jr;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<d>
{
    static void a(final d d, final Parcel parcel, int d2) {
        d2 = b.D(parcel);
        b.c(parcel, 1, d.getVersionCode());
        b.a(parcel, 2, d.auo, false);
        b.a(parcel, 3, d.aup, false);
        b.c(parcel, 4, d.auq, false);
        b.H(parcel, d2);
    }
    
    public d dI(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        int g = 0;
        ArrayList<com.google.android.gms.wallet.wobs.b> list = jr.hz();
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
                case 4: {
                    list = a.c(parcel, b, com.google.android.gms.wallet.wobs.b.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new d(g, o2, o, list);
    }
    
    public d[] fK(final int n) {
        return new d[n];
    }
}
