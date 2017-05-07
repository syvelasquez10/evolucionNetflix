// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import java.util.List;
import android.net.Uri;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class he implements Parcelable$Creator<hd>
{
    static void a(final hd hd, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.a(parcel, 1, hd.getId(), false);
        b.a(parcel, 2, hd.ee(), false);
        b.a(parcel, 3, (Parcelable)hd.ef(), n, false);
        b.a(parcel, 4, (Parcelable)hd.dX(), n, false);
        b.a(parcel, 5, hd.dY());
        b.a(parcel, 6, (Parcelable)hd.dZ(), n, false);
        b.a(parcel, 7, hd.eg(), false);
        b.a(parcel, 8, (Parcelable)hd.ea(), n, false);
        b.a(parcel, 9, hd.eb());
        b.a(parcel, 10, hd.getRating());
        b.c(parcel, 11, hd.ec());
        b.a(parcel, 12, hd.ed());
        b.b(parcel, 13, hd.dW(), false);
        b.c(parcel, 1000, hd.kg);
        b.D(parcel, o);
    }
    
    public hd ao(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        String m = null;
        List<gx> c = null;
        Bundle o = null;
        hf hf = null;
        LatLng latLng = null;
        float j = 0.0f;
        LatLngBounds latLngBounds = null;
        String i = null;
        Uri uri = null;
        boolean c2 = false;
        float k = 0.0f;
        int g2 = 0;
        long h = 0L;
        while (parcel.dataPosition() < n) {
            final int l = a.m(parcel);
            switch (a.M(l)) {
                default: {
                    a.b(parcel, l);
                    continue;
                }
                case 1: {
                    m = a.m(parcel, l);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, l);
                    continue;
                }
                case 3: {
                    hf = a.a(parcel, l, (android.os.Parcelable$Creator<hf>)com.google.android.gms.internal.hf.CREATOR);
                    continue;
                }
                case 4: {
                    latLng = a.a(parcel, l, (android.os.Parcelable$Creator<LatLng>)LatLng.CREATOR);
                    continue;
                }
                case 5: {
                    j = a.j(parcel, l);
                    continue;
                }
                case 6: {
                    latLngBounds = a.a(parcel, l, (android.os.Parcelable$Creator<LatLngBounds>)LatLngBounds.CREATOR);
                    continue;
                }
                case 7: {
                    i = a.m(parcel, l);
                    continue;
                }
                case 8: {
                    uri = a.a(parcel, l, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 9: {
                    c2 = a.c(parcel, l);
                    continue;
                }
                case 10: {
                    k = a.j(parcel, l);
                    continue;
                }
                case 11: {
                    g2 = a.g(parcel, l);
                    continue;
                }
                case 12: {
                    h = a.h(parcel, l);
                    continue;
                }
                case 13: {
                    c = a.c(parcel, l, (android.os.Parcelable$Creator<gx>)gx.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, l);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new hd(g, m, c, o, hf, latLng, j, latLngBounds, i, uri, c2, k, g2, h);
    }
    
    public hd[] be(final int n) {
        return new hd[n];
    }
}
