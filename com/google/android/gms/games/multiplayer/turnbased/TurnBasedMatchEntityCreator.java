// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.games.multiplayer.Participant;
import java.util.List;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class TurnBasedMatchEntityCreator implements Parcelable$Creator<TurnBasedMatchEntity>
{
    public static final int CONTENT_DESCRIPTION = 0;
    
    static void a(final TurnBasedMatchEntity turnBasedMatchEntity, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.a(parcel, 1, (Parcelable)turnBasedMatchEntity.getGame(), n, false);
        b.a(parcel, 2, turnBasedMatchEntity.getMatchId(), false);
        b.a(parcel, 3, turnBasedMatchEntity.getCreatorId(), false);
        b.a(parcel, 4, turnBasedMatchEntity.getCreationTimestamp());
        b.a(parcel, 5, turnBasedMatchEntity.getLastUpdaterId(), false);
        b.a(parcel, 6, turnBasedMatchEntity.getLastUpdatedTimestamp());
        b.a(parcel, 7, turnBasedMatchEntity.getPendingParticipantId(), false);
        b.c(parcel, 8, turnBasedMatchEntity.getStatus());
        b.c(parcel, 10, turnBasedMatchEntity.getVariant());
        b.c(parcel, 11, turnBasedMatchEntity.getVersion());
        b.a(parcel, 12, turnBasedMatchEntity.getData(), false);
        b.b(parcel, 13, turnBasedMatchEntity.getParticipants(), false);
        b.a(parcel, 14, turnBasedMatchEntity.getRematchId(), false);
        b.a(parcel, 15, turnBasedMatchEntity.getPreviousMatchData(), false);
        b.a(parcel, 17, turnBasedMatchEntity.getAutoMatchCriteria(), false);
        b.c(parcel, 16, turnBasedMatchEntity.getMatchNumber());
        b.c(parcel, 1000, turnBasedMatchEntity.getVersionCode());
        b.a(parcel, 19, turnBasedMatchEntity.isLocallyModified());
        b.c(parcel, 18, turnBasedMatchEntity.getTurnStatus());
        b.D(parcel, o);
    }
    
    public TurnBasedMatchEntity createFromParcel(final Parcel parcel) {
        final int n = a.n(parcel);
        int g = 0;
        GameEntity gameEntity = null;
        String m = null;
        String i = null;
        long h = 0L;
        String j = null;
        long h2 = 0L;
        String k = null;
        int g2 = 0;
        int g3 = 0;
        int g4 = 0;
        byte[] p = null;
        ArrayList<ParticipantEntity> c = null;
        String l = null;
        byte[] p2 = null;
        int g5 = 0;
        Bundle o = null;
        int g6 = 0;
        boolean c2 = false;
        while (parcel.dataPosition() < n) {
            final int m2 = a.m(parcel);
            switch (a.M(m2)) {
                default: {
                    a.b(parcel, m2);
                    continue;
                }
                case 1: {
                    gameEntity = a.a(parcel, m2, GameEntity.CREATOR);
                    continue;
                }
                case 2: {
                    m = a.m(parcel, m2);
                    continue;
                }
                case 3: {
                    i = a.m(parcel, m2);
                    continue;
                }
                case 4: {
                    h = a.h(parcel, m2);
                    continue;
                }
                case 5: {
                    j = a.m(parcel, m2);
                    continue;
                }
                case 6: {
                    h2 = a.h(parcel, m2);
                    continue;
                }
                case 7: {
                    k = a.m(parcel, m2);
                    continue;
                }
                case 8: {
                    g2 = a.g(parcel, m2);
                    continue;
                }
                case 10: {
                    g3 = a.g(parcel, m2);
                    continue;
                }
                case 11: {
                    g4 = a.g(parcel, m2);
                    continue;
                }
                case 12: {
                    p = a.p(parcel, m2);
                    continue;
                }
                case 13: {
                    c = a.c(parcel, m2, ParticipantEntity.CREATOR);
                    continue;
                }
                case 14: {
                    l = a.m(parcel, m2);
                    continue;
                }
                case 15: {
                    p2 = a.p(parcel, m2);
                    continue;
                }
                case 17: {
                    o = a.o(parcel, m2);
                    continue;
                }
                case 16: {
                    g5 = a.g(parcel, m2);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, m2);
                    continue;
                }
                case 19: {
                    c2 = a.c(parcel, m2);
                    continue;
                }
                case 18: {
                    g6 = a.g(parcel, m2);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new a.a("Overread allowed size end=" + n, parcel);
        }
        return new TurnBasedMatchEntity(g, gameEntity, m, i, h, j, h2, k, g2, g3, g4, p, c, l, p2, g5, o, g6, c2);
    }
    
    public TurnBasedMatchEntity[] newArray(final int n) {
        return new TurnBasedMatchEntity[n];
    }
}
