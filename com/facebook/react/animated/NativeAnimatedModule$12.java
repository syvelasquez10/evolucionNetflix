// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

class NativeAnimatedModule$12 implements NativeAnimatedModule$UIThreadOperation
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$animationId;
    
    NativeAnimatedModule$12(final NativeAnimatedModule this$0, final int val$animationId) {
        this.this$0 = this$0;
        this.val$animationId = val$animationId;
    }
    
    @Override
    public void execute(final NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        nativeAnimatedNodesManager.stopAnimation(this.val$animationId);
    }
}
