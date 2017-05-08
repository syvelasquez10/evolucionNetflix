// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.art;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;
import android.graphics.Region$Op;
import android.graphics.Paint;
import android.graphics.Canvas;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import android.graphics.RectF;

public class ARTGroupShadowNode extends ARTVirtualNode
{
    protected RectF mClipping;
    
    private static RectF createClipping(final float[] array) {
        if (array.length != 4) {
            throw new JSApplicationIllegalArgumentException("Clipping should be array of length 4 (e.g. [x, y, width, height])");
        }
        return new RectF(array[0], array[1], array[0] + array[2], array[1] + array[3]);
    }
    
    @Override
    public void draw(final Canvas canvas, final Paint paint, float n) {
        n *= this.mOpacity;
        if (n > 0.01f) {
            this.saveAndSetupCanvas(canvas);
            if (this.mClipping != null) {
                canvas.clipRect(this.mScale * this.mClipping.left, this.mScale * this.mClipping.top, this.mScale * this.mClipping.right, this.mScale * this.mClipping.bottom, Region$Op.REPLACE);
            }
            for (int i = 0; i < this.getChildCount(); ++i) {
                final ARTVirtualNode artVirtualNode = (ARTVirtualNode)this.getChildAt(i);
                artVirtualNode.draw(canvas, paint, n);
                artVirtualNode.markUpdateSeen();
            }
            this.restoreCanvas(canvas);
        }
    }
    
    @Override
    public boolean isVirtual() {
        return true;
    }
    
    @ReactProp(name = "clipping")
    public void setClipping(final ReadableArray readableArray) {
        final float[] floatArray = PropHelper.toFloatArray(readableArray);
        if (floatArray != null) {
            this.mClipping = createClipping(floatArray);
            this.markUpdated();
        }
    }
}
