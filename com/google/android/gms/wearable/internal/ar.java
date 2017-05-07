// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ar implements Parcelable$Creator<aq>
{
    static void a(final aq aq, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, aq.BR);
        b.a(parcel, 2, aq.pT(), false);
        b.H(parcel, d);
    }
    
    public aq eg(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        IBinder p = null;
        while (parcel.dataPosition() < c) {
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
                    p = a.p(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new aq(g, p);
    }
    
    public aq[] gi(final int n) {
        return new aq[n];
    }
}
