// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class w implements Parcelable$Creator<TileOverlayOptions>
{
    static void a(final TileOverlayOptions tileOverlayOptions, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, tileOverlayOptions.getVersionCode());
        b.a(parcel, 2, tileOverlayOptions.mP(), false);
        b.a(parcel, 3, tileOverlayOptions.isVisible());
        b.a(parcel, 4, tileOverlayOptions.getZIndex());
        b.a(parcel, 5, tileOverlayOptions.getFadeIn());
        b.H(parcel, d);
    }
    
    public TileOverlayOptions cV(final Parcel parcel) {
        boolean c = false;
        final int c2 = a.C(parcel);
        IBinder p = null;
        float l = 0.0f;
        boolean c3 = true;
        int g = 0;
        while (parcel.dataPosition() < c2) {
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
                    p = a.p(parcel, b);
                    continue;
                }
                case 3: {
                    c = a.c(parcel, b);
                    continue;
                }
                case 4: {
                    l = a.l(parcel, b);
                    continue;
                }
                case 5: {
                    c3 = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a.a("Overread allowed size end=" + c2, parcel);
        }
        return new TileOverlayOptions(g, p, c, l, c3);
    }
    
    public TileOverlayOptions[] eK(final int n) {
        return new TileOverlayOptions[n];
    }
}
