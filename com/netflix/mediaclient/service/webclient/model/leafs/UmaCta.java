// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.TypeAdapter;
import com.google.gson.Gson;

public abstract class UmaCta
{
    public static final String ACTION_NOT_NOW = "NOT_NOW";
    public static final String ACTION_TYPE_LINK = "LINK";
    public static final String ACTION_TYPE_UMS_IMPRESSION = "UMS_IMPRESSION";
    public static final String CALLBACK_ACKNOWLEDGED = "ACKNOWLEDGED";
    
    public static TypeAdapter<UmaCta> typeAdapter(final Gson gson) {
        return new AutoValue_UmaCta$GsonTypeAdapter(gson);
    }
    
    public abstract String action();
    
    public abstract String actionType();
    
    public abstract boolean autoLogin();
    
    public abstract String callback();
    
    public abstract boolean selected();
    
    public abstract String text();
    
    public abstract String trackingInfo();
}
