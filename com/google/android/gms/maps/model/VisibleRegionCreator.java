// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class VisibleRegionCreator implements Parcelable$Creator<VisibleRegion>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final VisibleRegion visibleRegion, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, visibleRegion.getVersionCode());
        b.a(parcel, 2, (Parcelable)visibleRegion.nearLeft, n, false);
        b.a(parcel, 3, (Parcelable)visibleRegion.nearRight, n, false);
        b.a(parcel, 4, (Parcelable)visibleRegion.farLeft, n, false);
        b.a(parcel, 5, (Parcelable)visibleRegion.farRight, n, false);
        b.a(parcel, 6, (Parcelable)visibleRegion.latLngBounds, n, false);
        b.F(parcel, p3);
    }
    
    public VisibleRegion createFromParcel(final Parcel parcel) {
        LatLngBounds latLngBounds = null;
        final int o = a.o(parcel);
        int g = 0;
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
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
                    latLng4 = a.a(parcel, n, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    latLng3 = a.a(parcel, n, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 4: {
                    latLng2 = a.a(parcel, n, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 5: {
                    latLng = a.a(parcel, n, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 6: {
                    latLngBounds = a.a(parcel, n, (android.os.Parcelable$Creator<LatLngBounds>)LatLngBounds.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new VisibleRegion(g, latLng4, latLng3, latLng2, latLng, latLngBounds);
    }
    
    public VisibleRegion[] newArray(final int n) {
        return new VisibleRegion[n];
    }
}
