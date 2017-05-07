// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class User$Summary
{
    private String email;
    private String firstName;
    private String lastName;
    final /* synthetic */ User this$0;
    @SerializedName("userId")
    private String userToken;
    
    public User$Summary(final User this$0) {
        this.this$0 = this$0;
    }
}
