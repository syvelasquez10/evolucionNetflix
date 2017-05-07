// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hw implements Parcelable$Creator<hu>
{
    static void a(final hu hu, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.a(parcel, 1, hu.getAccountName(), false);
        b.c(parcel, 1000, hu.getVersionCode());
        b.a(parcel, 2, hu.eR(), false);
        b.a(parcel, 3, hu.eS(), false);
        b.a(parcel, 4, hu.eT(), false);
        b.a(parcel, 5, hu.eU(), false);
        b.a(parcel, 6, hu.eV(), false);
        b.a(parcel, 7, hu.eW(), false);
        b.a(parcel, 8, hu.eX(), false);
        b.D(parcel, o);
    }
    
    public hu ar(final Parcel parcel) {
        String m = null;
        final int n = a.n(parcel);
        int g = 0;
        String i = null;
        String j = null;
        String k = null;
        String[] x = null;
        String[] x2 = null;
        String[] x3 = null;
        String l = null;
        while (parcel.dataPosition() < n) {
            final int m2 = a.m(parcel);
            switch (a.M(m2)) {
                default: {
                    a.b(parcel, m2);
                    continue;
                }
                case 1: {
                    l = a.m(parcel, m2);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, m2);
                    continue;
                }
                case 2: {
                    x3 = a.x(parcel, m2);
                    continue;
                }
                case 3: {
                    x2 = a.x(parcel, m2);
                    continue;
                }
                case 4: {
                    x = a.x(parcel, m2);
                    continue;
                }
                case 5: {
                    k = a.m(parcel, m2);
                    continue;
                }
                case 6: {
                    j = a.m(parcel, m2);
                    continue;
                }
                case 7: {
                    i = a.m(parcel, m2);
                    continue;
                }
                case 8: {
                    m = a.m(parcel, m2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new hu(g, l, x3, x2, x, k, j, i, m);
    }
    
    public hu[] bj(final int n) {
        return new hu[n];
    }
}
