// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.List;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class b implements Parcelable$Creator<CastDevice>
{
    static void a(final CastDevice castDevice, final Parcel parcel, int d) {
        d = com.google.android.gms.common.internal.safeparcel.b.D(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, castDevice.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, castDevice.getDeviceId(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, castDevice.ES, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 4, castDevice.getFriendlyName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 5, castDevice.getModelName(), false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 6, castDevice.getDeviceVersion(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 7, castDevice.getServicePort());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 8, castDevice.getIcons(), false);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 9, castDevice.getCapabilities());
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 10, castDevice.getStatus());
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, d);
    }
    
    public CastDevice[] Y(final int n) {
        return new CastDevice[n];
    }
    
    public CastDevice u(final Parcel parcel) {
        int g = 0;
        List<WebImage> c = null;
        final int c2 = a.C(parcel);
        int g2 = 0;
        int g3 = 0;
        String o = null;
        String o2 = null;
        String o3 = null;
        String o4 = null;
        String o5 = null;
        int g4 = 0;
        while (parcel.dataPosition() < c2) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 8: {
                    c = a.c(parcel, b, WebImage.CREATOR);
                    continue;
                }
                case 9: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 10: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new CastDevice(g4, o5, o4, o3, o2, o, g3, c, g2, g);
    }
}
