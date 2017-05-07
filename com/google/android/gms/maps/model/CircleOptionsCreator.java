// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class CircleOptionsCreator implements Parcelable$Creator<CircleOptions>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final CircleOptions circleOptions, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.c(parcel, 1, circleOptions.getVersionCode());
        b.a(parcel, 2, (Parcelable)circleOptions.getCenter(), n, false);
        b.a(parcel, 3, circleOptions.getRadius());
        b.a(parcel, 4, circleOptions.getStrokeWidth());
        b.c(parcel, 5, circleOptions.getStrokeColor());
        b.c(parcel, 6, circleOptions.getFillColor());
        b.a(parcel, 7, circleOptions.getZIndex());
        b.a(parcel, 8, circleOptions.isVisible());
        b.D(parcel, o);
    }
    
    public CircleOptions createFromParcel(final Parcel parcel) {
        float j = 0.0f;
        boolean c = false;
        final int n = a.n(parcel);
        LatLng latLng = null;
        double k = 0.0;
        int g = 0;
        int g2 = 0;
        float i = 0.0f;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    latLng = a.a(parcel, m, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    k = a.k(parcel, m);
                    continue;
                }
                case 4: {
                    i = a.j(parcel, m);
                    continue;
                }
                case 5: {
                    g2 = a.g(parcel, m);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 7: {
                    j = a.j(parcel, m);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new CircleOptions(g3, latLng, k, i, g2, g, j, c);
    }
    
    public CircleOptions[] newArray(final int n) {
        return new CircleOptions[n];
    }
}
