// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.javabridge.ui.EventListener;

class NrdController$1 implements EventListener
{
    final /* synthetic */ NrdController this$0;
    
    NrdController$1(final NrdController this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void received(final UIEvent uiEvent) {
        new BackgroundTask().execute(new NrdController$1$1(this, this));
    }
}
