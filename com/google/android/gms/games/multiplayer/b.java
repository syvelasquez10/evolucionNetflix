// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.internal.eg;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;
import com.google.android.gms.games.Game;

public final class b extends com.google.android.gms.common.data.b implements Invitation
{
    private final Game vG;
    private final ArrayList<Participant> wn;
    private final d wq;
    
    b(final DataHolder dataHolder, int i, final int n) {
        super(dataHolder, i);
        this.vG = new com.google.android.gms.games.b(dataHolder, i);
        this.wn = new ArrayList<Participant>(n);
        final String string = this.getString("external_inviter_id");
        i = 0;
        d d = null;
        while (i < n) {
            final d d2 = new d(this.nE, this.nG + i);
            if (d2.getParticipantId().equals(string)) {
                d = d2;
            }
            this.wn.add(d2);
            ++i;
        }
        this.wq = eg.b(d, "Must have a valid inviter!");
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
        return this.vG;
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
        return this.wq;
    }
    
    public ArrayList<Participant> getParticipants() {
        return this.wn;
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
