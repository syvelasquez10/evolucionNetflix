// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class GameEntityCreator implements Parcelable$Creator<GameEntity>
{
    static void a(final GameEntity gameEntity, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, gameEntity.getApplicationId(), false);
        b.a(parcel, 2, gameEntity.getDisplayName(), false);
        b.a(parcel, 3, gameEntity.getPrimaryCategory(), false);
        b.a(parcel, 4, gameEntity.getSecondaryCategory(), false);
        b.a(parcel, 5, gameEntity.getDescription(), false);
        b.a(parcel, 6, gameEntity.getDeveloperName(), false);
        b.a(parcel, 7, (Parcelable)gameEntity.getIconImageUri(), n, false);
        b.a(parcel, 8, (Parcelable)gameEntity.getHiResImageUri(), n, false);
        b.a(parcel, 9, (Parcelable)gameEntity.getFeaturedImageUri(), n, false);
        b.a(parcel, 10, gameEntity.jL());
        b.a(parcel, 11, gameEntity.jN());
        b.a(parcel, 12, gameEntity.jO(), false);
        b.c(parcel, 13, gameEntity.jP());
        b.c(parcel, 14, gameEntity.getAchievementTotalCount());
        b.c(parcel, 15, gameEntity.getLeaderboardCount());
        b.a(parcel, 17, gameEntity.isTurnBasedMultiplayerEnabled());
        b.a(parcel, 16, gameEntity.isRealTimeMultiplayerEnabled());
        b.c(parcel, 1000, gameEntity.getVersionCode());
        b.a(parcel, 19, gameEntity.getHiResImageUrl(), false);
        b.a(parcel, 18, gameEntity.getIconImageUrl(), false);
        b.a(parcel, 21, gameEntity.isMuted());
        b.a(parcel, 20, gameEntity.getFeaturedImageUrl(), false);
        b.a(parcel, 23, gameEntity.areSnapshotsEnabled());
        b.a(parcel, 22, gameEntity.jM());
        b.a(parcel, 24, gameEntity.getThemeColor(), false);
        b.H(parcel, d);
    }
    
    public GameEntity cd(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        String o2 = null;
        String o3 = null;
        String o4 = null;
        String o5 = null;
        String o6 = null;
        Uri uri = null;
        Uri uri2 = null;
        Uri uri3 = null;
        boolean c2 = false;
        boolean c3 = false;
        String o7 = null;
        int g2 = 0;
        int g3 = 0;
        int g4 = 0;
        boolean c4 = false;
        boolean c5 = false;
        String o8 = null;
        String o9 = null;
        String o10 = null;
        boolean c6 = false;
        boolean c7 = false;
        boolean c8 = false;
        String o11 = null;
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
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    o6 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    uri = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 8: {
                    uri2 = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 9: {
                    uri3 = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 10: {
                    c2 = a.c(parcel, b);
                    continue;
                }
                case 11: {
                    c3 = a.c(parcel, b);
                    continue;
                }
                case 12: {
                    o7 = a.o(parcel, b);
                    continue;
                }
                case 13: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 14: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 15: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 17: {
                    c5 = a.c(parcel, b);
                    continue;
                }
                case 16: {
                    c4 = a.c(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 19: {
                    o9 = a.o(parcel, b);
                    continue;
                }
                case 18: {
                    o8 = a.o(parcel, b);
                    continue;
                }
                case 21: {
                    c6 = a.c(parcel, b);
                    continue;
                }
                case 20: {
                    o10 = a.o(parcel, b);
                    continue;
                }
                case 23: {
                    c8 = a.c(parcel, b);
                    continue;
                }
                case 22: {
                    c7 = a.c(parcel, b);
                    continue;
                }
                case 24: {
                    o11 = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new GameEntity(g, o, o2, o3, o4, o5, o6, uri, uri2, uri3, c2, c3, o7, g2, g3, g4, c4, c5, o8, o9, o10, c6, c7, c8, o11);
    }
    
    public GameEntity[] dv(final int n) {
        return new GameEntity[n];
    }
}
