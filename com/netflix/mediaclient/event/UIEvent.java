// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event;

import org.json.JSONObject;

public interface UIEvent
{
    JSONObject getData();
    
    String getName();
    
    int getNrdSource();
    
    String getObject();
    
    String getType();
}
