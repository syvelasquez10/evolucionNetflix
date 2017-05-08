// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import org.json.JSONObject;

public interface Logblob
{
    long getClientEpoch();
    
    Logblob$Severity getSeverity();
    
    String getType();
    
    boolean isMandatory();
    
    boolean shouldSendNow();
    
    JSONObject toJson();
    
    String toJsonString();
}
