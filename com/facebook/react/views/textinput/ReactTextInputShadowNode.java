// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.yoga.YogaDirection;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.react.views.view.MeasureUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.infer.annotation.Assertions;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;
import android.widget.EditText;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.react.views.text.ReactTextShadowNode;

public class ReactTextInputShadowNode extends ReactTextShadowNode implements YogaMeasureFunction
{
    private float[] mComputedPadding;
    private EditText mEditText;
    private int mJsEventCount;
    
    public ReactTextInputShadowNode() {
        this.mJsEventCount = -1;
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
        this.mComputedPadding = new float[] { this.getPadding(4), this.getPadding(1), this.getPadding(5), this.getPadding(3) };
        editText.setPadding((int)Math.floor(this.getPadding(4)), (int)Math.floor(this.getPadding(1)), (int)Math.floor(this.getPadding(5)), (int)Math.floor(this.getPadding(3)));
        if (this.mNumberOfLines != -1) {
            editText.setLines(this.mNumberOfLines);
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
        if (this.mComputedPadding != null) {
            float[] mComputedPadding = this.mComputedPadding;
            if (this.getLayoutDirection() == YogaDirection.RTL) {
                mComputedPadding = new float[] { this.getPadding(5), this.getPadding(1), this.getPadding(4), this.getPadding(3) };
            }
            uiViewOperationQueue.enqueueUpdateExtraData(this.getReactTag(), mComputedPadding);
            this.mComputedPadding = null;
        }
        if (this.mJsEventCount != -1) {
            uiViewOperationQueue.enqueueUpdateExtraData(this.getReactTag(), new ReactTextUpdate(ReactTextShadowNode.fromTextCSSNode(this), this.mJsEventCount, this.mContainsImages, this.getPadding(4), this.getPadding(1), this.getPadding(5), this.getPadding(3), this.mTextAlign));
        }
    }
    
    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(final int mJsEventCount) {
        this.mJsEventCount = mJsEventCount;
    }
    
    @Override
    public void setPadding(final int n, final float n2) {
        super.setPadding(n, n2);
        this.mComputedPadding = new float[] { this.getPadding(4), this.getPadding(1), this.getPadding(5), this.getPadding(3) };
        this.markUpdated();
    }
    
    @Override
    public void setThemedContext(final ThemedReactContext themedContext) {
        super.setThemedContext(themedContext);
        (this.mEditText = new EditText((Context)this.getThemedContext())).setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
        this.setDefaultPadding(4, this.mEditText.getPaddingStart());
        this.setDefaultPadding(1, this.mEditText.getPaddingTop());
        this.setDefaultPadding(5, this.mEditText.getPaddingEnd());
        this.setDefaultPadding(3, this.mEditText.getPaddingBottom());
        this.mComputedPadding = new float[] { this.getPadding(4), this.getPadding(1), this.getPadding(5), this.getPadding(3) };
    }
}
