// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import java.util.ArrayList;
import com.google.android.gms.common.data.d;

public final class InvitationRef extends d implements Invitation
{
    private final ArrayList<Participant> abR;
    private final ParticipantRef abU;
    private final Game abm;
    
    InvitationRef(final DataHolder dataHolder, int i, final int n) {
        super(dataHolder, i);
        this.abm = new GameRef(dataHolder, i);
        this.abR = new ArrayList<Participant>(n);
        final String string = this.getString("external_inviter_id");
        i = 0;
        ParticipantRef participantRef = null;
        while (i < n) {
            final ParticipantRef participantRef2 = new ParticipantRef(this.IC, this.JQ + i);
            if (participantRef2.getParticipantId().equals(string)) {
                participantRef = participantRef2;
            }
            this.abR.add(participantRef2);
            ++i;
        }
        this.abU = n.b(participantRef, "Must have a valid inviter!");
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
        return this.abm;
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
        return this.abU;
    }
    
    public ArrayList<Participant> getParticipants() {
        return this.abR;
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
