// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class y implements Parcelable$Creator<x>
{
    static void a(final x x, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, x.versionCode);
        b.c(parcel, 2, x.statusCode);
        b.a(parcel, 3, (Parcelable)x.avp, n, false);
        b.H(parcel, d);
    }
    
    public x dZ(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        m m = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    m = a.a(parcel, b, com.google.android.gms.wearable.internal.m.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new x(g2, g, m);
    }
    
    public x[] gb(final int n) {
        return new x[n];
    }
}
