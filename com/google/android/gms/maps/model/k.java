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

public class k implements Parcelable$Creator<MarkerOptions>
{
    static void a(final MarkerOptions markerOptions, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.c(parcel, 1, markerOptions.getVersionCode());
        b.a(parcel, 2, (Parcelable)markerOptions.getPosition(), n, false);
        b.a(parcel, 3, markerOptions.getTitle(), false);
        b.a(parcel, 4, markerOptions.getSnippet(), false);
        b.a(parcel, 5, markerOptions.mN(), false);
        b.a(parcel, 6, markerOptions.getAnchorU());
        b.a(parcel, 7, markerOptions.getAnchorV());
        b.a(parcel, 8, markerOptions.isDraggable());
        b.a(parcel, 9, markerOptions.isVisible());
        b.a(parcel, 10, markerOptions.isFlat());
        b.a(parcel, 11, markerOptions.getRotation());
        b.a(parcel, 12, markerOptions.getInfoWindowAnchorU());
        b.a(parcel, 13, markerOptions.getInfoWindowAnchorV());
        b.a(parcel, 14, markerOptions.getAlpha());
        b.H(parcel, d);
    }
    
    public MarkerOptions cN(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        LatLng latLng = null;
        String o = null;
        String o2 = null;
        IBinder p = null;
        float l = 0.0f;
        float i = 0.0f;
        boolean c2 = false;
        boolean c3 = false;
        boolean c4 = false;
        float j = 0.0f;
        float k = 0.5f;
        float m = 0.0f;
        float l2 = 1.0f;
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
                    latLng = a.a(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    p = a.p(parcel, b);
                    continue;
                }
                case 6: {
                    l = a.l(parcel, b);
                    continue;
                }
                case 7: {
                    i = a.l(parcel, b);
                    continue;
                }
                case 8: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 9: {
                    c3 = a.c(parcel, b);
                    continue;
                }
                case 10: {
                    c4 = a.c(parcel, b);
                    continue;
                }
                case 11: {
                    j = a.l(parcel, b);
                    continue;
                }
                case 12: {
                    k = a.l(parcel, b);
                    continue;
                }
                case 13: {
                    m = a.l(parcel, b);
                    continue;
                }
                case 14: {
                    l2 = a.l(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new MarkerOptions(g, latLng, o, o2, p, l, i, c2, c3, c4, j, k, m, l2);
    }
    
    public MarkerOptions[] eC(final int n) {
        return new MarkerOptions[n];
    }
}
