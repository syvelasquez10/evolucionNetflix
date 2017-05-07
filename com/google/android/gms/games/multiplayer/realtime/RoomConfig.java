// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.common.internal.n;
import android.os.Bundle;

public final class RoomConfig
{
    private final String WD;
    private final int abS;
    private final RoomUpdateListener acd;
    private final RoomStatusUpdateListener ace;
    private final RealTimeMessageReceivedListener acf;
    private final String[] acg;
    private final Bundle ach;
    private final boolean aci;
    
    private RoomConfig(final Builder builder) {
        this.acd = builder.acd;
        this.ace = builder.ace;
        this.acf = builder.acf;
        this.WD = builder.acj;
        this.abS = builder.abS;
        this.ach = builder.ach;
        this.aci = builder.aci;
        this.acg = builder.ack.toArray(new String[builder.ack.size()]);
        if (this.acf == null) {
            n.a(this.aci, (Object)"Must either enable sockets OR specify a message listener");
        }
    }
    
    public static Builder builder(final RoomUpdateListener roomUpdateListener) {
        return new Builder(roomUpdateListener);
    }
    
    public static Bundle createAutoMatchCriteria(final int n, final int n2, final long n3) {
        final Bundle bundle = new Bundle();
        bundle.putInt("min_automatch_players", n);
        bundle.putInt("max_automatch_players", n2);
        bundle.putLong("exclusive_bit_mask", n3);
        return bundle;
    }
    
    public Bundle getAutoMatchCriteria() {
        return this.ach;
    }
    
    public String getInvitationId() {
        return this.WD;
    }
    
    public String[] getInvitedPlayerIds() {
        return this.acg;
    }
    
    public RealTimeMessageReceivedListener getMessageReceivedListener() {
        return this.acf;
    }
    
    public RoomStatusUpdateListener getRoomStatusUpdateListener() {
        return this.ace;
    }
    
    public RoomUpdateListener getRoomUpdateListener() {
        return this.acd;
    }
    
    public int getVariant() {
        return this.abS;
    }
    
    public boolean isSocketEnabled() {
        return this.aci;
    }
    
    public static final class Builder
    {
        int abS;
        final RoomUpdateListener acd;
        RoomStatusUpdateListener ace;
        RealTimeMessageReceivedListener acf;
        Bundle ach;
        boolean aci;
        String acj;
        ArrayList<String> ack;
        
        private Builder(final RoomUpdateListener roomUpdateListener) {
            this.acj = null;
            this.abS = -1;
            this.ack = new ArrayList<String>();
            this.aci = false;
            this.acd = n.b(roomUpdateListener, "Must provide a RoomUpdateListener");
        }
        
        public Builder addPlayersToInvite(final ArrayList<String> list) {
            n.i(list);
            this.ack.addAll(list);
            return this;
        }
        
        public Builder addPlayersToInvite(final String... array) {
            n.i(array);
            this.ack.addAll(Arrays.asList(array));
            return this;
        }
        
        public RoomConfig build() {
            return new RoomConfig(this, null);
        }
        
        public Builder setAutoMatchCriteria(final Bundle ach) {
            this.ach = ach;
            return this;
        }
        
        public Builder setInvitationIdToAccept(final String acj) {
            n.i(acj);
            this.acj = acj;
            return this;
        }
        
        public Builder setMessageReceivedListener(final RealTimeMessageReceivedListener acf) {
            this.acf = acf;
            return this;
        }
        
        public Builder setRoomStatusUpdateListener(final RoomStatusUpdateListener ace) {
            this.ace = ace;
            return this;
        }
        
        public Builder setSocketCommunicationEnabled(final boolean aci) {
            this.aci = aci;
            return this;
        }
        
        public Builder setVariant(final int abS) {
            n.b(abS == -1 || abS > 0, (Object)"Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
            this.abS = abS;
            return this;
        }
    }
}
