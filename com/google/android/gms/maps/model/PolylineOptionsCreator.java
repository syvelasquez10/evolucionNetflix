// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class PolylineOptionsCreator implements Parcelable$Creator<PolylineOptions>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final PolylineOptions polylineOptions, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, polylineOptions.getVersionCode());
        b.b(parcel, 2, polylineOptions.getPoints(), false);
        b.a(parcel, 3, polylineOptions.getWidth());
        b.c(parcel, 4, polylineOptions.getColor());
        b.a(parcel, 5, polylineOptions.getZIndex());
        b.a(parcel, 6, polylineOptions.isVisible());
        b.a(parcel, 7, polylineOptions.isGeodesic());
        b.D(parcel, o);
    }
    
    public PolylineOptions createFromParcel(final Parcel parcel) {
        float j = 0.0f;
        boolean c = false;
        final int n = a.n(parcel);
        List<Object> c2 = null;
        boolean c3 = false;
        int g = 0;
        float i = 0.0f;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    c2 = a.c(parcel, m, (android.os.Parcelable$Creator<Object>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    i = a.j(parcel, m);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 5: {
                    j = a.j(parcel, m);
                    continue;
                }
                case 6: {
                    c3 = a.c(parcel, m);
                    continue;
                }
                case 7: {
                    c = a.c(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new PolylineOptions(g2, c2, i, g, j, c3, c);
    }
    
    public PolylineOptions[] newArray(final int n) {
        return new PolylineOptions[n];
    }
}
