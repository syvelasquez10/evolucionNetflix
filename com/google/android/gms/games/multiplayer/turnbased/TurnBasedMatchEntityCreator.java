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
        final int d = b.D(parcel);
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
        b.c(parcel, 13, turnBasedMatchEntity.getParticipants(), false);
        b.a(parcel, 14, turnBasedMatchEntity.getRematchId(), false);
        b.a(parcel, 15, turnBasedMatchEntity.getPreviousMatchData(), false);
        b.a(parcel, 17, turnBasedMatchEntity.getAutoMatchCriteria(), false);
        b.c(parcel, 16, turnBasedMatchEntity.getMatchNumber());
        b.c(parcel, 1000, turnBasedMatchEntity.getVersionCode());
        b.a(parcel, 19, turnBasedMatchEntity.isLocallyModified());
        b.c(parcel, 18, turnBasedMatchEntity.getTurnStatus());
        b.a(parcel, 21, turnBasedMatchEntity.getDescriptionParticipantId(), false);
        b.a(parcel, 20, turnBasedMatchEntity.getDescription(), false);
        b.H(parcel, d);
    }
    
    public TurnBasedMatchEntity createFromParcel(final Parcel parcel) {
        final int c = a.C(parcel);
        int g = 0;
        GameEntity gameEntity = null;
        String o = null;
        String o2 = null;
        long i = 0L;
        String o3 = null;
        long j = 0L;
        String o4 = null;
        int g2 = 0;
        int g3 = 0;
        int g4 = 0;
        byte[] r = null;
        ArrayList<ParticipantEntity> c2 = null;
        String o5 = null;
        byte[] r2 = null;
        int g5 = 0;
        Bundle q = null;
        int g6 = 0;
        boolean c3 = false;
        String o6 = null;
        String o7 = null;
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
                    o2 = a.o(parcel, b);
                    continue;
                }
                case 4: {
                    i = a.i(parcel, b);
                    continue;
                }
                case 5: {
                    o3 = a.o(parcel, b);
                    continue;
                }
                case 6: {
                    j = a.i(parcel, b);
                    continue;
                }
                case 7: {
                    o4 = a.o(parcel, b);
                    continue;
                }
                case 8: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 10: {
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 11: {
                    g4 = a.g(parcel, b);
                    continue;
                }
                case 12: {
                    r = a.r(parcel, b);
                    continue;
                }
                case 13: {
                    c2 = a.c(parcel, b, ParticipantEntity.CREATOR);
                    continue;
                }
                case 14: {
                    o5 = a.o(parcel, b);
                    continue;
                }
                case 15: {
                    r2 = a.r(parcel, b);
                    continue;
                }
                case 17: {
                    q = a.q(parcel, b);
                    continue;
                }
                case 16: {
                    g5 = a.g(parcel, b);
                    continue;
                }
                case 1000: {
                    g = a.g(parcel, b);
                    continue;
                }
                case 19: {
                    c3 = a.c(parcel, b);
                    continue;
                }
                case 18: {
                    g6 = a.g(parcel, b);
                    continue;
                }
                case 21: {
                    o7 = a.o(parcel, b);
                    continue;
                }
                case 20: {
                    o6 = a.o(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c) {
            throw new a.a("Overread allowed size end=" + c, parcel);
        }
        return new TurnBasedMatchEntity(g, gameEntity, o, o2, i, o3, j, o4, g2, g3, g4, r, c2, o5, r2, g5, q, g6, c3, o6, o7);
    }
    
    public TurnBasedMatchEntity[] newArray(final int n) {
        return new TurnBasedMatchEntity[n];
    }
}
