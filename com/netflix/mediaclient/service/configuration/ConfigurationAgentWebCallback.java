// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;

public interface ConfigurationAgentWebCallback
{
    void onConfigDataFetched(final ConfigData p0, final Status p1);
}
