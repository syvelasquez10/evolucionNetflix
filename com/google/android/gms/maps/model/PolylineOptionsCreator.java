// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class PolylineOptionsCreator implements Parcelable$Creator<PolylineOptions>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final PolylineOptions polylineOptions, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, polylineOptions.getVersionCode());
        b.b(parcel, 2, polylineOptions.getPoints(), false);
        b.a(parcel, 3, polylineOptions.getWidth());
        b.c(parcel, 4, polylineOptions.getColor());
        b.a(parcel, 5, polylineOptions.getZIndex());
        b.a(parcel, 6, polylineOptions.isVisible());
        b.a(parcel, 7, polylineOptions.isGeodesic());
        b.F(parcel, p3);
    }
    
    public PolylineOptions createFromParcel(final Parcel parcel) {
        float k = 0.0f;
        boolean c = false;
        final int o = a.o(parcel);
        List<Object> c2 = null;
        boolean c3 = false;
        int g = 0;
        float i = 0.0f;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    c2 = a.c(parcel, n, (android.os.Parcelable$Creator<Object>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    i = a.k(parcel, n);
                    continue;
                }
                case 4: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 5: {
                    k = a.k(parcel, n);
                    continue;
                }
                case 6: {
                    c3 = a.c(parcel, n);
                    continue;
                }
                case 7: {
                    c = a.c(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new PolylineOptions(g2, c2, i, g, k, c3, c);
    }
    
    public PolylineOptions[] newArray(final int n) {
        return new PolylineOptions[n];
    }
}
