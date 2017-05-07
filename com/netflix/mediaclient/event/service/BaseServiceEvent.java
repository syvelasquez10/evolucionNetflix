// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.service;

import com.netflix.mediaclient.event.nrdp.BaseUIEvent;

public abstract class BaseServiceEvent extends BaseUIEvent
{
    public BaseServiceEvent(final String s) {
        super(s);
    }
    
    @Override
    public String getObject() {
        return "nrdp.service";
    }
}
