// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

class NativeAnimatedModule$15 implements NativeAnimatedModule$UIThreadOperation
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$animatedNodeTag;
    final /* synthetic */ int val$viewTag;
    
    NativeAnimatedModule$15(final NativeAnimatedModule this$0, final int val$animatedNodeTag, final int val$viewTag) {
        this.this$0 = this$0;
        this.val$animatedNodeTag = val$animatedNodeTag;
        this.val$viewTag = val$viewTag;
    }
    
    @Override
    public void execute(final NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        nativeAnimatedNodesManager.connectAnimatedNodeToView(this.val$animatedNodeTag, this.val$viewTag);
    }
}
