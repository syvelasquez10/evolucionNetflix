// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp;

import org.json.JSONObject;

public class BindEvent extends BaseNrdpEvent
{
    public static final String TYPE = "bind";
    
    public BindEvent(final JSONObject jsonObject) {
        super("bind", jsonObject);
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
    }
}
