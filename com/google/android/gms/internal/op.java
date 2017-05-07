// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class op implements Parcelable$Creator<oo>
{
    static void a(final oo oo, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, oo.getVersionCode());
        b.a(parcel, 2, oo.atD, false);
        b.a(parcel, 3, oo.atE, false);
        b.H(parcel, d);
    }
    
    public oo dC(final Parcel parcel) {
        String[] a = null;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        byte[][] s = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    a = com.google.android.gms.common.internal.safeparcel.a.A(parcel, b);
                    continue;
                }
                case 3: {
                    s = com.google.android.gms.common.internal.safeparcel.a.s(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new oo(g, a, s);
    }
    
    public oo[] fC(final int n) {
        return new oo[n];
    }
}
