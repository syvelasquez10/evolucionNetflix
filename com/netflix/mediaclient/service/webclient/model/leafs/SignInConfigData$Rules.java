// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class SignInConfigData$Rules
{
    @SerializedName("fieldType")
    public String fieldType;
    @SerializedName("maxLength")
    public int maxLength;
    @SerializedName("minLength")
    public int minLength;
    final /* synthetic */ SignInConfigData this$0;
    
    public SignInConfigData$Rules(final SignInConfigData this$0) {
        this.this$0 = this$0;
    }
}
