// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class im implements Parcelable$Creator<il>
{
    static void a(final il il, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, il.getVersionCode());
        b.a(parcel, 2, il.fF());
        b.a(parcel, 3, il.fN());
        b.c(parcel, 4, il.fO());
        b.a(parcel, 5, (Parcelable)il.getApplicationMetadata(), n, false);
        b.c(parcel, 6, il.fP());
        b.H(parcel, d);
    }
    
    public il[] ah(final int n) {
        return new il[n];
    }
    
    public il x(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        double m = 0.0;
        ApplicationMetadata applicationMetadata = null;
        int g2 = 0;
        boolean c2 = false;
        int g3 = 0;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, b);
                    continue;
                }
                case 3: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    applicationMetadata = a.a(parcel, b, ApplicationMetadata.CREATOR);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new il(g3, m, c2, g2, applicationMetadata, g);
    }
}
