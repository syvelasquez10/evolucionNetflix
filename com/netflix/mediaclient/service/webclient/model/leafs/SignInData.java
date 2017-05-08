// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.annotations.SerializedName;

public class SignInData
{
    public static final String FIELD_EDGE_FIELDS = "edgeFields";
    public static final String FIELD_FLOW = "flow";
    public static final String FIELD_FLWSSN = "flwssn";
    public static final String FIELD_MODE = "mode";
    public static final String FIELD_NFLXID = "netflixId";
    public static final String FIELD_SECURE_NFLXID = "secureNetflixId";
    public static final String FIELD_UPDATE = "updateNetflixId";
    public static final String FLOW_CLIENT = "client";
    public static final String MODE_ENTER_CREDENTIALS = "enterMemberCredentials";
    public static final String MODE_MEMBER_HOME = "memberHome";
    public static final String MODE_WELCOME = "welcome";
    public static final String TAG = "nf_signin";
    @SerializedName("edgeFields")
    public SignInData$EdgeFields edgeFields;
    @SerializedName("flow")
    public String flow;
    @SerializedName("flwssn")
    public String flwssn;
    @SerializedName("mode")
    public String mode;
    
    public boolean isSignInSuccessful() {
        return StringUtils.safeEquals("client", this.flow) && StringUtils.safeEquals("memberHome", this.mode) && this.edgeFields != null && StringUtils.isNotEmpty(this.edgeFields.netflixId) && StringUtils.isNotEmpty(this.edgeFields.secureNetflixId);
    }
    
    public boolean shouldRetrySignIn() {
        return StringUtils.safeEquals("enterMemberCredentials", this.mode);
    }
    
    public boolean shouldTrySignUp() {
        return StringUtils.safeEquals("welcome", this.mode);
    }
}
