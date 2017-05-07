// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.multiplayer;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ZInvitationCluster implements SafeParcelable, Invitation
{
    public static final InvitationClusterCreator CREATOR;
    private final int BR;
    private final ArrayList<InvitationEntity> aaA;
    
    static {
        CREATOR = new InvitationClusterCreator();
    }
    
    ZInvitationCluster(final int br, final ArrayList<InvitationEntity> aaA) {
        this.BR = br;
        this.aaA = aaA;
        this.lg();
    }
    
    private void lg() {
        a.I(!this.aaA.isEmpty());
        final InvitationEntity invitationEntity = this.aaA.get(0);
        for (int size = this.aaA.size(), i = 1; i < size; ++i) {
            a.a(invitationEntity.getInviter().equals(this.aaA.get(i).getInviter()), "All the invitations must be from the same inviter");
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ZInvitationCluster)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final ZInvitationCluster zInvitationCluster = (ZInvitationCluster)o;
        if (zInvitationCluster.aaA.size() != this.aaA.size()) {
            return false;
        }
        for (int size = this.aaA.size(), i = 0; i < size; ++i) {
            if (!this.aaA.get(i).equals(zInvitationCluster.aaA.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    public Invitation freeze() {
        return this;
    }
    
    @Override
    public int getAvailableAutoMatchSlots() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public long getCreationTimestamp() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public Game getGame() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public String getInvitationId() {
        return this.aaA.get(0).getInvitationId();
    }
    
    @Override
    public int getInvitationType() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public Participant getInviter() {
        return this.aaA.get(0).getInviter();
    }
    
    public ArrayList<Participant> getParticipants() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public int getVariant() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.aaA.toArray());
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    public ArrayList<Invitation> lh() {
        return new ArrayList<Invitation>(this.aaA);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        InvitationClusterCreator.a(this, parcel, n);
    }
}
