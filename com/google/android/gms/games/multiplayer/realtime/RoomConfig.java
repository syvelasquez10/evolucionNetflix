// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

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
    
    private RoomConfig(final RoomConfig$Builder roomConfig$Builder) {
        this.acd = roomConfig$Builder.acd;
        this.ace = roomConfig$Builder.ace;
        this.acf = roomConfig$Builder.acf;
        this.WD = roomConfig$Builder.acj;
        this.abS = roomConfig$Builder.abS;
        this.ach = roomConfig$Builder.ach;
        this.aci = roomConfig$Builder.aci;
        this.acg = roomConfig$Builder.ack.toArray(new String[roomConfig$Builder.ack.size()]);
        if (this.acf == null) {
            n.a(this.aci, (Object)"Must either enable sockets OR specify a message listener");
        }
    }
    
    public static RoomConfig$Builder builder(final RoomUpdateListener roomUpdateListener) {
        return new RoomConfig$Builder(roomUpdateListener, null);
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
}
