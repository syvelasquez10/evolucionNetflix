// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class jq implements Parcelable$Creator<jp>
{
    static void a(final jp jp, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, jp.getVersionCode());
        b.a(parcel, 2, jp.hx(), false);
        b.a(parcel, 3, (Parcelable)jp.hy(), n, false);
        b.H(parcel, d);
    }
    
    public jp M(final Parcel parcel) {
        jm jm = null;
        final int c = a.C(parcel);
        int g = 0;
        Parcel d = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    d = a.D(parcel, b);
                    continue;
                }
                case 3: {
                    jm = a.a(parcel, b, (android.os.Parcelable$Creator<jm>)com.google.android.gms.internal.jm.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new jp(g, d, jm);
    }
    
    public jp[] aM(final int n) {
        return new jp[n];
    }
}
