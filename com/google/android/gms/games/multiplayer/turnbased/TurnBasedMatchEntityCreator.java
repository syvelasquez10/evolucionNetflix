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
        final int p3 = b.p(parcel);
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
        b.a(parcel, 21, turnBasedMatchEntity.getDescriptionParticipantId(), false);
        b.a(parcel, 20, turnBasedMatchEntity.getDescription(), false);
        b.F(parcel, p3);
    }
    
    public TurnBasedMatchEntity createFromParcel(final Parcel parcel) {
        final int o = a.o(parcel);
        int g = 0;
        GameEntity gameEntity = null;
        String n = null;
        String n2 = null;
        long i = 0L;
        String n3 = null;
        long j = 0L;
        String n4 = null;
        int g2 = 0;
        int g3 = 0;
        int g4 = 0;
        byte[] q = null;
        ArrayList<ParticipantEntity> c = null;
        String n5 = null;
        byte[] q2 = null;
        int g5 = 0;
        Bundle p = null;
        int g6 = 0;
        boolean c2 = false;
        String n6 = null;
        String n7 = null;
        while (parcel.dataPosition() < o) {
            final int n8 = a.n(parcel);
            switch (a.R(n8)) {
                default: {
                    a.b(parcel, n8);
                    continue;
                }
                case 1: {
                    gameEntity = a.a(parcel, n8, GameEntity.CREATOR);
                    continue;
                }
                case 2: {
                    n = a.n(parcel, n8);
                    continue;
                }
                case 3: {
                    n2 = a.n(parcel, n8);
                    continue;
                }
                case 4: {
                    i = a.i(parcel, n8);
                    continue;
                }
                case 5: {
                    n3 = a.n(parcel, n8);
                    continue;
                }
                case 6: {
                    j = a.i(parcel, n8);
                    continue;
                }
                case 7: {
                    n4 = a.n(parcel, n8);
                    continue;
                }
                case 8: {
                    g2 = a.g(parcel, n8);
                    continue;
                }
                case 10: {
                    g3 = a.g(parcel, n8);
                    continue;
                }
                case 11: {
                    g4 = a.g(parcel, n8);
                    continue;
                }
                case 12: {
                    q = a.q(parcel, n8);
                    continue;
                }
                case 13: {
                    c = a.c(parcel, n8, ParticipantEntity.CREATOR);
                    continue;
                }
                case 14: {
                    n5 = a.n(parcel, n8);
                    continue;
                }
                case 15: {
                    q2 = a.q(parcel, n8);
                    continue;
                }
                case 17: {
                    p = a.p(parcel, n8);
                    continue;
                }
                case 16: {
                    g5 = a.g(parcel, n8);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, n8);
                    continue;
                }
                case 19: {
                    c2 = a.c(parcel, n8);
                    continue;
                }
                case 18: {
                    g6 = a.g(parcel, n8);
                    continue;
                }
                case 21: {
                    n7 = a.n(parcel, n8);
                    continue;
                }
                case 20: {
                    n6 = a.n(parcel, n8);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != o) {
            throw new a.a("Overread allowed size end=" + o, parcel);
        }
        return new TurnBasedMatchEntity(g, gameEntity, n, n2, i, n3, j, n4, g2, g3, g4, q, c, n5, q2, g5, p, g6, c2, n6, n7);
    }
    
    public TurnBasedMatchEntity[] newArray(final int n) {
        return new TurnBasedMatchEntity[n];
    }
}
