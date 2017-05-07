// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class y implements Parcelable$Creator<x>
{
    static void a(final x x, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, x.versionCode);
        b.a(parcel, 2, x.lX);
        b.a(parcel, 3, x.mh);
        b.H(parcel, d);
    }
    
    public x a(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        boolean c3 = false;
        int g = 0;
        while (parcel.dataPosition() < c2) {
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
                    c3 = a.c(parcel, b);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new x(g, c3, c);
    }
    
    public x[] b(final int n) {
        return new x[n];
    }
}
