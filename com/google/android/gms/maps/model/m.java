// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a$a;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class m implements Parcelable$Creator<PolygonOptions>
{
    static void a(final PolygonOptions polygonOptions, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, polygonOptions.getVersionCode());
        b.c(parcel, 2, polygonOptions.getPoints(), false);
        b.d(parcel, 3, polygonOptions.mO(), false);
        b.a(parcel, 4, polygonOptions.getStrokeWidth());
        b.c(parcel, 5, polygonOptions.getStrokeColor());
        b.c(parcel, 6, polygonOptions.getFillColor());
        b.a(parcel, 7, polygonOptions.getZIndex());
        b.a(parcel, 8, polygonOptions.isVisible());
        b.a(parcel, 9, polygonOptions.isGeodesic());
        b.H(parcel, d);
    }
    
    public PolygonOptions cO(final Parcel parcel) {
        float l = 0.0f;
        boolean c = false;
        final int c2 = a.C(parcel);
        List<LatLng> c3 = null;
        final ArrayList list = new ArrayList();
        boolean c4 = false;
        int g = 0;
        int g2 = 0;
        float i = 0.0f;
        int g3 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    c3 = a.c(parcel, b, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 3: {
                    a.a(parcel, b, list, this.getClass().getClassLoader());
                    continue;
                }
                case 4: {
                    i = a.l(parcel, b);
                    continue;
                }
                case 5: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 6: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 7: {
                    l = a.l(parcel, b);
                    continue;
                }
                case 8: {
                    c4 = a.c(parcel, b);
                    continue;
                }
                case 9: {
                    c = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new PolygonOptions(g3, c3, list, i, g2, g, l, c4, c);
    }
    
    public PolygonOptions[] eD(final int n) {
        return new PolygonOptions[n];
    }
}
