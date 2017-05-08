// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.switchview;

import com.facebook.yoga.YogaMeasureOutput;
import android.view.View$MeasureSpec;
import android.content.Context;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.react.uimanager.LayoutShadowNode;

class ReactSwitchManager$ReactSwitchShadowNode extends LayoutShadowNode implements YogaMeasureFunction
{
    private int mHeight;
    private boolean mMeasured;
    private int mWidth;
    
    private ReactSwitchManager$ReactSwitchShadowNode() {
        this.setMeasureFunction(this);
    }
    
    @Override
    public long measure(final YogaNodeAPI yogaNodeAPI, final float n, final YogaMeasureMode yogaMeasureMode, final float n2, final YogaMeasureMode yogaMeasureMode2) {
        if (!this.mMeasured) {
            final ReactSwitch reactSwitch = new ReactSwitch((Context)this.getThemedContext());
            final int measureSpec = View$MeasureSpec.makeMeasureSpec(-2, 0);
            reactSwitch.measure(measureSpec, measureSpec);
            this.mWidth = reactSwitch.getMeasuredWidth();
            this.mHeight = reactSwitch.getMeasuredHeight();
            this.mMeasured = true;
        }
        return YogaMeasureOutput.make(this.mWidth, this.mHeight);
    }
}
