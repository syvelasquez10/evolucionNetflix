// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

class NativeAnimatedModule$18 implements NativeAnimatedModule$UIThreadOperation
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ String val$eventName;
    final /* synthetic */ int val$viewTag;
    
    NativeAnimatedModule$18(final NativeAnimatedModule this$0, final int val$viewTag, final String val$eventName) {
        this.this$0 = this$0;
        this.val$viewTag = val$viewTag;
        this.val$eventName = val$eventName;
    }
    
    @Override
    public void execute(final NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        nativeAnimatedNodesManager.removeAnimatedEventFromView(this.val$viewTag, this.val$eventName);
    }
}
