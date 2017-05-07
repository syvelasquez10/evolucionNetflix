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
    
    static void a(final TileOverlayOptions tileOverlayOptions, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, tileOverlayOptions.getVersionCode());
        b.a(parcel, 2, tileOverlayOptions.eI(), false);
        b.a(parcel, 3, tileOverlayOptions.isVisible());
        b.a(parcel, 4, tileOverlayOptions.getZIndex());
        b.a(parcel, 5, tileOverlayOptions.getFadeIn());
        b.D(parcel, o);
    }
    
    public TileOverlayOptions createFromParcel(final Parcel parcel) {
        boolean c = false;
        final int n = a.n(parcel);
        IBinder n2 = null;
        float j = 0.0f;
        boolean c2 = true;
        int g = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, m);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, m);
                    continue;
                }
                case 4: {
                    j = a.j(parcel, m);
                    continue;
                }
                case 5: {
                    c2 = a.c(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new TileOverlayOptions(g, n2, c, j, c2);
    }
    
    public TileOverlayOptions[] newArray(final int n) {
        return new TileOverlayOptions[n];
    }
}
