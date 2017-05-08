// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

public class MatrixMathHelper$MatrixDecompositionContext
{
    double[] perspective;
    double[] quaternion;
    double[] rotationDegrees;
    double[] scale;
    double[] skew;
    double[] translation;
    
    public MatrixMathHelper$MatrixDecompositionContext() {
        this.perspective = new double[4];
        this.quaternion = new double[4];
        this.scale = new double[3];
        this.skew = new double[3];
        this.translation = new double[3];
        this.rotationDegrees = new double[3];
    }
}
