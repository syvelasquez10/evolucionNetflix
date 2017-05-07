// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class i implements Parcelable$Creator<LatLng>
{
    static void a(final LatLng latLng, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, latLng.getVersionCode());
        b.a(parcel, 2, latLng.latitude);
        b.a(parcel, 3, latLng.longitude);
        b.H(parcel, d);
    }
    
    public LatLng cM(final Parcel parcel) {
        double m = 0.0;
        final int c = a.C(parcel);
        int g = 0;
        double i = 0.0;
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
                    i = a.m(parcel, b);
                    continue;
                }
                case 3: {
                    m = a.m(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new LatLng(g, i, m);
    }
    
    public LatLng[] eB(final int n) {
        return new LatLng[n];
    }
}
