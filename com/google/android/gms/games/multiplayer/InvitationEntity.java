// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.internal.c;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import com.google.android.gms.games.GameEntity;
import android.os.Parcelable$Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

public final class InvitationEntity extends GamesDowngradeableSafeParcel implements Invitation
{
    public static final Parcelable$Creator<InvitationEntity> CREATOR;
    private final int BR;
    private final String WD;
    private final GameEntity aan;
    private final long abO;
    private final int abP;
    private final ParticipantEntity abQ;
    private final ArrayList<ParticipantEntity> abR;
    private final int abS;
    private final int abT;
    
    static {
        CREATOR = (Parcelable$Creator)new InvitationEntityCreatorCompat();
    }
    
    InvitationEntity(final int br, final GameEntity aan, final String wd, final long abO, final int abP, final ParticipantEntity abQ, final ArrayList<ParticipantEntity> abR, final int abS, final int abT) {
        this.BR = br;
        this.aan = aan;
        this.WD = wd;
        this.abO = abO;
        this.abP = abP;
        this.abQ = abQ;
        this.abR = abR;
        this.abS = abS;
        this.abT = abT;
    }
    
    InvitationEntity(final Invitation invitation) {
        this.BR = 2;
        this.aan = new GameEntity(invitation.getGame());
        this.WD = invitation.getInvitationId();
        this.abO = invitation.getCreationTimestamp();
        this.abP = invitation.getInvitationType();
        this.abS = invitation.getVariant();
        this.abT = invitation.getAvailableAutoMatchSlots();
        final String participantId = invitation.getInviter().getParticipantId();
        final Freezable<ParticipantEntity> freezable = null;
        final ArrayList<Participant> participants = invitation.getParticipants();
        final int size = participants.size();
        this.abR = new ArrayList<ParticipantEntity>(size);
        int i = 0;
        Object o = freezable;
        while (i < size) {
            final Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(participantId)) {
                o = participant;
            }
            this.abR.add(((Freezable<ParticipantEntity>)participant).freeze());
            ++i;
        }
        n.b(o, "Must have a valid inviter!");
        this.abQ = ((Freezable<ParticipantEntity>)o).freeze();
    }
    
    static int a(final Invitation invitation) {
        return m.hashCode(invitation.getGame(), invitation.getInvitationId(), invitation.getCreationTimestamp(), invitation.getInvitationType(), invitation.getInviter(), invitation.getParticipants(), invitation.getVariant(), invitation.getAvailableAutoMatchSlots());
    }
    
    static boolean a(final Invitation invitation, final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof Invitation)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (invitation != o) {
                final Invitation invitation2 = (Invitation)o;
                if (m.equal(invitation2.getGame(), invitation.getGame()) && m.equal(invitation2.getInvitationId(), invitation.getInvitationId()) && m.equal(invitation2.getCreationTimestamp(), invitation.getCreationTimestamp()) && m.equal(invitation2.getInvitationType(), invitation.getInvitationType()) && m.equal(invitation2.getInviter(), invitation.getInviter()) && m.equal(invitation2.getParticipants(), invitation.getParticipants()) && m.equal(invitation2.getVariant(), invitation.getVariant())) {
                    b2 = b;
                    if (m.equal(invitation2.getAvailableAutoMatchSlots(), invitation.getAvailableAutoMatchSlots())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Invitation invitation) {
        return m.h(invitation).a("Game", invitation.getGame()).a("InvitationId", invitation.getInvitationId()).a("CreationTimestamp", invitation.getCreationTimestamp()).a("InvitationType", invitation.getInvitationType()).a("Inviter", invitation.getInviter()).a("Participants", invitation.getParticipants()).a("Variant", invitation.getVariant()).a("AvailableAutoMatchSlots", invitation.getAvailableAutoMatchSlots()).toString();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return a(this, o);
    }
    
    public Invitation freeze() {
        return this;
    }
    
    @Override
    public int getAvailableAutoMatchSlots() {
        return this.abT;
    }
    
    @Override
    public long getCreationTimestamp() {
        return this.abO;
    }
    
    @Override
    public Game getGame() {
        return this.aan;
    }
    
    @Override
    public String getInvitationId() {
        return this.WD;
    }
    
    @Override
    public int getInvitationType() {
        return this.abP;
    }
    
    @Override
    public Participant getInviter() {
        return this.abQ;
    }
    
    public ArrayList<Participant> getParticipants() {
        return new ArrayList<Participant>(this.abR);
    }
    
    @Override
    public int getVariant() {
        return this.abS;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return a(this);
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    @Override
    public String toString() {
        return b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (!this.gQ()) {
            InvitationEntityCreator.a(this, parcel, n);
        }
        else {
            this.aan.writeToParcel(parcel, n);
            parcel.writeString(this.WD);
            parcel.writeLong(this.abO);
            parcel.writeInt(this.abP);
            this.abQ.writeToParcel(parcel, n);
            final int size = this.abR.size();
            parcel.writeInt(size);
            for (int i = 0; i < size; ++i) {
                this.abR.get(i).writeToParcel(parcel, n);
            }
        }
    }
    
    static final class InvitationEntityCreatorCompat extends InvitationEntityCreator
    {
        @Override
        public InvitationEntity cl(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(c.gP()) || c.aV(InvitationEntity.class.getCanonicalName())) {
                return super.cl(parcel);
            }
            final GameEntity gameEntity = (GameEntity)GameEntity.CREATOR.createFromParcel(parcel);
            final String string = parcel.readString();
            final long long1 = parcel.readLong();
            final int int1 = parcel.readInt();
            final ParticipantEntity participantEntity = (ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(parcel);
            final int int2 = parcel.readInt();
            final ArrayList list = new ArrayList<ParticipantEntity>(int2);
            for (int i = 0; i < int2; ++i) {
                list.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new InvitationEntity(2, gameEntity, string, long1, int1, participantEntity, (ArrayList<ParticipantEntity>)list, -1, 0);
        }
    }
}
