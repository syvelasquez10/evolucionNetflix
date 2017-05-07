// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class hj implements Parcelable$Creator<hi>
{
    static void a(final hi hi, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.a(parcel, 1, hi.Bn, false);
        b.c(parcel, 1000, hi.versionCode);
        b.a(parcel, 2, hi.Bo, false);
        b.D(parcel, o);
    }
    
    public hi aq(final Parcel parcel) {
        String m = null;
        final int n = a.n(parcel);
        int g = 0;
        String i = null;
        while (parcel.dataPosition() < n) {
            final int j = a.m(parcel);
            switch (a.M(j)) {
                default: {
                    a.b(parcel, j);
                    continue;
                }
                case 1: {
                    i = a.m(parcel, j);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, j);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, j);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new hi(g, i, m);
    }
    
    public hi[] bg(final int n) {
        return new hi[n];
    }
}
