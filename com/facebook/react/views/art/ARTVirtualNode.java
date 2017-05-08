// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.art;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.annotations.ReactProp;
import android.graphics.Paint;
import android.graphics.Canvas;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import android.graphics.Matrix;
import com.facebook.react.uimanager.ReactShadowNode;

public abstract class ARTVirtualNode extends ReactShadowNode
{
    private static final float[] sMatrixData;
    private static final float[] sRawMatrix;
    private Matrix mMatrix;
    protected float mOpacity;
    protected final float mScale;
    
    static {
        sMatrixData = new float[9];
        sRawMatrix = new float[9];
    }
    
    public ARTVirtualNode() {
        this.mOpacity = 1.0f;
        this.mMatrix = new Matrix();
        this.mScale = DisplayMetricsHolder.getWindowDisplayMetrics().density;
    }
    
    public abstract void draw(final Canvas p0, final Paint p1, final float p2);
    
    @Override
    public boolean isVirtual() {
        return true;
    }
    
    protected void restoreCanvas(final Canvas canvas) {
        canvas.restore();
    }
    
    protected final void saveAndSetupCanvas(final Canvas canvas) {
        canvas.save();
        if (this.mMatrix != null) {
            canvas.concat(this.mMatrix);
        }
    }
    
    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(final float mOpacity) {
        this.mOpacity = mOpacity;
        this.markUpdated();
    }
    
    @ReactProp(name = "transform")
    public void setTransform(final ReadableArray readableArray) {
        if (readableArray != null) {
            final int floatArray = PropHelper.toFloatArray(readableArray, ARTVirtualNode.sMatrixData);
            if (floatArray == 6) {
                this.setupMatrix();
            }
            else if (floatArray != -1) {
                throw new JSApplicationIllegalArgumentException("Transform matrices must be of size 6");
            }
        }
        else {
            this.mMatrix = null;
        }
        this.markUpdated();
    }
    
    protected void setupMatrix() {
        ARTVirtualNode.sRawMatrix[0] = ARTVirtualNode.sMatrixData[0];
        ARTVirtualNode.sRawMatrix[1] = ARTVirtualNode.sMatrixData[2];
        ARTVirtualNode.sRawMatrix[2] = ARTVirtualNode.sMatrixData[4] * this.mScale;
        ARTVirtualNode.sRawMatrix[3] = ARTVirtualNode.sMatrixData[1];
        ARTVirtualNode.sRawMatrix[4] = ARTVirtualNode.sMatrixData[3];
        ARTVirtualNode.sRawMatrix[5] = ARTVirtualNode.sMatrixData[5] * this.mScale;
        ARTVirtualNode.sRawMatrix[6] = 0.0f;
        ARTVirtualNode.sRawMatrix[7] = 0.0f;
        ARTVirtualNode.sRawMatrix[8] = 1.0f;
        if (this.mMatrix == null) {
            this.mMatrix = new Matrix();
        }
        this.mMatrix.setValues(ARTVirtualNode.sRawMatrix);
    }
}
