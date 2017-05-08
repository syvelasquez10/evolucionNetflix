// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import com.netflix.mediaclient.StatusCode;

public enum ClientActionFromLase
{
    ACQUIRE_NEW_LICENSE(1), 
    DELETE_LICENSES(2), 
    MARK_PLAYABLE(3), 
    NO_ACTION(0);
    
    private int mAction;
    
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
    
    private boolean isActionAcquireNewLicense() {
        return this.mAction == ClientActionFromLase.DELETE_LICENSES.getValue();
    }
    
    public StatusCode getStatusCode() {
        if (this.isActionAcquireNewLicense()) {
            return StatusCode.OFFLINE_LICENSE_FETCH_NEW;
        }
        return StatusCode.OK;
    }
    
    public int getValue() {
        return this.mAction;
    }
    
    public boolean isRecoverable() {
        return this.isActionAcquireNewLicense();
    }
}
