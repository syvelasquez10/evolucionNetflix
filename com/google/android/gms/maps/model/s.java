// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class s implements Parcelable$Creator<StreetViewPanoramaLocation>
{
    static void a(final StreetViewPanoramaLocation streetViewPanoramaLocation, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, streetViewPanoramaLocation.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaLocation.links, n, false);
        b.a(parcel, 3, (Parcelable)streetViewPanoramaLocation.position, n, false);
        b.a(parcel, 4, streetViewPanoramaLocation.panoId, false);
        b.H(parcel, d);
    }
    
    public StreetViewPanoramaLocation cS(final Parcel parcel) {
        String o = null;
        final int c = a.C(parcel);
        int g = 0;
        LatLng latLng = null;
        StreetViewPanoramaLink[] array = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            StreetViewPanoramaLink[] array2 = null;
            LatLng latLng3 = null;
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    final LatLng latLng2 = latLng;
                    array2 = array;
                    latLng3 = latLng2;
                    break;
                }
                case 1: {
                    g = a.g(parcel, b);
                    final StreetViewPanoramaLink[] array3 = array;
                    latLng3 = latLng;
                    array2 = array3;
                    break;
                }
                case 2: {
                    final StreetViewPanoramaLink[] array4 = a.b(parcel, b, (android.os.Parcelable$Creator<StreetViewPanoramaLink>)StreetViewPanoramaLink.CREATOR);
                    latLng3 = latLng;
                    array2 = array4;
                    break;
                }
                case 3: {
                    final LatLng latLng4 = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    array2 = array;
                    latLng3 = latLng4;
                    break;
                }
                case 4: {
                    o = a.o(parcel, b);
                    final StreetViewPanoramaLink[] array5 = array;
                    latLng3 = latLng;
                    array2 = array5;
                    break;
                }
            }
            final StreetViewPanoramaLink[] array6 = array2;
            latLng = latLng3;
            array = array6;
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new StreetViewPanoramaLocation(g, array, latLng, o);
    }
    
    public StreetViewPanoramaLocation[] eH(final int n) {
        return new StreetViewPanoramaLocation[n];
    }
}
