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
    
    static void a(final Tile tile, final Parcel parcel, int p3) {
        p3 = b.p(parcel);
        b.c(parcel, 1, tile.getVersionCode());
        b.c(parcel, 2, tile.width);
        b.c(parcel, 3, tile.height);
        b.a(parcel, 4, tile.data, false);
        b.F(parcel, p3);
    }
    
    public Tile createFromParcel(final Parcel parcel) {
        int g = 0;
        final int o = a.o(parcel);
        byte[] q = null;
        int g2 = 0;
        int g3 = 0;
        while (parcel.dataPosition() < o) {
            final int n = a.n(parcel);
            switch (a.R(n)) {
                default: {
                    a.b(parcel, n);
                    continue;
                }
                case 1: {
                    g3 = a.g(parcel, n);
                    continue;
                }
                case 2: {
                    g2 = a.g(parcel, n);
                    continue;
                }
                case 3: {
                    g = a.g(parcel, n);
                    continue;
                }
                case 4: {
                    q = a.q(parcel, n);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new Tile(g3, g2, g, q);
    }
    
    public Tile[] newArray(final int n) {
        return new Tile[n];
    }
}
