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
        final int p3 = b.p(parcel);
        b.a(parcel, 1, gameEntity.getApplicationId(), false);
        b.a(parcel, 2, gameEntity.getDisplayName(), false);
        b.a(parcel, 3, gameEntity.getPrimaryCategory(), false);
        b.a(parcel, 4, gameEntity.getSecondaryCategory(), false);
        b.a(parcel, 5, gameEntity.getDescription(), false);
        b.a(parcel, 6, gameEntity.getDeveloperName(), false);
        b.a(parcel, 7, (Parcelable)gameEntity.getIconImageUri(), n, false);
        b.a(parcel, 8, (Parcelable)gameEntity.getHiResImageUri(), n, false);
        b.a(parcel, 9, (Parcelable)gameEntity.getFeaturedImageUri(), n, false);
        b.a(parcel, 10, gameEntity.gb());
        b.a(parcel, 11, gameEntity.gd());
        b.a(parcel, 12, gameEntity.ge(), false);
        b.c(parcel, 13, gameEntity.gf());
        b.c(parcel, 14, gameEntity.getAchievementTotalCount());
        b.c(parcel, 15, gameEntity.getLeaderboardCount());
        b.a(parcel, 17, gameEntity.isTurnBasedMultiplayerEnabled());
        b.a(parcel, 16, gameEntity.isRealTimeMultiplayerEnabled());
        b.c(parcel, 1000, gameEntity.getVersionCode());
        b.a(parcel, 19, gameEntity.getHiResImageUrl(), false);
        b.a(parcel, 18, gameEntity.getIconImageUrl(), false);
        b.a(parcel, 21, gameEntity.isMuted());
        b.a(parcel, 20, gameEntity.getFeaturedImageUrl(), false);
        b.a(parcel, 22, gameEntity.gc());
        b.F(parcel, p3);
    }
    
    public GameEntity[] aS(final int n) {
        return new GameEntity[n];
    }
    
    public GameEntity an(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        String n = null;
        String n2 = null;
        String n3 = null;
        String n4 = null;
        String n5 = null;
        String n6 = null;
        Uri uri = null;
        Uri uri2 = null;
        Uri uri3 = null;
        boolean c = false;
        boolean c2 = false;
        String n7 = null;
        int g2 = 0;
        int g3 = 0;
        int g4 = 0;
        boolean c3 = false;
        boolean c4 = false;
        String n8 = null;
        String n9 = null;
        String n10 = null;
        boolean c5 = false;
        boolean c6 = false;
        while (parcel.dataPosition() < o) {
            final int n11 = a.n(parcel);
            switch (a.R(n11)) {
                default: {
                    a.b(parcel, n11);
                    continue;
                }
                case 1: {
                    n = a.n(parcel, n11);
                    continue;
                }
                case 2: {
                    n2 = a.n(parcel, n11);
                    continue;
                }
                case 3: {
                    n3 = a.n(parcel, n11);
                    continue;
                }
                case 4: {
                    n4 = a.n(parcel, n11);
                    continue;
                }
                case 5: {
                    n5 = a.n(parcel, n11);
                    continue;
                }
                case 6: {
                    n6 = a.n(parcel, n11);
                    continue;
                }
                case 7: {
                    uri = a.a(parcel, n11, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 8: {
                    uri2 = a.a(parcel, n11, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 9: {
                    uri3 = a.a(parcel, n11, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 10: {
                    c = a.c(parcel, n11);
                    continue;
                }
                case 11: {
                    c2 = a.c(parcel, n11);
                    continue;
                }
                case 12: {
                    n7 = a.n(parcel, n11);
                    continue;
                }
                case 13: {
                    g2 = a.g(parcel, n11);
                    continue;
                }
                case 14: {
                    g3 = a.g(parcel, n11);
                    continue;
                }
                case 15: {
                    g4 = a.g(parcel, n11);
                    continue;
                }
                case 17: {
                    c4 = a.c(parcel, n11);
                    continue;
                }
                case 16: {
                    c3 = a.c(parcel, n11);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n11);
                    continue;
                }
                case 19: {
                    n9 = a.n(parcel, n11);
                    continue;
                }
                case 18: {
                    n8 = a.n(parcel, n11);
                    continue;
                }
                case 21: {
                    c5 = a.c(parcel, n11);
                    continue;
                }
                case 20: {
                    n10 = a.n(parcel, n11);
                    continue;
                }
                case 22: {
                    c6 = a.c(parcel, n11);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new GameEntity(g, n, n2, n3, n4, n5, n6, uri, uri2, uri3, c, c2, n7, g2, g3, g4, c3, c4, n8, n9, n10, c5, c6);
    }
}
