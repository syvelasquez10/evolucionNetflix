// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

final class TransformHelper$1 extends ThreadLocal<double[]>
{
    @Override
    protected double[] initialValue() {
        return new double[16];
    }
}
