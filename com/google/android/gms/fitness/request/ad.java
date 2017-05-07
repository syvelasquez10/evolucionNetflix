// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ad implements Parcelable$Creator<ac>
{
    static void a(final ac ac, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.a(parcel, 1, ac.jz(), false);
        b.c(parcel, 1000, ac.getVersionCode());
        b.H(parcel, d);
    }
    
    public ac bR(final Parcel parcel) {
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
                    p = a.p(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new ac(g, p);
    }
    
    public ac[] dj(final int n) {
        return new ac[n];
    }
}
