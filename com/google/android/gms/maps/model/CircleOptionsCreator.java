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
        final int p3 = b.p(parcel);
        b.c(parcel, 1, circleOptions.getVersionCode());
        b.a(parcel, 2, (Parcelable)circleOptions.getCenter(), n, false);
        b.a(parcel, 3, circleOptions.getRadius());
        b.a(parcel, 4, circleOptions.getStrokeWidth());
        b.c(parcel, 5, circleOptions.getStrokeColor());
        b.c(parcel, 6, circleOptions.getFillColor());
        b.a(parcel, 7, circleOptions.getZIndex());
        b.a(parcel, 8, circleOptions.isVisible());
        b.F(parcel, p3);
    }
    
    public CircleOptions createFromParcel(final Parcel parcel) {
        float k = 0.0f;
        boolean c = false;
        final int o = a.o(parcel);
        LatLng latLng = null;
        double l = 0.0;
        int g = 0;
        int g2 = 0;
        float i = 0.0f;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    latLng = a.a(parcel, n, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    l = a.l(parcel, n);
                    continue;
                }
                case 4: {
                    i = a.k(parcel, n);
                    continue;
                }
                case 5: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 7: {
                    k = a.k(parcel, n);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new CircleOptions(g3, latLng, l, i, g2, g, k, c);
    }
    
    public CircleOptions[] newArray(final int n) {
        return new CircleOptions[n];
    }
}
