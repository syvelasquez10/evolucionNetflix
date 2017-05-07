// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

class Settings$2$1 implements Runnable
{
    final /* synthetic */ Settings$2 this$0;
    final /* synthetic */ Response val$response;
    
    Settings$2$1(final Settings$2 this$0, final Response val$response) {
        this.this$0 = this$0;
        this.val$response = val$response;
    }
    
    @Override
    public void run() {
        this.this$0.val$callback.onCompleted(this.val$response);
    }
}
