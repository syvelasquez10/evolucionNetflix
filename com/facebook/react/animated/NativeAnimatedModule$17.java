// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

class NativeAnimatedModule$17 implements NativeAnimatedModule$UIThreadOperation
{
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ ReadableMap val$eventMapping;
    final /* synthetic */ String val$eventName;
    final /* synthetic */ int val$viewTag;
    
    NativeAnimatedModule$17(final NativeAnimatedModule this$0, final int val$viewTag, final String val$eventName, final ReadableMap val$eventMapping) {
        this.this$0 = this$0;
        this.val$viewTag = val$viewTag;
        this.val$eventName = val$eventName;
        this.val$eventMapping = val$eventMapping;
    }
    
    @Override
    public void execute(final NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        nativeAnimatedNodesManager.addAnimatedEventToView(this.val$viewTag, this.val$eventName, this.val$eventMapping);
    }
}
