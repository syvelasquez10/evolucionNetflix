// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.multiplayer;

import android.os.Parcel;
import java.util.Collection;
import com.google.android.gms.internal.fo;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.Game;
import com.google.android.gms.internal.fb;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ZInvitationCluster implements SafeParcelable, Invitation
{
    public static final InvitationClusterCreator CREATOR;
    private final ArrayList<InvitationEntity> LG;
    private final int xH;
    
    static {
        CREATOR = new InvitationClusterCreator();
    }
    
    ZInvitationCluster(final int xh, final ArrayList<InvitationEntity> lg) {
        this.xH = xh;
        this.LG = lg;
        this.hn();
    }
    
    private void hn() {
        fb.x(!this.LG.isEmpty());
        final InvitationEntity invitationEntity = this.LG.get(0);
        for (int size = this.LG.size(), i = 1; i < size; ++i) {
            fb.a(invitationEntity.getInviter().equals(this.LG.get(i).getInviter()), "All the invitations must be from the same inviter");
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
        if (zInvitationCluster.LG.size() != this.LG.size()) {
            return false;
        }
        for (int size = this.LG.size(), i = 0; i < size; ++i) {
            if (!this.LG.get(i).equals(zInvitationCluster.LG.get(i))) {
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
        return this.LG.get(0).getInvitationId();
    }
    
    @Override
    public int getInvitationType() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public Participant getInviter() {
        return this.LG.get(0).getInviter();
    }
    
    public ArrayList<Participant> getParticipants() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    @Override
    public int getVariant() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public int hashCode() {
        return fo.hashCode(this.LG.toArray());
    }
    
    public ArrayList<Invitation> ho() {
        return new ArrayList<Invitation>(this.LG);
    }
    
    public boolean isDataValid() {
        return true;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        InvitationClusterCreator.a(this, parcel, n);
    }
}
