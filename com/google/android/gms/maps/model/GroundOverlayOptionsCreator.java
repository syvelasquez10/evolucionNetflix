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
        final int o = b.o(parcel);
        b.c(parcel, 1, groundOverlayOptions.getVersionCode());
        b.a(parcel, 2, groundOverlayOptions.eF(), false);
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
        b.D(parcel, o);
    }
    
    public GroundOverlayOptions createFromParcel(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        IBinder n2 = null;
        LatLng latLng = null;
        float j = 0.0f;
        float i = 0.0f;
        LatLngBounds latLngBounds = null;
        float k = 0.0f;
        float l = 0.0f;
        boolean c = false;
        float m = 0.0f;
        float j2 = 0.0f;
        float j3 = 0.0f;
        while (parcel.dataPosition() < n) {
            final int m2 = a.m(parcel);
            switch (a.M(m2)) {
                default: {
                    a.b(parcel, m2);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m2);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, m2);
                    continue;
                }
                case 3: {
                    latLng = a.a(parcel, m2, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 4: {
                    j = a.j(parcel, m2);
                    continue;
                }
                case 5: {
                    i = a.j(parcel, m2);
                    continue;
                }
                case 6: {
                    latLngBounds = a.a(parcel, m2, (android.os.Parcelable$Creator<LatLngBounds>)LatLngBounds.CREATOR);
                    continue;
                }
                case 7: {
                    k = a.j(parcel, m2);
                    continue;
                }
                case 8: {
                    l = a.j(parcel, m2);
                    continue;
                }
                case 9: {
                    c = a.c(parcel, m2);
                    continue;
                }
                case 10: {
                    m = a.j(parcel, m2);
                    continue;
                }
                case 11: {
                    j2 = a.j(parcel, m2);
                    continue;
                }
                case 12: {
                    j3 = a.j(parcel, m2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new GroundOverlayOptions(g, n2, latLng, j, i, latLngBounds, k, l, c, m, j2, j3);
    }
    
    public GroundOverlayOptions[] newArray(final int n) {
        return new GroundOverlayOptions[n];
    }
}
