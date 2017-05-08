// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.annotation.TargetApi;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import android.text.SpannableStringBuilder;
import android.text.Spannable;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.react.views.view.MeasureUtil;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.infer.annotation.Assertions;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNodeAPI;
import com.facebook.react.uimanager.ReactShadowNode;
import android.widget.EditText;
import com.facebook.yoga.YogaMeasureFunction;

public class RCTTextInput extends RCTVirtualText implements YogaMeasureFunction
{
    private EditText mEditText;
    private int mJsEventCount;
    private int mNumberOfLines;
    private boolean mPaddingChanged;
    private String mText;
    
    public RCTTextInput() {
        this.mJsEventCount = -1;
        this.mPaddingChanged = false;
        this.mNumberOfLines = -1;
        this.forceMountToView();
        this.setMeasureFunction(this);
    }
    
    @Override
    boolean isEditable() {
        return true;
    }
    
    @Override
    public boolean isVirtual() {
        return false;
    }
    
    @Override
    public boolean isVirtualAnchor() {
        return true;
    }
    
    @Override
    public long measure(final YogaNodeAPI yogaNodeAPI, final float n, final YogaMeasureMode yogaMeasureMode, final float n2, final YogaMeasureMode yogaMeasureMode2) {
        final EditText editText = Assertions.assertNotNull(this.mEditText);
        final int fontSize = this.getFontSize();
        float n3;
        if (fontSize == -1) {
            n3 = (int)Math.ceil(PixelUtil.toPixelFromSP(14.0f));
        }
        else {
            n3 = fontSize;
        }
        editText.setTextSize(0, n3);
        if (this.mNumberOfLines != -1) {
            editText.setLines(this.mNumberOfLines);
        }
        editText.measure(MeasureUtil.getMeasureSpec(n, yogaMeasureMode), MeasureUtil.getMeasureSpec(n2, yogaMeasureMode2));
        return YogaMeasureOutput.make(editText.getMeasuredWidth(), editText.getMeasuredHeight());
    }
    
    @Override
    protected void notifyChanged(final boolean b) {
        super.notifyChanged(b);
        this.markUpdated();
    }
    
    @Override
    public void onCollectExtraUpdates(final UIViewOperationQueue uiViewOperationQueue) {
        super.onCollectExtraUpdates(uiViewOperationQueue);
        if (this.mJsEventCount != -1) {
            uiViewOperationQueue.enqueueUpdateExtraData(this.getReactTag(), new ReactTextUpdate((Spannable)this.getText(), this.mJsEventCount, false, this.getPadding(4), this.getPadding(1), this.getPadding(5), this.getPadding(3), -1));
        }
    }
    
    @Override
    protected void performCollectText(final SpannableStringBuilder spannableStringBuilder) {
        if (this.mText != null) {
            spannableStringBuilder.append((CharSequence)this.mText);
        }
        super.performCollectText(spannableStringBuilder);
    }
    
    @Override
    public void setBackgroundColor(final int n) {
    }
    
    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(final int mJsEventCount) {
        this.mJsEventCount = mJsEventCount;
    }
    
    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(final int mNumberOfLines) {
        this.mNumberOfLines = mNumberOfLines;
        this.notifyChanged(true);
    }
    
    @Override
    public void setPadding(final int n, final float n2) {
        super.setPadding(n, n2);
        this.mPaddingChanged = true;
        this.dirty();
    }
    
    @ReactProp(name = "text")
    public void setText(final String mText) {
        this.mText = mText;
        this.notifyChanged(true);
    }
    
    @TargetApi(17)
    @Override
    public void setThemedContext(final ThemedReactContext themedContext) {
        super.setThemedContext(themedContext);
        (this.mEditText = new EditText((Context)themedContext)).setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
        this.setDefaultPadding(4, this.mEditText.getPaddingStart());
        this.setDefaultPadding(1, this.mEditText.getPaddingTop());
        this.setDefaultPadding(5, this.mEditText.getPaddingEnd());
        this.setDefaultPadding(3, this.mEditText.getPaddingBottom());
        this.mEditText.setPadding(0, 0, 0, 0);
    }
    
    @Override
    boolean shouldAllowEmptySpans() {
        return true;
    }
}
