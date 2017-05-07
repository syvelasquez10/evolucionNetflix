// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class cv implements Parcelable$Creator<cu>
{
    static void a(final cu cu, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, cu.versionCode);
        b.a(parcel, 2, cu.iJ, false);
        b.c(parcel, 3, cu.iK);
        b.c(parcel, 4, cu.iL);
        b.a(parcel, 5, cu.iM);
        b.D(parcel, o);
    }
    
    public cu h(final Parcel parcel) {
        boolean c = false;
        final int n = a.n(parcel);
        String m = null;
        int g = 0;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 3: {
                    g2 = a.g(parcel, i);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, i);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new cu(g3, m, g2, g, c);
    }
    
    public cu[] o(final int n) {
        return new cu[n];
    }
}
