// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.slider;

import com.facebook.yoga.YogaMeasureOutput;
import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.react.uimanager.LayoutShadowNode;

class ReactSliderManager$ReactSliderShadowNode extends LayoutShadowNode implements YogaMeasureFunction
{
    private int mHeight;
    private boolean mMeasured;
    private int mWidth;
    
    private ReactSliderManager$ReactSliderShadowNode() {
        this.setMeasureFunction(this);
    }
    
    @Override
    public long measure(final YogaNodeAPI yogaNodeAPI, final float n, final YogaMeasureMode yogaMeasureMode, final float n2, final YogaMeasureMode yogaMeasureMode2) {
        if (!this.mMeasured) {
            final ReactSlider reactSlider = new ReactSlider((Context)this.getThemedContext(), null, 16842875);
            final int measureSpec = View$MeasureSpec.makeMeasureSpec(-2, 0);
            reactSlider.measure(measureSpec, measureSpec);
            this.mWidth = reactSlider.getMeasuredWidth();
            this.mHeight = reactSlider.getMeasuredHeight();
            this.mMeasured = true;
        }
        return YogaMeasureOutput.make(this.mWidth, this.mHeight);
    }
}
