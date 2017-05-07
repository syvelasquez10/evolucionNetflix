// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class bi implements Parcelable$Creator<bj>
{
    static void a(final bj bj, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, bj.versionCode);
        b.a(parcel, 2, bj.gn, false);
        b.a(parcel, 3, bj.go, false);
        b.a(parcel, 4, bj.mimeType, false);
        b.a(parcel, 5, bj.packageName, false);
        b.a(parcel, 6, bj.gp, false);
        b.a(parcel, 7, bj.gq, false);
        b.a(parcel, 8, bj.gr, false);
        b.D(parcel, o);
    }
    
    public bj d(final Parcel parcel) {
        String m = null;
        final int n = a.n(parcel);
        int g = 0;
        String i = null;
        String j = null;
        String k = null;
        String l = null;
        String m2 = null;
        String m3 = null;
        while (parcel.dataPosition() < n) {
            final int m4 = a.m(parcel);
            switch (a.M(m4)) {
                default: {
                    a.b(parcel, m4);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m4);
                    continue;
                }
                case 2: {
                    m3 = a.m(parcel, m4);
                    continue;
                }
                case 3: {
                    m2 = a.m(parcel, m4);
                    continue;
                }
                case 4: {
                    l = a.m(parcel, m4);
                    continue;
                }
                case 5: {
                    k = a.m(parcel, m4);
                    continue;
                }
                case 6: {
                    j = a.m(parcel, m4);
                    continue;
                }
                case 7: {
                    i = a.m(parcel, m4);
                    continue;
                }
                case 8: {
                    m = a.m(parcel, m4);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new bj(g, m3, m2, l, k, j, i, m);
    }
    
    public bj[] i(final int n) {
        return new bj[n];
    }
}
