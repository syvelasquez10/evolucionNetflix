// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.Context;

public final class Session$Builder
{
    private String applicationId;
    private final Context context;
    private TokenCachingStrategy tokenCachingStrategy;
    
    public Session$Builder(final Context context) {
        this.context = context;
    }
    
    public Session build() {
        return new Session(this.context, this.applicationId, this.tokenCachingStrategy);
    }
    
    public Session$Builder setApplicationId(final String applicationId) {
        this.applicationId = applicationId;
        return this;
    }
}
