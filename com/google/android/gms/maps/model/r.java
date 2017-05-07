// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class r implements Parcelable$Creator<StreetViewPanoramaLink>
{
    static void a(final StreetViewPanoramaLink streetViewPanoramaLink, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, streetViewPanoramaLink.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaLink.panoId, false);
        b.a(parcel, 3, streetViewPanoramaLink.bearing);
        b.H(parcel, d);
    }
    
    public StreetViewPanoramaLink cR(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        float l = 0.0f;
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
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    l = a.l(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new StreetViewPanoramaLink(g, o, l);
    }
    
    public StreetViewPanoramaLink[] eG(final int n) {
        return new StreetViewPanoramaLink[n];
    }
}
