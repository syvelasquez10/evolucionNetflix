// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class VoipAuthorization
{
    @SerializedName("expirationTime")
    private long expirationTime;
    @SerializedName("userEncToken")
    private String userEncToken;
    @SerializedName("userToken")
    private String userToken;
    
    public VoipAuthorization() {
        this.expirationTime = Long.MAX_VALUE;
    }
    
    public long getExpirationTime() {
        return this.expirationTime;
    }
    
    public String getUserEncToken() {
        return this.userEncToken;
    }
    
    public String getUserToken() {
        return this.userToken;
    }
    
    @Override
    public String toString() {
        return "VoipAuthorization{userToken='" + this.userToken + '\'' + ", userEncToken='" + this.userEncToken + '\'' + ", expirationTime=" + this.expirationTime + '}';
    }
}
