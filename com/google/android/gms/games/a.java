// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<GameEntity>
{
    static void a(final GameEntity gameEntity, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.a(parcel, 1, gameEntity.getApplicationId(), false);
        b.a(parcel, 2, gameEntity.getDisplayName(), false);
        b.a(parcel, 3, gameEntity.getPrimaryCategory(), false);
        b.a(parcel, 4, gameEntity.getSecondaryCategory(), false);
        b.a(parcel, 5, gameEntity.getDescription(), false);
        b.a(parcel, 6, gameEntity.getDeveloperName(), false);
        b.a(parcel, 7, (Parcelable)gameEntity.getIconImageUri(), n, false);
        b.a(parcel, 8, (Parcelable)gameEntity.getHiResImageUri(), n, false);
        b.a(parcel, 9, (Parcelable)gameEntity.getFeaturedImageUri(), n, false);
        b.a(parcel, 10, gameEntity.isPlayEnabledGame());
        b.a(parcel, 11, gameEntity.isInstanceInstalled());
        b.a(parcel, 12, gameEntity.getInstancePackageName(), false);
        b.c(parcel, 13, gameEntity.getGameplayAclStatus());
        b.c(parcel, 14, gameEntity.getAchievementTotalCount());
        b.c(parcel, 15, gameEntity.getLeaderboardCount());
        b.a(parcel, 17, gameEntity.isTurnBasedMultiplayerEnabled());
        b.a(parcel, 16, gameEntity.isRealTimeMultiplayerEnabled());
        b.c(parcel, 1000, gameEntity.getVersionCode());
        b.D(parcel, o);
    }
    
    public GameEntity Y(final Parcel parcel) {
        final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
        int g = 0;
        String m = null;
        String i = null;
        String j = null;
        String k = null;
        String l = null;
        String m2 = null;
        Uri uri = null;
        Uri uri2 = null;
        Uri uri3 = null;
        boolean c = false;
        boolean c2 = false;
        String m3 = null;
        int g2 = 0;
        int g3 = 0;
        int g4 = 0;
        boolean c3 = false;
        boolean c4 = false;
        while (parcel.dataPosition() < n) {
            final int m4 = com.google.android.gms.common.internal.safeparcel.a.m(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.M(m4)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, m4);
                    continue;
                }
                case 1: {
                    m = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m4);
                    continue;
                }
                case 2: {
                    i = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m4);
                    continue;
                }
                case 3: {
                    j = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m4);
                    continue;
                }
                case 4: {
                    k = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m4);
                    continue;
                }
                case 5: {
                    l = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m4);
                    continue;
                }
                case 6: {
                    m2 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m4);
                    continue;
                }
                case 7: {
                    uri = com.google.android.gms.common.internal.safeparcel.a.a(parcel, m4, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 8: {
                    uri2 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, m4, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 9: {
                    uri3 = com.google.android.gms.common.internal.safeparcel.a.a(parcel, m4, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 10: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, m4);
                    continue;
                }
                case 11: {
                    c2 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, m4);
                    continue;
                }
                case 12: {
                    m3 = com.google.android.gms.common.internal.safeparcel.a.m(parcel, m4);
                    continue;
                }
                case 13: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, m4);
                    continue;
                }
                case 14: {
                    g3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, m4);
                    continue;
                }
                case 15: {
                    g4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, m4);
                    continue;
                }
                case 17: {
                    c4 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, m4);
                    continue;
                }
                case 16: {
                    c3 = com.google.android.gms.common.internal.safeparcel.a.c(parcel, m4);
                    continue;
                }
                case 1000: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, m4);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + n, parcel);
        }
        return new GameEntity(g, m, i, j, k, l, m2, uri, uri2, uri3, c, c2, m3, g2, g3, g4, c3, c4);
    }
    
    public GameEntity[] az(final int n) {
        return new GameEntity[n];
    }
}
