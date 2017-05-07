// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class u implements Parcelable$Creator<Tile>
{
    static void a(final Tile tile, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, tile.getVersionCode());
        b.c(parcel, 2, tile.width);
        b.c(parcel, 3, tile.height);
        b.a(parcel, 4, tile.data, false);
        b.H(parcel, d);
    }
    
    public Tile cU(final Parcel parcel) {
        int g = 0;
        final int c = a.C(parcel);
        byte[] r = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < c) {
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
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 4: {
                    r = a.r(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new Tile(g3, g2, g, r);
    }
    
    public Tile[] eJ(final int n) {
        return new Tile[n];
    }
}
