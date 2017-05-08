// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.volley;

import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.mediaclient.servicemgr.IMSLClient$MSLUserCredentialRegistry;

class FalkorMSLVolleyRequest$1 implements IMSLClient$MSLUserCredentialRegistry
{
    final /* synthetic */ FalkorMSLVolleyRequest this$0;
    final /* synthetic */ UserAuthenticationData val$authenticationData;
    final /* synthetic */ String val$userId;
    
    FalkorMSLVolleyRequest$1(final FalkorMSLVolleyRequest this$0, final String val$userId, final UserAuthenticationData val$authenticationData) {
        this.this$0 = this$0;
        this.val$userId = val$userId;
        this.val$authenticationData = val$authenticationData;
    }
    
    @Override
    public UserAuthenticationData getUserAuthenticationData() {
        return this.val$authenticationData;
    }
    
    @Override
    public String getUserId() {
        return this.val$userId;
    }
    
    @Override
    public void updateApiEndpointHost(final String s) {
    }
}
