// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.uimanager.annotations.ReactProp;
import android.text.SpannableStringBuilder;

final class RCTRawText extends FlatTextShadowNode
{
    private String mText;
    
    @Override
    protected void performApplySpans(final SpannableStringBuilder spannableStringBuilder, final int n, final int n2, final boolean b) {
        spannableStringBuilder.setSpan((Object)this, n, n2, 17);
    }
    
    @Override
    protected void performCollectText(final SpannableStringBuilder spannableStringBuilder) {
        if (this.mText != null) {
            spannableStringBuilder.append((CharSequence)this.mText);
        }
    }
    
    @ReactProp(name = "text")
    public void setText(final String mText) {
        this.mText = mText;
        this.notifyChanged(true);
    }
}
