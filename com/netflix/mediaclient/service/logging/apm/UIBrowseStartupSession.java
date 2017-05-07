// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.apm.model.UIBrowseStartupSessionEndedEvent;
import com.netflix.mediaclient.service.logging.client.model.UIError;

public final class UIBrowseStartupSession extends BaseApmSession
{
    public static final String NAME = "uiBrowseStartup";
    private static final String TAG = "UIBrowseStartupSession";
    
    public UIBrowseStartupSessionEndedEvent createEndedEvent(final long n, final boolean success, final UIError error) {
        final UIBrowseStartupSessionEndedEvent uiBrowseStartupSessionEndedEvent = new UIBrowseStartupSessionEndedEvent(System.currentTimeMillis() - this.mStarted, n);
        uiBrowseStartupSessionEndedEvent.setCategory(this.getCategory());
        uiBrowseStartupSessionEndedEvent.setSessionId(this.mId);
        uiBrowseStartupSessionEndedEvent.setError(error);
        uiBrowseStartupSessionEndedEvent.setSuccess(success);
        if (Log.isLoggable()) {
            Log.v("UIBrowseStartupSession", "createEndedEvent: " + uiBrowseStartupSessionEndedEvent);
        }
        return uiBrowseStartupSessionEndedEvent;
    }
    
    @Override
    public String getName() {
        return "uiBrowseStartup";
    }
}
