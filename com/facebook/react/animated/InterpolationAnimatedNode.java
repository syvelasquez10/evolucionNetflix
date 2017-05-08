// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

class InterpolationAnimatedNode extends ValueAnimatedNode
{
    private final String mExtrapolateLeft;
    private final String mExtrapolateRight;
    private final double[] mInputRange;
    private final double[] mOutputRange;
    private ValueAnimatedNode mParent;
    
    public InterpolationAnimatedNode(final ReadableMap readableMap) {
        this.mInputRange = fromDoubleArray(readableMap.getArray("inputRange"));
        this.mOutputRange = fromDoubleArray(readableMap.getArray("outputRange"));
        this.mExtrapolateLeft = readableMap.getString("extrapolateLeft");
        this.mExtrapolateRight = readableMap.getString("extrapolateRight");
    }
    
    private static int findRangeIndex(final double n, final double[] array) {
        int n2;
        for (n2 = 1; n2 < array.length - 1 && array[n2] < n; ++n2) {}
        return n2 - 1;
    }
    
    private static double[] fromDoubleArray(final ReadableArray readableArray) {
        final double[] array = new double[readableArray.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = readableArray.getDouble(i);
        }
        return array;
    }
    
    private static double interpolate(double n, final double n2, final double n3, final double n4, final double n5, final String s, final String s2) {
        if (n < n2) {
            final double n7 = n;
            switch (s) {
                default: {
                    throw new JSApplicationIllegalArgumentException("Invalid extrapolation type " + s + "for left extrapolation");
                }
                case "clamp": {
                    n = n2;
                    break;
                }
                case "extend": {
                    break;
                }
                case "identity": {
                    return n7;
                }
            }
        }
        double n8 = n;
        if (n > n3) {
            n8 = n;
            switch (s2) {
                default: {
                    throw new JSApplicationIllegalArgumentException("Invalid extrapolation type " + s2 + "for right extrapolation");
                }
                case "identity": {
                    return n;
                }
                case "clamp": {
                    n8 = n3;
                }
                case "extend": {
                    break;
                }
            }
        }
        return n4 + (n8 - n2) * (n5 - n4) / (n3 - n2);
    }
    
    static double interpolate(final double n, final double[] array, final double[] array2, final String s, final String s2) {
        final int rangeIndex = findRangeIndex(n, array);
        return interpolate(n, array[rangeIndex], array[rangeIndex + 1], array2[rangeIndex], array2[rangeIndex + 1], s, s2);
    }
    
    @Override
    public void onAttachedToNode(final AnimatedNode animatedNode) {
        if (this.mParent != null) {
            throw new IllegalStateException("Parent already attached");
        }
        if (!(animatedNode instanceof ValueAnimatedNode)) {
            throw new IllegalArgumentException("Parent is of an invalid type");
        }
        this.mParent = (ValueAnimatedNode)animatedNode;
    }
    
    @Override
    public void onDetachedFromNode(final AnimatedNode animatedNode) {
        if (animatedNode != this.mParent) {
            throw new IllegalArgumentException("Invalid parent node provided");
        }
        this.mParent = null;
    }
    
    @Override
    public void update() {
        if (this.mParent == null) {
            throw new IllegalStateException("Trying to update interpolation node that has not been attached to the parent");
        }
        this.mValue = interpolate(this.mParent.getValue(), this.mInputRange, this.mOutputRange, this.mExtrapolateLeft, this.mExtrapolateRight);
    }
}
