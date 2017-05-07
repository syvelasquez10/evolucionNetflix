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
    static void a(final CastDevice castDevice, final Parcel parcel, int p3) {
        p3 = com.google.android.gms.common.internal.safeparcel.b.p(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, castDevice.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, castDevice.getDeviceId(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, castDevice.yb, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, castDevice.getFriendlyName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, castDevice.getModelName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, castDevice.getDeviceVersion(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, castDevice.getServicePort());
        com.google.android.gms.common.internal.safeparcel.b.b(parcel, 8, castDevice.getIcons(), false);
        com.google.android.gms.common.internal.safeparcel.b.F(parcel, p3);
    }
    
    public CastDevice k(final Parcel parcel) {
        int g = 0;
        List<WebImage> c = null;
        final int o = a.o(parcel);
        String n = null;
        String n2 = null;
        String n3 = null;
        String n4 = null;
        String n5 = null;
        int g2 = 0;
        while (parcel.dataPosition() < o) {
            final int n6 = a.n(parcel);
            switch (a.R(n6)) {
                default: {
                    a.b(parcel, n6);
                    continue;
                }
                case 1: {
                    g2 = a.g(parcel, n6);
                    continue;
                }
                case 2: {
                    n5 = a.n(parcel, n6);
                    continue;
                }
                case 3: {
                    n4 = a.n(parcel, n6);
                    continue;
                }
                case 4: {
                    n3 = a.n(parcel, n6);
                    continue;
                }
                case 5: {
                    n2 = a.n(parcel, n6);
                    continue;
                }
                case 6: {
                    n = a.n(parcel, n6);
                    continue;
                }
                case 7: {
                    g = a.g(parcel, n6);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, n6, WebImage.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new CastDevice(g2, n5, n4, n3, n2, n, g, c);
    }
    
    public CastDevice[] y(final int n) {
        return new CastDevice[n];
    }
}
