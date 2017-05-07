// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class mf implements Parcelable$Creator<me>
{
    static void a(final me me, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, me.mc());
        b.c(parcel, 1000, me.BR);
        b.c(parcel, 2, me.md(), false);
        b.H(parcel, d);
    }
    
    public me cx(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        Object c3 = null;
        int g = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    c = a.c(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    c3 = a.c(parcel, b, (android.os.Parcelable$Creator<Object>)mo.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new me(g, c, (List<mo>)c3);
    }
    
    public me[] em(final int n) {
        return new me[n];
    }
}
