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

public class c implements Parcelable$Creator<StreetViewPanoramaOptions>
{
    static void a(final StreetViewPanoramaOptions streetViewPanoramaOptions, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, streetViewPanoramaOptions.getVersionCode());
        b.a(parcel, 2, (Parcelable)streetViewPanoramaOptions.getStreetViewPanoramaCamera(), n, false);
        b.a(parcel, 3, streetViewPanoramaOptions.getPanoramaId(), false);
        b.a(parcel, 4, (Parcelable)streetViewPanoramaOptions.getPosition(), n, false);
        b.a(parcel, 5, streetViewPanoramaOptions.getRadius(), false);
        b.a(parcel, 6, streetViewPanoramaOptions.mC());
        b.a(parcel, 7, streetViewPanoramaOptions.mu());
        b.a(parcel, 8, streetViewPanoramaOptions.mD());
        b.a(parcel, 9, streetViewPanoramaOptions.mE());
        b.a(parcel, 10, streetViewPanoramaOptions.mq());
        b.H(parcel, d);
    }
    
    public StreetViewPanoramaOptions cH(final Parcel parcel) {
        Integer h = null;
        byte e = 0;
        final int c = a.C(parcel);
        byte e2 = 0;
        byte e3 = 0;
        byte e4 = 0;
        byte e5 = 0;
        LatLng latLng = null;
        String o = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        int g = 0;
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
                    streetViewPanoramaCamera = a.a(parcel, b, (android.os.Parcelable$Creator<StreetViewPanoramaCamera>)StreetViewPanoramaCamera.CREATOR);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    latLng = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 5: {
                    h = a.h(parcel, b);
                    continue;
                }
                case 6: {
                    e5 = a.e(parcel, b);
                    continue;
                }
                case 7: {
                    e4 = a.e(parcel, b);
                    continue;
                }
                case 8: {
                    e3 = a.e(parcel, b);
                    continue;
                }
                case 9: {
                    e2 = a.e(parcel, b);
                    continue;
                }
                case 10: {
                    e = a.e(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new StreetViewPanoramaOptions(g, streetViewPanoramaCamera, o, latLng, h, e5, e4, e3, e2, e);
    }
    
    public StreetViewPanoramaOptions[] ew(final int n) {
        return new StreetViewPanoramaOptions[n];
    }
}
