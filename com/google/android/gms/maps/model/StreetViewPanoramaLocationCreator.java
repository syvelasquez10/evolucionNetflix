// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class StreetViewPanoramaLocationCreator implements Parcelable$Creator<StreetViewPanoramaLocation>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final StreetViewPanoramaLocation streetViewPanoramaLocation, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, streetViewPanoramaLocation.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaLocation.links, n, false);
        b.a(parcel, 3, (Parcelable)streetViewPanoramaLocation.position, n, false);
        b.a(parcel, 4, streetViewPanoramaLocation.panoId, false);
        b.F(parcel, p3);
    }
    
    public StreetViewPanoramaLocation createFromParcel(final Parcel parcel) {
        String n = null;
        final int o = a.o(parcel);
        int g = 0;
        LatLng latLng = null;
        StreetViewPanoramaLink[] array = null;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            StreetViewPanoramaLink[] array2 = null;
            LatLng latLng3 = null;
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    final LatLng latLng2 = latLng;
                    array2 = array;
                    latLng3 = latLng2;
                    break;
                }
                case 1: {
                    g = a.g(parcel, n2);
                    final StreetViewPanoramaLink[] array3 = array;
                    latLng3 = latLng;
                    array2 = array3;
                    break;
                }
                case 2: {
                    final StreetViewPanoramaLink[] array4 = a.b(parcel, n2, (android.os.Parcelable$Creator<StreetViewPanoramaLink>)StreetViewPanoramaLink.CREATOR);
                    latLng3 = latLng;
                    array2 = array4;
                    break;
                }
                case 3: {
                    final LatLng latLng4 = a.a(parcel, n2, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    array2 = array;
                    latLng3 = latLng4;
                    break;
                }
                case 4: {
                    n = a.n(parcel, n2);
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
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new StreetViewPanoramaLocation(g, array, latLng, n);
    }
    
    public StreetViewPanoramaLocation[] newArray(final int n) {
        return new StreetViewPanoramaLocation[n];
    }
}
