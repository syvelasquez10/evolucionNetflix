// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.javabridge.ui.EventListener;

class UserAgent$DeactivateListener implements EventListener
{
    final /* synthetic */ UserAgent this$0;
    
    private UserAgent$DeactivateListener(final UserAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void received(final UIEvent uiEvent) {
        Log.d("nf_service_useragent", "Received a deactivate event ");
    }
}
