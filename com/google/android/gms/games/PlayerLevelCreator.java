// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class PlayerLevelCreator implements Parcelable$Creator<PlayerLevel>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final PlayerLevel playerLevel, final Parcel parcel, int d) {
        d = b.D(parcel);
        b.c(parcel, 1, playerLevel.getLevelNumber());
        b.c(parcel, 1000, playerLevel.getVersionCode());
        b.a(parcel, 2, playerLevel.getMinXp());
        b.a(parcel, 3, playerLevel.getMaxXp());
        b.H(parcel, d);
    }
    
    public PlayerLevel createFromParcel(final Parcel parcel) {
        long i = 0L;
        int g = 0;
        final int c = a.C(parcel);
        long j = 0L;
        int g2 = 0;
        while (parcel.dataPosition() < c) {
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
                case 1000: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new PlayerLevel(g2, g, j, i);
    }
    
    public PlayerLevel[] newArray(final int n) {
        return new PlayerLevel[n];
    }
}
