// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.achievement;

import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class AchievementEntityCreator implements Parcelable$Creator<AchievementEntity>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final AchievementEntity achievementEntity, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, achievementEntity.getAchievementId(), false);
        b.c(parcel, 2, achievementEntity.getType());
        b.a(parcel, 3, achievementEntity.getName(), false);
        b.a(parcel, 4, achievementEntity.getDescription(), false);
        b.a(parcel, 5, (Parcelable)achievementEntity.getUnlockedImageUri(), n, false);
        b.a(parcel, 6, achievementEntity.getUnlockedImageUrl(), false);
        b.a(parcel, 7, (Parcelable)achievementEntity.getRevealedImageUri(), n, false);
        b.a(parcel, 8, achievementEntity.getRevealedImageUrl(), false);
        b.c(parcel, 9, achievementEntity.getTotalSteps());
        b.a(parcel, 10, achievementEntity.getFormattedTotalSteps(), false);
        b.a(parcel, 11, (Parcelable)achievementEntity.getPlayer(), n, false);
        b.c(parcel, 12, achievementEntity.getState());
        b.c(parcel, 13, achievementEntity.getCurrentSteps());
        b.a(parcel, 14, achievementEntity.getFormattedCurrentSteps(), false);
        b.a(parcel, 15, achievementEntity.getLastUpdatedTimestamp());
        b.a(parcel, 16, achievementEntity.getXpValue());
        b.c(parcel, 1000, achievementEntity.getVersionCode());
        b.H(parcel, d);
    }
    
    public AchievementEntity createFromParcel(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        String o = null;
        int g2 = 0;
        String o2 = null;
        String o3 = null;
        Uri uri = null;
        String o4 = null;
        Uri uri2 = null;
        String o5 = null;
        int g3 = 0;
        String o6 = null;
        PlayerEntity playerEntity = null;
        int g4 = 0;
        int g5 = 0;
        String o7 = null;
        long i = 0L;
        long j = 0L;
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
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 3: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 5: {
                    uri = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 6: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    uri2 = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 8: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 9: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 10: {
                    o6 = a.o(parcel, b);
                    continue;
                }
                case 11: {
                    playerEntity = a.a(parcel, b, PlayerEntity.CREATOR);
                    continue;
                }
                case 12: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 13: {
                    g5 = a.g(parcel, b);
                    continue;
                }
                case 14: {
                    o7 = a.o(parcel, b);
                    continue;
                }
                case 15: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 16: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new AchievementEntity(g, o, g2, o2, o3, uri, o4, uri2, o5, g3, o6, playerEntity, g4, g5, o7, i, j);
    }
    
    public AchievementEntity[] newArray(final int n) {
        return new AchievementEntity[n];
    }
}
