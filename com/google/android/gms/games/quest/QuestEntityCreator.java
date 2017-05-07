// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.quest;

import java.util.ArrayList;
import android.net.Uri;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.a;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class QuestEntityCreator implements Parcelable$Creator<QuestEntity>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final QuestEntity questEntity, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)questEntity.getGame(), n, false);
        b.a(parcel, 2, questEntity.getQuestId(), false);
        b.a(parcel, 3, questEntity.getAcceptedTimestamp());
        b.a(parcel, 4, (Parcelable)questEntity.getBannerImageUri(), n, false);
        b.a(parcel, 5, questEntity.getBannerImageUrl(), false);
        b.a(parcel, 6, questEntity.getDescription(), false);
        b.a(parcel, 7, questEntity.getEndTimestamp());
        b.a(parcel, 8, questEntity.getLastUpdatedTimestamp());
        b.a(parcel, 9, (Parcelable)questEntity.getIconImageUri(), n, false);
        b.a(parcel, 10, questEntity.getIconImageUrl(), false);
        b.a(parcel, 12, questEntity.getName(), false);
        b.a(parcel, 13, questEntity.lI());
        b.a(parcel, 14, questEntity.getStartTimestamp());
        b.c(parcel, 15, questEntity.getState());
        b.c(parcel, 17, questEntity.lH(), false);
        b.c(parcel, 16, questEntity.getType());
        b.c(parcel, 1000, questEntity.getVersionCode());
        b.H(parcel, d);
    }
    
    public QuestEntity createFromParcel(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        GameEntity gameEntity = null;
        String o = null;
        long i = 0L;
        Uri uri = null;
        String o2 = null;
        String o3 = null;
        long j = 0L;
        long k = 0L;
        Uri uri2 = null;
        String o4 = null;
        String o5 = null;
        long l = 0L;
        long m = 0L;
        int g2 = 0;
        int g3 = 0;
        ArrayList<MilestoneEntity> c2 = null;
        while (parcel.dataPosition() < c) {
            final int b = a.B(parcel);
            switch (a.aD(b)) {
                default: {
                    a.b(parcel, b);
                    continue;
                }
                case 1: {
                    gameEntity = a.a(parcel, b, GameEntity.CREATOR);
                    continue;
                }
                case 2: {
                    o = a.o(parcel, b);
                    continue;
                }
                case 3: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 4: {
                    uri = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 5: {
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 7: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 8: {
                    k = a.i(parcel, b);
                    continue;
                }
                case 9: {
                    uri2 = a.a(parcel, b, (android.os.Parcelable$Creator<Uri>)Uri.CREATOR);
                    continue;
                }
                case 10: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 12: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 13: {
                    l = a.i(parcel, b);
                    continue;
                }
                case 14: {
                    m = a.i(parcel, b);
                    continue;
                }
                case 15: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 17: {
                    c2 = a.c(parcel, b, (android.os.Parcelable$Creator<MilestoneEntity>)MilestoneEntity.CREATOR);
                    continue;
                }
                case 16: {
                    g3 = a.g(parcel, b);
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
        return new QuestEntity(g, gameEntity, o, i, uri, o2, o3, j, k, uri2, o4, o5, l, m, g2, g3, c2);
    }
    
    public QuestEntity[] newArray(final int n) {
        return new QuestEntity[n];
    }
}
