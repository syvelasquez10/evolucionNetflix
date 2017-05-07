// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class g implements Parcelable$Creator<LatLngBounds>
{
    static void a(final LatLngBounds latLngBounds, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, latLngBounds.getVersionCode());
        b.a(parcel, 2, (Parcelable)latLngBounds.southwest, n, false);
        b.a(parcel, 3, (Parcelable)latLngBounds.northeast, n, false);
        b.H(parcel, d);
    }
    
    public LatLngBounds cL(final Parcel parcel) {
        LatLng latLng = null;
        final int c = a.C(parcel);
        int g = 0;
        LatLng latLng2 = null;
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
                    latLng2 = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    latLng = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new LatLngBounds(g, latLng2, latLng);
    }
    
    public LatLngBounds[] eA(final int n) {
        return new LatLngBounds[n];
    }
}
