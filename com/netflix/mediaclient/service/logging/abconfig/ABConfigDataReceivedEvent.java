// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.abconfig;

import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class ABConfigDataReceivedEvent extends DiscreteEvent
{
    private static final String NAME = "abConfigDataReceived";
    
    public ABConfigDataReceivedEvent() {
        this.category = "abTest";
        this.name = "abConfigDataReceived";
    }
}
