// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.partner.playbilling;

import org.json.JSONObject;

public abstract class PlayBillingCallback
{
    private final String callback;
    
    public PlayBillingCallback(final String callback) {
        this.callback = callback;
    }
    
    public String getCallback() {
        return this.callback;
    }
    
    public abstract void onResult(final JSONObject p0);
}
