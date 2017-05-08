// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.service.webclient.model.leafs.SignInData;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import com.netflix.mediaclient.android.app.Status;

public interface ConfigurationAgentWebCallback
{
    void onAllocateABTestCompleted(final Status p0);
    
    void onConfigDataFetched(final ConfigData p0, final Status p1);
    
    void onLoginVerified(final SignInData p0, final Status p1);
}
