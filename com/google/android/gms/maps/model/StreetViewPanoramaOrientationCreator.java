// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class StreetViewPanoramaOrientationCreator implements Parcelable$Creator<StreetViewPanoramaOrientation>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final StreetViewPanoramaOrientation streetViewPanoramaOrientation, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, streetViewPanoramaOrientation.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaOrientation.tilt);
        b.a(parcel, 3, streetViewPanoramaOrientation.bearing);
        b.F(parcel, p3);
    }
    
    public StreetViewPanoramaOrientation createFromParcel(final Parcel parcel) {
        float k = 0.0f;
        final int o = a.o(parcel);
        int g = 0;
        float i = 0.0f;
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
                    i = a.k(parcel, n);
                    continue;
                }
                case 3: {
                    k = a.k(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new StreetViewPanoramaOrientation(g, i, k);
    }
    
    public StreetViewPanoramaOrientation[] newArray(final int n) {
        return new StreetViewPanoramaOrientation[n];
    }
}
