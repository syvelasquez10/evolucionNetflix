// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.TypeAdapter;
import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;

public abstract class UmaAlert
{
    public static final String ACTION_UMA_MESSAGE_CONSUMED = "RefreshUserMessageRequest.ACTION_UMA_MESSAGE_CONSUMED";
    public static final String ACTION_UMA_MESSAGE_UPDATED = "RefreshUserMessageRequest.ACTION_UMA_MESSAGE_UPDATED";
    static final String FIELD_UMA = "umaAlert";
    public static final String TYPE_ERROR = "ERROR";
    public static final String TYPE_INFO = "INFO";
    public static final String TYPE_WARN = "WARN";
    private static final long UMA_STALE_TIMEOUT_MILLIS;
    private boolean mConsumed;
    
    static {
        UMA_STALE_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(10L);
    }
    
    public UmaAlert() {
        this.mConsumed = false;
    }
    
    public static TypeAdapter<UmaAlert> typeAdapter(final Gson gson) {
        return new AutoValue_UmaAlert$GsonTypeAdapter(gson);
    }
    
    public abstract int abTestCell();
    
    public abstract int abTestId();
    
    public abstract boolean blocking();
    
    public abstract String body();
    
    public abstract UmaCta cta1();
    
    public abstract UmaCta cta2();
    
    public boolean isConsumed() {
        return this.mConsumed;
    }
    
    public boolean isStale() {
        return System.currentTimeMillis() - this.timestamp() > UmaAlert.UMA_STALE_TIMEOUT_MILLIS;
    }
    
    public abstract String locale();
    
    public abstract long messageId();
    
    public abstract String messageName();
    
    public void setConsumed(final boolean mConsumed) {
        this.mConsumed = mConsumed;
    }
    
    public abstract long timestamp();
    
    public abstract String title();
    
    public abstract String trackingInfo();
    
    public abstract String viewType();
}
