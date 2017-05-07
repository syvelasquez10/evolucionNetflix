// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<a>
{
    static void a(final a a, final Parcel parcel, int d) {
        d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 1, a.getPackageName(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1000, a.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, a.getVersion(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, a.iz(), false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public a bi(final Parcel parcel) {
        String o = null;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        int g = 0;
        String o2 = null;
        String o3 = null;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o3 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o2 = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o = com.google.android.gms.common.internal.safeparcel.a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c, parcel);
        }
        return new a(g, o3, o2, o);
    }
    
    public a[] cx(final int n) {
        return new a[n];
    }
}
