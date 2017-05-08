// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

class NativeAnimatedModule$13 implements NativeAnimatedModule$UIThreadOperation
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$childNodeTag;
    final /* synthetic */ int val$parentNodeTag;
    
    NativeAnimatedModule$13(final NativeAnimatedModule this$0, final int val$parentNodeTag, final int val$childNodeTag) {
        this.this$0 = this$0;
        this.val$parentNodeTag = val$parentNodeTag;
        this.val$childNodeTag = val$childNodeTag;
    }
    
    @Override
    public void execute(final NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        nativeAnimatedNodesManager.connectAnimatedNodes(this.val$parentNodeTag, this.val$childNodeTag);
    }
}
