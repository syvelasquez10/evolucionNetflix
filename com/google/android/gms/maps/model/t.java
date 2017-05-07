// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class t implements Parcelable$Creator<StreetViewPanoramaOrientation>
{
    static void a(final StreetViewPanoramaOrientation streetViewPanoramaOrientation, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, streetViewPanoramaOrientation.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaOrientation.tilt);
        b.a(parcel, 3, streetViewPanoramaOrientation.bearing);
        b.H(parcel, d);
    }
    
    public StreetViewPanoramaOrientation cT(final Parcel parcel) {
        float l = 0.0f;
        final int c = a.C(parcel);
        int g = 0;
        float i = 0.0f;
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
                    l = a.l(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new StreetViewPanoramaOrientation(g, i, l);
    }
    
    public StreetViewPanoramaOrientation[] eI(final int n) {
        return new StreetViewPanoramaOrientation[n];
    }
}
