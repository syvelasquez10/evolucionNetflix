// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class LatLngCreator implements Parcelable$Creator<LatLng>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final LatLng latLng, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, latLng.getVersionCode());
        b.a(parcel, 2, latLng.latitude);
        b.a(parcel, 3, latLng.longitude);
        b.D(parcel, o);
    }
    
    public LatLng createFromParcel(final Parcel parcel) {
        double k = 0.0;
        final int n = a.n(parcel);
        int g = 0;
        double i = 0.0;
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
                    i = a.k(parcel, m);
                    continue;
                }
                case 3: {
                    k = a.k(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new LatLng(g, i, k);
    }
    
    public LatLng[] newArray(final int n) {
        return new LatLng[n];
    }
}
