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
    static void a(final PlusCommonExtras plusCommonExtras, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.a(parcel, 1, plusCommonExtras.iN(), false);
        b.c(parcel, 1000, plusCommonExtras.getVersionCode());
        b.a(parcel, 2, plusCommonExtras.iO(), false);
        b.F(parcel, p3);
    }
    
    public PlusCommonExtras aJ(final Parcel parcel) {
        String n = null;
        final int o = a.o(parcel);
        int g = 0;
        String n2 = null;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new PlusCommonExtras(g, n2, n);
    }
    
    public PlusCommonExtras[] bM(final int n) {
        return new PlusCommonExtras[n];
    }
}
