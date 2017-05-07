// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.a$a;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.a;
import java.util.List;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.b;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class InvitationEntityCreator implements Parcelable$Creator<InvitationEntity>
{
    static void a(final InvitationEntity invitationEntity, final Parcel parcel, final int n) {
        final int d = b.D(parcel);
        b.a(parcel, 1, (Parcelable)invitationEntity.getGame(), n, false);
        b.c(parcel, 1000, invitationEntity.getVersionCode());
        b.a(parcel, 2, invitationEntity.getInvitationId(), false);
        b.a(parcel, 3, invitationEntity.getCreationTimestamp());
        b.c(parcel, 4, invitationEntity.getInvitationType());
        b.a(parcel, 5, (Parcelable)invitationEntity.getInviter(), n, false);
        b.c(parcel, 6, invitationEntity.getParticipants(), false);
        b.c(parcel, 7, invitationEntity.getVariant());
        b.c(parcel, 8, invitationEntity.getAvailableAutoMatchSlots());
        b.H(parcel, d);
    }
    
    public InvitationEntity cl(final Parcel parcel) {
        ArrayList<ParticipantEntity> c = null;
        int g = 0;
        final int c2 = a.C(parcel);
        long i = 0L;
        int g2 = 0;
        ParticipantEntity participantEntity = null;
        int g3 = 0;
        String o = null;
        GameEntity gameEntity = null;
        int g4 = 0;
        while (parcel.dataPosition() < c2) {
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
                case 1000: {
                    g4 = a.g(parcel, b);
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
                    g3 = a.g(parcel, b);
                    continue;
                }
                case 5: {
                    participantEntity = a.a(parcel, b, ParticipantEntity.CREATOR);
                    continue;
                }
                case 6: {
                    c = a.c(parcel, b, ParticipantEntity.CREATOR);
                    continue;
                }
                case 7: {
                    g2 = a.g(parcel, b);
                    continue;
                }
                case 8: {
                    g = a.g(parcel, b);
                    continue;
                }
            }
        }
        if (parcel.dataPosition() != c2) {
            throw new a$a("Overread allowed size end=" + c2, parcel);
        }
        return new InvitationEntity(g4, gameEntity, o, i, g3, participantEntity, c, g2, g);
    }
    
    public InvitationEntity[] dS(final int n) {
        return new InvitationEntity[n];
    }
}
