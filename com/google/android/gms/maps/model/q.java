// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class q implements Parcelable$Creator<StreetViewPanoramaCamera>
{
    static void a(final StreetViewPanoramaCamera streetViewPanoramaCamera, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, streetViewPanoramaCamera.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaCamera.zoom);
        b.a(parcel, 3, streetViewPanoramaCamera.tilt);
        b.a(parcel, 4, streetViewPanoramaCamera.bearing);
        b.H(parcel, d);
    }
    
    public StreetViewPanoramaCamera cQ(final Parcel parcel) {
        float l = 0.0f;
        final int c = a.C(parcel);
        float i = 0.0f;
        int g = 0;
        float j = 0.0f;
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
                    i = a.l(parcel, b);
                    continue;
                }
                case 3: {
                    j = a.l(parcel, b);
                    continue;
                }
                case 4: {
                    l = a.l(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new StreetViewPanoramaCamera(g, i, j, l);
    }
    
    public StreetViewPanoramaCamera[] eF(final int n) {
        return new StreetViewPanoramaCamera[n];
    }
}
