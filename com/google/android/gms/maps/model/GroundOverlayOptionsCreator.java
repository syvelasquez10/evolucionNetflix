// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class GroundOverlayOptionsCreator implements Parcelable$Creator<GroundOverlayOptions>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final GroundOverlayOptions groundOverlayOptions, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, groundOverlayOptions.getVersionCode());
        b.a(parcel, 2, groundOverlayOptions.iD(), false);
        b.a(parcel, 3, (Parcelable)groundOverlayOptions.getLocation(), n, false);
        b.a(parcel, 4, groundOverlayOptions.getWidth());
        b.a(parcel, 5, groundOverlayOptions.getHeight());
        b.a(parcel, 6, (Parcelable)groundOverlayOptions.getBounds(), n, false);
        b.a(parcel, 7, groundOverlayOptions.getBearing());
        b.a(parcel, 8, groundOverlayOptions.getZIndex());
        b.a(parcel, 9, groundOverlayOptions.isVisible());
        b.a(parcel, 10, groundOverlayOptions.getTransparency());
        b.a(parcel, 11, groundOverlayOptions.getAnchorU());
        b.a(parcel, 12, groundOverlayOptions.getAnchorV());
        b.F(parcel, p3);
    }
    
    public GroundOverlayOptions createFromParcel(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        IBinder o2 = null;
        LatLng latLng = null;
        float k = 0.0f;
        float i = 0.0f;
        LatLngBounds latLngBounds = null;
        float j = 0.0f;
        float l = 0.0f;
        boolean c = false;
        float m = 0.0f;
        float k2 = 0.0f;
        float k3 = 0.0f;
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
                    o2 = a.o(parcel, n);
                    continue;
                }
                case 3: {
                    latLng = a.a(parcel, n, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 4: {
                    k = a.k(parcel, n);
                    continue;
                }
                case 5: {
                    i = a.k(parcel, n);
                    continue;
                }
                case 6: {
                    latLngBounds = a.a(parcel, n, (android.os.Parcelable$Creator<LatLngBounds>)LatLngBounds.CREATOR);
                    continue;
                }
                case 7: {
                    j = a.k(parcel, n);
                    continue;
                }
                case 8: {
                    l = a.k(parcel, n);
                    continue;
                }
                case 9: {
                    c = a.c(parcel, n);
                    continue;
                }
                case 10: {
                    m = a.k(parcel, n);
                    continue;
                }
                case 11: {
                    k2 = a.k(parcel, n);
                    continue;
                }
                case 12: {
                    k3 = a.k(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new GroundOverlayOptions(g, o2, latLng, k, i, latLngBounds, j, l, c, m, k2, k3);
    }
    
    public GroundOverlayOptions[] newArray(final int n) {
        return new GroundOverlayOptions[n];
    }
}
