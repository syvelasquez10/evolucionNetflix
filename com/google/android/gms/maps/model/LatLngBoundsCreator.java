// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class LatLngBoundsCreator implements Parcelable$Creator<LatLngBounds>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final LatLngBounds latLngBounds, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, latLngBounds.getVersionCode());
        b.a(parcel, 2, (Parcelable)latLngBounds.southwest, n, false);
        b.a(parcel, 3, (Parcelable)latLngBounds.northeast, n, false);
        b.F(parcel, p3);
    }
    
    public LatLngBounds createFromParcel(final Parcel parcel) {
        LatLng latLng = null;
        final int o = a.o(parcel);
        int g = 0;
        LatLng latLng2 = null;
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
                    latLng2 = a.a(parcel, n, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    latLng = a.a(parcel, n, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new LatLngBounds(g, latLng2, latLng);
    }
    
    public LatLngBounds[] newArray(final int n) {
        return new LatLngBounds[n];
    }
}
