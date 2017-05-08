// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.react.views.view.MeasureUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.infer.annotation.Assertions;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;
import android.os.Build$VERSION;
import android.widget.EditText;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.react.views.text.ReactTextShadowNode;

public class ReactTextInputShadowNode extends ReactTextShadowNode implements YogaMeasureFunction
{
    private EditText mEditText;
    private int mJsEventCount;
    
    public ReactTextInputShadowNode() {
        this.mJsEventCount = -1;
        if (Build$VERSION.SDK_INT < 23) {}
        this.mTextBreakStrategy = 0;
        this.setMeasureFunction(this);
    }
    
    @Override
    public long measure(final YogaNodeAPI yogaNodeAPI, final float n, final YogaMeasureMode yogaMeasureMode, final float n2, final YogaMeasureMode yogaMeasureMode2) {
        final EditText editText = Assertions.assertNotNull(this.mEditText);
        float n3;
        if (this.mFontSize == -1) {
            n3 = (int)Math.ceil(PixelUtil.toPixelFromSP(14.0f));
        }
        else {
            n3 = this.mFontSize;
        }
        editText.setTextSize(0, n3);
        if (this.mNumberOfLines != -1) {
            editText.setLines(this.mNumberOfLines);
        }
        if (Build$VERSION.SDK_INT >= 23 && editText.getBreakStrategy() != this.mTextBreakStrategy) {
            editText.setBreakStrategy(this.mTextBreakStrategy);
        }
        editText.measure(MeasureUtil.getMeasureSpec(n, yogaMeasureMode), MeasureUtil.getMeasureSpec(n2, yogaMeasureMode2));
        return YogaMeasureOutput.make(editText.getMeasuredWidth(), editText.getMeasuredHeight());
    }
    
    @Override
    public void onBeforeLayout() {
    }
    
    @Override
    public void onCollectExtraUpdates(final UIViewOperationQueue uiViewOperationQueue) {
        super.onCollectExtraUpdates(uiViewOperationQueue);
        if (this.mJsEventCount != -1) {
            uiViewOperationQueue.enqueueUpdateExtraData(this.getReactTag(), new ReactTextUpdate(ReactTextShadowNode.fromTextCSSNode(this), this.mJsEventCount, this.mContainsImages, this.getPadding(0), this.getPadding(1), this.getPadding(2), this.getPadding(3), this.mTextAlign, this.mTextBreakStrategy));
        }
    }
    
    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(final int mJsEventCount) {
        this.mJsEventCount = mJsEventCount;
    }
    
    @Override
    public void setPadding(final int n, final float n2) {
        super.setPadding(n, n2);
        this.markUpdated();
    }
    
    @Override
    public void setTextBreakStrategy(final String s) {
        if (Build$VERSION.SDK_INT < 23) {
            return;
        }
        if (s == null || "simple".equals(s)) {
            this.mTextBreakStrategy = 0;
            return;
        }
        if ("highQuality".equals(s)) {
            this.mTextBreakStrategy = 1;
            return;
        }
        if ("balanced".equals(s)) {
            this.mTextBreakStrategy = 2;
            return;
        }
        throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + s);
    }
    
    @Override
    public void setThemedContext(final ThemedReactContext themedContext) {
        super.setThemedContext(themedContext);
        (this.mEditText = new EditText((Context)this.getThemedContext())).setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
        this.setDefaultPadding(4, this.mEditText.getPaddingStart());
        this.setDefaultPadding(1, this.mEditText.getPaddingTop());
        this.setDefaultPadding(5, this.mEditText.getPaddingEnd());
        this.setDefaultPadding(3, this.mEditText.getPaddingBottom());
        this.mEditText.setPadding(0, 0, 0, 0);
    }
}
