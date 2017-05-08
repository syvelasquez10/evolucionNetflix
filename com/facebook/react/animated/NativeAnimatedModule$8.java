// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

class NativeAnimatedModule$8 implements NativeAnimatedModule$UIThreadOperation
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$tag;
    final /* synthetic */ double val$value;
    
    NativeAnimatedModule$8(final NativeAnimatedModule this$0, final int val$tag, final double val$value) {
        this.this$0 = this$0;
        this.val$tag = val$tag;
        this.val$value = val$value;
    }
    
    @Override
    public void execute(final NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        nativeAnimatedNodesManager.setAnimatedNodeOffset(this.val$tag, this.val$value);
    }
}
