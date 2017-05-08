// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

class ReactContext$1 implements Runnable
{
    final /* synthetic */ ReactContext this$0;
    final /* synthetic */ LifecycleEventListener val$listener;
    
    ReactContext$1(final ReactContext this$0, final LifecycleEventListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    @Override
    public void run() {
        this.val$listener.onHostResume();
    }
}
