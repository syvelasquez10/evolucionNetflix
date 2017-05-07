// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class TileOverlayOptionsCreator implements Parcelable$Creator<TileOverlayOptions>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final TileOverlayOptions tileOverlayOptions, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, tileOverlayOptions.getVersionCode());
        b.a(parcel, 2, tileOverlayOptions.iG(), false);
        b.a(parcel, 3, tileOverlayOptions.isVisible());
        b.a(parcel, 4, tileOverlayOptions.getZIndex());
        b.a(parcel, 5, tileOverlayOptions.getFadeIn());
        b.F(parcel, p3);
    }
    
    public TileOverlayOptions createFromParcel(final Parcel parcel) {
        boolean c = false;
        final int o = a.o(parcel);
        IBinder o2 = null;
        float k = 0.0f;
        boolean c2 = true;
        int g = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    o2 = a.o(parcel, n);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, n);
                    continue;
                }
                case 4: {
                    k = a.k(parcel, n);
                    continue;
                }
                case 5: {
                    c2 = a.c(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new TileOverlayOptions(g, o2, c, k, c2);
    }
    
    public TileOverlayOptions[] newArray(final int n) {
        return new TileOverlayOptions[n];
    }
}
