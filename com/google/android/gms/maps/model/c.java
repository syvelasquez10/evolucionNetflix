// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class c implements Parcelable$Creator<CircleOptions>
{
    static void a(final CircleOptions circleOptions, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, circleOptions.getVersionCode());
        b.a(parcel, 2, (Parcelable)circleOptions.getCenter(), n, false);
        b.a(parcel, 3, circleOptions.getRadius());
        b.a(parcel, 4, circleOptions.getStrokeWidth());
        b.c(parcel, 5, circleOptions.getStrokeColor());
        b.c(parcel, 6, circleOptions.getFillColor());
        b.a(parcel, 7, circleOptions.getZIndex());
        b.a(parcel, 8, circleOptions.isVisible());
        b.H(parcel, d);
    }
    
    public CircleOptions cJ(final Parcel parcel) {
        float l = 0.0f;
        boolean c = false;
        final int c2 = a.C(parcel);
        LatLng latLng = null;
        double m = 0.0;
        int g = 0;
        int g2 = 0;
        float i = 0.0f;
        int g3 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    latLng = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    m = a.m(parcel, b);
                    continue;
                }
                case 4: {
                    i = a.l(parcel, b);
                    continue;
                }
                case 5: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 7: {
                    l = a.l(parcel, b);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new CircleOptions(g3, latLng, m, i, g2, g, l, c);
    }
    
    public CircleOptions[] ey(final int n) {
        return new CircleOptions[n];
    }
}
