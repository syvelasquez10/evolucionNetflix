// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class em implements Parcelable$Creator<el>
{
    static void a(final el el, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, el.getType());
        b.c(parcel, 1000, el.getVersionCode());
        b.c(parcel, 2, el.bY());
        b.a(parcel, 3, el.bZ(), false);
        b.a(parcel, 4, el.ca(), false);
        b.a(parcel, 5, el.getDisplayName(), false);
        b.a(parcel, 6, el.cb(), false);
        b.a(parcel, 7, el.getMetadata(), false);
        b.D(parcel, o);
    }
    
    public el[] N(final int n) {
        return new el[n];
    }
    
    public el p(final Parcel parcel) {
        int g = 0;
        Bundle o = null;
        final int n = a.n(parcel);
        String m = null;
        String i = null;
        String j = null;
        String k = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int l = a.m(parcel);
            switch (a.M(l)) {
                default: {
                    a.b(parcel, l);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, l);
                    continue;
                }
                case 1000: {
                    g3 = a.g(parcel, l);
                    continue;
                }
                case 2: {
                    g = a.g(parcel, l);
                    continue;
                }
                case 3: {
                    k = a.m(parcel, l);
                    continue;
                }
                case 4: {
                    j = a.m(parcel, l);
                    continue;
                }
                case 5: {
                    i = a.m(parcel, l);
                    continue;
                }
                case 6: {
                    m = a.m(parcel, l);
                    continue;
                }
                case 7: {
                    o = a.o(parcel, l);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new el(g3, g2, g, k, j, i, m, o);
    }
}
