// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class VoipAuthorizationData
{
    @SerializedName("nonMemberVoipAuthorization")
    private VoipAuthorization nonMemberVoipAuthorization;
    @SerializedName("userVoipAuthorization")
    private VoipAuthorization userVoipAuthorization;
    
    public VoipAuthorization getNonMemberVoipAuthorization() {
        return this.nonMemberVoipAuthorization;
    }
    
    public VoipAuthorization getUserVoipAuthorization() {
        return this.userVoipAuthorization;
    }
    
    @Override
    public String toString() {
        return "VoipAuthorizationData{userVoipAuthorization=" + this.userVoipAuthorization + ", nonMemberVoipAuthorization=" + this.nonMemberVoipAuthorization + '}';
    }
}
