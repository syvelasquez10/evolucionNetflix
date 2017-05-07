// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.internal.eg;
import android.os.Bundle;

public final class RoomConfig
{
    private final String uf;
    private final RoomUpdateListener wD;
    private final RoomStatusUpdateListener wE;
    private final RealTimeMessageReceivedListener wF;
    private final String[] wG;
    private final Bundle wH;
    private final boolean wI;
    private final int wo;
    
    private RoomConfig(final Builder builder) {
        this.wD = builder.wD;
        this.wE = builder.wE;
        this.wF = builder.wF;
        this.uf = builder.wJ;
        this.wo = builder.wo;
        this.wH = builder.wH;
        this.wI = builder.wI;
        this.wG = builder.wK.toArray(new String[builder.wK.size()]);
        if (this.wF == null) {
            eg.a(this.wI, (Object)"Must either enable sockets OR specify a message listener");
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
        return this.wH;
    }
    
    public String getInvitationId() {
        return this.uf;
    }
    
    public String[] getInvitedPlayerIds() {
        return this.wG;
    }
    
    public RealTimeMessageReceivedListener getMessageReceivedListener() {
        return this.wF;
    }
    
    public RoomStatusUpdateListener getRoomStatusUpdateListener() {
        return this.wE;
    }
    
    public RoomUpdateListener getRoomUpdateListener() {
        return this.wD;
    }
    
    public int getVariant() {
        return this.wo;
    }
    
    public boolean isSocketEnabled() {
        return this.wI;
    }
    
    public static final class Builder
    {
        final RoomUpdateListener wD;
        RoomStatusUpdateListener wE;
        RealTimeMessageReceivedListener wF;
        Bundle wH;
        boolean wI;
        String wJ;
        ArrayList<String> wK;
        int wo;
        
        private Builder(final RoomUpdateListener roomUpdateListener) {
            this.wJ = null;
            this.wo = -1;
            this.wK = new ArrayList<String>();
            this.wI = false;
            this.wD = eg.b(roomUpdateListener, "Must provide a RoomUpdateListener");
        }
        
        public Builder addPlayersToInvite(final ArrayList<String> list) {
            eg.f(list);
            this.wK.addAll(list);
            return this;
        }
        
        public Builder addPlayersToInvite(final String... array) {
            eg.f(array);
            this.wK.addAll(Arrays.asList(array));
            return this;
        }
        
        public RoomConfig build() {
            return new RoomConfig(this, null);
        }
        
        public Builder setAutoMatchCriteria(final Bundle wh) {
            this.wH = wh;
            return this;
        }
        
        public Builder setInvitationIdToAccept(final String wj) {
            eg.f(wj);
            this.wJ = wj;
            return this;
        }
        
        public Builder setMessageReceivedListener(final RealTimeMessageReceivedListener wf) {
            this.wF = wf;
            return this;
        }
        
        public Builder setRoomStatusUpdateListener(final RoomStatusUpdateListener we) {
            this.wE = we;
            return this;
        }
        
        public Builder setSocketCommunicationEnabled(final boolean wi) {
            this.wI = wi;
            return this;
        }
        
        public Builder setVariant(final int wo) {
            eg.b(wo == -1 || wo > 0, "Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
            this.wo = wo;
            return this;
        }
    }
}
