// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class SignInConfigData$Fields
{
    @SerializedName("backAction")
    public SignInConfigData$CachedMode backAction;
    @SerializedName("email")
    public SignInConfigData$Rules email;
    @SerializedName("password")
    public SignInConfigData$Rules password;
    final /* synthetic */ SignInConfigData this$0;
    
    public SignInConfigData$Fields(final SignInConfigData this$0) {
        this.this$0 = this$0;
    }
}
