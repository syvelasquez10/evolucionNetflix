// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ac implements Parcelable$Creator<ab>
{
    static void a(final ab ab, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, ab.versionCode);
        b.c(parcel, 2, ab.statusCode);
        b.a(parcel, 3, (Parcelable)ab.avr, n, false);
        b.H(parcel, d);
    }
    
    public ab eb(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        ak ak = null;
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
                    ak = a.a(parcel, b, com.google.android.gms.wearable.internal.ak.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ab(g2, g, ak);
    }
    
    public ab[] gd(final int n) {
        return new ab[n];
    }
}