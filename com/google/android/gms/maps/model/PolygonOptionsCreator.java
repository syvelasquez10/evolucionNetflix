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
    
    static void a(final PolygonOptions polygonOptions, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, polygonOptions.getVersionCode());
        b.b(parcel, 2, polygonOptions.getPoints(), false);
        b.c(parcel, 3, polygonOptions.eH(), false);
        b.a(parcel, 4, polygonOptions.getStrokeWidth());
        b.c(parcel, 5, polygonOptions.getStrokeColor());
        b.c(parcel, 6, polygonOptions.getFillColor());
        b.a(parcel, 7, polygonOptions.getZIndex());
        b.a(parcel, 8, polygonOptions.isVisible());
        b.a(parcel, 9, polygonOptions.isGeodesic());
        b.D(parcel, o);
    }
    
    public PolygonOptions createFromParcel(final Parcel parcel) {
        float j = 0.0f;
        boolean c = false;
        final int n = a.n(parcel);
        List<LatLng> c2 = null;
        final ArrayList list = new ArrayList();
        boolean c3 = false;
        int g = 0;
        int g2 = 0;
        float i = 0.0f;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    c2 = a.c(parcel, m, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    a.a(parcel, m, list, this.getClass().getClassLoader());
                    continue;
                }
                case 4: {
                    i = a.j(parcel, m);
                    continue;
                }
                case 5: {
                    g2 = a.g(parcel, m);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 7: {
                    j = a.j(parcel, m);
                    continue;
                }
                case 8: {
                    c3 = a.c(parcel, m);
                    continue;
                }
                case 9: {
                    c = a.c(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new PolygonOptions(g3, c2, list, i, g2, g, j, c3, c);
    }
    
    public PolygonOptions[] newArray(final int n) {
        return new PolygonOptions[n];
    }
}
