// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import org.json.JSONObject;

public class ExceptionClEvent extends Event
{
    public static final String CATEGORY_VALUE = "error";
    public static final String NAME_VALUE = "ExceptionLogCl";
    private Error mError;
    
    public ExceptionClEvent(final Error mError) {
        this.mError = null;
        this.mError = mError;
        this.name = "ExceptionLogCl";
        this.category = "error";
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        if (this.mError != null) {
            data.put("errorObj", (Object)this.mError.toJSONObject());
        }
        return data;
    }
}
