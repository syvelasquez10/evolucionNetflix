// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import com.google.android.gms.internal.fq;
import android.os.Bundle;

public final class RoomConfig
{
    private final String IV;
    private final RoomUpdateListener MK;
    private final RoomStatusUpdateListener ML;
    private final RealTimeMessageReceivedListener MM;
    private final String[] MN;
    private final Bundle MO;
    private final boolean MP;
    private final int My;
    
    private RoomConfig(final Builder builder) {
        this.MK = builder.MK;
        this.ML = builder.ML;
        this.MM = builder.MM;
        this.IV = builder.MQ;
        this.My = builder.My;
        this.MO = builder.MO;
        this.MP = builder.MP;
        this.MN = builder.MR.toArray(new String[builder.MR.size()]);
        if (this.MM == null) {
            fq.a(this.MP, (Object)"Must either enable sockets OR specify a message listener");
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
        return this.MO;
    }
    
    public String getInvitationId() {
        return this.IV;
    }
    
    public String[] getInvitedPlayerIds() {
        return this.MN;
    }
    
    public RealTimeMessageReceivedListener getMessageReceivedListener() {
        return this.MM;
    }
    
    public RoomStatusUpdateListener getRoomStatusUpdateListener() {
        return this.ML;
    }
    
    public RoomUpdateListener getRoomUpdateListener() {
        return this.MK;
    }
    
    public int getVariant() {
        return this.My;
    }
    
    public boolean isSocketEnabled() {
        return this.MP;
    }
    
    public static final class Builder
    {
        final RoomUpdateListener MK;
        RoomStatusUpdateListener ML;
        RealTimeMessageReceivedListener MM;
        Bundle MO;
        boolean MP;
        String MQ;
        ArrayList<String> MR;
        int My;
        
        private Builder(final RoomUpdateListener roomUpdateListener) {
            this.MQ = null;
            this.My = -1;
            this.MR = new ArrayList<String>();
            this.MP = false;
            this.MK = fq.b(roomUpdateListener, "Must provide a RoomUpdateListener");
        }
        
        public Builder addPlayersToInvite(final ArrayList<String> list) {
            fq.f(list);
            this.MR.addAll(list);
            return this;
        }
        
        public Builder addPlayersToInvite(final String... array) {
            fq.f(array);
            this.MR.addAll(Arrays.asList(array));
            return this;
        }
        
        public RoomConfig build() {
            return new RoomConfig(this, null);
        }
        
        public Builder setAutoMatchCriteria(final Bundle mo) {
            this.MO = mo;
            return this;
        }
        
        public Builder setInvitationIdToAccept(final String mq) {
            fq.f(mq);
            this.MQ = mq;
            return this;
        }
        
        public Builder setMessageReceivedListener(final RealTimeMessageReceivedListener mm) {
            this.MM = mm;
            return this;
        }
        
        public Builder setRoomStatusUpdateListener(final RoomStatusUpdateListener ml) {
            this.ML = ml;
            return this;
        }
        
        public Builder setSocketCommunicationEnabled(final boolean mp) {
            this.MP = mp;
            return this;
        }
        
        public Builder setVariant(final int my) {
            fq.b(my == -1 || my > 0, "Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
            this.My = my;
            return this;
        }
    }
}
