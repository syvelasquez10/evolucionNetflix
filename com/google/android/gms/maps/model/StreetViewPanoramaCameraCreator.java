// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class StreetViewPanoramaCameraCreator implements Parcelable$Creator<StreetViewPanoramaCamera>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final StreetViewPanoramaCamera streetViewPanoramaCamera, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, streetViewPanoramaCamera.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaCamera.zoom);
        b.a(parcel, 3, streetViewPanoramaCamera.tilt);
        b.a(parcel, 4, streetViewPanoramaCamera.bearing);
        b.F(parcel, p3);
    }
    
    public StreetViewPanoramaCamera createFromParcel(final Parcel parcel) {
        float k = 0.0f;
        final int o = a.o(parcel);
        float i = 0.0f;
        int g = 0;
        float j = 0.0f;
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
                    j = a.k(parcel, n);
                    continue;
                }
                case 4: {
                    k = a.k(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new StreetViewPanoramaCamera(g, i, j, k);
    }
    
    public StreetViewPanoramaCamera[] newArray(final int n) {
        return new StreetViewPanoramaCamera[n];
    }
}
