// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp;

import org.json.JSONObject;

public abstract class JsonBaseNccpEvent extends BaseNccpEvent
{
    protected static final String TAG = "nf_event";
    protected JSONObject json;
    
    public JsonBaseNccpEvent(final String s) {
        super(s);
    }
    
    public JsonBaseNccpEvent(final String s, final JSONObject json) {
        super(s);
        if (json == null) {
            throw new IllegalArgumentException("JSON is null");
        }
        this.populate(this.json = json);
    }
    
    @Override
    public JSONObject getData() {
        return this.json;
    }
    
    @Override
    protected String getJSON() {
        return this.json.toString();
    }
    
    @Override
    public String getName() {
        return this.getType();
    }
    
    @Override
    public int getNrdSource() {
        return 0;
    }
    
    protected abstract void populate(final JSONObject p0);
}
