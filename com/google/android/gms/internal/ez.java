// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ez implements Parcelable$Creator<ey>
{
    static void a(final ey ey, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, ey.getVersionCode());
        b.a(parcel, 2, ey.cB(), false);
        b.a(parcel, 3, (Parcelable)ey.cC(), n, false);
        b.D(parcel, o);
    }
    
    public ey[] V(final int n) {
        return new ey[n];
    }
    
    public ey x(final Parcel parcel) {
        ev ev = null;
        final int n = a.n(parcel);
        int g = 0;
        Parcel z = null;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    z = a.z(parcel, m);
                    continue;
                }
                case 3: {
                    ev = a.a(parcel, m, (android.os.Parcelable$Creator<ev>)com.google.android.gms.internal.ev.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new ey(g, z, ev);
    }
}
