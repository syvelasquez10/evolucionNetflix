// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.art;

import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;
import com.facebook.yoga.YogaMeasureFunction;

final class ARTSurfaceViewManager$1 implements YogaMeasureFunction
{
    @Override
    public long measure(final YogaNodeAPI yogaNodeAPI, final float n, final YogaMeasureMode yogaMeasureMode, final float n2, final YogaMeasureMode yogaMeasureMode2) {
        throw new IllegalStateException("SurfaceView should have explicit width and height set");
    }
}
