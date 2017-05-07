// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class bl implements Parcelable$Creator<bm>
{
    static void a(final bm bm, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, bm.versionCode);
        b.a(parcel, 2, (Parcelable)bm.gG, n, false);
        b.a(parcel, 3, bm.aa(), false);
        b.a(parcel, 4, bm.ab(), false);
        b.a(parcel, 5, bm.ac(), false);
        b.a(parcel, 6, bm.ad(), false);
        b.a(parcel, 7, bm.gL, false);
        b.a(parcel, 8, bm.gM);
        b.a(parcel, 9, bm.gN, false);
        b.a(parcel, 10, bm.ae(), false);
        b.c(parcel, 11, bm.orientation);
        b.c(parcel, 12, bm.gP);
        b.a(parcel, 13, bm.go, false);
        b.a(parcel, 14, (Parcelable)bm.ej, n, false);
        b.D(parcel, o);
    }
    
    public bm e(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        bj bj = null;
        IBinder n2 = null;
        IBinder n3 = null;
        IBinder n4 = null;
        IBinder n5 = null;
        String m = null;
        boolean c = false;
        String i = null;
        IBinder n6 = null;
        int g2 = 0;
        int g3 = 0;
        String j = null;
        cu cu = null;
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
                    bj = a.a(parcel, k, (android.os.Parcelable$Creator<bj>)com.google.android.gms.internal.bj.CREATOR);
                    continue;
                }
                case 3: {
                    n2 = a.n(parcel, k);
                    continue;
                }
                case 4: {
                    n3 = a.n(parcel, k);
                    continue;
                }
                case 5: {
                    n4 = a.n(parcel, k);
                    continue;
                }
                case 6: {
                    n5 = a.n(parcel, k);
                    continue;
                }
                case 7: {
                    m = a.m(parcel, k);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, k);
                    continue;
                }
                case 9: {
                    i = a.m(parcel, k);
                    continue;
                }
                case 10: {
                    n6 = a.n(parcel, k);
                    continue;
                }
                case 11: {
                    g2 = a.g(parcel, k);
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
                case 14: {
                    cu = a.a(parcel, k, (android.os.Parcelable$Creator<cu>)com.google.android.gms.internal.cu.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new bm(g, bj, n2, n3, n4, n5, m, c, i, n6, g2, g3, j, cu);
    }
    
    public bm[] j(final int n) {
        return new bm[n];
    }
}
