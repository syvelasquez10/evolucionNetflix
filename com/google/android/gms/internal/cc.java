// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class cc implements Parcelable$Creator<cb>
{
    static void a(final cb cb, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, cb.versionCode);
        b.a(parcel, 2, cb.gL, false);
        b.a(parcel, 3, cb.hw, false);
        b.a(parcel, 4, cb.fK, false);
        b.c(parcel, 5, cb.errorCode);
        b.a(parcel, 6, cb.fL, false);
        b.a(parcel, 7, cb.hx);
        b.a(parcel, 8, cb.hy);
        b.a(parcel, 9, cb.hz);
        b.a(parcel, 10, cb.hA, false);
        b.a(parcel, 11, cb.fO);
        b.c(parcel, 12, cb.orientation);
        b.a(parcel, 13, cb.hB, false);
        b.D(parcel, o);
    }
    
    public cb g(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
        String i = null;
        List<String> y = null;
        int g2 = 0;
        List<String> y2 = null;
        long h = 0L;
        boolean c = false;
        long h2 = 0L;
        List<String> y3 = null;
        long h3 = 0L;
        int g3 = 0;
        String j = null;
        while (parcel.dataPosition() < n) {
            final int k = a.m(parcel);
            switch (a.M(k)) {
                default: {
                    a.b(parcel, k);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, k);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, k);
                    continue;
                }
                case 3: {
                    i = a.m(parcel, k);
                    continue;
                }
                case 4: {
                    y = a.y(parcel, k);
                    continue;
                }
                case 5: {
                    g2 = a.g(parcel, k);
                    continue;
                }
                case 6: {
                    y2 = a.y(parcel, k);
                    continue;
                }
                case 7: {
                    h = a.h(parcel, k);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, k);
                    continue;
                }
                case 9: {
                    h2 = a.h(parcel, k);
                    continue;
                }
                case 10: {
                    y3 = a.y(parcel, k);
                    continue;
                }
                case 11: {
                    h3 = a.h(parcel, k);
                    continue;
                }
                case 12: {
                    g3 = a.g(parcel, k);
                    continue;
                }
                case 13: {
                    j = a.m(parcel, k);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new cb(g, m, i, y, g2, y2, h, c, h2, y3, h3, g3, j);
    }
    
    public cb[] l(final int n) {
        return new cb[n];
    }
}
