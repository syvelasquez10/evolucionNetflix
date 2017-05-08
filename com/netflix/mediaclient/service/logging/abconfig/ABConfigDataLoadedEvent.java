// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.abconfig;

import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class ABConfigDataLoadedEvent extends DiscreteEvent
{
    private static final String NAME = "abConfigDataLoaded";
    
    public ABConfigDataLoadedEvent() {
        this.category = "abTest";
        this.name = "abConfigDataLoaded";
    }
}
