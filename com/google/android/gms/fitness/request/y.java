// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class y implements Parcelable$Creator<x>
{
    static void a(final x x, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, x.getName(), false);
        b.c(parcel, 1000, x.getVersionCode());
        b.a(parcel, 2, x.getIdentifier(), false);
        b.H(parcel, d);
    }
    
    public x bO(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        int g = 0;
        String o2 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new x(g, o2, o);
    }
    
    public x[] dg(final int n) {
        return new x[n];
    }
}
