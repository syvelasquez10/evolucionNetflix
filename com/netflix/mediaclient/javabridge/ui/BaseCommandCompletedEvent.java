// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.event.CallbackEvent;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public abstract class BaseCommandCompletedEvent extends JsonBaseNccpEvent implements CallbackEvent
{
    protected static final String PROP_idx = "idx";
    protected static final String PROP_result = "result";
    protected static final String VALUE_COMPLETE_result = "COMPLETE";
    protected boolean failed;
    protected int idx;
    protected String result;
    
    public BaseCommandCompletedEvent(final String s) {
        super(s);
    }
    
    public BaseCommandCompletedEvent(final String s, final JSONObject jsonObject) throws JSONException {
        super(s, jsonObject);
    }
    
    @Override
    public int getCallerId() {
        return this.idx;
    }
    
    public String getResult() {
        return this.result;
    }
    
    @Override
    public boolean isFailed() {
        return this.failed;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.idx = Integer.parseInt(BaseNccpEvent.getString(jsonObject, "idx", null));
        this.failed = !"COMPLETE".equals(BaseNccpEvent.getString(jsonObject, "result", null));
    }
}
