// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class o implements Parcelable$Creator<PolylineOptions>
{
    static void a(final PolylineOptions polylineOptions, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, polylineOptions.getVersionCode());
        b.c(parcel, 2, polylineOptions.getPoints(), false);
        b.a(parcel, 3, polylineOptions.getWidth());
        b.c(parcel, 4, polylineOptions.getColor());
        b.a(parcel, 5, polylineOptions.getZIndex());
        b.a(parcel, 6, polylineOptions.isVisible());
        b.a(parcel, 7, polylineOptions.isGeodesic());
        b.H(parcel, d);
    }
    
    public PolylineOptions cP(final Parcel parcel) {
        float l = 0.0f;
        boolean c = false;
        final int c2 = a.C(parcel);
        List<Object> c3 = null;
        boolean c4 = false;
        int g = 0;
        float i = 0.0f;
        int g2 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    c3 = a.c(parcel, b, (android.os.Parcelable$Creator<Object>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    i = a.l(parcel, b);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    l = a.l(parcel, b);
                    continue;
                }
                case 6: {
                    c4 = a.c(parcel, b);
                    continue;
                }
                case 7: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new PolylineOptions(g2, c3, i, g, l, c4, c);
    }
    
    public PolylineOptions[] eE(final int n) {
        return new PolylineOptions[n];
    }
}
