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
        final int o = b.o(parcel);
        b.c(parcel, 1, markerOptions.getVersionCode());
        b.a(parcel, 2, (Parcelable)markerOptions.getPosition(), n, false);
        b.a(parcel, 3, markerOptions.getTitle(), false);
        b.a(parcel, 4, markerOptions.getSnippet(), false);
        b.a(parcel, 5, markerOptions.eG(), false);
        b.a(parcel, 6, markerOptions.getAnchorU());
        b.a(parcel, 7, markerOptions.getAnchorV());
        b.a(parcel, 8, markerOptions.isDraggable());
        b.a(parcel, 9, markerOptions.isVisible());
        b.a(parcel, 10, markerOptions.isFlat());
        b.a(parcel, 11, markerOptions.getRotation());
        b.a(parcel, 12, markerOptions.getInfoWindowAnchorU());
        b.a(parcel, 13, markerOptions.getInfoWindowAnchorV());
        b.a(parcel, 14, markerOptions.getAlpha());
        b.D(parcel, o);
    }
    
    public MarkerOptions createFromParcel(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        LatLng latLng = null;
        String m = null;
        String i = null;
        IBinder n2 = null;
        float j = 0.0f;
        float k = 0.0f;
        boolean c = false;
        boolean c2 = false;
        boolean c3 = false;
        float l = 0.0f;
        float j2 = 0.5f;
        float j3 = 0.0f;
        float j4 = 1.0f;
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
                    latLng = a.a(parcel, m2, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    m = a.m(parcel, m2);
                    continue;
                }
                case 4: {
                    i = a.m(parcel, m2);
                    continue;
                }
                case 5: {
                    n2 = a.n(parcel, m2);
                    continue;
                }
                case 6: {
                    j = a.j(parcel, m2);
                    continue;
                }
                case 7: {
                    k = a.j(parcel, m2);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, m2);
                    continue;
                }
                case 9: {
                    c2 = a.c(parcel, m2);
                    continue;
                }
                case 10: {
                    c3 = a.c(parcel, m2);
                    continue;
                }
                case 11: {
                    l = a.j(parcel, m2);
                    continue;
                }
                case 12: {
                    j2 = a.j(parcel, m2);
                    continue;
                }
                case 13: {
                    j3 = a.j(parcel, m2);
                    continue;
                }
                case 14: {
                    j4 = a.j(parcel, m2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new MarkerOptions(g, latLng, m, i, n2, j, k, c, c2, c3, l, j2, j3, j4);
    }
    
    public MarkerOptions[] newArray(final int n) {
        return new MarkerOptions[n];
    }
}
