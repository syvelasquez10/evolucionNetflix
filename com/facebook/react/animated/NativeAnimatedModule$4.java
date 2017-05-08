// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

class NativeAnimatedModule$4 implements NativeAnimatedModule$UIThreadOperation
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ AnimatedNodeValueListener val$listener;
    final /* synthetic */ int val$tag;
    
    NativeAnimatedModule$4(final NativeAnimatedModule this$0, final int val$tag, final AnimatedNodeValueListener val$listener) {
        this.this$0 = this$0;
        this.val$tag = val$tag;
        this.val$listener = val$listener;
    }
    
    @Override
    public void execute(final NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        nativeAnimatedNodesManager.startListeningToAnimatedNodeValue(this.val$tag, this.val$listener);
    }
}
