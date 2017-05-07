// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class et implements Parcelable$Creator<es.a>
{
    static void a(final es.a a, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, a.getVersionCode());
        b.c(parcel, 2, a.ch());
        b.a(parcel, 3, a.cn());
        b.c(parcel, 4, a.ci());
        b.a(parcel, 5, a.co());
        b.a(parcel, 6, a.cp(), false);
        b.c(parcel, 7, a.cq());
        b.a(parcel, 8, a.cs(), false);
        b.a(parcel, 9, (Parcelable)a.cu(), n, false);
        b.D(parcel, o);
    }
    
    public es.a[] R(final int n) {
        return new es.a[n];
    }
    
    public es.a t(final Parcel parcel) {
        en en = null;
        int g = 0;
        final int n = a.n(parcel);
        String m = null;
        String i = null;
        boolean c = false;
        int g2 = 0;
        boolean c2 = false;
        int g3 = 0;
        int g4 = 0;
        while (parcel.dataPosition() < n) {
            final int j = a.m(parcel);
            switch (a.M(j)) {
                default: {
                    a.b(parcel, j);
                    continue;
                }
                case 1: {
                    g4 = a.g(parcel, j);
                    continue;
                }
                case 2: {
                    g3 = a.g(parcel, j);
                    continue;
                }
                case 3: {
                    c2 = a.c(parcel, j);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, j);
                    continue;
                }
                case 5: {
                    c = a.c(parcel, j);
                    continue;
                }
                case 6: {
                    i = a.m(parcel, j);
                    continue;
                }
                case 7: {
                    g = a.g(parcel, j);
                    continue;
                }
                case 8: {
                    m = a.m(parcel, j);
                    continue;
                }
                case 9: {
                    en = a.a(parcel, j, (android.os.Parcelable$Creator<en>)com.google.android.gms.internal.en.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new es.a(g4, g3, c2, g2, c, i, g, m, en);
    }
}
