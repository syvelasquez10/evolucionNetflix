// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class TileCreator implements Parcelable$Creator<Tile>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final Tile tile, final Parcel parcel, int o) {
        o = b.o(parcel);
        b.c(parcel, 1, tile.getVersionCode());
        b.c(parcel, 2, tile.width);
        b.c(parcel, 3, tile.height);
        b.a(parcel, 4, tile.data, false);
        b.D(parcel, o);
    }
    
    public Tile createFromParcel(final Parcel parcel) {
        int g = 0;
        final int n = a.n(parcel);
        byte[] p = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < n) {
            final int m = a.m(parcel);
            switch (a.M(m)) {
                default: {
                    a.b(parcel, m);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, m);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, m);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, m);
                    continue;
                }
                case 4: {
                    p = a.p(parcel, m);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new Tile(g3, g2, g, p);
    }
    
    public Tile[] newArray(final int n) {
        return new Tile[n];
    }
}
