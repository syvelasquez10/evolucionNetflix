// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.progressbar;

import com.facebook.react.uimanager.annotations.ReactProp;
import android.widget.ProgressBar;
import com.facebook.yoga.YogaMeasureOutput;
import android.view.View$MeasureSpec;
import android.content.Context;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;
import java.util.HashSet;
import java.util.Set;
import android.util.SparseIntArray;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.react.uimanager.LayoutShadowNode;

public class ProgressBarShadowNode extends LayoutShadowNode implements YogaMeasureFunction
{
    private final SparseIntArray mHeight;
    private final Set<Integer> mMeasured;
    private String mStyle;
    private final SparseIntArray mWidth;
    
    public ProgressBarShadowNode() {
        this.mStyle = "Normal";
        this.mHeight = new SparseIntArray();
        this.mWidth = new SparseIntArray();
        this.mMeasured = new HashSet<Integer>();
        this.setMeasureFunction(this);
    }
    
    public String getStyle() {
        return this.mStyle;
    }
    
    @Override
    public long measure(final YogaNodeAPI yogaNodeAPI, final float n, final YogaMeasureMode yogaMeasureMode, final float n2, final YogaMeasureMode yogaMeasureMode2) {
        final int styleFromString = ReactProgressBarViewManager.getStyleFromString(this.getStyle());
        if (!this.mMeasured.contains(styleFromString)) {
            final ProgressBar progressBar = ReactProgressBarViewManager.createProgressBar((Context)this.getThemedContext(), styleFromString);
            final int measureSpec = View$MeasureSpec.makeMeasureSpec(-2, 0);
            progressBar.measure(measureSpec, measureSpec);
            this.mHeight.put(styleFromString, progressBar.getMeasuredHeight());
            this.mWidth.put(styleFromString, progressBar.getMeasuredWidth());
            this.mMeasured.add(styleFromString);
        }
        return YogaMeasureOutput.make(this.mWidth.get(styleFromString), this.mHeight.get(styleFromString));
    }
    
    @ReactProp(name = "styleAttr")
    public void setStyle(final String s) {
        String mStyle = s;
        if (s == null) {
            mStyle = "Normal";
        }
        this.mStyle = mStyle;
    }
}
