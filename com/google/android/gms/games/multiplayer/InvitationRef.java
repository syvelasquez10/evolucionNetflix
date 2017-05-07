// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.internal.fq;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.b;

public final class InvitationRef extends b implements Invitation
{
    private final Game LS;
    private final ParticipantRef MA;
    private final ArrayList<Participant> Mx;
    
    InvitationRef(final DataHolder dataHolder, int i, final int n) {
        super(dataHolder, i);
        this.LS = new GameRef(dataHolder, i);
        this.Mx = new ArrayList<Participant>(n);
        final String string = this.getString("external_inviter_id");
        i = 0;
        ParticipantRef participantRef = null;
        while (i < n) {
            final ParticipantRef participantRef2 = new ParticipantRef(this.BB, this.BD + i);
            if (participantRef2.getParticipantId().equals(string)) {
                participantRef = participantRef2;
            }
            this.Mx.add(participantRef2);
            ++i;
        }
        this.MA = fq.b(participantRef, "Must have a valid inviter!");
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return InvitationEntity.a(this, o);
    }
    
    public Invitation freeze() {
        return new InvitationEntity(this);
    }
    
    @Override
    public int getAvailableAutoMatchSlots() {
        if (!this.getBoolean("has_automatch_criteria")) {
            return 0;
        }
        return this.getInteger("automatch_max_players");
    }
    
    @Override
    public long getCreationTimestamp() {
        return Math.max(this.getLong("creation_timestamp"), this.getLong("last_modified_timestamp"));
    }
    
    @Override
    public Game getGame() {
        return this.LS;
    }
    
    @Override
    public String getInvitationId() {
        return this.getString("external_invitation_id");
    }
    
    @Override
    public int getInvitationType() {
        return this.getInteger("type");
    }
    
    @Override
    public Participant getInviter() {
        return this.MA;
    }
    
    public ArrayList<Participant> getParticipants() {
        return this.Mx;
    }
    
    @Override
    public int getVariant() {
        return this.getInteger("variant");
    }
    
    @Override
    public int hashCode() {
        return InvitationEntity.a(this);
    }
    
    @Override
    public String toString() {
        return InvitationEntity.b(this);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ((InvitationEntity)this.freeze()).writeToParcel(parcel, n);
    }
}
