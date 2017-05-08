// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

class MultiplicationAnimatedNode extends ValueAnimatedNode
{
    private final int[] mInputNodes;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    
    public MultiplicationAnimatedNode(final ReadableMap readableMap, final NativeAnimatedNodesManager mNativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = mNativeAnimatedNodesManager;
        final ReadableArray array = readableMap.getArray("input");
        this.mInputNodes = new int[array.size()];
        for (int i = 0; i < this.mInputNodes.length; ++i) {
            this.mInputNodes[i] = array.getInt(i);
        }
    }
    
    @Override
    public void update() {
        this.mValue = 1.0;
        for (int i = 0; i < this.mInputNodes.length; ++i) {
            final AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNodes[i]);
            if (nodeById == null || !(nodeById instanceof ValueAnimatedNode)) {
                throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.multiply node");
            }
            this.mValue *= ((ValueAnimatedNode)nodeById).getValue();
        }
    }
}
