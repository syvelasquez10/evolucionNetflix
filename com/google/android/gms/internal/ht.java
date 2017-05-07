// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ht implements Parcelable$Creator<hs>
{
    static void a(final hs hs, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)hs.CD, n, false);
        b.c(parcel, 1000, hs.BR);
        b.a(parcel, 2, hs.CE);
        b.c(parcel, 3, hs.CF);
        b.a(parcel, 4, hs.oT, false);
        b.a(parcel, 5, (Parcelable)hs.CG, n, false);
        b.H(parcel, d);
    }
    
    public hs[] R(final int n) {
        return new hs[n];
    }
    
    public hs s(final Parcel parcel) {
        int g = 0;
        he he = null;
        final int c = a.C(parcel);
        long i = 0L;
        String o = null;
        hg hg = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    hg = a.a(parcel, b, (android.os.Parcelable$Creator<hg>)com.google.android.gms.internal.hg.CREATOR);
                    continue;
                }
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    he = a.a(parcel, b, (android.os.Parcelable$Creator<he>)com.google.android.gms.internal.he.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new hs(g2, hg, i, g, o, he);
    }
}
