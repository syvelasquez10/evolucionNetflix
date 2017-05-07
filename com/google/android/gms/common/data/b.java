// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<a>
{
    static void a(final a a, final Parcel parcel, final int n) {
        final int d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, a.BR);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable)a.JK, n, false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 3, a.FD);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public a[] ao(final int n) {
        return new a[n];
    }
    
    public a y(final Parcel parcel) {
        int g = 0;
        final int c = com.google.android.gms.common.internal.safeparcel.a.C(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
            final int b = com.google.android.gms.common.internal.safeparcel.a.B(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aD(b)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
                case 2: {
                    parcelFileDescriptor = com.google.android.gms.common.internal.safeparcel.a.a(parcel, b, (android.os.Parcelable$Creator<ParcelFileDescriptor>)ParcelFileDescriptor.CREATOR);
                    continue;
                }
                case 3: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + c, parcel);
        }
        return new a(g2, parcelFileDescriptor, g);
    }
}
