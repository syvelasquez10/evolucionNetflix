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

public class MarkerOptionsCreator implements Parcelable$Creator<MarkerOptions>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final MarkerOptions markerOptions, final Parcel parcel, final int n) {
        final int p3 = b.p(parcel);
        b.c(parcel, 1, markerOptions.getVersionCode());
        b.a(parcel, 2, (Parcelable)markerOptions.getPosition(), n, false);
        b.a(parcel, 3, markerOptions.getTitle(), false);
        b.a(parcel, 4, markerOptions.getSnippet(), false);
        b.a(parcel, 5, markerOptions.iE(), false);
        b.a(parcel, 6, markerOptions.getAnchorU());
        b.a(parcel, 7, markerOptions.getAnchorV());
        b.a(parcel, 8, markerOptions.isDraggable());
        b.a(parcel, 9, markerOptions.isVisible());
        b.a(parcel, 10, markerOptions.isFlat());
        b.a(parcel, 11, markerOptions.getRotation());
        b.a(parcel, 12, markerOptions.getInfoWindowAnchorU());
        b.a(parcel, 13, markerOptions.getInfoWindowAnchorV());
        b.a(parcel, 14, markerOptions.getAlpha());
        b.F(parcel, p3);
    }
    
    public MarkerOptions createFromParcel(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        LatLng latLng = null;
        String n = null;
        String n2 = null;
        IBinder o2 = null;
        float k = 0.0f;
        float i = 0.0f;
        boolean c = false;
        boolean c2 = false;
        boolean c3 = false;
        float j = 0.0f;
        float l = 0.5f;
        float m = 0.0f;
        float k2 = 1.0f;
        while (parcel.dataPosition() < o) {
            final int n3 = a.n(parcel);
            switch (a.R(n3)) {
                default: {
                    a.b(parcel, n3);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n3);
                    continue;
                }
                case 2: {
                    latLng = a.a(parcel, n3, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    n = a.n(parcel, n3);
                    continue;
                }
                case 4: {
                    n2 = a.n(parcel, n3);
                    continue;
                }
                case 5: {
                    o2 = a.o(parcel, n3);
                    continue;
                }
                case 6: {
                    k = a.k(parcel, n3);
                    continue;
                }
                case 7: {
                    i = a.k(parcel, n3);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, n3);
                    continue;
                }
                case 9: {
                    c2 = a.c(parcel, n3);
                    continue;
                }
                case 10: {
                    c3 = a.c(parcel, n3);
                    continue;
                }
                case 11: {
                    j = a.k(parcel, n3);
                    continue;
                }
                case 12: {
                    l = a.k(parcel, n3);
                    continue;
                }
                case 13: {
                    m = a.k(parcel, n3);
                    continue;
                }
                case 14: {
                    k2 = a.k(parcel, n3);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new MarkerOptions(g, latLng, n, n2, o2, k, i, c, c2, c3, j, l, m, k2);
    }
    
    public MarkerOptions[] newArray(final int n) {
        return new MarkerOptions[n];
    }
}
