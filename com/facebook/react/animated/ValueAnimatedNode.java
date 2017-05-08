// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

class ValueAnimatedNode extends AnimatedNode
{
    double mOffset;
    double mValue;
    private AnimatedNodeValueListener mValueListener;
    
    public ValueAnimatedNode() {
        this.mValue = Double.NaN;
        this.mOffset = 0.0;
    }
    
    public ValueAnimatedNode(final ReadableMap readableMap) {
        this.mValue = Double.NaN;
        this.mOffset = 0.0;
        this.mValue = readableMap.getDouble("value");
        this.mOffset = readableMap.getDouble("offset");
    }
    
    public void extractOffset() {
        this.mOffset += this.mValue;
        this.mValue = 0.0;
    }
    
    public void flattenOffset() {
        this.mValue += this.mOffset;
        this.mOffset = 0.0;
    }
    
    public double getValue() {
        return this.mOffset + this.mValue;
    }
    
    public void onValueUpdate() {
        if (this.mValueListener == null) {
            return;
        }
        this.mValueListener.onValueUpdate(this.mValue);
    }
    
    public void setValueListener(final AnimatedNodeValueListener mValueListener) {
        this.mValueListener = mValueListener;
    }
}
