// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class y implements Parcelable$Creator<VisibleRegion>
{
    static void a(final VisibleRegion visibleRegion, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, visibleRegion.getVersionCode());
        b.a(parcel, 2, (Parcelable)visibleRegion.nearLeft, n, false);
        b.a(parcel, 3, (Parcelable)visibleRegion.nearRight, n, false);
        b.a(parcel, 4, (Parcelable)visibleRegion.farLeft, n, false);
        b.a(parcel, 5, (Parcelable)visibleRegion.farRight, n, false);
        b.a(parcel, 6, (Parcelable)visibleRegion.latLngBounds, n, false);
        b.H(parcel, d);
    }
    
    public VisibleRegion cW(final Parcel parcel) {
        LatLngBounds latLngBounds = null;
        final int c = a.C(parcel);
        int g = 0;
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
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
                    latLng4 = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    latLng3 = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 4: {
                    latLng2 = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 5: {
                    latLng = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 6: {
                    latLngBounds = a.a(parcel, b, (android.os.Parcelable$Creator<LatLngBounds>)LatLngBounds.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new VisibleRegion(g, latLng4, latLng3, latLng2, latLng, latLngBounds);
    }
    
    public VisibleRegion[] eL(final int n) {
        return new VisibleRegion[n];
    }
}
