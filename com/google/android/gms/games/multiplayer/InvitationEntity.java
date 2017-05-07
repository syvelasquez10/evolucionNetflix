// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.games.Game;
import com.google.android.gms.internal.dv;
import com.google.android.gms.internal.ee;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.internal.eg;
import java.util.ArrayList;
import com.google.android.gms.games.GameEntity;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.fm;

public final class InvitationEntity extends fm implements Invitation
{
    public static final Parcelable$Creator<InvitationEntity> CREATOR;
    private final int kg;
    private final String uf;
    private final GameEntity wj;
    private final long wk;
    private final int wl;
    private final ParticipantEntity wm;
    private final ArrayList<ParticipantEntity> wn;
    private final int wo;
    private final int wp;
    
    static {
        CREATOR = (Parcelable$Creator)new a();
    }
    
    InvitationEntity(final int kg, final GameEntity wj, final String uf, final long wk, final int wl, final ParticipantEntity wm, final ArrayList<ParticipantEntity> wn, final int wo, final int wp) {
        this.kg = kg;
        this.wj = wj;
        this.uf = uf;
        this.wk = wk;
        this.wl = wl;
        this.wm = wm;
        this.wn = wn;
        this.wo = wo;
        this.wp = wp;
    }
    
    InvitationEntity(final Invitation invitation) {
        this.kg = 2;
        this.wj = new GameEntity(invitation.getGame());
        this.uf = invitation.getInvitationId();
        this.wk = invitation.getCreationTimestamp();
        this.wl = invitation.getInvitationType();
        this.wo = invitation.getVariant();
        this.wp = invitation.getAvailableAutoMatchSlots();
        final String participantId = invitation.getInviter().getParticipantId();
        final Freezable<ParticipantEntity> freezable = null;
        final ArrayList<Participant> participants = invitation.getParticipants();
        final int size = participants.size();
        this.wn = new ArrayList<ParticipantEntity>(size);
        int i = 0;
        Object o = freezable;
        while (i < size) {
            final Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(participantId)) {
                o = participant;
            }
            this.wn.add(((Freezable<ParticipantEntity>)participant).freeze());
            ++i;
        }
        eg.b(o, "Must have a valid inviter!");
        this.wm = ((Freezable<ParticipantEntity>)o).freeze();
    }
    
    static int a(final Invitation invitation) {
        return ee.hashCode(invitation.getGame(), invitation.getInvitationId(), invitation.getCreationTimestamp(), invitation.getInvitationType(), invitation.getInviter(), invitation.getParticipants(), invitation.getVariant(), invitation.getAvailableAutoMatchSlots());
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
                if (ee.equal(invitation2.getGame(), invitation.getGame()) && ee.equal(invitation2.getInvitationId(), invitation.getInvitationId()) && ee.equal(invitation2.getCreationTimestamp(), invitation.getCreationTimestamp()) && ee.equal(invitation2.getInvitationType(), invitation.getInvitationType()) && ee.equal(invitation2.getInviter(), invitation.getInviter()) && ee.equal(invitation2.getParticipants(), invitation.getParticipants()) && ee.equal(invitation2.getVariant(), invitation.getVariant())) {
                    b2 = b;
                    if (ee.equal(invitation2.getAvailableAutoMatchSlots(), invitation.getAvailableAutoMatchSlots())) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    static String b(final Invitation invitation) {
        return ee.e(invitation).a("Game", invitation.getGame()).a("InvitationId", invitation.getInvitationId()).a("CreationTimestamp", invitation.getCreationTimestamp()).a("InvitationType", invitation.getInvitationType()).a("Inviter", invitation.getInviter()).a("Participants", invitation.getParticipants()).a("Variant", invitation.getVariant()).a("AvailableAutoMatchSlots", invitation.getAvailableAutoMatchSlots()).toString();
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
        return this.wp;
    }
    
    @Override
    public long getCreationTimestamp() {
        return this.wk;
    }
    
    @Override
    public Game getGame() {
        return this.wj;
    }
    
    @Override
    public String getInvitationId() {
        return this.uf;
    }
    
    @Override
    public int getInvitationType() {
        return this.wl;
    }
    
    @Override
    public Participant getInviter() {
        return this.wm;
    }
    
    public ArrayList<Participant> getParticipants() {
        return new ArrayList<Participant>(this.wn);
    }
    
    @Override
    public int getVariant() {
        return this.wo;
    }
    
    public int getVersionCode() {
        return this.kg;
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
        if (!this.bN()) {
            com.google.android.gms.games.multiplayer.a.a(this, parcel, n);
        }
        else {
            this.wj.writeToParcel(parcel, n);
            parcel.writeString(this.uf);
            parcel.writeLong(this.wk);
            parcel.writeInt(this.wl);
            this.wm.writeToParcel(parcel, n);
            final int size = this.wn.size();
            parcel.writeInt(size);
            for (int i = 0; i < size; ++i) {
                this.wn.get(i).writeToParcel(parcel, n);
            }
        }
    }
    
    static final class a extends com.google.android.gms.games.multiplayer.a
    {
        @Override
        public InvitationEntity aa(final Parcel parcel) {
            if (fm.c(dv.bM()) || dv.P(InvitationEntity.class.getCanonicalName())) {
                return super.aa(parcel);
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
