// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

final class LikeActionController$3 implements Runnable
{
    final /* synthetic */ LikeActionController$CreationCallback val$callback;
    final /* synthetic */ LikeActionController val$controller;
    
    LikeActionController$3(final LikeActionController$CreationCallback val$callback, final LikeActionController val$controller) {
        this.val$callback = val$callback;
        this.val$controller = val$controller;
    }
    
    @Override
    public void run() {
        this.val$callback.onComplete(this.val$controller);
    }
}
