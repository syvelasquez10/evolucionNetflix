// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;

class NativeAnimatedModule$11 implements NativeAnimatedModule$UIThreadOperation
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$animatedNodeTag;
    final /* synthetic */ ReadableMap val$animationConfig;
    final /* synthetic */ int val$animationId;
    final /* synthetic */ Callback val$endCallback;
    
    NativeAnimatedModule$11(final NativeAnimatedModule this$0, final int val$animationId, final int val$animatedNodeTag, final ReadableMap val$animationConfig, final Callback val$endCallback) {
        this.this$0 = this$0;
        this.val$animationId = val$animationId;
        this.val$animatedNodeTag = val$animatedNodeTag;
        this.val$animationConfig = val$animationConfig;
        this.val$endCallback = val$endCallback;
    }
    
    @Override
    public void execute(final NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        nativeAnimatedNodesManager.startAnimatingNode(this.val$animationId, this.val$animatedNodeTag, this.val$animationConfig, this.val$endCallback);
    }
}
