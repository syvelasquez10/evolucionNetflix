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
    
    static void a(final LatLng latLng, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, latLng.getVersionCode());
        b.a(parcel, 2, latLng.latitude);
        b.a(parcel, 3, latLng.longitude);
        b.F(parcel, p3);
    }
    
    public LatLng createFromParcel(final Parcel parcel) {
        double l = 0.0;
        final int o = a.o(parcel);
        int g = 0;
        double i = 0.0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    i = a.l(parcel, n);
                    continue;
                }
                case 3: {
                    l = a.l(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new LatLng(g, i, l);
    }
    
    public LatLng[] newArray(final int n) {
        return new LatLng[n];
    }
}
