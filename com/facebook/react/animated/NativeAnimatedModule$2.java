// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

class NativeAnimatedModule$2 implements NativeAnimatedModule$UIThreadOperation
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ ReadableMap val$config;
    final /* synthetic */ int val$tag;
    
    NativeAnimatedModule$2(final NativeAnimatedModule this$0, final int val$tag, final ReadableMap val$config) {
        this.this$0 = this$0;
        this.val$tag = val$tag;
        this.val$config = val$config;
    }
    
    @Override
    public void execute(final NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        nativeAnimatedNodesManager.createAnimatedNode(this.val$tag, this.val$config);
    }
}
