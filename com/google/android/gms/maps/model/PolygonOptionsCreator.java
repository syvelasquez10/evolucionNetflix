// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class PolygonOptionsCreator implements Parcelable$Creator<PolygonOptions>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final PolygonOptions polygonOptions, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, polygonOptions.getVersionCode());
        b.b(parcel, 2, polygonOptions.getPoints(), false);
        b.c(parcel, 3, polygonOptions.iF(), false);
        b.a(parcel, 4, polygonOptions.getStrokeWidth());
        b.c(parcel, 5, polygonOptions.getStrokeColor());
        b.c(parcel, 6, polygonOptions.getFillColor());
        b.a(parcel, 7, polygonOptions.getZIndex());
        b.a(parcel, 8, polygonOptions.isVisible());
        b.a(parcel, 9, polygonOptions.isGeodesic());
        b.F(parcel, p3);
    }
    
    public PolygonOptions createFromParcel(final Parcel parcel) {
        float k = 0.0f;
        boolean c = false;
        final int o = a.o(parcel);
        List<LatLng> c2 = null;
        final ArrayList list = new ArrayList();
        boolean c3 = false;
        int g = 0;
        int g2 = 0;
        float i = 0.0f;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    c2 = a.c(parcel, n, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    a.a(parcel, n, list, this.getClass().getClassLoader());
                    continue;
                }
                case 4: {
                    i = a.k(parcel, n);
                    continue;
                }
                case 5: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 7: {
                    k = a.k(parcel, n);
                    continue;
                }
                case 8: {
                    c3 = a.c(parcel, n);
                    continue;
                }
                case 9: {
                    c = a.c(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new PolygonOptions(g3, c2, list, i, g2, g, k, c3, c);
    }
    
    public PolygonOptions[] newArray(final int n) {
        return new PolygonOptions[n];
    }
}
