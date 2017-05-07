// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class e implements Parcelable$Creator<GroundOverlayOptions>
{
    static void a(final GroundOverlayOptions groundOverlayOptions, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, groundOverlayOptions.getVersionCode());
        b.a(parcel, 2, groundOverlayOptions.mM(), false);
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
        b.H(parcel, d);
    }
    
    public GroundOverlayOptions cK(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        IBinder p = null;
        LatLng latLng = null;
        float l = 0.0f;
        float i = 0.0f;
        LatLngBounds latLngBounds = null;
        float j = 0.0f;
        float k = 0.0f;
        boolean c2 = false;
        float m = 0.0f;
        float l2 = 0.0f;
        float l3 = 0.0f;
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
                    p = a.p(parcel, b);
                    continue;
                }
                case 3: {
                    latLng = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 4: {
                    l = a.l(parcel, b);
                    continue;
                }
                case 5: {
                    i = a.l(parcel, b);
                    continue;
                }
                case 6: {
                    latLngBounds = a.a(parcel, b, (android.os.Parcelable$Creator<LatLngBounds>)LatLngBounds.CREATOR);
                    continue;
                }
                case 7: {
                    j = a.l(parcel, b);
                    continue;
                }
                case 8: {
                    k = a.l(parcel, b);
                    continue;
                }
                case 9: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 10: {
                    m = a.l(parcel, b);
                    continue;
                }
                case 11: {
                    l2 = a.l(parcel, b);
                    continue;
                }
                case 12: {
                    l3 = a.l(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new GroundOverlayOptions(g, p, latLng, l, i, latLngBounds, j, k, c2, m, l2, l3);
    }
    
    public GroundOverlayOptions[] ez(final int n) {
        return new GroundOverlayOptions[n];
    }
}
