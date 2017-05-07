// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.c;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class u implements Parcelable$Creator<t>
{
    static void a(final t t, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, t.versionCode);
        b.c(parcel, 2, t.statusCode);
        b.a(parcel, 3, t.avn, n, false);
        b.H(parcel, d);
    }
    
    public t dX(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        c[] array = null;
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
                    array = a.b(parcel, b, com.google.android.gms.wearable.c.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new t(g2, g, array);
    }
    
    public t[] fZ(final int n) {
        return new t[n];
    }
}
