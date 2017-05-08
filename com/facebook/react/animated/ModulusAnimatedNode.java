// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;

class ModulusAnimatedNode extends ValueAnimatedNode
{
    private final int mInputNode;
    private final int mModulus;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    
    public ModulusAnimatedNode(final ReadableMap readableMap, final NativeAnimatedNodesManager mNativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = mNativeAnimatedNodesManager;
        this.mInputNode = readableMap.getInt("input");
        this.mModulus = readableMap.getInt("modulus");
    }
    
    @Override
    public void update() {
        final AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNode);
        if (nodeById != null && nodeById instanceof ValueAnimatedNode) {
            this.mValue = ((ValueAnimatedNode)nodeById).mValue % this.mModulus;
            return;
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.modulus node");
    }
}
