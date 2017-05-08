// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

class NativeAnimatedModule$5 implements NativeAnimatedModule$UIThreadOperation
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$tag;
    
    NativeAnimatedModule$5(final NativeAnimatedModule this$0, final int val$tag) {
        this.this$0 = this$0;
        this.val$tag = val$tag;
    }
    
    @Override
    public void execute(final NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        nativeAnimatedNodesManager.stopListeningToAnimatedNodeValue(this.val$tag);
    }
}
