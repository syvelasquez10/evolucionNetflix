// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class fp implements Parcelable$Creator<fc.a>
{
    static void a(final fc.a a, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.a(parcel, 1, a.getAccountName(), false);
        b.c(parcel, 1000, a.getVersionCode());
        b.a(parcel, 2, a.eE(), false);
        b.c(parcel, 3, a.eD());
        b.a(parcel, 4, a.eG(), false);
        b.F(parcel, p3);
    }
    
    public fc.a[] Q(final int n) {
        return new fc.a[n];
    }
    
    public fc.a m(final Parcel parcel) {
        int g = 0;
        String n = null;
        final int o = a.o(parcel);
        List<String> a = null;
        String n2 = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n3 = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.R(n3)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    n2 = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n3);
                    continue;
                }
                case 1000: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    a = com.google.android.gms.common.internal.safeparcel.a.A(parcel, n3);
                    continue;
                }
                case 3: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, n3);
                    continue;
                }
                case 4: {
                    n = com.google.android.gms.common.internal.safeparcel.a.n(parcel, n3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new fc.a(g2, n2, a, g, n);
    }
}
