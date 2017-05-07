// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import android.os.Bundle;
import android.location.Location;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class w implements Parcelable$Creator<v>
{
    static void a(final v v, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, v.versionCode);
        b.a(parcel, 2, v.ex);
        b.a(parcel, 3, v.extras, false);
        b.c(parcel, 4, v.ey);
        b.a(parcel, 5, v.ez, false);
        b.a(parcel, 6, v.eA);
        b.c(parcel, 7, v.tagForChildDirectedTreatment);
        b.a(parcel, 8, v.eB);
        b.a(parcel, 9, v.eC, false);
        b.a(parcel, 10, (Parcelable)v.eD, n, false);
        b.a(parcel, 11, (Parcelable)v.eE, n, false);
        b.D(parcel, o);
    }
    
    public v a(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        long h = 0L;
        Bundle o = null;
        int g2 = 0;
        List<String> y = null;
        boolean c = false;
        int g3 = 0;
        boolean c2 = false;
        String m = null;
        ai ai = null;
        Location location = null;
        while (parcel.dataPosition() < n) {
            final int i = a.m(parcel);
            switch (a.M(i)) {
                default: {
                    a.b(parcel, i);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, i);
                    continue;
                }
                case 2: {
                    h = a.h(parcel, i);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, i);
                    continue;
                }
                case 4: {
                    g2 = a.g(parcel, i);
                    continue;
                }
                case 5: {
                    y = a.y(parcel, i);
                    continue;
                }
                case 6: {
                    c = a.c(parcel, i);
                    continue;
                }
                case 7: {
                    g3 = a.g(parcel, i);
                    continue;
                }
                case 8: {
                    c2 = a.c(parcel, i);
                    continue;
                }
                case 9: {
                    m = a.m(parcel, i);
                    continue;
                }
                case 10: {
                    ai = a.a(parcel, i, (android.os.Parcelable$Creator<ai>)com.google.android.gms.internal.ai.CREATOR);
                    continue;
                }
                case 11: {
                    location = a.a(parcel, i, (android.os.Parcelable$Creator<Location>)Location.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new v(g, h, o, g2, y, c, g3, c2, m, ai, location);
    }
    
    public v[] b(final int n) {
        return new v[n];
    }
}
