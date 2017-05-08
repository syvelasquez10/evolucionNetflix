// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.RectF;

public interface TransformCallback
{
    void getRootBounds(final RectF p0);
    
    void getTransform(final Matrix p0);
}
