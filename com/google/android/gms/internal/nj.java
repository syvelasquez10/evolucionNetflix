// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class nj implements Parcelable$Creator<nh>
{
    static void a(final nh nh, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, nh.versionCode);
        b.a(parcel, 2, nh.akw);
        b.a(parcel, 3, nh.tag, false);
        b.a(parcel, 4, nh.akx, false);
        b.a(parcel, 5, nh.aky, false);
        b.H(parcel, d);
    }
    
    public nh cX(final Parcel parcel) {
        Bundle q = null;
        final int c = a.C(parcel);
        int g = 0;
        long i = 0L;
        byte[] r = null;
        String o = null;
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
                    i = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    r = a.r(parcel, b);
                    continue;
                }
                case 5: {
                    q = a.q(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new nh(g, i, o, r, q);
    }
    
    public nh[] eN(final int n) {
        return new nh[n];
    }
}
