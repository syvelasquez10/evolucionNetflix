// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class f implements Parcelable$Creator<PlusCommonExtras>
{
    static void a(final PlusCommonExtras plusCommonExtras, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, plusCommonExtras.nc(), false);
        b.c(parcel, 1000, plusCommonExtras.getVersionCode());
        b.a(parcel, 2, plusCommonExtras.nd(), false);
        b.H(parcel, d);
    }
    
    public PlusCommonExtras cZ(final Parcel parcel) {
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
                    o2 = a.o(parcel, b);
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
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new PlusCommonExtras(g, o2, o);
    }
    
    public PlusCommonExtras[] eQ(final int n) {
        return new PlusCommonExtras[n];
    }
}
