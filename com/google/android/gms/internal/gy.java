// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class gy implements Parcelable$Creator<gx>
{
    static void a(final gx gx, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.a(parcel, 1, gx.AI, false);
        b.c(parcel, 1000, gx.kg);
        b.D(parcel, o);
    }
    
    public gx al(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, i);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new gx(g, m);
    }
    
    public gx[] ba(final int n) {
        return new gx[n];
    }
}
