// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import com.netflix.mediaclient.StatusCode;

public enum ClientActionFromLase
{
    ACQUIRE_NEW_LICENSE(1), 
    DELETE_CONTENT_ON_REVOCATION(4), 
    DELETE_LICENSES(2), 
    MARK_PLAYABLE(3), 
    NO_ACTION(0);
    
    private final int mAction;
    
    private ClientActionFromLase(final int mAction) {
        this.mAction = mAction;
    }
    
    public static ClientActionFromLase create(final int n) {
        final ClientActionFromLase[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final ClientActionFromLase clientActionFromLase = values[i];
            if (clientActionFromLase.mAction == n) {
                return clientActionFromLase;
            }
        }
        return ClientActionFromLase.NO_ACTION;
    }
    
    public StatusCode getStatusCode() {
        switch (ClientActionFromLase$1.$SwitchMap$com$netflix$mediaclient$service$player$bladerunnerclient$volley$ClientActionFromLase[this.ordinal()]) {
            default: {
                return StatusCode.OK;
            }
            case 2: {
                return StatusCode.OFFLINE_LICENSE_FETCH_NEW;
            }
            case 5: {
                return StatusCode.DL_ENCODES_DELETE_ON_REVOCATION;
            }
        }
    }
    
    public int getValue() {
        return this.mAction;
    }
}
