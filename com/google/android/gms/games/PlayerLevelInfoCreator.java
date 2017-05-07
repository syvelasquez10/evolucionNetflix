// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class PlayerLevelInfoCreator implements Parcelable$Creator<PlayerLevelInfo>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final PlayerLevelInfo playerLevelInfo, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, playerLevelInfo.getCurrentXpTotal());
        b.c(parcel, 1000, playerLevelInfo.getVersionCode());
        b.a(parcel, 2, playerLevelInfo.getLastLevelUpTimestamp());
        b.a(parcel, 3, (Parcelable)playerLevelInfo.getCurrentLevel(), n, false);
        b.a(parcel, 4, (Parcelable)playerLevelInfo.getNextLevel(), n, false);
        b.H(parcel, d);
    }
    
    public PlayerLevelInfo createFromParcel(final Parcel parcel) {
        long i = 0L;
        PlayerLevel playerLevel = null;
        final int c = a.C(parcel);
        int g = 0;
        PlayerLevel playerLevel2 = null;
        long j = 0L;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 2: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 3: {
                    playerLevel2 = a.a(parcel, b, (android.os.Parcelable$Creator<PlayerLevel>)PlayerLevel.CREATOR);
                    continue;
                }
                case 4: {
                    playerLevel = a.a(parcel, b, (android.os.Parcelable$Creator<PlayerLevel>)PlayerLevel.CREATOR);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a$a("Overread allowed size end=" + c, parcel);
        }
        return new PlayerLevelInfo(g, j, i, playerLevel2, playerLevel);
    }
    
    public PlayerLevelInfo[] newArray(final int n) {
        return new PlayerLevelInfo[n];
    }
}
