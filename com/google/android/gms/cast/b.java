// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.List;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<CastDevice>
{
    static void a(final CastDevice castDevice, final Parcel parcel, int o) {
        o = com.google.android.gms.common.internal.safeparcel.b.o(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, castDevice.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, castDevice.getDeviceId(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, castDevice.kA, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, castDevice.getFriendlyName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, castDevice.getModelName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, castDevice.getDeviceVersion(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, castDevice.getServicePort());
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 8, castDevice.getIcons(), false);
        com.google.android.gms.common.internal.safeparcel.b.D(parcel, o);
    }
    
    public CastDevice j(final Parcel parcel) {
        int g = 0;
        List<WebImage> c = null;
        final int n = a.n(parcel);
        String m = null;
        String i = null;
        String j = null;
        String k = null;
        String l = null;
        int g2 = 0;
        while (parcel.dataPosition() < n) {
            final int m2 = a.m(parcel);
            switch (a.M(m2)) {
                default: {
                    a.b(parcel, m2);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, m2);
                    continue;
                }
                case 2: {
                    l = a.m(parcel, m2);
                    continue;
                }
                case 3: {
                    k = a.m(parcel, m2);
                    continue;
                }
                case 4: {
                    j = a.m(parcel, m2);
                    continue;
                }
                case 5: {
                    i = a.m(parcel, m2);
                    continue;
                }
                case 6: {
                    m = a.m(parcel, m2);
                    continue;
                }
                case 7: {
                    g = a.g(parcel, m2);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, m2, WebImage.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new CastDevice(g2, l, k, j, i, m, g, c);
    }
    
    public CastDevice[] s(final int n) {
        return new CastDevice[n];
    }
}
