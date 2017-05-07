// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class PlayerEntityCreator implements Parcelable$Creator<PlayerEntity>
{
    static void a(final PlayerEntity playerEntity, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, playerEntity.getPlayerId(), false);
        b.a(parcel, 2, playerEntity.getDisplayName(), false);
        b.a(parcel, 3, (Parcelable)playerEntity.getIconImageUri(), n, false);
        b.a(parcel, 4, (Parcelable)playerEntity.getHiResImageUri(), n, false);
        b.a(parcel, 5, playerEntity.getRetrievedTimestamp());
        b.c(parcel, 6, playerEntity.jR());
        b.a(parcel, 7, playerEntity.getLastPlayedWithTimestamp());
        b.a(parcel, 8, playerEntity.getIconImageUrl(), false);
        b.a(parcel, 9, playerEntity.getHiResImageUrl(), false);
        b.a(parcel, 14, playerEntity.getTitle(), false);
        b.a(parcel, 15, (Parcelable)playerEntity.jS(), n, false);
        b.a(parcel, 16, (Parcelable)playerEntity.getLevelInfo(), n, false);
        b.c(parcel, 1000, playerEntity.getVersionCode());
        b.a(parcel, 18, playerEntity.isProfileVisible());
        b.H(parcel, d);
    }
    
    public PlayerEntity ce(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        String o2 = null;
        Uri uri = null;
        Uri uri2 = null;
        long i = 0L;
        int g2 = 0;
        long j = 0L;
        String o3 = null;
        String o4 = null;
        String o5 = null;
        MostRecentGameInfoEntity mostRecentGameInfoEntity = null;
        PlayerLevelInfo playerLevelInfo = null;
        boolean c2 = false;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 2: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    uri = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 4: {
                    uri2 = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 5: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 6: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 7: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 8: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 9: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 14: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 15: {
                    mostRecentGameInfoEntity = a.a(parcel, b, (android.os.Parcelable$Creator<MostRecentGameInfoEntity>)MostRecentGameInfoEntity.CREATOR);
                    continue;
                }
                case 16: {
                    playerLevelInfo = a.a(parcel, b, (android.os.Parcelable$Creator<PlayerLevelInfo>)PlayerLevelInfo.CREATOR);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 18: {
                    c2 = a.c(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new PlayerEntity(g, o, o2, uri, uri2, i, g2, j, o3, o4, o5, mostRecentGameInfoEntity, playerLevelInfo, c2);
    }
    
    public PlayerEntity[] dw(final int n) {
        return new PlayerEntity[n];
    }
}
