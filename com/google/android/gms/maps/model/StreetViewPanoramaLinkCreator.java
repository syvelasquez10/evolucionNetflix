// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class StreetViewPanoramaLinkCreator implements Parcelable$Creator<StreetViewPanoramaLink>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final StreetViewPanoramaLink streetViewPanoramaLink, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, streetViewPanoramaLink.getVersionCode());
        b.a(parcel, 2, streetViewPanoramaLink.panoId, false);
        b.a(parcel, 3, streetViewPanoramaLink.bearing);
        b.F(parcel, p3);
    }
    
    public StreetViewPanoramaLink createFromParcel(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        float k = 0.0f;
        while (parcel.dataPosition() < o) {
            final int n2 = a.n(parcel);
            switch (a.R(n2)) {
                default: {
                    a.b(parcel, n2);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n2);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n2);
                    continue;
                }
                case 3: {
                    k = a.k(parcel, n2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new StreetViewPanoramaLink(g, n, k);
    }
    
    public StreetViewPanoramaLink[] newArray(final int n) {
        return new StreetViewPanoramaLink[n];
    }
}
