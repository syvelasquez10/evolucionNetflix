// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ai implements Parcelable$Creator<ah>
{
    static void a(final ah ah, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, ah.BR);
        b.c(parcel, 2, ah.getRequestId());
        b.a(parcel, 3, ah.getPath(), false);
        b.a(parcel, 4, ah.getData(), false);
        b.a(parcel, 5, ah.getSourceNodeId(), false);
        b.H(parcel, d);
    }
    
    public ah ec(final Parcel parcel) {
        int g = 0;
        String o = null;
        final int c = a.C(parcel);
        byte[] r = null;
        String o2 = null;
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
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    r = a.r(parcel, b);
                    continue;
                }
                case 5: {
                    o = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new ah(g2, g, o2, r, o);
    }
    
    public ah[] ge(final int n) {
        return new ah[n];
    }
}
