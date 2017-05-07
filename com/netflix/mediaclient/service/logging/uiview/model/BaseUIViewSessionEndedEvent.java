// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.uiview.model;

import com.netflix.mediaclient.service.logging.client.model.DeviceUniqueId;
import com.netflix.mediaclient.service.logging.client.model.SessionEndedEvent;

public abstract class BaseUIViewSessionEndedEvent extends SessionEndedEvent
{
    public BaseUIViewSessionEndedEvent(final String s, final DeviceUniqueId deviceUniqueId, final long n) {
        super(s, deviceUniqueId, n);
    }
}
