// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class at implements Parcelable$Creator<as>
{
    static void a(final as as, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, as.versionCode);
        b.c(parcel, 2, as.statusCode);
        b.c(parcel, 3, as.avD);
        b.H(parcel, d);
    }
    
    public as eh(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        int g2 = 0;
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
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new as(g3, g2, g);
    }
    
    public as[] gj(final int n) {
        return new as[n];
    }
}
