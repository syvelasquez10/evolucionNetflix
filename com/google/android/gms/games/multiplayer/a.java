// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import java.util.ArrayList;
import com.google.android.gms.games.GameEntity;
import java.util.List;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class a implements Parcelable$Creator<InvitationEntity>
{
    static void a(final InvitationEntity invitationEntity, final Parcel parcel, final int n) {
        final int o = b.o(parcel);
        b.a(parcel, 1, (Parcelable)invitationEntity.getGame(), n, false);
        b.c(parcel, 1000, invitationEntity.getVersionCode());
        b.a(parcel, 2, invitationEntity.getInvitationId(), false);
        b.a(parcel, 3, invitationEntity.getCreationTimestamp());
        b.c(parcel, 4, invitationEntity.getInvitationType());
        b.a(parcel, 5, (Parcelable)invitationEntity.getInviter(), n, false);
        b.b(parcel, 6, invitationEntity.getParticipants(), false);
        b.c(parcel, 7, invitationEntity.getVariant());
        b.c(parcel, 8, invitationEntity.getAvailableAutoMatchSlots());
        b.D(parcel, o);
    }
    
    public InvitationEntity[] aI(final int n) {
        return new InvitationEntity[n];
    }
    
    public InvitationEntity aa(final Parcel parcel) {
        ArrayList<ParticipantEntity> c = null;
        int g = 0;
        final int n = com.google.android.gms.common.internal.safeparcel.a.n(parcel);
        long h = 0L;
        int g2 = 0;
        ParticipantEntity participantEntity = null;
        int g3 = 0;
        String m = null;
        GameEntity gameEntity = null;
        int g4 = 0;
        while (parcel.dataPosition() < n) {
            final int i = com.google.android.gms.common.internal.safeparcel.a.m(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.M(i)) {
                default: {
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, i);
                    continue;
                }
                case 1: {
                    gameEntity = com.google.android.gms.common.internal.safeparcel.a.a(parcel, i, GameEntity.CREATOR);
                    continue;
                }
                case 1000: {
                    g4 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i);
                    continue;
                }
                case 2: {
                    m = com.google.android.gms.common.internal.safeparcel.a.m(parcel, i);
                    continue;
                }
                case 3: {
                    h = com.google.android.gms.common.internal.safeparcel.a.h(parcel, i);
                    continue;
                }
                case 4: {
                    g3 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i);
                    continue;
                }
                case 5: {
                    participantEntity = com.google.android.gms.common.internal.safeparcel.a.a(parcel, i, ParticipantEntity.CREATOR);
                    continue;
                }
                case 6: {
                    c = com.google.android.gms.common.internal.safeparcel.a.c(parcel, i, ParticipantEntity.CREATOR);
                    continue;
                }
                case 7: {
                    g2 = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i);
                    continue;
                }
                case 8: {
                    g = com.google.android.gms.common.internal.safeparcel.a.g(parcel, i);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != n) {
            throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + n, parcel);
        }
        return new InvitationEntity(g4, gameEntity, m, h, g3, participantEntity, c, g2, g);
    }
}
