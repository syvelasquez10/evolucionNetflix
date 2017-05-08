// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.TypeAdapter;
import com.google.gson.Gson;

public abstract class ThumbMessaging
{
    public static ThumbMessaging$Builder builder() {
        return new $AutoValue_ThumbMessaging$Builder();
    }
    
    public static TypeAdapter<ThumbMessaging> typeAdapter(final Gson gson) {
        return new AutoValue_ThumbMessaging$GsonTypeAdapter(gson);
    }
    
    public abstract boolean shouldShowFirstThumbsRatingMessage();
    
    public abstract boolean shouldShowOneTimeProfileThumbsMessage();
}
