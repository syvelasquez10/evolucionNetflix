// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.google.gson.annotations.SerializedName;

public class SignInData$EdgeFields
{
    @SerializedName("flwssn")
    public String flwssn;
    @SerializedName("netflixId")
    public String netflixId;
    @SerializedName("secureNetflixId")
    public String secureNetflixId;
    final /* synthetic */ SignInData this$0;
    @SerializedName("updateNetflixId")
    public boolean updateNetflixId;
    
    public SignInData$EdgeFields(final SignInData this$0) {
        this.this$0 = this$0;
    }
}
