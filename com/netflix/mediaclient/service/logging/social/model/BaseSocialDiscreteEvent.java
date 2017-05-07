// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.social.model;

import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public abstract class BaseSocialDiscreteEvent extends DiscreteEvent
{
    protected static final String CATEGORY = "social";
    protected static final String CHANNEL = "channel";
    protected static final String ERROR = "error";
    protected static final String GUID = "originatingRequestGuid";
    protected static final String SOURCE = "source";
    protected static final String SUCCESS = "success";
    protected static final String TRACK_ID = "trackId";
    protected static final String VIEW = "view";
    
    BaseSocialDiscreteEvent(final String name) {
        this.name = name;
        this.category = "social";
    }
}
