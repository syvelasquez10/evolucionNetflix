// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

public class SignInConfigData$CachedMode
{
    @SerializedName("cached")
    public HashMap<String, Object> cached;
    @SerializedName("hidden")
    public boolean hidden;
    final /* synthetic */ SignInConfigData this$0;
    
    public SignInConfigData$CachedMode(final SignInConfigData this$0) {
        this.this$0 = this$0;
    }
}
