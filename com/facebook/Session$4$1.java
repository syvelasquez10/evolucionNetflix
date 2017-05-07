// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

class Session$4$1 implements Runnable
{
    final /* synthetic */ Session$4 this$1;
    final /* synthetic */ Session$StatusCallback val$callback;
    
    Session$4$1(final Session$4 this$1, final Session$StatusCallback val$callback) {
        this.this$1 = this$1;
        this.val$callback = val$callback;
    }
    
    @Override
    public void run() {
        this.val$callback.call(this.this$1.this$0, this.this$1.val$newState, this.this$1.val$exception);
    }
}
