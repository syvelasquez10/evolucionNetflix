// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.registration;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;

public class DeactivatedEvent extends BaseRegistrationEvent
{
    public static final String NAME = "deactivate";
    private static final String PROP_accountKey = "accountKey";
    private static final String PROP_current = "current";
    public static final String TYPE = "deactivated";
    private String accountKey;
    private boolean current;
    
    public DeactivatedEvent(final JSONObject jsonObject) throws JSONException {
        super("deactivated", jsonObject);
    }
    
    public String getAccountKey() {
        return this.accountKey;
    }
    
    @Override
    public String getName() {
        return "deactivate";
    }
    
    public boolean isCurrent() {
        return this.current;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.accountKey = BaseNccpEvent.getString(jsonObject, "accountKey", null);
        this.current = BaseNccpEvent.getBoolean(jsonObject, "current", false);
    }
}
