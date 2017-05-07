// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp;

import org.json.JSONObject;

public abstract class BaseNrdpEvent extends JsonBaseNccpEvent
{
    public BaseNrdpEvent(final String s) {
        super(s);
    }
    
    public BaseNrdpEvent(final String s, final JSONObject jsonObject) {
        super(s, jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp";
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
    }
}
