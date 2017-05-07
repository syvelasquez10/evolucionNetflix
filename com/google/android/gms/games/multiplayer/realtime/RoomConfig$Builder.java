// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import java.util.Arrays;
import java.util.Collection;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import android.os.Bundle;

public final class RoomConfig$Builder
{
    int abS;
    final RoomUpdateListener acd;
    RoomStatusUpdateListener ace;
    RealTimeMessageReceivedListener acf;
    Bundle ach;
    boolean aci;
    String acj;
    ArrayList<String> ack;
    
    private RoomConfig$Builder(final RoomUpdateListener roomUpdateListener) {
        this.acj = null;
        this.abS = -1;
        this.ack = new ArrayList<String>();
        this.aci = false;
        this.acd = n.b(roomUpdateListener, "Must provide a RoomUpdateListener");
    }
    
    public RoomConfig$Builder addPlayersToInvite(final ArrayList<String> list) {
        n.i(list);
        this.ack.addAll(list);
        return this;
    }
    
    public RoomConfig$Builder addPlayersToInvite(final String... array) {
        n.i(array);
        this.ack.addAll(Arrays.asList(array));
        return this;
    }
    
    public RoomConfig build() {
        return new RoomConfig(this, null);
    }
    
    public RoomConfig$Builder setAutoMatchCriteria(final Bundle ach) {
        this.ach = ach;
        return this;
    }
    
    public RoomConfig$Builder setInvitationIdToAccept(final String acj) {
        n.i(acj);
        this.acj = acj;
        return this;
    }
    
    public RoomConfig$Builder setMessageReceivedListener(final RealTimeMessageReceivedListener acf) {
        this.acf = acf;
        return this;
    }
    
    public RoomConfig$Builder setRoomStatusUpdateListener(final RoomStatusUpdateListener ace) {
        this.ace = ace;
        return this;
    }
    
    public RoomConfig$Builder setSocketCommunicationEnabled(final boolean aci) {
        this.aci = aci;
        return this;
    }
    
    public RoomConfig$Builder setVariant(final int abS) {
        n.b(abS == -1 || abS > 0, (Object)"Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
        this.abS = abS;
        return this;
    }
}
