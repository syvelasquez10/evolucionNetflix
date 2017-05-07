// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class StreetViewPanoramaOptionsCreator implements Parcelable$Creator<StreetViewPanoramaOptions>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final StreetViewPanoramaOptions streetViewPanoramaOptions, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, streetViewPanoramaOptions.getVersionCode());
        b.a(parcel, 2, (Parcelable)streetViewPanoramaOptions.getStreetViewPanoramaCamera(), n, false);
        b.a(parcel, 3, streetViewPanoramaOptions.getPanoramaId(), false);
        b.a(parcel, 4, (Parcelable)streetViewPanoramaOptions.getPosition(), n, false);
        b.a(parcel, 5, streetViewPanoramaOptions.getRadius(), false);
        b.a(parcel, 6, streetViewPanoramaOptions.it());
        b.a(parcel, 7, streetViewPanoramaOptions.il());
        b.a(parcel, 8, streetViewPanoramaOptions.iu());
        b.a(parcel, 9, streetViewPanoramaOptions.iv());
        b.a(parcel, 10, streetViewPanoramaOptions.ih());
        b.F(parcel, p3);
    }
    
    public StreetViewPanoramaOptions createFromParcel(final Parcel parcel) {
        Integer h = null;
        byte e = 0;
        final int o = a.o(parcel);
        byte e2 = 0;
        byte e3 = 0;
        byte e4 = 0;
        byte e5 = 0;
        LatLng latLng = null;
        String n = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        int g = 0;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    streetViewPanoramaCamera = a.a(parcel, n2, (android.os.Parcelable$Creator<StreetViewPanoramaCamera>)StreetViewPanoramaCamera.CREATOR);
                    continue;
                }
                case 3: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 4: {
                    latLng = a.a(parcel, n2, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 5: {
                    h = a.h(parcel, n2);
                    continue;
                }
                case 6: {
                    e5 = a.e(parcel, n2);
                    continue;
                }
                case 7: {
                    e4 = a.e(parcel, n2);
                    continue;
                }
                case 8: {
                    e3 = a.e(parcel, n2);
                    continue;
                }
                case 9: {
                    e2 = a.e(parcel, n2);
                    continue;
                }
                case 10: {
                    e = a.e(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new StreetViewPanoramaOptions(g, streetViewPanoramaCamera, n, latLng, h, e5, e4, e3, e2, e);
    }
    
    public StreetViewPanoramaOptions[] newArray(final int n) {
        return new StreetViewPanoramaOptions[n];
    }
}
