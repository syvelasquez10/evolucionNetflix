// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;

class DiffClampAnimatedNode extends ValueAnimatedNode
{
    private final int mInputNodeTag;
    private double mLastValue;
    private final double mMax;
    private final double mMin;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    
    public DiffClampAnimatedNode(final ReadableMap readableMap, final NativeAnimatedNodesManager mNativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = mNativeAnimatedNodesManager;
        this.mInputNodeTag = readableMap.getInt("input");
        this.mMin = readableMap.getDouble("min");
        this.mMax = readableMap.getDouble("max");
        final double inputNodeValue = this.getInputNodeValue();
        this.mLastValue = inputNodeValue;
        this.mValue = inputNodeValue;
    }
    
    private double getInputNodeValue() {
        final AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNodeTag);
        if (nodeById == null || !(nodeById instanceof ValueAnimatedNode)) {
            throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.DiffClamp node");
        }
        return ((ValueAnimatedNode)nodeById).getValue();
    }
    
    @Override
    public void update() {
        final double inputNodeValue = this.getInputNodeValue();
        final double mLastValue = this.mLastValue;
        this.mLastValue = inputNodeValue;
        this.mValue = Math.min(Math.max(this.mValue + (inputNodeValue - mLastValue), this.mMin), this.mMax);
    }
}
