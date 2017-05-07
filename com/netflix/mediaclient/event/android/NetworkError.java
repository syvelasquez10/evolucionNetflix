// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.android;

import org.json.JSONObject;
import com.netflix.mediaclient.event.nrdp.media.NccpError;

public class NetworkError extends NccpError
{
    @Override
    public String getObject() {
        return "nrdp.android";
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
    }
}
